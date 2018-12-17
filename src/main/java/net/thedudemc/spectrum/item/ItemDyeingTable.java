package net.thedudemc.spectrum.item;

import net.minecraft.block.SoundType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.block.BlockDyeingTable;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.util.RegistryHandler;

public class ItemDyeingTable extends Item {

	public ItemDyeingTable() {
		this.setRegistryName("item_dyeing_table");
		this.setUnlocalizedName(Constants.MODID + ".item_dyeing_table");
		this.setMaxStackSize(1);
		this.setCreativeTab(Spectrum.SPECTRUM_TAB);

		RegistryHandler.ITEMS_TO_REGISTER.add(this);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return EnumActionResult.SUCCESS;
		} else if (facing != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			EnumFacing enumFacing = player.getHorizontalFacing();
			ItemStack stack = player.getHeldItem(hand);
			boolean isBlockReplaceable = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
			if (!isBlockReplaceable) {
				pos = pos.up();
			}
			BlockPos side = pos.offset(enumFacing.rotateYCCW());
			boolean isSideReplaceable = worldIn.getBlockState(side).getBlock().isReplaceable(worldIn, side);
			boolean isSideAirBlock = worldIn.isAirBlock(side);
			boolean placeable = isSideReplaceable || isSideAirBlock;
			if (placeable) {
				worldIn.setBlockState(pos, ModBlocks.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, enumFacing).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumSide.RIGHT));
				worldIn.setBlockState(side, ModBlocks.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, enumFacing).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumSide.LEFT));
				worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, (SoundType.WOOD.getVolume() + 1.0F) / 2.0F, SoundType.WOOD.getPitch());
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.FAIL;
			}
		}
	}

}
