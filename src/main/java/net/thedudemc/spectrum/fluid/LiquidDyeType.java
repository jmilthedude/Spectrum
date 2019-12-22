package net.thedudemc.spectrum.fluid;

public enum LiquidDyeType {

	RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);

	private final int color;

	LiquidDyeType(int color) {
		this.color = color;
	}

	public int toInt() {
		return color;
	}

}
