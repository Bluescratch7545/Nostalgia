package com.bluescratch.endofitall.meta.event.packet;

import com.bluescratch.endofitall.Teoia;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record KillMinecraftPacket(int retrn) implements CustomPacketPayload {
    public static final Type<KillMinecraftPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "show_popup")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, KillMinecraftPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    KillMinecraftPacket::retrn,
                    KillMinecraftPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
