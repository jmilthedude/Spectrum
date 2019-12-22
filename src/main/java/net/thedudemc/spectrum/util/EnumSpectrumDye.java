package net.thedudemc.spectrum.util;

public enum EnumSpectrumDye {

	RED(0), GREEN(1), BLUE(2);

	private int color;

	EnumSpectrumDye(int color) {
		this.color = color;
	}

	public int getInt() {
		return this.color;
	}
	


}
