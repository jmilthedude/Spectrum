package net.thedudemc.spectrum.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

	public enum EnumDirection implements IStringSerializable {
		


		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return this.name;
		}

	}
	/*
	 * if (facing == EnumFacing.NORTH) { worldIn.setBlockState(pos.add(-1, 0, 0),
	 * ModBlocks.BLOCK_FRONT_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, 0),
	 * ModBlocks.BLOCK_FRONT_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 0, 0),
	 * ModBlocks.BLOCK_FRONT_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 0, -1),
	 * ModBlocks.BLOCK_BACK_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, -1),
	 * ModBlocks.BLOCK_BACK_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 0, -1),
	 * ModBlocks.BLOCK_BACK_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 1, -1),
	 * ModBlocks.BLOCK_TOP_LEFT.getDefaultState()); worldIn.setBlockState(pos.add(0,
	 * 1, -1), ModBlocks.BLOCK_TOP_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 1, -1),
	 * ModBlocks.BLOCK_TOP_RIGHT.getDefaultState());
	 * System.out.println("***Placed structure facing NORTH"); } else if (facing ==
	 * EnumFacing.EAST) { worldIn.setBlockState(pos.add(0, 0, -1),
	 * ModBlocks.BLOCK_FRONT_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, 0),
	 * ModBlocks.BLOCK_FRONT_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, 1),
	 * ModBlocks.BLOCK_FRONT_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 0, -1),
	 * ModBlocks.BLOCK_BACK_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 0, 0),
	 * ModBlocks.BLOCK_BACK_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 0, 1),
	 * ModBlocks.BLOCK_BACK_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 1, -1),
	 * ModBlocks.BLOCK_TOP_LEFT.getDefaultState()); worldIn.setBlockState(pos.add(1,
	 * 1, 0), ModBlocks.BLOCK_TOP_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 1, 1),
	 * ModBlocks.BLOCK_TOP_RIGHT.getDefaultState());
	 * System.out.println("***Placed structure facing EAST"); } else if (facing ==
	 * EnumFacing.SOUTH) { worldIn.setBlockState(pos.add(1, 0, 0),
	 * ModBlocks.BLOCK_FRONT_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, 0),
	 * ModBlocks.BLOCK_FRONT_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 0, 0),
	 * ModBlocks.BLOCK_FRONT_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 0, 1),
	 * ModBlocks.BLOCK_BACK_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, 1),
	 * ModBlocks.BLOCK_BACK_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 0, 1),
	 * ModBlocks.BLOCK_BACK_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 1, 1),
	 * ModBlocks.BLOCK_TOP_LEFT.getDefaultState()); worldIn.setBlockState(pos.add(0,
	 * 1, 1), ModBlocks.BLOCK_TOP_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(1, 1, 1),
	 * ModBlocks.BLOCK_TOP_RIGHT.getDefaultState());
	 * System.out.println("***Placed structure facing SOUTH"); } else if (facing ==
	 * EnumFacing.WEST) { worldIn.setBlockState(pos.add(0, 0, 1),
	 * ModBlocks.BLOCK_FRONT_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, 0),
	 * ModBlocks.BLOCK_FRONT_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(0, 0, -1),
	 * ModBlocks.BLOCK_FRONT_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 0, 1),
	 * ModBlocks.BLOCK_BACK_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 0, 0),
	 * ModBlocks.BLOCK_BACK_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 0, -1),
	 * ModBlocks.BLOCK_BACK_RIGHT.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 1, 1),
	 * ModBlocks.BLOCK_TOP_LEFT.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 1, 0),
	 * ModBlocks.BLOCK_TOP_CENTER.getDefaultState());
	 * worldIn.setBlockState(pos.add(-1, 1, -1),
	 * ModBlocks.BLOCK_TOP_RIGHT.getDefaultState());
	 * System.out.println("***Placed structure facing WEST"); } else {
	 * System.out.println("***CANNOT PLACE - OBSTRUCTION IN THE WAY***"); }
	 */
}
