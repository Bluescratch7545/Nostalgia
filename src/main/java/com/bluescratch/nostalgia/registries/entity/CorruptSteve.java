package com.bluescratch.nostalgia.registries.entity;

import com.bluescratch.nostalgia.registries.ModEntities;
import com.bluescratch.nostalgia.registries.entity.customGoals.DisposeEntity;
import com.bluescratch.nostalgia.registries.entity.customGoals.SpawnEntityRandomGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import com.bluescratch.nostalgia.registries.entity.customGoals.CorruptSteveDefault;

public class CorruptSteve extends CorruptSteveDefault {
    private final AnimatableInstanceCache cache =
            new SingletonAnimatableInstanceCache(this);

    public CorruptSteve(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 0.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0);

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(
                1,
                new SpawnEntityRandomGoal(this, ModEntities.CORRUPT_STEVE_STALK.get(), ModEntities.CORRUPT_STEVE_CHASE.get(), 0.20f)
        );
        this.goalSelector.addGoal(
                2,
                new DisposeEntity(this)
        );
    }

    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
        return !source.is(DamageTypes.GENERIC_KILL);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
