package net.thedudemc.spectrum.init;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.thedudemc.spectrum.block.BlockLiquidDye;
import net.thedudemc.spectrum.fluid.FluidLiquidDye;
import net.thedudemc.spectrum.fluid.LiquidDyeType;

public class ModFluids {

	public static Fluid fluidRedLiquidDye;
	public static Fluid fluidGreenLiquidDye;
	public static Fluid fluidBlueLiquidDye;

	public static Block blockRedLiquidDye;
	public static Block blockGreenLiquidDye;
	public static Block blockBlueLiquidDye;

	public static void init() {
		fluidRedLiquidDye = registerFluid("redliquiddye", "red_liquid_dye");
		fluidGreenLiquidDye = registerFluid("greenliquiddye", "green_liquid_dye");
		fluidBlueLiquidDye = registerFluid("blueliquiddye", "blue_liquid_dye");
		blockRedLiquidDye = registerFluidBlock(fluidRedLiquidDye, Material.WATER, "block_red_liquid_dye", LiquidDyeType.RED);
		blockGreenLiquidDye = registerFluidBlock(fluidGreenLiquidDye, Material.WATER, "block_green_liquid_dye", LiquidDyeType.GREEN);
		blockBlueLiquidDye = registerFluidBlock(fluidBlueLiquidDye, Material.WATER, "block_blue_liquid_dye", LiquidDyeType.BLUE);
		FluidRegistry.addBucketForFluid(fluidRedLiquidDye);
		FluidRegistry.addBucketForFluid(fluidGreenLiquidDye);
		FluidRegistry.addBucketForFluid(fluidBlueLiquidDye);
	}

	private static Fluid registerFluid(String fluidName, String fluidTextureName) {
		Fluid fluid = new FluidLiquidDye(fluidName.toLowerCase(Locale.ROOT), fluidTextureName);
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		return FluidRegistry.getFluid(fluid.getName());
	}

	private static Block registerFluidBlock(Fluid fluid, Material material, String name, LiquidDyeType type) {
		return new BlockLiquidDye(fluid, material, name, type);
	}
}
