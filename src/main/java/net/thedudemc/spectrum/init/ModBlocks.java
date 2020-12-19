package net.thedudemc.spectrum.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.block.SpectrumBlock;
import net.thedudemc.spectrum.block.entity.SpectrumBlockTileEntity;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static List<Block> BLOCKS = new ArrayList<>();

    public static final SpectrumBlock SPECTRUM_STONE = new SpectrumBlock();

    public static final TileEntityType<SpectrumBlockTileEntity> SPECTRUM_BLOCK_TILE_ENTITY =
            TileEntityType.Builder.create(SpectrumBlockTileEntity::new, SPECTRUM_STONE).build(null);

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        registerBlock(event, SPECTRUM_STONE, Spectrum.id("stone"));
    }

    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        registerTileEntity(event, SPECTRUM_BLOCK_TILE_ENTITY, Spectrum.id("spectrum_block_tile_entity"));
    }

    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        registerBlockItem(event, SPECTRUM_STONE);
    }

    /* --------------------------------------------- */

    private static void registerBlock(RegistryEvent.Register<Block> event, Block block, ResourceLocation id) {
        block.setRegistryName(id);
        event.getRegistry().register(block);
        BLOCKS.add(block);
    }

    private static <T extends TileEntity> void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event, TileEntityType<?> type, ResourceLocation id) {
        type.setRegistryName(id);
        event.getRegistry().register(type);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block) {
        BlockItem blockItem = new BlockItem(block, new Item.Properties().group(ModItems.SPECTRUM_BLOCKS).maxStackSize(64));
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

}
