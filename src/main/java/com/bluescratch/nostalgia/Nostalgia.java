package com.bluescratch.nostalgia;

import com.bluescratch.nostalgia.commands.ModCommands;
import com.bluescratch.nostalgia.event.ClientEventHandler;
import com.bluescratch.nostalgia.event.NostalgiaEvents;
import com.bluescratch.nostalgia.network.ModNetworking;
import com.bluescratch.nostalgia.registries.ModEntities;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Nostalgia.MOD_ID)
public class Nostalgia {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "nostlg";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Nostalgia(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(NostalgiaEvents.class);
        NeoForge.EVENT_BUS.register(ModCommands.class);

        modEventBus.addListener(ClientEventHandler::registerRenderers);
        modEventBus.addListener(ModEntities::registerAttributes);
        modEventBus.addListener(ModNetworking::register);

        ModEntities.ENTITIES.register(modEventBus);


        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
}

