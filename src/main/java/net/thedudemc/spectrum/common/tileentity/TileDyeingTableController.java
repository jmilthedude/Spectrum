package net.thedudemc.spectrum.common.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thedudemc.spectrum.common.config.Config;
import net.thedudemc.spectrum.common.init.SpectrumItems;
import net.thedudemc.spectrum.common.item.ItemDyeingTable;
import net.thedudemc.spectrum.common.util.SpectrumUtils;
import net.thedudemc.spectrum.common.util.EnumSpectrumDye;
import net.thedudemc.spectrum.common.util.NBTUtility;
import scala.actors.threadpool.Arrays;

public class TileDyeingTableController extends TileEntity implements ITickable {

	public static final int SIZE = 5;
	public static final int RED_SLOT = 0;
	public static final int GREEN_SLOT = 1;
	public static final int BLUE_SLOT = 2;
	public static final int INPUT_SLOT = 3;
	public static final int OUTPUT_SLOT = 4;
	public static final int MAX_FLUID = 10000;
	public static final int WATER_COST = Config.waterCostPerDye;
	public static final int UNIT_PER_DYE = Config.unitPerDyeItem;

	private boolean doDye = false;
	private boolean doClean = false;
	private NBTTagCompound clientColor = new NBTTagCompound();
	private BlockPos clientPos = null;

	private int amountToDye = 0;
	private boolean hasSetAmount = false;

	private int progress = 0;

	private Canister canisterRed = new Canister(EnumDyeColor.RED);
	private Canister canisterGreen = new Canister(EnumDyeColor.GREEN);
	private Canister canisterBlue = new Canister(EnumDyeColor.BLUE);

	private FluidTank tank = new FluidTank(MAX_FLUID) {
		@Override
		protected void onContentsChanged() {
			IBlockState state = world.getBlockState(pos);
			IBlockState fluidState = world.getBlockState(pos.down());
			world.notifyBlockUpdate(pos, state, state, 3);
			world.notifyBlockUpdate(pos.down(), fluidState, fluidState, 3);
			markDirty();
		}

		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == FluidRegistry.WATER;
		}
	};

	private ItemStackHandler itemHandler = new ItemStackHandler(SIZE) {

		@Override
		protected void onContentsChanged(int slot) {
			if (!doDye) {
				TileDyeingTableController.this.progress = 0;
			}
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			TileDyeingTableController.this.markDirty();
		}

		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			if (slot == INPUT_SLOT && SpectrumUtils.isDyeable(stack)) {
				return true;
			} else if (slot == OUTPUT_SLOT) {
				return false;
			} else if (slot == INPUT_SLOT) {
				return false;
			} else {
				return true;
			}
		}
	};

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbtTag = super.getUpdateTag().copy();
		NBTTagCompound tankNBT = new NBTTagCompound();
		tank.writeToNBT(tankNBT);
		nbtTag.setTag("tank", tankNBT);
		nbtTag.setIntArray(NBTUtility.RGB_TAG, getDyeAmount());
		nbtTag.setTag("items", itemHandler.serializeNBT());
		return nbtTag;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		tank.readFromNBT(tag.getCompoundTag("tank"));
		setDyeAmount(tag.getIntArray(NBTUtility.RGB_TAG));
		itemHandler.deserializeNBT(tag.getCompoundTag("items"));
		super.handleUpdateTag(tag);
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 1, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		tank.readFromNBT(packet.getNbtCompound().getCompoundTag("tank"));
		setDyeAmount(packet.getNbtCompound().getIntArray(NBTUtility.RGB_TAG));
		itemHandler.deserializeNBT(packet.getNbtCompound().getCompoundTag("items"));
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			addDye();
			if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
				hasSetAmount = false;
			}
			if (doDye) {
				if (!hasSetAmount) {
					amountToDye = itemHandler.getStackInSlot(INPUT_SLOT).getCount();
					hasSetAmount = true;
				}
				if (!hasRequiredMaterials() || itemHandler.getStackInSlot(INPUT_SLOT).isEmpty()) {
					doDye = false;
					return;
				} else {
					setProgress();
					dyeBlocks();
					consumeDye();
					world.notifyBlockUpdate(this.getPos(), world.getBlockState(pos), world.getBlockState(pos), 3);
				}
			}
			if (doClean) {
				ItemStack stackIn = itemHandler.getStackInSlot(INPUT_SLOT);
				ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);
				int amount = stackIn.getCount();
				int waterCost = SpectrumUtils.getWaterDecrementForClean(amount);
				if (!stackIn.isEmpty() && SpectrumUtils.isCleanable(stackIn) && output.isEmpty() && waterCost <= tank.getFluidAmount()) {
					itemHandler.extractItem(INPUT_SLOT, amount, false);
					ItemStack stackOut = SpectrumUtils.getOutput(stackIn, doClean);
					stackOut.setCount(amount);
					itemHandler.insertItem(OUTPUT_SLOT, stackOut, false);
					tank.drain(waterCost, true);
				}
				doClean = false;
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("items")) {
			itemHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
		}
		if (compound.hasKey("tank")) {
			tank.readFromNBT(compound.getCompoundTag("tank"));
		}
		if (compound.hasKey(NBTUtility.RGB_TAG)) {
			int[] colors = compound.getIntArray(NBTUtility.RGB_TAG);
			canisterRed.setCurrentAmount(colors[0]);
			canisterGreen.setCurrentAmount(colors[1]);
			canisterBlue.setCurrentAmount(colors[2]);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagCompound tankNBT = new NBTTagCompound();
		compound.setTag("items", itemHandler.serializeNBT());
		tank.writeToNBT(tankNBT);
		compound.setTag("tank", tankNBT);
		int[] colors = new int[] { canisterRed.getCurrentAmount(), canisterGreen.getCurrentAmount(), canisterBlue.getCurrentAmount() };
		compound.setIntArray(NBTUtility.RGB_TAG, colors);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (face == null) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
			}
		}
		return super.getCapability(capability, face);
	}

	public ItemStack getTableItemStack() {
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagCompound tankNBT = new NBTTagCompound();
		ItemStack stack = new ItemStack(SpectrumItems.ITEM_DYEING_TABLE, 1, 0, null);
		int red = canisterRed.getCurrentAmount();
		int green = canisterGreen.getCurrentAmount();
		int blue = canisterBlue.getCurrentAmount();
		nbt.setIntArray(NBTUtility.RGB_TAG, new int[] { red, green, blue });
		tank.writeToNBT(tankNBT);
		nbt.setTag("tank", tankNBT);
		stack.setTagCompound(nbt);
		return stack;
	}

	public void restoreTileEntity(ItemStack stack, ItemDyeingTable item) {
		int[] colors = NBTUtility.getIntArray(stack, NBTUtility.RGB_TAG);
		canisterRed.setCurrentAmount(colors[0]);
		canisterGreen.setCurrentAmount(colors[1]);
		canisterBlue.setCurrentAmount(colors[2]);
		if (stack.getTagCompound().hasKey("tank")) {
			tank.readFromNBT(stack.getSubCompound("tank"));
		}
		this.markDirty();
	}

	public NonNullList<ItemStack> getTableDrops() {
		NonNullList<ItemStack> list = NonNullList.create();
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			list.add(itemHandler.getStackInSlot(i));
		}
		return list;
	}

	// ----------------------------Dyeing Processes--------------------------//

	private void addDye() {
		if (!itemHandler.getStackInSlot(RED_SLOT).isEmpty() && canisterRed.getCurrentAmount() < (canisterRed.getMaxAmount() - 31)) {
			if (tank.getFluidAmount() >= WATER_COST) {
				itemHandler.extractItem(RED_SLOT, 1, false);
				tank.drain(WATER_COST, true);
				canisterRed.increaseAmount(UNIT_PER_DYE);
				this.markDirty();
			}
		}
		if (!itemHandler.getStackInSlot(GREEN_SLOT).isEmpty() && canisterGreen.getCurrentAmount() < (canisterGreen.getMaxAmount() - 31)) {
			if (tank.getFluidAmount() >= WATER_COST) {
				itemHandler.extractItem(GREEN_SLOT, 1, false);
				tank.drain(WATER_COST, true);
				canisterGreen.increaseAmount(UNIT_PER_DYE);
				this.markDirty();
			}
		}
		if (!itemHandler.getStackInSlot(BLUE_SLOT).isEmpty() && canisterBlue.getCurrentAmount() < (canisterBlue.getMaxAmount() - 31)) {
			if (tank.getFluidAmount() >= WATER_COST) {
				itemHandler.extractItem(BLUE_SLOT, 1, false);
				tank.drain(WATER_COST, true);
				canisterBlue.increaseAmount(UNIT_PER_DYE);
				this.markDirty();
			}
		}
	}

	public boolean hasRequiredMaterials() {
		int[] color = getColorFromClient().getIntArray(NBTUtility.RGB_TAG);
		int redCost = SpectrumUtils.getDecrementAmount(color[0]);
		int greenCost = SpectrumUtils.getDecrementAmount(color[1]);
		int blueCost = SpectrumUtils.getDecrementAmount(color[2]);
		return canisterRed.getCurrentAmount() >= redCost && canisterBlue.getCurrentAmount() >= blueCost && canisterGreen.getCurrentAmount() >= greenCost;
	}

	private void dyeBlocks() {
		ItemStack stackIn = itemHandler.getStackInSlot(INPUT_SLOT);
		if (SpectrumUtils.isDyeable(stackIn) && !isFull()) {
			ItemStack possibleOut = SpectrumUtils.getOutput(stackIn, false);
			possibleOut.setTagCompound(getColorFromClient());
			if (isValidOutput(itemHandler.getStackInSlot(OUTPUT_SLOT), possibleOut)) {
				itemHandler.extractItem(INPUT_SLOT, 1, false);
				itemHandler.insertItem(OUTPUT_SLOT, possibleOut, false);
			} else {
				doDye = false;
			}
		} else {
			doDye = false;
		}
		this.markDirty();
	}

	public boolean isFull() {
		return itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < itemHandler.getSlotLimit(OUTPUT_SLOT) ? false : true;
	}

	public boolean isValidOutput(ItemStack stackIn, ItemStack stackOut) {
		if (stackIn.isEmpty()) {
			return true;
		}
		if (stackIn.hasTagCompound() && stackOut.hasTagCompound() && stackIn.isItemEqual(stackOut)) {
			if (stackIn.getTagCompound().hasKey(NBTUtility.RGB_TAG) && stackOut.getTagCompound().hasKey(NBTUtility.RGB_TAG)) {
				int[] in = stackIn.getTagCompound().getIntArray(NBTUtility.RGB_TAG);
				int[] out = stackOut.getTagCompound().getIntArray(NBTUtility.RGB_TAG);
				if (Arrays.equals(in, out)) {
					return true;
				}
			}
		}
		return false;
	}

	public void consumeDye() {
		int[] colors = getColorFromClient().getIntArray(NBTUtility.RGB_TAG);
		canisterRed.decreaseAmount(SpectrumUtils.getDecrementAmount(colors[0]));
		canisterGreen.decreaseAmount(SpectrumUtils.getDecrementAmount(colors[1]));
		canisterBlue.decreaseAmount(SpectrumUtils.getDecrementAmount(colors[2]));
		this.markDirty();
	}

	// ---------------------------Getters and Setters---------------------------//

	public FluidTank getTank() {
		return tank;
	}

	public void setTank(FluidTank tank) {
		this.tank = tank;
	}

	public int[] getDyeAmount() {
		return new int[] { canisterRed.getCurrentAmount(), canisterGreen.getCurrentAmount(), canisterBlue.getCurrentAmount() };
	}

	public void setDyeAmount(int[] amounts) {
		canisterRed.setCurrentAmount(amounts[0]);
		canisterGreen.setCurrentAmount(amounts[1]);
		canisterBlue.setCurrentAmount(amounts[2]);
	}

	public void setDyeAmount(EnumSpectrumDye color, int amount) {
		switch (color) {
		case RED:
			canisterRed.setCurrentAmount(amount);
			break;
		case GREEN:
			canisterGreen.setCurrentAmount(amount);
			break;
		case BLUE:
			canisterBlue.setCurrentAmount(amount);
			break;
		default:
			break;
		}
	}

	public void setDoDye(boolean doDye) {
		this.doDye = doDye;
	}

	public boolean isDoClean() {
		return doClean;
	}

	public void setDoClean(boolean doClean) {
		this.doClean = doClean;
	}

	public NBTTagCompound getColorFromClient() {
		return clientColor;
	}

	public void setClientColor(NBTTagCompound clientColor) {
		this.clientColor = clientColor;
	}

	public BlockPos getClientPos() {
		return clientPos;
	}

	public void setClientPos(BlockPos clientPos) {
		this.clientPos = clientPos;
	}

	public int getAmountToDye() {
		return amountToDye;
	}

	public void setAmountToDye(int amount) {
		this.amountToDye = amount;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress() {
		progress = (int) (((double) itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() / (double) amountToDye) * 100);
	}

	public int getClientProgress() {
		return progress;
	}

	public void setClientProgress(int progress) {
		this.progress = progress;
	}

}
