package com.bluescratch.endofitall.entities.customGoals;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class ChatText extends Goal {
    private final String text;
    private final Mob mob;

    public ChatText(Mob mob, String text) {
        this.mob = mob;
        this.text = text;
    }

    @Override
    public void tick() {
        for (var level : this.mob.getServer().getAllLevels()) {
            for (var player : level.players()) {
                player.sendSystemMessage(Component.literal(text));
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
