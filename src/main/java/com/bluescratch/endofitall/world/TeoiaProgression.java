package com.bluescratch.endofitall.world;

public class TeoiaProgression {
    public static float getEventChance(long days) {
        if (days <= 100) {
            return (float) days / 100;
        }
        else {
            return 1;
        }
    }
}
