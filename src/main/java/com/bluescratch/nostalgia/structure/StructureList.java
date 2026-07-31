package com.bluescratch.nostalgia.structure;

public enum StructureList {
    BRICK_PYRAMID("brick_pyramid"),
    MOSS_STONE_BLOCK("moss_stone_block"),
    HOUSE("house"),
    NETHER_HOUSE("nether_house"),
    SHRINE("shrine");

    private final String id;

    StructureList(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
