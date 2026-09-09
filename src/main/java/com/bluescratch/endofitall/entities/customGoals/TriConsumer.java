package com.bluescratch.endofitall.entities.customGoals;

@FunctionalInterface
public interface TriConsumer<A, B, C> {
    void accept(A a, B b, C c);
}
