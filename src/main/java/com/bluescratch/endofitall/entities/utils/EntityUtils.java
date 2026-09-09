package com.bluescratch.endofitall.entities.utils;

import com.bluescratch.endofitall.Teoia;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityUtils {
    public static ResourceLocation getTexture(String textureName) {
        return ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "textures/entity/" + textureName);
    }

    public static ResourceLocation getGeo(String entityName) {
        return ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "geo/" + entityName + ".geo.json");
    }

    public static ResourceLocation getAnim(String entityName) {
        return ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "animations/" + entityName + ".animation.json");
    }

    public static <T> Class<T> getClassFromString(String className) {
        try {
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) Class.forName(className);
            return clazz;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found: " + className, e);
        }
    }
}
