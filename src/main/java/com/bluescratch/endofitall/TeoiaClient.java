package com.bluescratch.endofitall;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Teoia.MOD_ID, dist = Dist.CLIENT)
public class TeoiaClient {

    public TeoiaClient(ModContainer container) {
    }

    @SubscribeEvent
    public void onClientRegister() {

    }
}