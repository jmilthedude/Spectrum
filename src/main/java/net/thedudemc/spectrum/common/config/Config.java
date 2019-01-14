package net.thedudemc.spectrum.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public static Configuration config;
	public static int unitPerDyeItem;
	public static int unitCostIncrement;
	public static int waterCostPerDye;
	public static int maxAmountDyeUnits;

	public static void init(File file) {

		config = new Configuration(new File("config/Spectrum.cfg"));
		config.load();

		unitPerDyeItem = config.getInt("unit_per_dye_item", "dyeing", 32, 8, 512, "This is the amount of Spectrum Dye Units created from one single Minecraft Dye item. ie. 1 red dye = this many units.");
		unitCostIncrement = config.getInt("unit_value", "dyeing", 32, 0, 255, "Controls dye unit consumption. This is how much of an RGB color value you get per block from one dye unit during the dyeing process.");
		waterCostPerDye = config.getInt("water_cost_per_dye", "dyeing", 32, 1, 1000, "Measured by mB (1000mB = 1 bucket). This is the amount of water required to turn 1 minecraft dye item into Spectrum dye units.");
		maxAmountDyeUnits = config.getInt("max_amount_dye_units", "dyeing", 2048, 512, 10240, "This is the maximum amount of Spectrum Dye Units that can be held by each canister.");

		config.save();
	}

}
