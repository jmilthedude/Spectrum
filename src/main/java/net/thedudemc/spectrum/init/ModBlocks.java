package net.thedudemc.spectrum.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.block.BlockDyeingTable;

@ObjectHolder(Constants.MODID)
public class ModBlocks {

	public static Block DYEING_TABLE;

	public static void init() {
		DYEING_TABLE = new BlockDyeingTable();
	}

}
