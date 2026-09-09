package com.bluescratch.endofitall.registries;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.entities.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Teoia.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<StructureSpawner>> STRUCTURE_SPAWNER =
            ENTITIES.register(
                    "structure_spawner",
                    () -> EntityType.Builder.of(
                            StructureSpawner::new,
                            MobCategory.CREATURE
                    )
                            .sized(0.6f, 0.9f)
                            .build("structure_spawner")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<Static>> STATIC =
            ENTITIES.register(
                    "static",
                    () -> EntityType.Builder.of(
                            Static::new,
                            MobCategory.CREATURE
                    )
                            .build("static")
            );
    public static final DeferredHolder<EntityType<?>, EntityType<Silence>> SILENCE =
            ENTITIES.register(
                    "silence",
                    () -> EntityType.Builder.of(
                            Silence::new,
                            MobCategory.CREATURE
                    )
                            .build("silence")
            );

    public static final DeferredHolder<EntityType<?>, EntityType<GuestIntro>> GUEST =
            ENTITIES.register(
                    "guest",
                    () -> EntityType.Builder.of(
                            GuestIntro::new,
                            MobCategory.CREATURE
                    )
                            .build("guest")
            );


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
                STRUCTURE_SPAWNER.get(),
                StructureSpawner.createAttributes().build()
        );
        event.put(
                STATIC.get(),
                Static.createAttributes().build()
        );
        event.put(
                SILENCE.get(),
                Silence.createAttributes().build()
        );
        event.put(
                GUEST.get(),
                GuestIntro.createAttributes().build()
        );
    }
}
