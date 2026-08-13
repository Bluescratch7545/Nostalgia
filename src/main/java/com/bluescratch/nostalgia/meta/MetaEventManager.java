package com.bluescratch.nostalgia.meta;

import com.bluescratch.nostalgia.meta.event.interf.MetaEvent;

public class MetaEventManager {
    public static void trigger(MetaEvent event) {
        event.execute();
    }
}
