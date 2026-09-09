package com.bluescratch.endofitall.mixin;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.registries.lang.CommandLang;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.KickCommand;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;

import java.util.Collection;

@Mixin(KickCommand.class)
public class EndOfItAll$KickCommand {

    @Inject(method = "kickPlayers", at = @At("HEAD"))
    private static void teoia$kickGuest(CommandSourceStack source, Collection<ServerPlayer> players, Component reason, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        for (ServerPlayer player : players) {
            TeoiaSavedData data = TeoiaSavedData.get(player.getServer().getLevel(player.level().dimension()));

            if (data.guest != null &&  player.getUUID() == data.guest.getUUID()) {
                source.sendFailure(CommandLang.KICK_ERROR);
                cir.cancel();
            }
        }
    }
}
