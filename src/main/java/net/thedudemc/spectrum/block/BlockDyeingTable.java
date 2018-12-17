package net.thedudemc.spectrum.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.RegistryHandler;

public class BlockDyeingTable extends Block {

	public static final PropertyEnum<BlockDyeingTable.EnumSide> PART = PropertyEnum.<BlockDyeingTable.EnumSide>create("side", BlockDyeingTable.EnumSide.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockDyeingTable() {
		super(Material.WOOD, MapColor.BROWN);
		this.setRegistryName("dyeing_table");
		this.setUnlocalizedName(Constants.MODID + ".dyeing_table");
		this.setCreativeTab(Spectrum.SPECTRUM_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockDyeingTable.EnumSide.LEFT));
		this.setHardness(1.0f);

		RegistryHandler.BLOCKS_TO_REGISTER.add(this);

	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		// TODO Auto-generated method stub
		super.onNeighborChange(world, pos, neighbor);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();

		if (state.getValue(PART) == BlockDyeingTable.EnumSide.LEFT) {
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, PART });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
		return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, BlockDyeingTable.EnumSide.LEFT).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(PART, BlockDyeingTable.EnumSide.RIGHT).withProperty(FACING, enumfacing);
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(PART) == EnumSide.RIGHT ? ModItems.DYEING_TABLE : Items.AIR;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		BlockPos possibleLeft = pos.offset(state.getValue(FACING).rotateYCCW());
		BlockPos possibleRight = pos.offset(state.getValue(FACING).rotateY());
		if (worldIn.getBlockState(possibleLeft).getBlock() == this) {
			worldIn.setBlockToAir(possibleLeft);
		} else if (worldIn.getBlockState(possibleRight).getBlock() == this) {
			worldIn.destroyBlock(possibleRight, true);
		}
	}

	public static enum EnumSide implements IStringSerializable {
		LEFT("left"), RIGHT("right");

		private final String name;

		private EnumSide(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}
	}

}