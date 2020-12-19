package net.thedudemc.spectrum.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.init.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event);
        ModBlocks.registerBlockItems(event);
    }

    @SubscribeEvent
    public static void onTileEntityRegister(RegistryEvent.Register<TileEntityType<?>> event) {
        ModBlocks.registerTileEntities(event);
    }

}
