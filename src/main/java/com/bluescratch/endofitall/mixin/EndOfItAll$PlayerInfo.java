package com.bluescratch.endofitall.mixin;

import com.bluescratch.endofitall.Teoia;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;

import java.util.function.Supplier;

@Mixin(PlayerInfo.class)
public class EndOfItAll$PlayerInfo {
    @Inject(method = "createSkinLookup", at = @At("HEAD"), cancellable = true)
    private static void teoia$getGuestSkin(GameProfile profile, CallbackInfoReturnable<Supplier<PlayerSkin>> cir) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server == null) return;

        TeoiaSavedData data = TeoiaSavedData.get(server.overworld());

        if (data.guest == null) return;

        if (data.guest != null && profile.getId().equals(data.guest.getGameProfile().getId())) {
            PlayerSkin guestSkin = new PlayerSkin(
                    ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "textures/entity/steve_default.png"),
                    null,
                    null,
                    null,
                    PlayerSkin.Model.WIDE,
                    true
            );

            cir.setReturnValue(() -> guestSkin);
        }
    }
}
