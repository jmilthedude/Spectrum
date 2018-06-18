package net.thedudemc.template.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedudemc.template.objects.blocks.BlockBase;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block TEMPLATE_BLOCK = new BlockBase("template_block", Material.IRON);
	
}
