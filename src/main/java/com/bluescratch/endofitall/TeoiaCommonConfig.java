package com.bluescratch.endofitall;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import net.minecraft.resources.ResourceLocation;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIsj
public class TeoiaCommonConfig extends Config {
    public TeoiaCommonConfig() {
        super(ResourceLocation.fromNamespaceAndPath(Teoia.MOD_ID, "teoia-common"));
    }

    @Desc("WARNING: THESE SETTINGS COULD BREAK THE EXPERIENCE OF THE MOD, SO ITS RECOMMENDED TO KEEP THEM AS IS.")
    public DangerSection danger = new DangerSection();

    public static class DangerSection extends ConfigSection {

        @Desc("Disables the mods ability to do popups. /nostlg dev popup <player> show <title> <text> <minimize> overrides it.")
        public ValidatedBoolean noPopup = new ValidatedBoolean(false);
    }

}
