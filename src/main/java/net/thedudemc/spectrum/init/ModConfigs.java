package net.thedudemc.spectrum.init;

import net.thedudemc.spectrum.config.DyeConfig;

public class ModConfigs {

    public static DyeConfig DYE_CONFIG;


    public static void register() {
        DYE_CONFIG = (DyeConfig) new DyeConfig().readConfig();

    }

}
