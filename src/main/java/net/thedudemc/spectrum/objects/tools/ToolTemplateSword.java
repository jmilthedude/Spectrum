package net.thedudemc.spectrum.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.IHasModel;

public class ToolTemplateSword extends ItemSword implements IHasModel {

	public ToolTemplateSword(String name, ToolMaterial material) {
		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		
		ModItems.ITEMS.add(this);
	}
		
		@Override
		public void registerModels() {
			Spectrum.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
