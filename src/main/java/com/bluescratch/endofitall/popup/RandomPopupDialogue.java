package com.bluescratch.endofitall.popup;

public enum RandomPopupDialogue {
    WAYH("Why are you here?", "§o"),
    GO("Get out.", "§o§4"),
    YDNB("You do not belong here.", "§o"),
    GA("Go away.", "§o"),
    W("Why.", "§o"),
    /*Yap*/ YEIP("Your existence is pathetic.", "§o§4"),
    LTP("Leave this place.", "§o"),
    IWAYF("It was all your fault.", "§o§4"),
    DGTTD("Dont go through the door.", "§o"),
    P("Please.", "§o"),
    PHM("Please Help Me.", "§o");

    private final String text;
    private final String color;

    RandomPopupDialogue(String chatText, String color) {
        this.text = chatText;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }
}
