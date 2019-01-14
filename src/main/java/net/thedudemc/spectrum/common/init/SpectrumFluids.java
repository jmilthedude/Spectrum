package net.thedudemc.spectrum.common.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.thedudemc.spectrum.common.Constants;
import net.thedudemc.spectrum.common.fluid.FluidLiquidDye;

public class SpectrumFluids {

	public static Fluid fluidRedLiquidDye;
	public static Fluid fluidGreenLiquidDye;
	public static Fluid fluidBlueLiquidDye;

	public static void init() {
		fluidRedLiquidDye = registerFluid("red_liquid_dye");
		fluidGreenLiquidDye = registerFluid("green_liquid_dye");
		fluidBlueLiquidDye = registerFluid("blue_liquid_dye");

	}

	private static Fluid registerFluid(String name) {
		Fluid fluid = new FluidLiquidDye(name, getStillResource(name), getFlowResource(name));
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		return FluidRegistry.getFluid(fluid.getName());
	}

	private static ResourceLocation getStillResource(String name) {
		return new ResourceLocation(Constants.MODID, "blocks/" + name + "_still");
	}

	private static ResourceLocation getFlowResource(String name) {
		return new ResourceLocation(Constants.MODID, "blocks/" + name + "_flow");
	}
}
