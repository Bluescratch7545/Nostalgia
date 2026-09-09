package com.bluescratch.endofitall.world.saved;

import com.bluescratch.endofitall.entities.fakeplayer.Guest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class TeoiaSavedData extends SavedData {

    public static Guest currentGuest;
    public Guest guest;
    public UUID guestUUID;
    public float guestPOSX;
    public float guestPOSY;
    public float guestPOSZ;
    public float angleX;
    public float angleY;
    public CompoundTag guestData;

    public static TeoiaSavedData load(CompoundTag tag, Provider provider) {
        TeoiaSavedData data = new TeoiaSavedData();

        if (tag.hasUUID("gUUID")) {
            data.guestUUID = tag.getUUID("gUUID");
        }

        data.guestPOSX = tag.getFloat("gPOSX");
        data.guestPOSY = tag.getFloat("gPOSY");
        data.guestPOSZ = tag.getFloat("gPOSZ"); // fixed typo
        data.angleX = tag.getFloat("angleX");
        data.angleY = tag.getFloat("angleY");

        if (tag.contains("GuestData")) {
            data.guestData = tag.getCompound("GuestData");
        }

        return data;
    }

    public static TeoiaSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        TeoiaSavedData::new,
                        TeoiaSavedData::load,
                        null
                ),
                "teoia_world"
        );
    }

    @Override
    @NotNull
    public CompoundTag save(@NotNull CompoundTag tag, Provider provider) {
        if (guestUUID != null) tag.putUUID("gUUID", guestUUID);

        tag.putFloat("gPOSX", guestPOSX);
        tag.putFloat("gPOSY", guestPOSY);
        tag.putFloat("gPOSZ", guestPOSZ);
        tag.putFloat("angleX", angleX);
        tag.putFloat("angleY", angleY);

        if (guestData != null) tag.put("GuestData", guestData.copy());

        return tag;
    }

    public void saveGuest(Guest g) {
        guestData = g.saveWithoutId(new CompoundTag());
        guestUUID = g.getUUID();
        guestPOSX = (float) g.getX();
        guestPOSY = (float) g.getY();
        guestPOSZ = (float) g.getZ();
        angleX = g.getXRot();
        angleY = g.getYRot();
        setDirty();
    }
}