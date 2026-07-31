package com.bluescratch.nostalgia.world;

import net.minecraft.server.level.ServerLevel;

public class NostalgiaWorldManager {

    private final ServerLevel level;

    public NostalgiaWorldManager(ServerLevel level) {
        this.level = level;
    }

    public long getDays() {
        return level.getDayTime() / 24000L;
    }
}
