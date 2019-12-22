package net.thedudemc.spectrum.block.entity.canister;

import net.minecraft.item.EnumDyeColor;
import net.thedudemc.spectrum.config.Config;

public class Canister {
	public static int MAX_AMOUNT = Config.maxAmountDyeUnits;
	private int currentAmount = 0;
	private EnumDyeColor dyeType;

	public Canister(EnumDyeColor dye) {
		this.dyeType = dye;
	}

	public EnumDyeColor getDyeType() {
		return dyeType;
	}

	public void setDyeType(EnumDyeColor dyeType) {
		this.dyeType = dyeType;
	}

	public int getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}

	public int getMaxAmount() {
		return MAX_AMOUNT;
	}

	public void increaseAmount(int amountIn) {
		if (currentAmount + amountIn >= MAX_AMOUNT)
			currentAmount = MAX_AMOUNT;
		else
			currentAmount += amountIn;
	}

	public void decreaseAmount(int amountIn) {
		if (currentAmount - amountIn <= 0)
			currentAmount = 0;
		else
			currentAmount -= amountIn;
	}

}
