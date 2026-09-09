package com.bluescratch.endofitall.mixin;

import com.bluescratch.endofitall.registries.lang.CommandLang;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.server.commands.GiveCommand;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(GiveCommand.class)
public class EndOfItAll$GiveCommand {
    @Inject(method = "giveItem", at = @At("HEAD"), cancellable = true)
    private static void teoia$giveGuest(CommandSourceStack source, ItemInput item, Collection<ServerPlayer> targets, int count, CallbackInfoReturnable<Integer> cir) {

        for (ServerPlayer target : targets) {
            TeoiaSavedData data = TeoiaSavedData.get(target.getServer().getLevel(target.level().dimension()));

            if (data.guest != null && target.getUUID() == data.guest.getUUID()) {
                source.sendFailure(CommandLang.GIVE_ERROR);
                cir.cancel();
            }
        }
    }
}
