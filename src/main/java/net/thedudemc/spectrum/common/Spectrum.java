package net.thedudemc.spectrum.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.thedudemc.spectrum.client.SpectrumClient;
import net.thedudemc.spectrum.common.config.Config;
import net.thedudemc.spectrum.common.creativetab.SpectrumTab;
import net.thedudemc.spectrum.common.init.SpectrumFluids;
import net.thedudemc.spectrum.common.network.CleanBlockPacket;
import net.thedudemc.spectrum.common.network.CleanBlockPacketHandler;
import net.thedudemc.spectrum.common.network.ColorPacket;
import net.thedudemc.spectrum.common.network.ColorPacketHandler;
import net.thedudemc.spectrum.common.network.ConfigSync;
import net.thedudemc.spectrum.common.network.ConfigSyncHandler;
import net.thedudemc.spectrum.common.util.GuiHandler;
import net.thedudemc.spectrum.common.util.SpectrumUtils;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class Spectrum {

	public static final Logger LOG = LogManager.getLogger("Spectrum");
	public static final SimpleNetworkWrapper PACKET = NetworkRegistry.INSTANCE.newSimpleChannel("spectrum_packets");

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@Instance
	public static Spectrum INSTANCE;

	public static final CreativeTabs SPECTRUM_TAB = new SpectrumTab("spectrumtab");

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		Config.init(event.getSuggestedConfigurationFile());
		SpectrumFluids.init();
		PACKET.registerMessage(ColorPacketHandler.class, ColorPacket.class, 0, Side.SERVER);
		PACKET.registerMessage(ConfigSyncHandler.class, ConfigSync.class, 1, Side.CLIENT);
		PACKET.registerMessage(CleanBlockPacketHandler.class, CleanBlockPacket.class, 3, Side.SERVER);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			SpectrumClient.initBlockColorHandler();
		}
		SpectrumUtils.addDyeRecipes();
		SpectrumUtils.addCleanRecipes();
	}

	public static final String getTranslationKey(String name) {
		return Constants.MODID + "." + name;
	}

}
