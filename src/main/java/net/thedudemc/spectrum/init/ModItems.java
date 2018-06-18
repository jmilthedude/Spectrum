package net.thedudemc.spectrum.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.thedudemc.spectrum.objects.armor.ArmorBase;
import net.thedudemc.spectrum.objects.items.ItemBase;
import net.thedudemc.spectrum.objects.tools.ToolTemplateAxe;
import net.thedudemc.spectrum.objects.tools.ToolTemplateHoe;
import net.thedudemc.spectrum.objects.tools.ToolTemplatePickaxe;
import net.thedudemc.spectrum.objects.tools.ToolTemplateShovel;
import net.thedudemc.spectrum.objects.tools.ToolTemplateSword;
import net.thedudemc.spectrum.util.Reference;

public class ModItems 
{

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial TOOL_TEMPLATE = EnumHelper.addToolMaterial("tool_template", 2, 250, 6.0F, 2.0F, 14);
	public static final ArmorMaterial ARMOR_TEMPLATE = EnumHelper.addArmorMaterial("armor_template", Reference.MOD_ID + ":material", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

	//Items
	public static final Item TEMPLATE_ITEM = new ItemBase("template_item");
	
	//Tools
	public static final Item TEMPLATE_AXE = new ToolTemplateAxe("template_axe", TOOL_TEMPLATE);
	public static final Item TEMPLATE_HOE = new ToolTemplateHoe("template_hoe", TOOL_TEMPLATE);
	public static final Item TEMPLATE_PICKAXE = new ToolTemplatePickaxe("template_pickaxe", TOOL_TEMPLATE);
	public static final Item TEMPLATE_SHOVEL = new ToolTemplateShovel("template_shovel", TOOL_TEMPLATE);
	public static final Item TEMPLATE_SWORD = new ToolTemplateSword("template_sword", TOOL_TEMPLATE);
	
	//Armor
	public static final Item TEMPLATE_HELMET = new ArmorBase("template_helmet", ARMOR_TEMPLATE, 1, EntityEquipmentSlot.HEAD);
	public static final Item TEMPLATE_CHESTPLATE = new ArmorBase("template_chestplate", ARMOR_TEMPLATE, 1, EntityEquipmentSlot.CHEST);
	public static final Item TEMPLATE_LEGGINGS = new ArmorBase("template_leggings", ARMOR_TEMPLATE, 2, EntityEquipmentSlot.LEGS);
	public static final Item TEMPLATE_BOOTS = new ArmorBase("template_boots", ARMOR_TEMPLATE, 1, EntityEquipmentSlot.FEET);
	
	//this is a test
	
}
