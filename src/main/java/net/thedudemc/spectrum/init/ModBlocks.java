package net.thedudemc.spectrum.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedudemc.spectrum.objects.blocks.BlockTable;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block TABLE_FRONT_LEFT = new BlockTable("table_front_left", Material.WOOD);
	public static final Block TABLE_FRONT_CENTER = new BlockTable("table_front_center", Material.WOOD);
	public static final Block TABLE_FRONT_RIGHT = new BlockTable("table_front_right", Material.WOOD);
	public static final Block TABLE_BACK_LEFT = new BlockTable("table_back_left", Material.WOOD);
	public static final Block TABLE_BACK_CENTER = new BlockTable("table_back_center", Material.WOOD);
	public static final Block TABLE_BACK_RIGHT = new BlockTable("table_back_right", Material.WOOD);
//	public static final Block TABLE_TOP_LEFT = new BlockTable("table_top_left", Material.ROCK);
//	public static final Block TABLE_TOP_CENTER = new BlockTable("table_top_center", Material.ROCK);
//	public static final Block TABLE_TOP_RIGHT = new BlockTable("table_top_right", Material.ROCK);
	
	public static final Block TABLE_SINGLE = new BlockTable("table_single", Material.WOOD);
	
}
