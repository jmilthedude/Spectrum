package net.thedudemc.spectrum.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.thedudemc.spectrum.block.BlockLiquidDye;
import net.thedudemc.spectrum.fluid.LiquidDyeType;

public class BlockColorTest implements IBlockColor {

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		switch (((BlockLiquidDye) state.getBlock()).getType()) {
		case BLUE:
			return LiquidDyeType.BLUE.toInt();
		case GREEN:
			return LiquidDyeType.GREEN.toInt();
		case RED:
			return LiquidDyeType.RED.toInt();
		default:
			return 0x000000;
		}
	}
}
