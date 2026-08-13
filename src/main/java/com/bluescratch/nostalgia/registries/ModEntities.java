package com.bluescratch.nostalgia.registries;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.registries.entity.CorruptSteve;
import com.bluescratch.nostalgia.registries.entity.CorruptSteveChase;
import com.bluescratch.nostalgia.registries.entity.CorruptSteveStalk;
import com.bluescratch.nostalgia.registries.entity.TestEntityTwo;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Nostalgia.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<TestEntityTwo>> TEST_ENTITY_TWO =
            ENTITIES.register(
                    "test_entity_two",
                    () -> EntityType.Builder.of(
                            TestEntityTwo::new,
                            MobCategory.CREATURE
                    )
                            .sized(1.3f, 1.0f)
                            .build("test_entity_two")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<CorruptSteve>> CORRUPT_STEVE =
            ENTITIES.register(
                    "corrupt_steve",
                    () -> EntityType.Builder.of(
                            CorruptSteve::new,
                            MobCategory.CREATURE
                    )
                            .build("corrupt_steve")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<CorruptSteveStalk>> CORRUPT_STEVE_STALK =
            ENTITIES.register(
                    "corrupt_steve_stalk",
                    () -> EntityType.Builder.of(
                            CorruptSteveStalk::new,
                            MobCategory.CREATURE
                    )
                            .build("corrupt_steve_stalk")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<CorruptSteveChase>> CORRUPT_STEVE_CHASE =
            ENTITIES.register(
                    "corrupt_steve_chase",
                    () -> EntityType.Builder.of(
                            CorruptSteveChase::new,
                            MobCategory.CREATURE
                    )
                            .build("corrupt_steve_chase")
            );

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
                TEST_ENTITY_TWO.get(),
                TestEntityTwo.createAttributes().build()
        );
        event.put(
                CORRUPT_STEVE.get(),
                CorruptSteve.createAttributes().build()
        );
        event.put(
                CORRUPT_STEVE_STALK.get(),
                CorruptSteveStalk.createAttributes().build()
        );
        event.put(
                CORRUPT_STEVE_CHASE.get(),
                CorruptSteveChase.createAttributes().build()
        );
    }
}
