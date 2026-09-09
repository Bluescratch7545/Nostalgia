package com.bluescratch.endofitall.entities;

import com.bluescratch.endofitall.Teoia;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class GuestIntro extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache =
            new SingletonAnimatableInstanceCache(this);

    private boolean introDone;
    private int tickInstance;

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.MAX_HEALTH,3640.0);
    }

    public GuestIntro(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    public void registerGoals() {

    }

    @Override
    public void tick() {
        super.tick();

        tickInstance++;

        if (this.level().isClientSide) return;

        if (tickInstance / 20 >= 3) {
            EntityType.COD.spawn(
                    (ServerLevel) this.level(),
                    new BlockPos(this.blockPosition().getX(), this.blockPosition().getY() + 4, this.blockPosition().getZ()),
                    MobSpawnType.MOB_SUMMONED
            );

            this.discard();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "main",
                        0,
                        this::mainCtrl
                )
        );
    }

    private PlayState mainCtrl(AnimationState<GuestIntro> state) {
        state.setAnimation(
                RawAnimation.begin().thenPlay("bossStartIntro")
        );

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
