package com.bluescratch.nostalgia.registries.entity.customGoals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;

public class SpawnEntityRandomGoal extends Goal {

    private final Mob mainEntity;
    private final EntityType<? extends Mob> entity1;
    private final EntityType<? extends Mob> entity2;
    private final float entity2chance;

    public SpawnEntityRandomGoal(Mob mainEntity, EntityType<? extends Mob> entity1, EntityType<? extends Mob> entity2, float entity2chance) {
        this.mainEntity = mainEntity;
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.entity2chance = entity2chance;
    }

    @Override
    public void start() {
        if (!(mainEntity.level() instanceof ServerLevel level))
            return;

        float chance = mainEntity.getRandom().nextFloat();
        EntityType<? extends Mob> selectedEntity;

        if (chance < entity2chance) {
            selectedEntity = entity2;
        } else if (chance > entity2chance) {
            selectedEntity = entity1;
        }
        else {
            return;
        }

        selectedEntity.spawn(
                level,
                mainEntity.blockPosition().above(),
                MobSpawnType.MOB_SUMMONED
        );
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

}

