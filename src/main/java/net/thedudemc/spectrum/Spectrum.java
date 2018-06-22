package net.thedudemc.spectrum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thedudemc.spectrum.objects.tabs.SpectrumTab;
import net.thedudemc.spectrum.proxy.CommonProxy;
import net.thedudemc.spectrum.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Spectrum {

	@Instance
	public static Spectrum instance;
	
	public static final CreativeTabs spectrumtab = new SpectrumTab("spectrumtab");
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
	
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
	
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
	
	}
}
