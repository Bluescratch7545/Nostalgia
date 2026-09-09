package com.bluescratch.endofitall.entities.customGoals;

import com.bluescratch.endofitall.meta.event.packet.PopupShowPacket;
import com.bluescratch.endofitall.network.TeoiaNetwork;
import com.bluescratch.endofitall.popup.PopupBaseValues;
import com.bluescratch.endofitall.popup.RandomPopupDialogue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class  SpawnEntityOrPopupWhenNearbyPlayerGoal extends Goal {

    private final Mob mob;
    private final EntityType<? extends Mob> toSummon;

    public SpawnEntityOrPopupWhenNearbyPlayerGoal(Mob mob, EntityType<? extends Mob> toSummon) {
        this.mob = mob;
        this.toSummon = toSummon;
    }



    @Override
    public void tick() {
        var context = TargetingConditions.forNonCombat()
                        .range((1000.0))
                        .selector(p_25531_ -> EntitySelector.notRiding(mob).test(p_25531_));

        var player = this.mob.level().getNearestPlayer(context, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());

        if (player != null && mob.distanceToSqr(player) <= 10 * 10) {
            var chance = mob.getRandom().nextFloat();

            if (chance <= 0.5f) {
                toSummon.spawn(
                        (ServerLevel) mob.level(),
                        mob.blockPosition(),
                        MobSpawnType.MOB_SUMMONED
                );
                mob.discard();
            }
            else if (chance > 0.5f) {
                var levels = mob.getServer().getAllLevels();

                for (var level : levels) {
                    for (var serverPlayer : level.players()) {
                        if (serverPlayer.getUUID() != player.getUUID()) continue;

                        TeoiaNetwork.sendToPlayer(
                                serverPlayer,
                                new PopupShowPacket(false, PopupBaseValues.popupVisibleName, RandomPopupDialogue.values()[mob.level().random.nextInt(RandomPopupDialogue.values().length)].getText())
                        );
                    }
                }
                mob.discard();
            }
        }
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }
}
