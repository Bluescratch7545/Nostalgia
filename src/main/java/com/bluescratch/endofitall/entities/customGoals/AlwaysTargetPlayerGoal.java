package com.bluescratch.endofitall.entities.customGoals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class AlwaysTargetPlayerGoal extends Goal {
    private final Mob mob;
    private final double range;

    public AlwaysTargetPlayerGoal(Mob mob, double range) {
        this.mob = mob;
        this.range = range;

        this.setFlags(EnumSet.of(Flag.TARGET));
    }



    @Override
    public boolean canUse() {
        Player player = mob.level().getNearestPlayer(mob, range);

        if (player != null && player.isAlive()) {
            mob.setTarget(player);
            return true;
        }

        return false;
    }
    @Override
    public boolean canContinueToUse() {
        Player player = mob.level().getNearestPlayer(mob, range);

        if (player != null && player.isAlive()) {
            mob.setTarget(player);
            return true;
        }

        mob.setTarget(null);
        return false;
    }
}
