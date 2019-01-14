package net.thedudemc.spectrum.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtility {

	public static final String RGB_TAG = "rgb";

	public static NBTTagCompound getNBT(ItemStack item) {
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
		}
		return item.getTagCompound();
	}

	public static boolean hasNBT(ItemStack item, String tag) {
		return !item.isEmpty() && getNBT(item).hasKey(tag);
	}

	public static void setInt(ItemStack item, String tag, int amount) {
		getNBT(item).setInteger(tag, amount);
	}

	public static int getInt(ItemStack item, String tag, int amount) {
		return hasNBT(item, tag) ? getNBT(item).getInteger(tag) : amount;
	}

	public static void setIntArray(ItemStack item, String tag, int[] arrayIn) {
		getNBT(item).setIntArray(tag, arrayIn);
	}

	public static int[] getIntArray(ItemStack item, String tag) {
		return hasNBT(item, tag) ? getNBT(item).getIntArray(tag) : new int[] { 0, 0, 0 };
	}
}