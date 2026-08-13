package com.bluescratch.nostalgia.event;

public enum EventType {
    POPUP("POPUP_ALERT"),
    STRUCTURE("STRUCTURE_SPAWN"),
    ENTITY("ENTITY_SPAWN");

    private final String id;

    EventType(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }
}
