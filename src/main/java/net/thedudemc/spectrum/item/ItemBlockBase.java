package net.thedudemc.spectrum.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedudemc.spectrum.util.NBTUtility;
import net.thedudemc.spectrum.util.SpectrumUtils;

public class ItemBlockBase extends ItemBlock {

	public ItemBlockBase(final Block block) {
		super(block);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().hasKey(NBTUtility.RGB_TAG)) {
				int[] colors = stack.getTagCompound().getIntArray(NBTUtility.RGB_TAG);
				String hex = SpectrumUtils.getHexFromIntArray(colors);
				tooltip.add(TextFormatting.GRAY + "Hex: #" + hex);
				tooltip.add(TextFormatting.GRAY + "Red: " + TextFormatting.RED + colors[0]);
				tooltip.add(TextFormatting.GRAY + "Green: " + TextFormatting.GREEN + colors[1]);
				tooltip.add(TextFormatting.GRAY + "Blue: " + TextFormatting.BLUE + colors[2]);
			}
		}
	}

}
