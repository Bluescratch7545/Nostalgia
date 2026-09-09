package com.bluescratch.endofitall.event;

public enum EventType {
    POPUP("popup"),
    CHAT("send_chat_text");

    private final String id;

    EventType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
