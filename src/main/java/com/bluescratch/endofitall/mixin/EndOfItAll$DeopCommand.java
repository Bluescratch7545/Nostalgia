package com.bluescratch.endofitall.mixin;

import com.bluescratch.endofitall.registries.lang.CommandLang;
import com.mojang.authlib.GameProfile;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.DeOpCommands;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

import com.bluescratch.endofitall.world.saved.TeoiaSavedData;

@Mixin(DeOpCommands.class)
public class EndOfItAll$DeopCommand {
    @Inject(method = "deopPlayers", at = @At("HEAD"), cancellable = true)
    private static void teoia$deopGuest(CommandSourceStack source, Collection<GameProfile> players, CallbackInfoReturnable<Integer> cir) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server == null) return;

        TeoiaSavedData data = TeoiaSavedData.get(server.overworld());

        if (data.guest == null) return;

        for (GameProfile player : players) {
            if (player.getId().equals(data.guest.getGameProfile().getId())) {
                source.sendFailure(CommandLang.DEOP_ERROR);
                cir.cancel();
            }
        }
    }
}
