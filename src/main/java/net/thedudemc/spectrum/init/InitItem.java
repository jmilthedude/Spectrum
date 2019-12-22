package net.thedudemc.spectrum.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.creativetab.SpectrumTab;
import net.thedudemc.spectrum.item.ItemDyeingTable;

public class InitItem {

	public static final CreativeTabs SPECTRUM_TAB = new SpectrumTab("spectrumtab");

	public static final Item ITEM_DYEING_TABLE = new ItemDyeingTable();

	public static void registerItems(IForgeRegistry<Item> registry) {

		registerItem(registry, "item_dyeing_table", ITEM_DYEING_TABLE);
	}

	private static void registerItem(final IForgeRegistry<Item> registry, final String name, final Item item) {
		item.setRegistryName(new ResourceLocation(Spectrum.MODID, name));
		item.setTranslationKey(name);

		registry.register(item);
	}

}
