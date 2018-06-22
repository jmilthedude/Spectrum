package net.thedudemc.spectrum.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedudemc.spectrum.objects.blocks.BlockBase;
import net.thedudemc.spectrum.objects.blocks.BlockMiniFull;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_FRONT_LEFT = new BlockBase("block_front_left", Material.ROCK);
	public static final Block BLOCK_FRONT_CENTER = new BlockBase("block_front_center", Material.ROCK);
	public static final Block BLOCK_FRONT_RIGHT = new BlockBase("block_front_right", Material.ROCK);
	public static final Block BLOCK_BACK_LEFT = new BlockBase("block_back_left", Material.ROCK);
	public static final Block BLOCK_BACK_CENTER = new BlockBase("block_back_center", Material.ROCK);
	public static final Block BLOCK_BACK_RIGHT = new BlockBase("block_back_right", Material.ROCK);
	public static final Block BLOCK_TOP_LEFT = new BlockBase("block_top_left", Material.ROCK);
	public static final Block BLOCK_TOP_CENTER = new BlockBase("block_top_center", Material.ROCK);
	public static final Block BLOCK_TOP_RIGHT = new BlockBase("block_top_right", Material.ROCK);
	
	public static final Block BLOCK_MINI_FULL = new BlockMiniFull("block_mini_full", Material.ROCK);
	
}
