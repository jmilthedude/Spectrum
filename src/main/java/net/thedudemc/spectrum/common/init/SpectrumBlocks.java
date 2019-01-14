package net.thedudemc.spectrum.common.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.spectrum.common.Constants;
import net.thedudemc.spectrum.common.Spectrum;
import net.thedudemc.spectrum.common.block.BlockDyeable;
import net.thedudemc.spectrum.common.block.BlockDyeablePillar;
import net.thedudemc.spectrum.common.block.BlockDyeableTransparent;
import net.thedudemc.spectrum.common.block.BlockDyeingTable;
import net.thedudemc.spectrum.common.tileentity.TileDyeable;
import net.thedudemc.spectrum.common.tileentity.TileDyeingTableController;
import net.thedudemc.spectrum.common.tileentity.TileDyeingTableFluidInput;

@Mod.EventBusSubscriber(modid = Constants.MODID)
@ObjectHolder(Constants.MODID)
public class SpectrumBlocks {

	public static List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block DYEING_TABLE = new BlockDyeingTable();
	public static final Block SPECTRUM_STONE = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(1.5f).setResistance(10.0F);
	public static final Block SPECTRUM_DIRT = new BlockDyeable(Material.GROUND, SoundType.GROUND).setHardness(0.5f);
	public static final Block SPECTRUM_COBBLESTONE = new BlockDyeable(Material.ROCK, SoundType.STONE).setHardness(2.0f).setResistance(10.0F);
	public static final Block SPECTRUM_PLANK = new BlockDyeable(Material.WOOD, SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
	public static final Block SPECTRUM_SAND = new BlockDyeable(Material.SAND, SoundType.SAND).setHardness(0.5F);
	public static final Block SPECTRUM_GRAVEL = new BlockDyeable(Material.GROUND, SoundType.GROUND).setHardness(0.6F);
	public static final Block SPECTRUM_OAK_LOG = new BlockDyeablePillar(Material.WOOD, SoundType.WOOD).setHardness(2.0F);
	public static final Block SPECTRUM_OAK_LEAVES = new BlockDyeableTransparent(Material.LEAVES, SoundType.PLANT).setHardness(0.2F).setLightOpacity(1);
	public static final Block RED_LIQUID_DYE = new BlockFluidClassic(SpectrumFluids.fluidRedLiquidDye, Material.WATER);
	public static final Block GREEN_LIQUID_DYE = new BlockFluidClassic(SpectrumFluids.fluidGreenLiquidDye, Material.WATER);
	public static final Block BLUE_LIQUID_DYE = new BlockFluidClassic(SpectrumFluids.fluidBlueLiquidDye, Material.WATER);

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registerBlock(registry, "dyeing_table", DYEING_TABLE);
		registerBlock(registry, "spectrum_stone", SPECTRUM_STONE);
		registerBlock(registry, "spectrum_dirt", SPECTRUM_DIRT);
		registerBlock(registry, "spectrum_cobblestone", SPECTRUM_COBBLESTONE);
		registerBlock(registry, "spectrum_plank", SPECTRUM_PLANK);
		registerBlock(registry, "spectrum_sand", SPECTRUM_SAND);
		registerBlock(registry, "spectrum_gravel", SPECTRUM_GRAVEL);
		registerBlock(registry, "spectrum_oak_log", SPECTRUM_OAK_LOG);
		registerBlock(registry, "spectrum_oak_leaves", SPECTRUM_OAK_LEAVES);
		registerBlock(registry, "block_red_liquid_dye", RED_LIQUID_DYE);
		registerBlock(registry, "block_green_liquid_dye", GREEN_LIQUID_DYE);
		registerBlock(registry, "block_blue_liquid_dye", BLUE_LIQUID_DYE);

		GameRegistry.registerTileEntity(TileDyeingTableController.class, new ResourceLocation(Constants.MODID + ":tiledyeingtable"));
		GameRegistry.registerTileEntity(TileDyeingTableFluidInput.class, new ResourceLocation(Constants.MODID + ":tiledyeingtablefluid"));
		GameRegistry.registerTileEntity(TileDyeable.class, new ResourceLocation(Constants.MODID + ":tile_dyeable"));

	}

	private static void registerBlock(final IForgeRegistry<Block> registry, final String name, final Block block) {
		block.setRegistryName(new ResourceLocation(Constants.MODID, name));
		block.setTranslationKey(Spectrum.getTranslationKey(name));
		registry.register(block);
		if (block instanceof BlockDyeable)
			BLOCKS.add(block);
	}

}
