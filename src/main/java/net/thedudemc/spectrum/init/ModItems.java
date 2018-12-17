package net.thedudemc.spectrum.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.item.ItemDyeingTable;

@ObjectHolder(Constants.MODID)
public class ModItems {

	public static Item DYEING_TABLE;

	public static void init() {
		DYEING_TABLE = new ItemDyeingTable();
	}

}
