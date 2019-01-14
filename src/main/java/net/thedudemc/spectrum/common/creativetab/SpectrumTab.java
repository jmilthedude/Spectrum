package net.thedudemc.spectrum.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.thedudemc.spectrum.common.init.SpectrumBlocks;

public class SpectrumTab extends CreativeTabs {

	public SpectrumTab(String label) {
		super("spectrumtab");
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(SpectrumBlocks.SPECTRUM_STONE);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(SpectrumBlocks.SPECTRUM_STONE);
	}

}
