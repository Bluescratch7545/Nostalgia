package com.bluescratch.nostalgia.registries.entity.customGoals;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

public class ForceLookAtGoal extends Goal {

    private final Mob mob;

    public ForceLookAtGoal(Mob mob) {
        this.mob = mob;
    }

    @Override
    public void tick() {
        var context = TargetingConditions.forNonCombat()
                .range((1000.0))
                .selector(p_25531_ -> EntitySelector.notRiding(mob).test(p_25531_));

        var player = this.mob.level().getNearestPlayer(context, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());

        if (player == null || !player.isAlive() || player.isRemoved()) return;

        player.lookAt(EntityAnchorArgument.Anchor.EYES, mob.position());
    }

    @Override
    public boolean canUse() {
        return true;
    }
    @Override
    public boolean canContinueToUse() {
        return true;
    }
}
