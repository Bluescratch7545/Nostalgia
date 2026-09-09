package com.bluescratch.endofitall.entities.customGoals;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EventIfLookedAtGoal extends Goal {
    private final Mob mob;
    private final TriConsumer<BlockPos, ServerLevel, ServerPlayer> function;
    private final float maxAngleDegrees;

    public EventIfLookedAtGoal(Mob mob, TriConsumer<BlockPos, ServerLevel, ServerPlayer> func, float maxAngleDegrees) {
        this.mob = mob;
        this.function = func;
        this.maxAngleDegrees = maxAngleDegrees;
    }

    @Override
    public void tick() {
        var context = TargetingConditions.forNonCombat()
                .range((1000.0))
                .selector(p_25531_ -> EntitySelector.notRiding(mob).test(p_25531_));

        var nearestPlayer = this.mob.level().getNearestPlayer(context, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());

        if (nearestPlayer == null || this.mob.getServer().getLevel(this.mob.level().dimension()) == null) return;


        if (isPlayerLookingAtEntity(nearestPlayer, this.mob, 128.0, maxAngleDegrees)) {
            ServerPlayer nearestSplayer = null;

            for (var player : this.mob.getServer().getLevel(this.mob.level().dimension()).players()) {
                if (player.getUUID() != nearestPlayer.getUUID()) {
                    continue;
                }

                nearestSplayer = player;
            }

            function.accept(this.mob.blockPosition(), this.mob.getServer().getLevel(this.mob.level().dimension()), nearestSplayer);
            mob.goalSelector.removeGoal(this);
        }
    }

    public static boolean isPlayerLookingAtEntity(Player player, Entity target, double maxDistance, double maxAngleDegrees) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 targetPos = target.getEyePosition();

        Vec3 toTarget = targetPos.subtract(eyePos);
        double distance = toTarget.length();

        if (distance > maxDistance) return false;

        Vec3 lookVec = player.getViewVector(1.0F).normalize();
        Vec3 toTargetNormalized = toTarget.normalize();

        double dot = lookVec.dot(toTargetNormalized);
        double angle = Math.toDegrees(Math.acos(dot));

        if (angle > maxAngleDegrees) return false;

        // optional: raytrace to confirm nothing's blocking line of sight
        HitResult hit = player.level().clip(new ClipContext(
                eyePos,
                targetPos,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        return hit.getType() == HitResult.Type.MISS;
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
