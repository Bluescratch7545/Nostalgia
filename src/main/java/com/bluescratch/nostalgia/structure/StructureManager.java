package com.bluescratch.nostalgia.structure;

import com.bluescratch.nostalgia.Nostalgia;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class StructureManager {
    public static void placeStructure(ServerLevel level, BlockPos pos) {
        String structureId = StructureList.values()[level.random.nextInt(StructureList.values().length)].getId();

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Nostalgia.MOD_ID, structureId);

        StructureTemplate template = level.getStructureManager().getOrCreate(id);

        template.placeInWorld(level, pos, pos, new StructurePlaceSettings(), level.random, 2);
    }

    public static boolean canSpawnAt(ServerLevel level, BlockPos pos) {
        return level.canSeeSky(pos);
    }

    public static void trySpawnNearPlayer(ServerLevel level, ServerPlayer player) {

        BlockPos pos = getRandomBoxPosition(level, player);

        if (canSpawnAt(level, pos)) {
            placeStructure(level, pos);
        }
    }

    public static BlockPos getRandomBoxPosition(ServerLevel level, ServerPlayer player) {

        int x = player.getBlockX()
                + level.random.nextInt(101) - 50;

        int y = player.getBlockY()
                + level.random.nextInt(101) - 50;

        int z = player.getBlockZ()
                + level.random.nextInt(101) - 50;

        return new BlockPos(x, y, z);
    }
}
