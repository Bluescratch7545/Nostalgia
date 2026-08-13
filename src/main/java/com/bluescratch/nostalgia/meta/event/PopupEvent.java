package com.bluescratch.nostalgia.meta.event;

import com.bluescratch.nostalgia.Nostalgia;
import com.bluescratch.nostalgia.meta.event.interf.MetaEvent;
import com.bluescratch.nostalgia.meta.event.packet.PopupShowPacket;
import com.bluescratch.nostalgia.network.NostalgiaNetwork;
import com.bluescratch.nostalgia.popup.PopupLauncher;
import net.minecraft.server.level.ServerLevel;

import javax.swing.*;

public class PopupEvent implements MetaEvent {

    private final String text;
    private final String title;
    private final boolean minimize;

    public PopupEvent(boolean minimize, String title, String text) {
        this.title = title;
        this.text = text;
        this.minimize = minimize;
    }

    @Override
    public void execute() {
        PopupLauncher.show(minimize, title, text);
    }
}
