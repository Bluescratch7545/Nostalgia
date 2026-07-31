package com.bluescratch.nostalgia.world;

public class NostalgiaProgression {
    public static float getStructureChance(long days) {
        if (days < 5) {
            return 0.001f;
        }
        if (days < 10) {
            return 0.01f;
        }
        if (days < 20) {
            return 0.05f;
        }
        if (days < 30) {
            return 0.1f;
        }
        if (days < 49) {
            return 0.15f;
        }

        return 0.25f;
    }
}
