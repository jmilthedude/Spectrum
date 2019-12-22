package net.thedudemc.spectrum.container.slot;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotDyeingTable extends SlotItemHandler {

	private EnumDyeColor color;

	public SlotDyeingTable(IItemHandler itemHandler, int index, int xPosition, int yPosition, EnumDyeColor dye) {
		super(itemHandler, index, xPosition, yPosition);
		this.color = dye;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof ItemDye) {
			EnumDyeColor color = EnumDyeColor.byDyeDamage(((ItemDye) stack.getItem()).getDamage(stack));
			if (this.color.equals(color)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
	}

	@Override
	public ItemStack decrStackSize(int amount) {
		return super.decrStackSize(amount);
	}

}
