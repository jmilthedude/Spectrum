package net.thedudemc.spectrum.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.IHasModel;

public class ToolTemplateHoe extends ItemHoe implements IHasModel {

	public ToolTemplateHoe(String name, ToolMaterial material) {
		super(material);

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
