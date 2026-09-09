package com.bluescratch.endofitall.meta.event.packet;

import com.bluescratch.endofitall.Teoia;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record PopupShowPacket(boolean minimize, String title, String text) implements CustomPacketPayload {
    public static final Type<PopupShowPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "kill_minecraft")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, PopupShowPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.BOOL,
                    PopupShowPacket::minimize,
                    ByteBufCodecs.STRING_UTF8,
                    PopupShowPacket::title,
                    ByteBufCodecs.STRING_UTF8,
                    PopupShowPacket::text,
                    PopupShowPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
