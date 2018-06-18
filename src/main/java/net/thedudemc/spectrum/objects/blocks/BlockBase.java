package net.thedudemc.spectrum.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.IHasModel;

public class BlockBase extends Block implements IHasModel {

	public BlockBase(String name,  Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Spectrum.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}
	
}
