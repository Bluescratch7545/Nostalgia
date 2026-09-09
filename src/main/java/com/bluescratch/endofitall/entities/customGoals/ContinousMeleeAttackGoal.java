package com.bluescratch.endofitall.entities.customGoals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;

public class ContinousMeleeAttackGoal extends Goal {
    private final Mob mob;
    private final double speedModifier;

    private int pathRecalculationCooldown;
    private int attackCooldown;

    public ContinousMeleeAttackGoal(Mob mob, double speedModifier) {
        this.mob = mob;
        this.speedModifier = speedModifier;

        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public void start() {
        pathRecalculationCooldown = 0;
        attackCooldown = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = mob.getTarget();

        if (target == null || !target.isAlive()) return;

        mob.getLookControl().setLookAt(target);

        if (pathRecalculationCooldown <= 0) {
            pathRecalculationCooldown = 10;

            var path = mob.getNavigation().createPath(
                    target,
                    0
            );

            if (path != null) {
                mob.getNavigation().moveTo(
                        path,
                        speedModifier
                );
            }
            else {
                mob.getMoveControl().setWantedPosition(
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        speedModifier
                );
            }
        }
        else {
            pathRecalculationCooldown--;
        }

        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (attackCooldown <= 0 && canAttack(target)) {
            mob.doHurtTarget(target);
            attackCooldown = 10;
        }
    }

    private boolean canAttack(LivingEntity target) {
        AABB attackBox = mob.getBoundingBox().inflate(
                mob.getBoundingBox().getXsize(),
                mob.getBoundingBox().getYsize() * 0.2,
                mob.getBoundingBox().getZsize()
        );

        return attackBox.intersects(
                target.getBoundingBox()
        );
    }

    @Override
    public void stop() {
        mob.getNavigation().stop();
    }

    @Override
    public boolean canUse() {
        return mob.getTarget() != null
                && mob.getTarget().isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = mob.getTarget();

        return target != null
                && target.isAlive();
    }
}
