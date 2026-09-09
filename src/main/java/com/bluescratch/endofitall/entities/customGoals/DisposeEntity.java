package com.bluescratch.endofitall.entities.customGoals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class DisposeEntity extends Goal {

    private final Mob mainEntity;

    public DisposeEntity(Mob mainEntity) {
        this.mainEntity = mainEntity;
    }

    @Override
    public void start() {
        mainEntity.discard();
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

}

