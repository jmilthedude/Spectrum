package net.thedudemc.spectrum.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.block.BlockDyeable;
import net.thedudemc.spectrum.block.BlockDyeableCutout;
import net.thedudemc.spectrum.block.BlockDyeablePillar;
import net.thedudemc.spectrum.block.BlockDyeableTransparent;
import net.thedudemc.spectrum.block.BlockDyeingTable;
import net.thedudemc.spectrum.block.entity.TileDyeable;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;
import net.thedudemc.spectrum.block.entity.TileDyeingTableFluidInput;
import net.thedudemc.spectrum.item.ItemBlockBase;

public class InitBlock {

	public static List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block DYEING_TABLE = new BlockDyeingTable("dyeing_table");

	public static final Block SPECTRUM_STONE = new BlockDyeable("spectrum_stone", Material.ROCK, SoundType.STONE).setHardness(1.5f).setResistance(10.0F);
	public static final ItemBlockBase ITEM_SPECTRUM_STONE = getItemBlock(SPECTRUM_STONE);

	public static final Block SPECTRUM_GRASS = new BlockDyeableCutout("spectrum_grass", Material.GROUND, SoundType.PLANT).setHardness(0.6f);
	public static final ItemBlockBase ITEM_SPECTRUM_GRASS = getItemBlock(SPECTRUM_GRASS);

	public static final Block SPECTRUM_DIRT = new BlockDyeable("spectrum_dirt", Material.GROUND, SoundType.GROUND).setHardness(0.5f);
	public static final ItemBlockBase ITEM_SPECTRUM_DIRT = getItemBlock(SPECTRUM_DIRT);

	public static final Block SPECTRUM_COBBLESTONE = new BlockDyeable("spectrum_cobblestone", Material.ROCK, SoundType.STONE).setHardness(2.0f).setResistance(10.0F);
	public static final ItemBlockBase ITEM_SPECTRUM_COBBLESTONE = getItemBlock(SPECTRUM_COBBLESTONE);

	public static final Block SPECTRUM_PLANK = new BlockDyeable("spectrum_plank", Material.WOOD, SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
	public static final ItemBlockBase ITEM_SPECTRUM_PLANK = getItemBlock(SPECTRUM_PLANK);

	public static final Block SPECTRUM_SAND = new BlockDyeable("spectrum_sand", Material.SAND, SoundType.SAND).setHardness(0.5F);
	public static final ItemBlockBase ITEM_SPECTRUM_SAND = getItemBlock(SPECTRUM_SAND);

	public static final Block SPECTRUM_GRAVEL = new BlockDyeable("spectrum_gravel", Material.GROUND, SoundType.GROUND).setHardness(0.6F);
	public static final ItemBlockBase ITEM_SPECTRUM_GRAVEL = getItemBlock(SPECTRUM_GRAVEL);

	public static final Block SPECTRUM_OAK_LOG = new BlockDyeablePillar("spectrum_oak_log", Material.WOOD, SoundType.WOOD).setHardness(2.0F);
	public static final ItemBlockBase ITEM_SPECTRUM_OAK_LOG = getItemBlock(SPECTRUM_OAK_LOG);

	public static final Block SPECTRUM_OAK_LEAVES = new BlockDyeableTransparent("spectrum_oak_leaves", Material.LEAVES, SoundType.PLANT).setHardness(0.2F).setLightOpacity(1);
	public static final ItemBlockBase ITEM_SPECTRUM_OAK_LEAVES = getItemBlock(SPECTRUM_OAK_LEAVES);

//	public static final Block SPECTRUM_GLASS = new BlockDyeableCutout(Material.GLASS, SoundType.GLASS).setHardness(0.3F);
//	public static final Block SPECTRUM_LAPIS_BLOCK = new BlockDyeable(Material.IRON, SoundType.STONE).setHardness(3.0f).setResistance(5.0F);
//	public static final Block SPECTRUM_SANDSTONE = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(0.8f);
//	public static final Block SPECTRUM_WEB = new BlockDyeableTransparent(Material.WEB, SoundType.CLOTH).setHardness(4.0f);
//	public static final Block SPECTRUM_WOOL = new BlockDyeable(Material.CLOTH, SoundType.CLOTH).setHardness(0.8f);
//	public static final Block SPECTRUM_BRICK_BLOCK = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(2.0f).setResistance(10.0F);
//	public static final Block SPECTRUM_MOSSY_COBBLESTONE = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(2.0f).setResistance(10.0F);
//	public static final Block SPECTRUM_OBSIDIAN = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(50.0f).setResistance(2000.0F);
//	public static final Block SPECTRUM_DIAMOND_BLOCK = new BlockDyeable(Material.IRON, SoundType.METAL).setHardness(5.0f).setResistance(10.0F);
//	public static final Block SPECTRUM_CRAFTING_TABLE = new BlockDyeable(Material.WOOD, SoundType.STONE).setHardness(2.5f);
//	public static final Block SPECTRUM_SNOW = new BlockDyeable(Material.SNOW, SoundType.SNOW).setHardness(0.2f);
//	public static final Block SPECTRUM_FENCE = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(2.0f).setResistance(10.0F);

	public static final Block RED_LIQUID_DYE = new BlockFluidClassic(InitFluid.fluidRedLiquidDye, Material.WATER).setRegistryName(Spectrum.getResource("block_red_liquid_dye"))
			.setTranslationKey("block_red_liquid_dye");
	public static final Block GREEN_LIQUID_DYE = new BlockFluidClassic(InitFluid.fluidGreenLiquidDye, Material.WATER).setRegistryName(Spectrum.getResource("block_green_liquid_dye"))
			.setTranslationKey("block_green_liquid_dye");
	public static final Block BLUE_LIQUID_DYE = new BlockFluidClassic(InitFluid.fluidBlueLiquidDye, Material.WATER).setRegistryName(Spectrum.getResource("block_blue_liquid_dye"))
			.setTranslationKey("block_blue_liquid_dye");

	public static void registerBlocks(IForgeRegistry<Block> registry) {
		Spectrum.LOG.info("Registering Spectrum Blocks...");
		registerBlock(registry, DYEING_TABLE);
		registerBlock(registry, SPECTRUM_STONE);
		registerBlock(registry, SPECTRUM_GRASS);
		registerBlock(registry, SPECTRUM_DIRT);
		registerBlock(registry, SPECTRUM_COBBLESTONE);
		registerBlock(registry, SPECTRUM_PLANK);
		registerBlock(registry, SPECTRUM_SAND);
		registerBlock(registry, SPECTRUM_GRAVEL);
		registerBlock(registry, SPECTRUM_OAK_LOG);
		registerBlock(registry, SPECTRUM_OAK_LEAVES);
		registerBlock(registry, RED_LIQUID_DYE);
		registerBlock(registry, GREEN_LIQUID_DYE);
		registerBlock(registry, BLUE_LIQUID_DYE);

	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileDyeingTableController.class, Spectrum.getResource("tiledyeingtable"));
		GameRegistry.registerTileEntity(TileDyeingTableFluidInput.class, Spectrum.getResource("tiledyeingtablefluid"));
		GameRegistry.registerTileEntity(TileDyeable.class, Spectrum.getResource("tiledyeingtablefluid"));
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.register(ITEM_SPECTRUM_STONE);
		registry.register(ITEM_SPECTRUM_GRASS);
		registry.register(ITEM_SPECTRUM_DIRT);
		registry.register(ITEM_SPECTRUM_COBBLESTONE);
		registry.register(ITEM_SPECTRUM_PLANK);
		registry.register(ITEM_SPECTRUM_SAND);
		registry.register(ITEM_SPECTRUM_GRAVEL);
		registry.register(ITEM_SPECTRUM_OAK_LOG);
		registry.register(ITEM_SPECTRUM_OAK_LEAVES);
	}

	/* -------------------------- */

	private static void registerBlock(IForgeRegistry<Block> registry, Block block) {
		registry.register(block);
		if (block instanceof BlockDyeable) {
			BLOCKS.add(block);
		}
	}

	private static ItemBlockBase getItemBlock(Block block) {
		return getItemBlock(block, 64);
	}

	private static ItemBlockBase getItemBlock(Block block, int maxStackSize) {
		ItemBlockBase itemBlock = new ItemBlockBase(block);

		if (block.getRegistryName() == null)
			throw new InternalError("Cannot create ItemBlock of " + block.getTranslationKey() + " without a Registry name");

		String resourceName = block.getRegistryName().getPath();
		itemBlock.setTranslationKey(resourceName);
		itemBlock.setRegistryName(resourceName);
		return itemBlock;
	}

}
