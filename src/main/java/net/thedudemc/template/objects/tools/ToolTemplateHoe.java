package net.thedudemc.template.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.thedudemc.template.Template;
import net.thedudemc.template.init.ModItems;
import net.thedudemc.template.util.IHasModel;

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
			Template.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
