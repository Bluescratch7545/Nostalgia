package com.bluescratch.nostalgia.registries.entity.customGoals;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class CorruptSteveDefault extends PathfinderMob implements GeoEntity {
    protected CorruptSteveDefault(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }


}
