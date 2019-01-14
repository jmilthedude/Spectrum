package net.thedudemc.spectrum.common.init;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.spectrum.common.Constants;
import net.thedudemc.spectrum.common.Spectrum;
import net.thedudemc.spectrum.common.item.ItemBlockBase;
import net.thedudemc.spectrum.common.item.ItemDyeingTable;

@ObjectHolder(Constants.MODID)
@Mod.EventBusSubscriber(modid = Constants.MODID)
public class SpectrumItems {

	public static final Item ITEM_DYEING_TABLE = null;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registerItem(registry, "item_dyeing_table", new ItemDyeingTable());
		registerItem(registry, "spectrum_stone", new ItemBlockBase(SpectrumBlocks.SPECTRUM_STONE));
		registerItem(registry, "spectrum_dirt", new ItemBlockBase(SpectrumBlocks.SPECTRUM_DIRT));
		registerItem(registry, "spectrum_cobblestone", new ItemBlockBase(SpectrumBlocks.SPECTRUM_COBBLESTONE));
		registerItem(registry, "spectrum_plank", new ItemBlockBase(SpectrumBlocks.SPECTRUM_PLANK));
		registerItem(registry, "spectrum_sand", new ItemBlockBase(SpectrumBlocks.SPECTRUM_SAND));
		registerItem(registry, "spectrum_gravel", new ItemBlockBase(SpectrumBlocks.SPECTRUM_GRAVEL));
		registerItem(registry, "spectrum_oak_log", new ItemBlockBase(SpectrumBlocks.SPECTRUM_OAK_LOG));
		registerItem(registry, "spectrum_oak_leaves", new ItemBlockBase(SpectrumBlocks.SPECTRUM_OAK_LEAVES));

	}

	private static void registerItem(final IForgeRegistry<Item> registry, final String name, final Item item) {
		item.setRegistryName(new ResourceLocation(Constants.MODID, name));
		item.setTranslationKey(Spectrum.getTranslationKey(name));

		registry.register(item);
	}

}
