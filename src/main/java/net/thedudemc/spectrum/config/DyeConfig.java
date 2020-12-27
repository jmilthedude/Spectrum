package net.thedudemc.spectrum.config;

import com.google.gson.annotations.Expose;
import net.thedudemc.spectrum.config.entry.ConfigOption;
import net.thedudemc.spectrum.init.ModConfigs;

import java.util.HashMap;

public class DyeConfig extends Config {

    @Expose
    public HashMap<String, ConfigOption> OPTIONS = new HashMap<>();

    @Override
    public String getName() {
        return "DyeConfig";
    }

    @Override
    protected void reset() {
        OPTIONS.put("unit_per_dye", new ConfigOption(32, "This is the amount of Units created from one Dye item. ie. 1 red dye = this many units."));
        OPTIONS.put("unit_cost", new ConfigOption(32, "Color cost per unit. If you want Red to be 32 in RGB, 1 unit required."));
        OPTIONS.put("water_cost", new ConfigOption(50, "Measured by mB (1000mB = 1 bucket). Mix 1 dye per x amount of water to create dye units."));
        OPTIONS.put("max_units", new ConfigOption(2048, "This is the maximum amount of Units that can be held by each tank."));
        OPTIONS.put("max_water", new ConfigOption(10000, "This is the maximum amount of Water that can be held by the table."));
    }

    public int getInt(String key) {
        ConfigOption o = ModConfigs.DYE_CONFIG.OPTIONS.get(key);
        if (o.getValue() instanceof Double) {
            return ((Double) o.getValue()).intValue();
        }
        return 0;
    }

    public String getString(String key) {
        ConfigOption o = ModConfigs.DYE_CONFIG.OPTIONS.get(key);
        if (o.getValue() instanceof String) {
            return (String) o.getValue();
        }
        return "";
    }
}
