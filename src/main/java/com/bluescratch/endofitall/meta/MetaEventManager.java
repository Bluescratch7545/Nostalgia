package com.bluescratch.endofitall.meta;

import com.bluescratch.endofitall.meta.event.interf.MetaEvent;

public class MetaEventManager {
    public static void trigger(MetaEvent event) {
        event.execute();
    }
}
