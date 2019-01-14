package net.thedudemc.spectrum.common.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedudemc.spectrum.common.config.Config;
import net.thedudemc.spectrum.common.init.SpectrumBlocks;

public class SpectrumUtils {

	private static final Map<Item, Item> dyeableBlocks = new HashMap<Item, Item>();
	private static final Map<Block, Block> blockDrops = new HashMap<Block, Block>();

	private static final int WATER_COST = Config.waterCostPerDye;
	private static final int DYE_COST = Config.unitCostIncrement;

	public static void addDyeRecipes() {
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.STONE), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_STONE));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_DIRT));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.COBBLESTONE), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_COBBLESTONE));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_PLANK));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.SAND), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_SAND));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.GRAVEL), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_GRAVEL));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.LOG), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_OAK_LOG));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.LEAVES), Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_OAK_LEAVES));
	}

	public static void addBlockDrops() {
		blockDrops.put(SpectrumBlocks.SPECTRUM_STONE, SpectrumBlocks.SPECTRUM_COBBLESTONE);
	}

	public static boolean isDyeable(ItemStack stack) {
		Item itemIn = stack.getItem();
		if (dyeableBlocks.containsKey(itemIn)) {
			return true;
		}
		if (dyeableBlocks.containsValue(itemIn)) {
			return true;
		}
		return false;
	}

	public static ItemStack getOutput(ItemStack stackIn) {
		Item itemIn = stackIn.getItem();
		if (dyeableBlocks.containsValue(itemIn)) {
			return new ItemStack(itemIn);
		}
		for (Item item : dyeableBlocks.keySet()) {
			if (itemIn.equals(item)) {
				return new ItemStack(dyeableBlocks.get(item));
			}
		}
		return ItemStack.EMPTY;
	}

	public static String getHexFromIntArray(int[] colorsIn) {
		Color color = new Color(colorsIn[0], colorsIn[1], colorsIn[2]);
		String hex = Integer.toHexString(color.getRGB() & 0x00FFFFFF);
		while (hex.length() < 6) {
			hex = "0" + hex;
		}
		return hex;
	}

	public static int getDecrementAmount(int color) {
		int count = 1;
		for (int i = 0; i < color; i++) {
			if (i % DYE_COST == 0 && i >= 1) {
				count++;
			}
		}
		return count;
	}

	public static int getWaterDecrementAmount(int color) {
		int count = 1;
		for (int i = 0; i < color; i++) {
			if (i % WATER_COST == 0 && i >= 1) {
				count++;
			}
		}
		return count;
	}

	public static ItemStack getBlockDrop(IBlockState state, boolean hasSilkTouch) {
		if (!hasSilkTouch && blockDrops.containsKey(state.getBlock())) {
			return new ItemStack(blockDrops.get(state.getBlock()));
		} else {
			return new ItemStack(state.getBlock());
		}
	}

}
