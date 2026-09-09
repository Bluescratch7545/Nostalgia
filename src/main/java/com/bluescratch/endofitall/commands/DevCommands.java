package com.bluescratch.endofitall.commands;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.fakeplayer.Guest;
import com.bluescratch.endofitall.event.TeoiaEvents;
import com.bluescratch.endofitall.world.TeoiaWorldManager;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.bluescratch.endofitall.world.saved.TeoiaSavedData.*;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class DevCommands {
    private static final Logger log = LoggerFactory.getLogger(DevCommands.class);

    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return literal("dev")
                    .then(literal("popup")
                        .then(literal("show")
                                .then(argument("players", EntityArgument.players())
                                        .then(argument("title", StringArgumentType.string())
                                                .then(argument("text", StringArgumentType.string())
                                                        .then(argument("minimize", BoolArgumentType.bool())
                                                                .executes(ModCommands::popup)
                                                        )
                                                )
                                        )
                                )
                        )

                    )
                    .then(literal("event")
                            .then(argument("player", EntityArgument.player())
                                    .then(literal("popup")
                                            .executes(ctx -> {
                                                boolean success = TeoiaEvents.POPUP(ctx);

                                                if (!success) {
                                                    ctx.getSource().sendFailure(Component.literal("Failed to create popup! Probable causes are: \n You removed the ability for the mod to create popups in the config. \n Other failure happened, so please report it to the creator of the mod!"));
                                                    return 0;
                                                }

                                                return 1;
                                            })
                                    )
                                    .then(literal("chat")
                                            .executes(TeoiaEvents::CHAT_CMD))
                            )
                    )
                    .then(argument("player", EntityArgument.player())
                            .then(literal("fakeplayer")
                                    .executes(ctx -> {
                                        ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
                                        ServerLevel level = player.getServer().getLevel(player.level().dimension());
                                        TeoiaSavedData data = TeoiaSavedData.get(level);

                                        data.guest = new Guest(level, UUID.randomUUID());

                                        data.guestUUID = data.guest.getUUID();
                                        data.guestPOSX = data.guest.blockPosition().getX();
                                        data.guestPOSY = data.guest.blockPosition().getY();
                                        data.guestPOSZ = data.guest.blockPosition().getZ();
                                        data.angleX = (float) data.guest.getLookAngle().x;
                                        data.angleY = (float) data.guest.getLookAngle().y;

                                        data.saveGuest(data.guest);

                                        data.guest.moveTo(
                                                player.getX(),
                                                player.getY(),
                                                player.getZ(),
                                                player.getYRot(),
                                                player.getXRot()
                                        );

                                        ClientboundPlayerInfoUpdatePacket infoPacket =
                                                new ClientboundPlayerInfoUpdatePacket(
                                                        ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER,
                                                        data.guest
                                                );

                                        for (ServerPlayer viewer : level.players()) {
                                            viewer.connection.send(infoPacket);
                                        }

                                        level.addNewPlayer(data.guest);

                                        return 1;
                                    })));

    }


}
