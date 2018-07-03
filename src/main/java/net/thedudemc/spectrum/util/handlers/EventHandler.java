package net.thedudemc.spectrum.util.handlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EventHandler {

	// offset directions: (facing) {{{UP=0}, {DOWN=1}, {SOUTH=2}, {NORTH=3},
	// {EAST=4}, {WEST=5}}}
	public static final int[][][] frontLeftOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, -1 }, { 0, 0, 1 } } };
	public static final int[][][] frontRightOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { -1, 0, 0 }, { 1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 } } };
	public static final int[][][] backLeftOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 1, 0, 1 }, { -1, 0, -1 }, { 1, 0, -1 }, { -1, 0, 1 } } };
	public static final int[][][] backCenterOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 0, 0 }, { -1, 0, 0 } } };
	public static final int[][][] backRightOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { -1, 0, 1 }, { 1, 0, -1 }, { 1, 0, 1 }, { -1, 0, -1 } } };
	public static final int[][][] topLeftOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 1, 1, 1 }, { -1, 1, -1 }, { 1, 1, -1 }, { -1, 1, 1 } } };
	public static final int[][][] topCenterOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 1, -1 }, { 1, 1, 0 }, { -1, 1, 0 } } };
	public static final int[][][] topRightOffset = new int[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { -1, 1, 1 }, { 1, 1, -1 }, { 1, 1, 1 }, { -1, 1, -1 } } };
	/*
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static boolean isMiniFull(PlaceEvent event) {
		if (event.getPlacedBlock() == ModBlocks.TABLE_SINGLE.getDefaultState()) {
			return true;
		} else {
			event.setCanceled(true);
		}
		return false;
	}

	public static void onMiniPlaced(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack, EnumFacing facing) {

		if (facing == EnumFacing.NORTH) {
			worldIn.setBlockState(pos.add(-1, 0, 0), ModBlocks.TABLE_FRONT_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 0), ModBlocks.TABLE_FRONT_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, 0), ModBlocks.TABLE_FRONT_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 0, -1), ModBlocks.TABLE_BACK_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, -1), ModBlocks.TABLE_BACK_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, -1), ModBlocks.TABLE_BACK_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(1, 1, -1), ModBlocks.TABLE_TOP_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 1, -1), ModBlocks.TABLE_TOP_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 1, -1), ModBlocks.TABLE_TOP_RIGHT.getDefaultState());
			System.out.println("***Placed structure facing NORTH");
		} else if (facing == EnumFacing.EAST) {
			worldIn.setBlockState(pos.add(0, 0, -1), ModBlocks.TABLE_FRONT_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 0), ModBlocks.TABLE_FRONT_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 1), ModBlocks.TABLE_FRONT_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, -1), ModBlocks.TABLE_BACK_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, 0), ModBlocks.TABLE_BACK_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, 1), ModBlocks.TABLE_BACK_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(1, 1, -1), ModBlocks.TABLE_TOP_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(1, 1, 0), ModBlocks.TABLE_TOP_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(1, 1, 1), ModBlocks.TABLE_TOP_RIGHT.getDefaultState());
			System.out.println("***Placed structure facing EAST");
		} else if (facing == EnumFacing.SOUTH) {
			worldIn.setBlockState(pos.add(1, 0, 0), ModBlocks.TABLE_FRONT_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 0), ModBlocks.TABLE_FRONT_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 0, 0), ModBlocks.TABLE_FRONT_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, 1), ModBlocks.TABLE_BACK_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 1), ModBlocks.TABLE_BACK_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 0, 1), ModBlocks.TABLE_BACK_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 1, 1), ModBlocks.TABLE_TOP_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 1, 1), ModBlocks.TABLE_TOP_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(1, 1, 1), ModBlocks.TABLE_TOP_RIGHT.getDefaultState());
			System.out.println("***Placed structure facing SOUTH");
		} else if (facing == EnumFacing.WEST) {
			worldIn.setBlockState(pos.add(0, 0, 1), ModBlocks.TABLE_FRONT_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 0), ModBlocks.TABLE_FRONT_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, -1), ModBlocks.TABLE_FRONT_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 0, 1), ModBlocks.TABLE_BACK_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 0, 0), ModBlocks.TABLE_BACK_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 0, -1), ModBlocks.TABLE_BACK_RIGHT.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 1, 1), ModBlocks.TABLE_TOP_LEFT.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 1, 0), ModBlocks.TABLE_TOP_CENTER.getDefaultState());
			worldIn.setBlockState(pos.add(-1, 1, -1), ModBlocks.TABLE_TOP_RIGHT.getDefaultState());
			System.out.println("***Placed structure facing WEST");
		} else {
			System.out.println("***CANNOT PLACE - OBSTRUCTION IN THE WAY***");
		}
	}
*/
	public static boolean checkAirSurrounding(World worldIn, BlockPos pos, EntityLivingBase placer, EnumFacing facing) {
		BlockPos frontCenter = new BlockPos(pos);
		BlockPos frontRight = pos.offset(facing.rotateY());
		BlockPos frontLeft = pos.offset(facing.rotateYCCW());
		BlockPos backCenter = pos.offset(facing);
		BlockPos backRight = pos.offset(facing).offset(facing.rotateY());
		BlockPos backLeft = pos.offset(facing).offset(facing.rotateYCCW());
		
		if (worldIn.isAirBlock(frontCenter) && worldIn.isAirBlock(frontRight) && worldIn.isAirBlock(frontLeft) && worldIn.isAirBlock(backCenter) && worldIn.isAirBlock(backRight) && worldIn.isAirBlock(backLeft)) {
			System.out.println("There are all air blocks where needed!");
			return true;
		} else {
			return false;
		}

		
		}
/*		if (facing == EnumFacing.NORTH) {
			System.out.println("***" + placer + " is facing " + facing + "***");

			if (worldIn.isAirBlock(pos.add(-1, 0, 0)) && worldIn.isAirBlock(pos.add(1, 0, 0)) && worldIn.isAirBlock(pos.add(-1, 0, -1)) && worldIn.isAirBlock(pos.add(0, 0, -1)) && worldIn.isAirBlock(pos.add(1, 0, -1))
					&& worldIn.isAirBlock(pos.add(1, 1, -1)) && worldIn.isAirBlock(pos.add(0, 1, -1)) && worldIn.isAirBlock(pos.add(-1, 1, -1))) {
				System.out.println("***all affected blocks are air blocks***");
				return true;
			} else {
				System.out.println("***CANNOT PLACE - OBSTRUCTION IN THE WAY***");
				return false;
			}
		} else if (facing == EnumFacing.EAST) {
			System.out.println("***" + placer + " is facing " + facing + "***");
			if (worldIn.isAirBlock(pos.add(0, 0, -1)) && worldIn.isAirBlock(pos.add(0, 0, 1)) && worldIn.isAirBlock(pos.add(1, 0, -1)) && worldIn.isAirBlock(pos.add(1, 0, 0)) && worldIn.isAirBlock(pos.add(1, 0, 1))
					&& worldIn.isAirBlock(pos.add(1, 1, -1)) && worldIn.isAirBlock(pos.add(1, 1, 0)) && worldIn.isAirBlock(pos.add(1, 1, 1))) {
				System.out.println("***all affected blocks are air blocks***");
				return true;
			} else {
				System.out.println("***CANNOT PLACE - OBSTRUCTION IN THE WAY***");
				return false;
			}
		} else if (facing == EnumFacing.SOUTH) {
			System.out.println("***" + placer + " is facing " + facing + "***");
			if (worldIn.isAirBlock(pos.add(1, 0, 0)) && worldIn.isAirBlock(pos.add(-1, 0, 0)) && worldIn.isAirBlock(pos.add(1, 0, 1)) && worldIn.isAirBlock(pos.add(0, 0, 1)) && worldIn.isAirBlock(pos.add(-1, 0, 1))
					&& worldIn.isAirBlock(pos.add(-1, 1, 1)) && worldIn.isAirBlock(pos.add(0, 1, 1)) && worldIn.isAirBlock(pos.add(1, 1, 1))) {
				System.out.println("***all affected blocks are air blocks***");
				return true;
			} else {
				System.out.println("***CANNOT PLACE - OBSTRUCTION IN THE WAY***");
				return false;
			}
		} else if (facing == EnumFacing.WEST) {
			System.out.println("***" + placer + " is facing " + facing + "***");
			if (worldIn.isAirBlock(pos.add(0, 0, 1)) && worldIn.isAirBlock(pos.add(0, 0, -1)) && worldIn.isAirBlock(pos.add(-1, 0, 1)) && worldIn.isAirBlock(pos.add(-1, 0, 0)) && worldIn.isAirBlock(pos.add(-1, 0, -1))
					&& worldIn.isAirBlock(pos.add(-1, 1, 1)) && worldIn.isAirBlock(pos.add(-1, 1, 0)) && worldIn.isAirBlock(pos.add(-1, 1, -1))) {
				System.out.println("***all affected blocks are air blocks***");
				return true;
			} else {
				System.out.println("***CANNOT PLACE - OBSTRUCTION IN THE WAY***");
				return false;
			}
		}

		System.out.println("***PLAYER NOT FACING ANY DIRECTION... HOW IS THAT POSSIBLE???***");
		return false;
	}
*/
}
