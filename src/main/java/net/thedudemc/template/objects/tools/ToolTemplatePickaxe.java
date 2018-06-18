package net.thedudemc.template.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.thedudemc.template.Template;
import net.thedudemc.template.init.ModItems;
import net.thedudemc.template.util.IHasModel;

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
			Template.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
