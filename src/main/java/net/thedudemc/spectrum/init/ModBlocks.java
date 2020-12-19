package net.thedudemc.spectrum.init;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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

    public static final SpectrumBlock STONE = new SpectrumBlock(Properties.from(Blocks.STONE), "stone");
    public static final SpectrumBlock GRANITE = new SpectrumBlock(Properties.from(Blocks.GRANITE), "granite");
    public static final SpectrumBlock POLISHED_GRANITE = new SpectrumBlock(Properties.from(Blocks.POLISHED_GRANITE), "polished_granite");
    public static final SpectrumBlock DIORITE = new SpectrumBlock(Properties.from(Blocks.DIORITE), "diorite");
    public static final SpectrumBlock POLISHED_DIORITE = new SpectrumBlock(Properties.from(Blocks.POLISHED_DIORITE), "polished_diorite");

    public static final TileEntityType<SpectrumBlockTileEntity> SPECTRUM_BLOCK_TILE_ENTITY =
            TileEntityType.Builder.create(SpectrumBlockTileEntity::new, BLOCKS.toArray(new SpectrumBlock[BLOCKS.size()])).build(null);

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        registerBlock(event, STONE);
        registerBlock(event, GRANITE);
        registerBlock(event, POLISHED_GRANITE);
        registerBlock(event, DIORITE);
        registerBlock(event, POLISHED_DIORITE);
    }

    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        registerTileEntity(event, SPECTRUM_BLOCK_TILE_ENTITY, Spectrum.id("spectrum_block_tile_entity"));
    }

    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        for (Block block : BLOCKS) {
            registerBlockItem(event, block);
        }
    }

    /* --------------------------------------------- */

    private static void registerBlock(RegistryEvent.Register<Block> event, Block block) {
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
