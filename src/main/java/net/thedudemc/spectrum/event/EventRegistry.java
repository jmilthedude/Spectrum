package net.thedudemc.spectrum.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.InitBlock;
import net.thedudemc.spectrum.init.InitItem;
import net.thedudemc.spectrum.init.InitModel;

@EventBusSubscriber(modid = Spectrum.MODID)
public class EventRegistry {

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		InitBlock.registerBlocks(event.getRegistry());
		InitBlock.registerTileEntities();
	}

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		InitItem.registerItems(event.getRegistry());
		InitBlock.registerItemBlocks(event.getRegistry());
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		InitModel.registerItemModels();
	}

}