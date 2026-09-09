package com.bluescratch.endofitall.entities;

import com.bluescratch.endofitall.entities.customGoals.AlwaysTargetPlayerGoal;
import com.bluescratch.endofitall.entities.customGoals.ContinousMeleeAttackGoal;
import com.bluescratch.endofitall.popup.RandomPopupDialogue;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class Static extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache =
            new SingletonAnimatableInstanceCache(this);

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1820.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1820.0);
    }

    public Static(EntityType<? extends Static> type, Level level) {
        super(type, level);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(
                1,
                new ContinousMeleeAttackGoal(this, 1.0)
        );

        this.targetSelector.addGoal(
                1,
                new AlwaysTargetPlayerGoal(this, 800)
        );

        this.goalSelector.addGoal(
                2,
                new LookAtPlayerGoal(this, Player.class, 100)
        );
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level() instanceof ServerLevel server) {
            for (ServerPlayer player : server.players()) {
                player.setGameMode(GameType.SURVIVAL);
            }
        }

        if (this.getTarget() == null) return;

        if (!this.getTarget().isAlive()) {
            this.discard();
        }
    }

    public static boolean checkSpawnRules(EntityType<Static> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return Mob.checkMobSpawnRules(type, level, spawnType, pos, random);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "movement",
                        5,
                        this::movement

                )
        );
    }

    private PlayState movement(AnimationState<Static> state) {
        if (this.getDeltaMovement().horizontalDistanceSqr() > 0.0001) {
            state.setAnimation(
                    RawAnimation.begin().thenLoop("walk")
            );

            return PlayState.CONTINUE;
        }

        state.setAnimation(
                RawAnimation.begin().thenLoop("idle")
        );

        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
