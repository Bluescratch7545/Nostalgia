package com.bluescratch.endofitall;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import net.minecraft.resources.ResourceLocation;

public class TeoiaClientConfig extends Config {
    public TeoiaClientConfig() {
        super(ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "teoia-client"));
    }

    public PopupUtility popupUtil = new PopupUtility();

    public static class PopupUtility extends ConfigSection {

        @Desc("The minimization of the popups. Reccomended to keep on.")
        public ValidatedBoolean minimize = new ValidatedBoolean(true);
    }
}
