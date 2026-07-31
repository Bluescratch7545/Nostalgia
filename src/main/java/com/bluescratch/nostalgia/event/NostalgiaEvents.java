package com.bluescratch.nostalgia.event;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.structure.StructureManager;
import com.bluescratch.nostalgia.world.NostalgiaProgression;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

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

                    StructureManager.trySpawnNearPlayer(
                            level,
                            player
                    );
                }
            }
        }
    }
}