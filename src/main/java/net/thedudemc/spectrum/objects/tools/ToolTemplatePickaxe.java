package net.thedudemc.spectrum.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.IHasModel;

public class ToolTemplatePickaxe extends ItemPickaxe implements IHasModel {

	public ToolTemplatePickaxe(String name, ToolMaterial material) {
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
