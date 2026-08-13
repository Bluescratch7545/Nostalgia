package com.bluescratch.nostalgia.registries.entity;

import com.bluescratch.nostalgia.registries.entity.customGoals.CorruptSteveDefault;
import com.bluescratch.nostalgia.registries.entity.customGoals.ForceLookAtGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class CorruptSteveChase extends CorruptSteveDefault {
    private final AnimatableInstanceCache cache =
            new SingletonAnimatableInstanceCache(this);

    public CorruptSteveChase(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 0.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 4);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(
                1,
                new MeleeAttackGoal(this, 1, true)
        );
        this.goalSelector.addGoal(
                2,
                new NearestAttackableTargetGoal<>(this, Player.class, false)
        );
        this.goalSelector.addGoal(
                3,
                new ForceLookAtGoal(this)
        );
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null && !this.getTarget().isAlive()) {
            this.discard();
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "controller",
                        0,
                        state -> {
                            state.setAnimation(
                                    RawAnimation.begin().thenLoop("angry")
                            );

                            return PlayState.CONTINUE;
                        }
                )
        );
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
