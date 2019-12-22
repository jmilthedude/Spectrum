package net.thedudemc.spectrum.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.thedudemc.spectrum.init.InitBlock;

public class SpectrumTab extends CreativeTabs {

	public SpectrumTab(String label) {
		super("spectrumtab");
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(InitBlock.SPECTRUM_STONE);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(InitBlock.SPECTRUM_STONE);
	}

}
