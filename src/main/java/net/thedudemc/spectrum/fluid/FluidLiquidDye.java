package net.thedudemc.spectrum.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.thedudemc.spectrum.Constants;

public class FluidLiquidDye extends Fluid {

	public FluidLiquidDye(String fluidName, String textureName) {
		super(fluidName, new ResourceLocation(Constants.MODID, "blocks/liquid_dye_still"), new ResourceLocation(Constants.MODID, "blocks/liquid_dye_flow"));
	}

	@Override
	public String getUnlocalizedName() {
		return "fluid." + Constants.MODID + "." + this.unlocalizedName;
	}
}
