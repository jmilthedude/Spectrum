package net.thedudemc.spectrum.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquidDye extends Fluid {

	public FluidLiquidDye(String name, ResourceLocation stillResource, ResourceLocation flowResource) {
		super(name, stillResource, flowResource);
	}

	public enum DyeType {

		RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);

		private final int color;

		DyeType(int color) {
			this.color = color;
		}

		public int toInt() {
			return color;
		}

	}

}
