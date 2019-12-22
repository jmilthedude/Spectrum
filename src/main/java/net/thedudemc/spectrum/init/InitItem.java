package net.thedudemc.spectrum.init;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.item.ItemDyeingTable;

@ObjectHolder(Spectrum.MODID)
@Mod.EventBusSubscriber(modid = Spectrum.MODID)
public class InitItem {

	public static final Item ITEM_DYEING_TABLE = new ItemDyeingTable();

	@SubscribeEvent
	public static void registerItems(IForgeRegistry<Item> registry) {

		registerItem(registry, "item_dyeing_table", ITEM_DYEING_TABLE);
	}

	private static void registerItem(final IForgeRegistry<Item> registry, final String name, final Item item) {
		item.setRegistryName(new ResourceLocation(Spectrum.MODID, name));
		item.setTranslationKey(Spectrum.getTranslationKey(name));

		registry.register(item);
	}

}
