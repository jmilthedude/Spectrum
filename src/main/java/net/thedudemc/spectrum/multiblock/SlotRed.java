package net.thedudemc.spectrum.multiblock;

import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotRed extends SlotItemHandler {

	public SlotRed(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof ItemDye)
			if (stack.getMetadata() == 1)
				return true;
		return false;
	}

	@Override
	public void onSlotChanged() {

		super.onSlotChanged();
	}

	@Override
	public ItemStack decrStackSize(int amount) {
		// TODO Auto-generated method stub
		return super.decrStackSize(amount);
	}

}
