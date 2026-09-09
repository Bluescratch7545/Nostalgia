package com.bluescratch.endofitall.event;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.fakeplayer.Guest;
import com.bluescratch.endofitall.meta.event.packet.PopupShowPacket;
import com.bluescratch.endofitall.network.TeoiaNetwork;
import com.bluescratch.endofitall.popup.PopupBaseValues;
import com.bluescratch.endofitall.popup.RandomPopupDialogue;
import com.bluescratch.endofitall.world.TeoiaProgression;

import com.bluescratch.endofitall.world.saved.TeoiaSavedData;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.List;
import java.util.Objects;

import static com.bluescratch.endofitall.world.saved.TeoiaSavedData.currentGuest;


public class TeoiaEvents {

    private static long tickCounter = 0;
    private static boolean enteredWorld = false;
    private static boolean sentInfoPacket = false;

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        ServerLevel level = event.getServer().overworld();

        TeoiaSavedData data = TeoiaSavedData.get(level);

        if (data.guest != null && data.guest.isAlive()) {
            data.saveGuest(data.guest);
        }

        enteredWorld = false;
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {

        tickCounter++;


        if (tickCounter % 200 != 0) {
            return;
        }

        for (ServerLevel level : event.getServer().getAllLevels()) {

            for (ServerPlayer ignored : level.players()) {


                long days = level.getDayTime() / 24000;

                float chance = TeoiaProgression.getEventChance(days);

                if (level.random.nextFloat() < chance) {
                    if (Teoia.CONFIG.danger.noPopup.get()) {
                        var dialogue = RandomPopupDialogue.values()[level.random.nextInt(RandomPopupDialogue.values().length)];

                        CHAT(level, dialogue.getText(), dialogue.getColor());
                        return;
                    }

                    var rnd = EventType.values()[level.random.nextInt(EventType.values().length)].getId();

                    if (Objects.equals(rnd, EventType.POPUP.getId())) {
                        POPUP_SEND(level, RandomPopupDialogue.values()[level.random.nextInt(RandomPopupDialogue.values().length)].getText());
                    } else if (Objects.equals(rnd, EventType.CHAT.getId())) {
                        var dialogue = RandomPopupDialogue.values()[level.random.nextInt(RandomPopupDialogue.values().length)];

                        CHAT(level, dialogue.getText(), dialogue.getColor());
                    }
                }
            }
        }

        if (enteredWorld)
            return;

        ServerLevel level = event.getServer().overworld();
        TeoiaSavedData data = TeoiaSavedData.get(level);

        if (data.guestUUID == null || data.guestData == null)
            return;

        // Create Guest ONCE
        if (data.guest == null) {
            data.guest = new Guest(level, data.guestUUID);
            currentGuest = data.guest;

            data.guest.load(data.guestData);

            data.guest.moveTo(
                    data.guestPOSX,
                    data.guestPOSY,
                    data.guestPOSZ,
                    data.angleY,
                    data.angleX
            );

            // Tell clients that this PLAYER exists
            ClientboundPlayerInfoUpdatePacket infoPacket =
                    ClientboundPlayerInfoUpdatePacket.createPlayerInitializing(
                            List.of(data.guest)
                    );

            for (ServerPlayer viewer : level.players()) {
                viewer.connection.send(infoPacket);
            }

            // Actually add it to the world
            level.addNewPlayer(data.guest);

            enteredWorld = true;

            Teoia.LOGGER.info("guest a'ed");
        }
    }

    static void POPUP_SEND(ServerLevel level, String popupText) {
        for(var player : level.players()) {
            TeoiaNetwork.sendToPlayer(
                    player,
                    new PopupShowPacket(
                            true,
                            PopupBaseValues.popupVisibleName,
                            popupText
                    )
            );
        }
    }

    static void CHAT(ServerLevel level, String text, String color) {
        var fullText = color + text;

        for (var player : level.players()) {
            player.sendSystemMessage(Component.literal(fullText));
        }
    }

    public static boolean POPUP(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        if (Teoia.CONFIG.danger.noPopup.get()) {
            return false;
        }

        var player = EntityArgument.getPlayer(ctx, "player");

        for (var level : player.getServer().getAllLevels()) {
            POPUP_SEND(level, RandomPopupDialogue.values()[level.random.nextInt(RandomPopupDialogue.values().length)].getText());
        }

        return true;
    }

    public static int CHAT_CMD(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        var player = EntityArgument.getPlayer(ctx, "player");

        for (var level : player.getServer().getAllLevels()) {
            var dialogue = RandomPopupDialogue.values()[level.random.nextInt(RandomPopupDialogue.values().length)];

            CHAT(level, dialogue.getText(), dialogue.getColor());
        }

        return 1;
    }
}