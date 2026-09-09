package com.bluescratch.endofitall.entities;

import com.bluescratch.endofitall.entities.customGoals.DisposeEntity;
import com.bluescratch.endofitall.structure.StructureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public class StructureSpawner extends PathfinderMob implements GeoEntity  {
    private final AnimatableInstanceCache cache =
            new SingletonAnimatableInstanceCache(this);

    public StructureSpawner(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(
                1,
                new StructureSpawnGoal(this)
        );
        this.goalSelector.addGoal(
                2,
                new DisposeEntity(this)
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private static class StructureSpawnGoal extends Goal {

        private final Mob mob;

        public StructureSpawnGoal(Mob mob) {
            this.mob = mob;
        }


        @Override
        public void start() {
            StructureManager.placeStructure(mob.getServer().getLevel(mob.level().dimension()), mob.blockPosition());
        }

        @Override
        public boolean canUse() {
            return true;
        }
    }

    public static boolean checkSpawnRules(EntityType<StructureSpawner> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return Mob.checkMobSpawnRules(type, level, spawnType, pos, random);
    }
}
