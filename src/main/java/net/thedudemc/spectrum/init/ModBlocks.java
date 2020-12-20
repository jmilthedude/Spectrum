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

    public static final SpectrumBlock STONE = new SpectrumBlock(Properties.from(Blocks.STONE));
    public static final SpectrumBlock GRANITE = new SpectrumBlock(Properties.from(Blocks.GRANITE));
    public static final SpectrumBlock POLISHED_GRANITE = new SpectrumBlock(Properties.from(Blocks.POLISHED_GRANITE));
    public static final SpectrumBlock DIORITE = new SpectrumBlock(Properties.from(Blocks.DIORITE));
    public static final SpectrumBlock POLISHED_DIORITE = new SpectrumBlock(Properties.from(Blocks.POLISHED_DIORITE));

    public static final TileEntityType<SpectrumBlockTileEntity> SPECTRUM_BLOCK_TILE_ENTITY =
            TileEntityType.Builder.create(SpectrumBlockTileEntity::new, STONE,
                    GRANITE,
                    POLISHED_GRANITE,
                    DIORITE,
                    POLISHED_DIORITE).build(null);

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        registerBlock(event, STONE, "stone");
        registerBlock(event, GRANITE, "granite");
        registerBlock(event, POLISHED_GRANITE, "polished_granite");
        registerBlock(event, DIORITE, "diorite");
        registerBlock(event, POLISHED_DIORITE, "polished_diorite");
    }

    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        registerTileEntity(event, SPECTRUM_BLOCK_TILE_ENTITY, Spectrum.id("spectrum_block_tile_entity"));
    }

    public static void registerTileEntityRenderers() {
        //ClientRegistry.bindTileEntityRenderer(ModBlocks.SPECTRUM_BLOCK_TILE_ENTITY, SpectrumBlockRenderer::new);
    }

    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        registerBlockItem(event, STONE);
        registerBlockItem(event, GRANITE);
        registerBlockItem(event, POLISHED_GRANITE);
        registerBlockItem(event, DIORITE);
        registerBlockItem(event, POLISHED_DIORITE);
    }

    /* --------------------------------------------- */

    private static void registerBlock(RegistryEvent.Register<Block> event, Block block, String name) {
        block.setRegistryName(Spectrum.id(name));
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
