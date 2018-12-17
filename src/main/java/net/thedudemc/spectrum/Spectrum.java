package net.thedudemc.spectrum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.thedudemc.spectrum.creativetab.SpectrumTab;
import net.thedudemc.spectrum.init.ModFluids;
import net.thedudemc.spectrum.util.BlockColorTest;
import net.thedudemc.spectrum.util.RegistryHandler;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class Spectrum {

	public static final Logger LOG = LogManager.getLogger("Spectrum");

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@Instance
	public static Spectrum INSTANCE;

	public static final CreativeTabs SPECTRUM_TAB = new SpectrumTab("spectrumtab");

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RegistryHandler());
		ModFluids.init();
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new BlockColorTest(), ModFluids.blockRedLiquidDye, ModFluids.blockGreenLiquidDye, ModFluids.blockBlueLiquidDye);
		}
	}

}
