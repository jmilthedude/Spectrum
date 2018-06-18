package net.thedudemc.spectrum.objects.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.IHasModel;

public class ArmorBase extends ItemArmor implements IHasModel {

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Spectrum.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}