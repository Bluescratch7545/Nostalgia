package com.bluescratch.endofitall;

import com.bluescratch.endofitall.commands.ModCommands;
import com.bluescratch.endofitall.entities.Silence;
import com.bluescratch.endofitall.entities.Static;
import com.bluescratch.endofitall.entities.StructureSpawner;
import com.bluescratch.endofitall.entities.fakeplayer.Guest;
import com.bluescratch.endofitall.event.ClientEventHandler;
import com.bluescratch.endofitall.event.TeoiaEvents;
import com.bluescratch.endofitall.network.ModNetworking;
import com.bluescratch.endofitall.registries.ModEntities;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.bluescratch.endofitall.registries.ModEntities.*;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Teoia.MOD_ID)
public class Teoia {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "teoia";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final TeoiaCommonConfig CONFIG =
            ConfigApiJava.registerAndLoadConfig(TeoiaCommonConfig::new);

    public static final TeoiaClientConfig CLIENT_CONFIG = ConfigApiJava.registerAndLoadConfig(TeoiaClientConfig::new, RegisterType.CLIENT);


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Teoia(IEventBus modEventBus, ModContainer modContainer) throws IOException {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(TeoiaEvents.class);
        NeoForge.EVENT_BUS.register(ModCommands.class);

        modEventBus.addListener(ClientEventHandler::registerRenderers);
        modEventBus.addListener(ModEntities::registerAttributes);
        modEventBus.addListener(ModNetworking::register);
        modEventBus.addListener(this::registerSpawnPlacements);

        ENTITIES.register(modEventBus);

    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                STRUCTURE_SPAWNER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                StructureSpawner::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.AND
        );
        event.register(
                STATIC.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Static::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.AND
        );
        event.register(
                SILENCE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Silence::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.AND
        );
    }
}

