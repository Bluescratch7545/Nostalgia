package com.bluescratch.nostalgia.registries.entity;

import com.bluescratch.nostalgia.registries.ModEntities;
import com.bluescratch.nostalgia.registries.entity.customGoals.CorruptSteveDefault;
import com.bluescratch.nostalgia.registries.entity.customGoals.SpawnEntityOrPopupWhenNearbyPlayerGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class CorruptSteveStalk extends CorruptSteveDefault {
    private final AnimatableInstanceCache cache =
            new SingletonAnimatableInstanceCache(this);

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 0.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    public CorruptSteveStalk(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(
                1,
                new SpawnEntityOrPopupWhenNearbyPlayerGoal(this, ModEntities.CORRUPT_STEVE_CHASE.get())
        );
        this.goalSelector.addGoal(
                2,
                new LookAtPlayerGoal(this, Player.class, 20.0f)
        );
        this.goalSelector.addGoal(
                3,
                new RandomStrollGoal(this, 1)
        );
        this.goalSelector.addGoal(
                4,
                new RandomLookAroundGoal(this)
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "movement",
                        3,
                        this::walkHandler
                )
        );
    }

    private PlayState walkHandler(AnimationState<CorruptSteveStalk> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(
                    RawAnimation.begin().thenLoop("move")
            );

            return PlayState.CONTINUE;
        }

        state.getController().setAnimation(
                RawAnimation.begin().thenLoop("idle")
        );

        return PlayState.CONTINUE;
    }

    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
        return !source.is(DamageTypes.GENERIC_KILL);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
