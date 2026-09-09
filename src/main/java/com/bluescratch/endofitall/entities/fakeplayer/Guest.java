package com.bluescratch.endofitall.entities.fakeplayer;

import com.bluescratch.endofitall.Teoia;
import com.bluescratch.endofitall.world.saved.TeoiaSavedData;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.FakePlayer;

import java.lang.reflect.Field;
import java.util.UUID;

import static com.bluescratch.endofitall.world.saved.TeoiaSavedData.currentGuest;

public class Guest extends FakePlayer {

    public Guest(ServerLevel level, UUID uuid) {
        super(level, new GameProfile(uuid, "Guest"));
        this.setInvulnerable(false);
        this.clearSpawnInvulnerableTime();
    }

    private void clearSpawnInvulnerableTime() {
        try {
            Field field = ServerPlayer.class.getDeclaredField("spawnInvulnerableTime");
            field.setAccessible(true);
            field.setInt(this, 0);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to clear spawnInvulnerableTime on Guest", e);
        }
    }

    @Override
    public void tick() {
        if (this.invulnerableTime > 0) {
            this.invulnerableTime--;
        }
        this.doTick();

        TeoiaSavedData data = TeoiaSavedData.get(this.getServer().getLevel(this.level().dimension()));

        if (data.guestPOSX != this.blockPosition().getX() || data.guestPOSY != this.blockPosition().getY() || data.guestPOSZ != this.blockPosition().getZ() || data.angleX != this.getLookAngle().x || data.angleY != this.getLookAngle().y) {
            data.saveGuest(this);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        return result;
    }

    @Override
    public boolean canHarmPlayer(Player player) {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return false;
    }

    @Override
    public void die(DamageSource cause) {
        String string = "Im jus goin off pure instinct";
        Inventory inventory = this.getInventory();

        ServerLevel level = (ServerLevel) this.level();

        ResourceKey<Level> respawnDimension = this.getRespawnDimension();
        BlockPos respawnBlockPos = this.getRespawnPosition();
        float respawnAngle = this.getRespawnAngle();

        TeoiaSavedData data = TeoiaSavedData.get(this.getServer().getLevel(this.getRespawnDimension()));

        super.die(cause);

        level.getServer().tell(new TickTask(level.getServer().getTickCount() + 100, () -> {
            ServerLevel respawnLevel = respawnDimension != null
                    ? level.getServer().getLevel(respawnDimension)
                    : level;

            if (respawnLevel == null) respawnLevel = level;

            Vec3 spawnPos = respawnBlockPos != null
                    ? Vec3.atBottomCenterOf(respawnBlockPos)
                    : Vec3.atBottomCenterOf(respawnLevel.getSharedSpawnPos());

            float yaw = respawnBlockPos != null ? respawnAngle : respawnLevel.getSharedSpawnAngle();

            data.guest = new Guest(respawnLevel, this.uuid);
            currentGuest = data.guest;
            data.guest.moveTo(spawnPos, yaw, 0.0F);

            ClientboundPlayerInfoUpdatePacket infoPacket =
                    new ClientboundPlayerInfoUpdatePacket(
                            ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER,
                            data.guest
                    );

            for (ServerPlayer viewer : respawnLevel.players()) {
                viewer.connection.send(infoPacket);
            }

            data.guest.setRespawnPosition(respawnDimension, respawnBlockPos, 0.0f, true, true);

            respawnLevel.addNewPlayer(data.guest);



            for (int i = 0; i < inventory.getContainerSize(); i++) {
                data.guest.getInventory().setItem(
                        i,
                        inventory.getItem(i).copy()
                );
            }
        }));
    }
}
