package net.thedudemc.spectrum.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;
import net.thedudemc.spectrum.container.slot.SlotDyeingTable;
import net.thedudemc.spectrum.util.EnumSpectrumDye;

public class ContainerDyeingTable extends Container {

	private TileDyeingTableController te;
	private static final int WATER_ID = 3;
	private static final int PROGRESS = 4;

	public ContainerDyeingTable(IInventory playerInventory, TileDyeingTableController te) {
		this.te = te;
		addDyeSlots();
		addInputSlot();
		addOutputSlot();
		addPlayerSlots(playerInventory);
	}

	private void addPlayerSlots(IInventory playerInventory) {
		// Slots for the main inventory
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 8 + col * 18;
				int y = row * 18 + 77;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
			}
		}

		// Slots for the hotbar
		for (int row = 0; row < 9; ++row) {
			int x = 8 + row * 18;
			int y = 58 + 77;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
	}

	private void addDyeSlots() {
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int x = 8;
		int y = 8;

		// Add our own slots
		addSlotToContainer(new SlotDyeingTable(itemHandler, 0, x, y, EnumDyeColor.RED));
		x += 18;
		addSlotToContainer(new SlotDyeingTable(itemHandler, 1, x, y, EnumDyeColor.GREEN));
		x += 18;
		addSlotToContainer(new SlotDyeingTable(itemHandler, 2, x, y, EnumDyeColor.BLUE));
	}

	private void addInputSlot() {
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int x = 98;
		int y = 8;

		addSlotToContainer(new SlotItemHandler(itemHandler, 3, x, y));

	}

	private void addOutputSlot() {
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int x = 134 + 18;
		int y = 8;

		addSlotToContainer(new SlotItemHandler(itemHandler, 4, x, y));

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < TileDyeingTableController.SIZE) {
				if (!this.mergeItemStack(itemstack1, TileDyeingTableController.SIZE, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, TileDyeingTableController.SIZE, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public NonNullList<ItemStack> getInventory() {
		return super.getInventory();
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		int[] amounts = te.getDyeAmount();
		for (IContainerListener listener : listeners) {
			listener.sendWindowProperty(this, EnumSpectrumDye.RED.getInt(), amounts[0]);
			listener.sendWindowProperty(this, EnumSpectrumDye.GREEN.getInt(), amounts[1]);
			listener.sendWindowProperty(this, EnumSpectrumDye.BLUE.getInt(), amounts[2]);

			listener.sendWindowProperty(this, WATER_ID, te.getTank().getFluidAmount());
			listener.sendWindowProperty(this, PROGRESS, te.getProgress());
		}
	}

	@Override
	public void updateProgressBar(int id, int data) {
		if (id == EnumSpectrumDye.RED.getInt())
			te.setDyeAmount(EnumSpectrumDye.RED, data);
		if (id == EnumSpectrumDye.GREEN.getInt())
			te.setDyeAmount(EnumSpectrumDye.GREEN, data);
		if (id == EnumSpectrumDye.BLUE.getInt())
			te.setDyeAmount(EnumSpectrumDye.BLUE, data);
		if (id == WATER_ID)
			te.getTank().setFluid(new FluidStack(FluidRegistry.WATER, data));
		else if (id == PROGRESS) {
			te.setClientProgress(data);
		}
	}
}
