package com.bluescratch.nostalgia.registries;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.registries.entity.TestEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Nostalgia.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<TestEntity>> TEST_ENTITY =
            ENTITIES.register(
                    "test_entity",
                    () -> EntityType.Builder.of(TestEntity::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build("test_entity"));
}
