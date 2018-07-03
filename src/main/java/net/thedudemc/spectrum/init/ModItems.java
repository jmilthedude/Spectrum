package net.thedudemc.spectrum.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.thedudemc.spectrum.objects.items.ItemDyeingStation;
import net.thedudemc.spectrum.util.Reference;

public class ModItems 
{

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial TOOL_TEMPLATE = EnumHelper.addToolMaterial("tool_template", 2, 250, 6.0F, 2.0F, 14);
	public static final ArmorMaterial ARMOR_TEMPLATE = EnumHelper.addArmorMaterial("armor_template", Reference.MOD_ID + ":material", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

	//Items
	public static final Item ITEM_DYEING_STATION = new ItemDyeingStation("item_dyeing_station");
	
	//Tools
	
	//Armor
	
	//this is a test
	
}
