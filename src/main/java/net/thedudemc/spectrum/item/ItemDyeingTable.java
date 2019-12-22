package net.thedudemc.spectrum.item;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.block.BlockDyeingTable;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;
import net.thedudemc.spectrum.init.InitBlock;
import net.thedudemc.spectrum.tileentity.Canister;
import net.thedudemc.spectrum.util.NBTUtility;

public class ItemDyeingTable extends Item {

	public ItemDyeingTable() {
		this.setMaxStackSize(1);
		this.setCreativeTab(Spectrum.SPECTRUM_TAB);

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
			BlockPos upLeft = side.up();
			boolean isSideReplaceable = worldIn.getBlockState(side).getBlock().isReplaceable(worldIn, side) || worldIn.isAirBlock(side);
			boolean isUpLeftReplaceable = worldIn.getBlockState(upLeft).getBlock().isReplaceable(worldIn, upLeft) || worldIn.isAirBlock(upLeft);
			boolean placeable = isSideReplaceable && isUpLeftReplaceable;
			if (placeable) {
				worldIn.setBlockState(pos, InitBlock.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, enumFacing).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumPart.ITEM_OUT));
				worldIn.setBlockState(side, InitBlock.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, enumFacing).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumPart.FLUID_IN));
				worldIn.setBlockState(upLeft, InitBlock.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, enumFacing).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumPart.CONTROLLER));
				worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, (SoundType.METAL.getVolume() + 1.0F) / 2.0F, SoundType.WOOD.getPitch());

				TileDyeingTableController te = (TileDyeingTableController) worldIn.getTileEntity(upLeft);
				te.restoreTileEntity(stack, this);
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.FAIL;
			}
		}
	}

	public static final int MAX_DYE = Canister.MAX_AMOUNT;
	public static final int MAX_WATER = 10000;

	public int getAmount(ItemStack stack, String tag) {
		return NBTUtility.getInt(stack, tag, 0);
	}

	public void setAmount(ItemStack stack, String tag, int amount) {
		NBTUtility.setInt(stack, tag, amount);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		int[] colors = NBTUtility.getIntArray(stack, NBTUtility.RGB_TAG);
		int redAmount = (int) (((double) colors[0] / MAX_DYE) * 100);
		int greenAmount = (int) (((double) colors[1] / MAX_DYE) * 100);
		int blueAmount = (int) (((double) colors[2] / MAX_DYE) * 100);
		int waterAmount = (int) (((double) stack.getTagCompound().getCompoundTag("tank").getInteger("Amount")) / 1000);

		String red = "Red Dye: " + TextFormatting.RED + redAmount + TextFormatting.RESET + "%";
		String green = "Green Dye: " + TextFormatting.GREEN + greenAmount + TextFormatting.RESET + "%";
		String blue = "Blue Dye: " + TextFormatting.BLUE + blueAmount + TextFormatting.RESET + "%";
		String water = "Water: " + TextFormatting.GRAY + waterAmount + TextFormatting.RESET + " Buckets";

		tooltip.add(red);
		tooltip.add(green);
		tooltip.add(blue);
		tooltip.add(water);
	}

}
