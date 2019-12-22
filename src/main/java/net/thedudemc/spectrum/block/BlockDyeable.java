package net.thedudemc.spectrum.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.block.entity.TileDyeable;
import net.thedudemc.spectrum.util.NBTUtility;

public class BlockDyeable extends Block implements ITileEntityProvider {

	private SoundType soundType;

	public BlockDyeable(Material materialIn, SoundType soundType) {
		super(materialIn);
		this.setCreativeTab(Spectrum.SPECTRUM_TAB);
		this.soundType = soundType;
	}

	@Override
	public SoundType getSoundType() {
		return this.soundType;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDyeable();
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote) {
			TileDyeable te = (TileDyeable) world.getTileEntity(pos);
			if (!player.capabilities.isCreativeMode && te != null) {
				ItemStack drop = new ItemStack(state.getBlock());
				NBTTagCompound compound = new NBTTagCompound();
				compound.setIntArray(NBTUtility.RGB_TAG, te.getColors());
				drop.setTagCompound(compound);
				world.spawnEntity(new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, drop));
			}
		}
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TileDyeable te = (TileDyeable) world.getTileEntity(target.getBlockPos());
		if (te != null) {
			int[] colors = te.getColors();
			ItemStack stack = new ItemStack(te.getBlockType());
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setIntArray(NBTUtility.RGB_TAG, colors);
			stack.setTagCompound(nbt);
			return stack;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileDyeable te = (TileDyeable) worldIn.getTileEntity(pos);
		if (te != null) {
			if (stack.hasTagCompound()) {
				if (stack.getTagCompound().hasKey(NBTUtility.RGB_TAG)) {
					int[] colors = stack.getTagCompound().getIntArray(NBTUtility.RGB_TAG);
					te.setRGB(colors);
				}
			}
		}
	}

}
