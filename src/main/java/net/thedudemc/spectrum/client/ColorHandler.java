package net.thedudemc.spectrum.client;

import java.awt.Color;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.common.tileentity.TileDyeable;
import net.thedudemc.spectrum.common.util.NBTUtility;

@SideOnly(Side.CLIENT)
public class ColorHandler implements IBlockColor, IItemColor {

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().hasKey(NBTUtility.RGB_TAG)) {
				int[] colors = stack.getTagCompound().getIntArray(NBTUtility.RGB_TAG);
				if (colors[0] >= 0) {
					Color color = new Color(colors[0], colors[1], colors[2]);
					return color.getRGB();
				}
			}
		}
		return 0xffffff;
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		TileDyeable te = (TileDyeable) worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileDyeable) {
			int[] colors = te.getColors();
			if (colors[0] >= 0 || colors[1] >= 0 || colors[2] >= 0) {
				Color color = new Color(colors[0], colors[1], colors[2]);
				return color.getRGB();
			}
		}

		return 0xffffff;
	}

}
