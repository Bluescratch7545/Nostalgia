package com.bluescratch.endofitall.world;

import net.minecraft.server.level.ServerLevel;

public class TeoiaWorldManager {

    private final ServerLevel level;

    public TeoiaWorldManager(ServerLevel level) {
        this.level = level;
    }

    public long getDays() {
        return level.getDayTime() / 24000L;
    }
}
