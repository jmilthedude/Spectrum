package net.thedudemc.spectrum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
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
import net.thedudemc.spectrum.client.ColorHandler;
import net.thedudemc.spectrum.config.Config;
import net.thedudemc.spectrum.creativetab.SpectrumTab;
import net.thedudemc.spectrum.init.InitFluid;
import net.thedudemc.spectrum.network.CleanBlockPacket;
import net.thedudemc.spectrum.network.CleanBlockPacketHandler;
import net.thedudemc.spectrum.network.ColorPacket;
import net.thedudemc.spectrum.network.ColorPacketHandler;
import net.thedudemc.spectrum.network.ConfigSync;
import net.thedudemc.spectrum.network.ConfigSyncHandler;
import net.thedudemc.spectrum.util.GuiHandler;
import net.thedudemc.spectrum.util.SpectrumUtils;

@Mod(modid = Spectrum.MODID, name = Spectrum.NAME, version = Spectrum.VERSION)
public class Spectrum {

	public static final Logger LOG = LogManager.getLogger("Spectrum");
	public static final String MODID = "spectrum";
	public static final String NAME = "Spectrum";
	public static final String VERSION = "0.1";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "net.thedudemc.spectrum.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "net.thedudemc.spectrum.proxy.CommonProxy";
	public static final SimpleNetworkWrapper PACKET = NetworkRegistry.INSTANCE.newSimpleChannel("spectrum_packets");

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@Instance
	public static Spectrum INSTANCE;


	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		Config.init(event.getSuggestedConfigurationFile());
		InitFluid.init();
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
			ColorHandler.initBlockColorHandler();
		}
		SpectrumUtils.addDyeRecipes();
		SpectrumUtils.addCleanRecipes();
	}

	public static Spectrum getInstance() {
		return INSTANCE;
	}

	public static ResourceLocation getResource(String name) {
		return new ResourceLocation(Spectrum.MODID, name);
	}

	public static String getTranslationKey(String name) {
		return Spectrum.MODID + "." + name;
	}

}
