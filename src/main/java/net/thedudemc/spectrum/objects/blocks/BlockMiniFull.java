package net.thedudemc.spectrum.objects.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.IHasModel;

public class BlockMiniFull extends BlockHorizontal implements IHasModel {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum<BlockMiniFull.EnumPartType> PART = PropertyEnum.<BlockMiniFull.EnumPartType>create("part", BlockMiniFull.EnumPartType.class);
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

	public BlockMiniFull(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Spectrum.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

	}


	public static EnumPartType getPartType(IBlockState state) {
		return state.getValue(PART);
	}

	public static enum EnumPartType implements IStringSerializable {
		FRONT_LEFT("front_left"), FRONT_CENTER("front_center"), FRONT_RIGHT("front_right"), BACK_LEFT("back_left"), BACK_CENTER("back_center"), BACK_RIGHT("back_right"), TOP_LEFT("top_left"), TOP_CENTER("top_center"), TOP_RIGHT("top_right");

		private final String name;

		private EnumPartType(String name) {
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
