package net.thedudemc.spectrum.client;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.block.entity.TileDyeable;
import net.thedudemc.spectrum.init.InitBlock;
import net.thedudemc.spectrum.util.NBTUtility;

@SideOnly(Side.CLIENT)
public class ColorHandler implements IBlockColor, IItemColor {

	public static void initBlockColorHandler() {
		IBlockColor blockColorHandler = new ColorHandler();
		IItemColor itemColorHandler = new ColorHandler();
		for (Block block : InitBlock.BLOCKS) {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(blockColorHandler, block);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, block);
		}
	}

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
