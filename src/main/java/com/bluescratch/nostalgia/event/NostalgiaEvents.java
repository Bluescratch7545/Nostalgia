package com.bluescratch.nostalgia.event;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.meta.event.packet.PopupShowPacket;
import com.bluescratch.nostalgia.network.NostalgiaNetwork;
import com.bluescratch.nostalgia.popup.PopupBaseValues;
import com.bluescratch.nostalgia.popup.RandomPopupDialogue;
import com.bluescratch.nostalgia.registries.ModEntities;
import com.bluescratch.nostalgia.structure.StructureManager;
import com.bluescratch.nostalgia.world.NostalgiaProgression;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import static com.bluescratch.nostalgia.event.EventType.*;

public class NostalgiaEvents {

    private static long tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {

        tickCounter++;

        // Try every 10 seconds
        if (tickCounter % 200 != 0) {
            return;
        }

        for (ServerLevel level : event.getServer().getAllLevels()) {

            for (ServerPlayer player : level.players()) {



                long days = level.getDayTime() / 24000;

                float chance = NostalgiaProgression.getStructureChance(days);

                Nostalgia.LOGGER.info(
                        "Days: {}, Chance: {}",
                        days,
                        chance
                );

                if (level.random.nextFloat() < chance) {
                    String eventTypeId = values()[level.random.nextInt(values().length)].getId();
                    //String eventTypeId = ENTITY.getId();

                    if (eventTypeId.equals(STRUCTURE.getId())) {
                        StructureManager.trySpawnNearPlayer(level, player);
                    } else if (eventTypeId.equals(POPUP.getId())) {
                        POPUP_SEND(level, RandomPopupDialogue.values()[level.random.nextInt(RandomPopupDialogue.values().length)].getText());
                    } else if (eventTypeId.equals(ENTITY.getId())) {
                        SPAWN_ENTITY(level, player);
                    }
                }
            }
        }
    }

    static void POPUP_SEND(ServerLevel level, String popupText) {
        for(var player : level.players()) {
            NostalgiaNetwork.sendToPlayer(
                    player,
                    new PopupShowPacket(
                            true,
                            PopupBaseValues.popupVisibleName,
                            popupText
                    )
            );
        }
    }

    static void SPAWN_ENTITY(ServerLevel level, ServerPlayer player) {
        var pos = StructureManager.getRandomBoxPosition(level, player);

        if (!StructureManager.canSpawnAt(level, pos)) return;

        ModEntities.CORRUPT_STEVE_STALK.get().spawn(
                level,
                pos,
                MobSpawnType.MOB_SUMMONED
        );
    }
}