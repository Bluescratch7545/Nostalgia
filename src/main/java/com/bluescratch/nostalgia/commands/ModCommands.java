package com.bluescratch.nostalgia.commands;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.meta.event.packet.PopupShowPacket;
import com.bluescratch.nostalgia.network.NostalgiaNetwork;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

@EventBusSubscriber
public class ModCommands {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                literal(Nostalgia.MOD_ID)
                        .then(DevCommands.register())
        );
    }

    protected static int popup(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        for(var player : EntityArgument.getPlayers(ctx, "players")) {
            NostalgiaNetwork.sendToPlayer(
                    player,
                    new PopupShowPacket(BoolArgumentType.getBool(ctx, "minimize"), StringArgumentType.getString(ctx, "title"), StringArgumentType.getString(ctx, "text"))
            );
        }

        return 1;
    }
}
