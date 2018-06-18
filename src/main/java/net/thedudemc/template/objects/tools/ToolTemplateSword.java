package net.thedudemc.template.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;
import net.thedudemc.template.Template;
import net.thedudemc.template.init.ModItems;
import net.thedudemc.template.util.IHasModel;

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
			Template.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
