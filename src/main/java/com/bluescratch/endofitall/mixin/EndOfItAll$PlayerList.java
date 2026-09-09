package com.bluescratch.endofitall.mixin;

import com.bluescratch.endofitall.Teoia;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;

@Mixin(PlayerList.class)
public class EndOfItAll$PlayerList {
    @Inject(method = "getPlayerByName", at = @At("HEAD"), cancellable = true)
    private void teoia$getGuest(String name, CallbackInfoReturnable<ServerPlayer> cir) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server == null) return;

        TeoiaSavedData data = TeoiaSavedData.get(server.overworld());

        if (data.guest == null) return;

        if (data.guest != null && data.guest.getGameProfile().getName().equalsIgnoreCase(name)) {
            cir.setReturnValue(data.guest);
        }
    }
}
