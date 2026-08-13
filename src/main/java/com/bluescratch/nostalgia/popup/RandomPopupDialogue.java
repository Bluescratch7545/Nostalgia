package com.bluescratch.nostalgia.popup;

public enum RandomPopupDialogue {
    WAYH("Why are you here?"),
    GO("Get out."),
    YDNB("You do not belong here."),
    GA("Go away.");

    private final String text;

    RandomPopupDialogue(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
