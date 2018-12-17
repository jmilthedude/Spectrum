package net.thedudemc.spectrum.util;

import net.minecraft.block.Block;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.thedudemc.spectrum.block.BlockLiquidDye;

@EventBusSubscriber
public class EventHandler {

	public static TickHandler tickHandler;

	public static void onDyePlaced(BlockEvent event) {
		if (!event.getWorld().isRemote) {
			Block block = event.getState().getBlock();
			if (block instanceof BlockLiquidDye) {
				((BlockLiquidDye) block).drain(event.getWorld(), event.getPos(), true);
			}
		}
	}

	@SubscribeEvent
	public static void tickEvent(WorldTickEvent event) {
		if (tickHandler != null) {
			if (tickHandler.getTicks() > 0) {
				tickHandler.update(); //ticks--;

			} else {
				event.world.setBlockToAir(tickHandler.getPos());
				tickHandler = null;
			}
		}
	}
}
