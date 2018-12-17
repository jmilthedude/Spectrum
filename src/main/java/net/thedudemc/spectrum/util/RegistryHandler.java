package net.thedudemc.spectrum.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.init.ModItems;

public class RegistryHandler {

	public static final List<Block> BLOCKS_TO_REGISTER = new ArrayList<>();
	public static final List<Item> ITEMS_TO_REGISTER = new ArrayList<>();

	@SubscribeEvent
	public void onRegister(RegistryEvent.Register<Block> event) {
		ModBlocks.init();

		for (Block block : BLOCKS_TO_REGISTER) {
			event.getRegistry().register(block);
		}
		

	}

	@SubscribeEvent
	public void onRegisterItems(final RegistryEvent.Register<Item> event) {
		ModItems.init();

		for (Item item : ITEMS_TO_REGISTER) {
			event.getRegistry().register(item);
		}

		ITEMS_TO_REGISTER.clear();

	}
	

}
