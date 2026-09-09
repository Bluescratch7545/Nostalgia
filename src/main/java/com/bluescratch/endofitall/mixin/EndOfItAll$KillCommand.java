package com.bluescratch.endofitall.mixin;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.registries.lang.CommandLang;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.KillCommand;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;

import java.util.Collection;

@Mixin(KillCommand.class)
public class EndOfItAll$KillCommand {
    @Inject(method = "kill", at = @At("HEAD"), cancellable = true)
    private static void teoia$killGuest(CommandSourceStack source, Collection<? extends Entity> targets, CallbackInfoReturnable<Integer> cir) {
        for (Entity target : targets) {
            TeoiaSavedData data = TeoiaSavedData.get(target.getServer().getLevel(target.level().dimension()));

            if (data.guest != null && target.getUUID() == data.guest.getUUID()) {
                source.sendFailure(CommandLang.KILL_ERROR);
                cir.cancel();
            }
        }
    }
}
