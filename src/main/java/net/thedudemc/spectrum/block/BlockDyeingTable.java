package net.thedudemc.spectrum.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.tileentity.TileDyeingTableController;
import net.thedudemc.spectrum.tileentity.TileDyeingTableFluidInput;

@SuppressWarnings(value = { "deprecation" })
public class BlockDyeingTable extends Block implements ITileEntityProvider {

	public static final PropertyEnum<BlockDyeingTable.EnumPart> PART = PropertyEnum.<BlockDyeingTable.EnumPart>create("part", BlockDyeingTable.EnumPart.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB CONTROLLER_AABB_NORTH = new AxisAlignedBB(0, 0, 0.3125D, 1, 1, 0);
	public static final AxisAlignedBB CONTROLLER_AABB_EAST = new AxisAlignedBB(1, 1, 1, 0.6875D, 0, 0);
	public static final AxisAlignedBB CONTROLLER_AABB_WEST = new AxisAlignedBB(0, 0, 0, 0.3125D, 1, 1);
	public static final AxisAlignedBB CONTROLLER_AABB_SOUTH = new AxisAlignedBB(1, 1, 0.6875D, 0, 0, 1);

	public BlockDyeingTable() {
		super(Material.WOOD, MapColor.BROWN);
		this.setCreativeTab(Spectrum.SPECTRUM_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockDyeingTable.EnumPart.FLUID_IN));
		this.setHardness(1.0f);

	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(PART) == EnumPart.CONTROLLER) {
			switch (state.getValue(FACING)) {
			case EAST:
				return CONTROLLER_AABB_EAST;
			case NORTH:
				return CONTROLLER_AABB_NORTH;
			case SOUTH:
				return CONTROLLER_AABB_SOUTH;
			case WEST:
				return CONTROLLER_AABB_WEST;
			default:
				break;
			}
		}
		return super.getBoundingBox(state, source, pos);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
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
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(PART).ordinal() << 2) | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(PART, EnumPart.values()[meta >> 2]).withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 0b11));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, PART });
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (!world.isRemote) {
			BlockPos controller = getControllerPos(world, pos);
			TileDyeingTableController te = (TileDyeingTableController) world.getTileEntity(controller);
			ItemStack stack = te.getTableItemStack();
			NonNullList<ItemStack> dyeDrops = te.getTableDrops();
			for (ItemStack is : dyeDrops) {
				world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), is));
			}
			world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
			world.removeTileEntity(controller);
			world.setBlockToAir(controller);
			world.setBlockToAir(controller.down());
			world.setBlockToAir(controller.down().offset(state.getValue(FACING).rotateY()));
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		IBlockState state = this.getDefaultState().withProperty(PART, EnumPart.values()[meta >> 2]).withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 0b11));
		switch (state.getValue(PART)) {
		case CONTROLLER:
			return new TileDyeingTableController();
		case FLUID_IN:
			return new TileDyeingTableFluidInput();
		case ITEM_OUT:
			return null;
		default:
			return null;

		}
	}

	public static enum EnumPart implements IStringSerializable {
		FLUID_IN("fluid"), ITEM_OUT("item"), CONTROLLER("controller");

		private final String name;

		private EnumPart(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (state.getValue(PART) == EnumPart.FLUID_IN) {
				Item item = playerIn.getHeldItem(hand).getItem();
				if (item instanceof ItemBucket) {
					FluidUtil.interactWithFluidHandler(playerIn, hand, worldIn.getTileEntity(getControllerPos(worldIn, pos)).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
					worldIn.notifyBlockUpdate(pos, state, state, 3);
					return true;
				}
			}
			if (state.getValue(PART) == EnumPart.CONTROLLER) {
				playerIn.openGui(Spectrum.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
				return true;
			} else {
				BlockPos controllerPos = getControllerPos(worldIn, pos);
				IBlockState controllerState = worldIn.getBlockState(controllerPos);
				return controllerState.getBlock().onBlockActivated(worldIn, controllerPos, controllerState, playerIn, hand, facing, hitX, hitY, hitZ);
			}
		}
		return true;
	}

	@Nullable
	public static BlockPos getControllerPos(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		EnumFacing facing = state.getValue(FACING);
		switch (state.getValue(PART)) {
		case CONTROLLER:
			return pos;
		case FLUID_IN:
			return pos.up();
		case ITEM_OUT:
			return pos.offset(facing.rotateYCCW()).up();
		default:
			return null;
		}
	}

}