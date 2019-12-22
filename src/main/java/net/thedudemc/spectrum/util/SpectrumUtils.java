package net.thedudemc.spectrum.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedudemc.spectrum.block.BlockDyeable;
import net.thedudemc.spectrum.config.Config;
import net.thedudemc.spectrum.init.InitBlock;

public class SpectrumUtils {

	private static final Map<Item, Item> dyeableBlocks = new HashMap<Item, Item>();
	private static final Map<Item, Item> cleanBlockOutput = new HashMap<Item, Item>();

	private static final int WATER_COST = Config.waterCostPerDye;
	private static final int DYE_COST = Config.unitCostIncrement;

	public static void addDyeRecipes() {
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.STONE), Item.getItemFromBlock(InitBlock.SPECTRUM_STONE));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.GRASS), Item.getItemFromBlock(InitBlock.SPECTRUM_GRASS));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.DIRT), Item.getItemFromBlock(InitBlock.SPECTRUM_DIRT));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.COBBLESTONE), Item.getItemFromBlock(InitBlock.SPECTRUM_COBBLESTONE));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(InitBlock.SPECTRUM_PLANK));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.SAND), Item.getItemFromBlock(InitBlock.SPECTRUM_SAND));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.GRAVEL), Item.getItemFromBlock(InitBlock.SPECTRUM_GRAVEL));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.LOG), Item.getItemFromBlock(InitBlock.SPECTRUM_OAK_LOG));
		dyeableBlocks.put(Item.getItemFromBlock(Blocks.LEAVES), Item.getItemFromBlock(InitBlock.SPECTRUM_OAK_LEAVES));
	}

	public static void addCleanRecipes() {
		cleanBlockOutput.put(Item.getItemFromBlock(InitBlock.SPECTRUM_STONE), Item.getItemFromBlock(Blocks.STONE));
		cleanBlockOutput.put(Item.getItemFromBlock(InitBlock.SPECTRUM_GRASS), Item.getItemFromBlock(Blocks.GRASS));
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

	public static boolean isCleanable(ItemStack stack) {
		Item itemIn = stack.getItem();
		if (Block.getBlockFromItem(itemIn) instanceof BlockDyeable) {
			return true;
		}
		return false;
	}

	public static ItemStack getOutput(ItemStack stackIn, boolean clean) {
		Item itemIn = stackIn.getItem();
		if (!clean) {
			if (dyeableBlocks.containsValue(itemIn)) {
				return new ItemStack(itemIn);
			}
			for (Item item : dyeableBlocks.keySet()) {
				if (itemIn.equals(item)) {
					return new ItemStack(dyeableBlocks.get(item));
				}
			}
		} else {
			for (Map.Entry<Item, Item> entry : dyeableBlocks.entrySet()) {
				if (entry.getValue().equals(itemIn)) {
					return new ItemStack(entry.getKey());
				}
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

	public static int getWaterDecrementForClean(int amount) {
		return (int) ((float) amount * (1000 / 64));
	}
}
