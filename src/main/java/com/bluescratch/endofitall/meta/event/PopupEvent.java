package com.bluescratch.endofitall.meta.event;

import com.bluescratch.endofitall.meta.event.interf.MetaEvent;
import com.bluescratch.endofitall.popup.PopupLauncher;

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
