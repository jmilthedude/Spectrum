package net.thedudemc.spectrum.objects.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.thedudemc.spectrum.init.ModBlocks;

public class SpectrumTab extends CreativeTabs {

		public SpectrumTab(String label) {
			super("spectrumtab");
			// TODO Auto-generated constructor stub
		}

		@Override
		public ItemStack getTabIconItem() {
			// TODO Auto-generated method stub
			return new ItemStack(ModBlocks.BLOCK_MINI_FULL);
			}


		

	}

	

