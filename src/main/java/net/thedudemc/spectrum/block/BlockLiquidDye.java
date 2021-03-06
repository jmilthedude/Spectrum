package net.thedudemc.spectrum.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.thedudemc.spectrum.fluid.FluidLiquidDye.DyeType;
import net.thedudemc.spectrum.item.ItemBlockBase;

public class BlockLiquidDye extends BlockFluidClassic {

	private final String name;
	private final DyeType type;

	public BlockLiquidDye(Fluid fluid, Material material, String unlocalizedName, DyeType type) {
		super(fluid, material);
		this.name = unlocalizedName;
		this.type = type;
		this.displacements.put(this, true);

	}

	public DyeType getType() {
		return type;
	}

	protected String getBaseName() {
		return this.name;
	}

	protected ItemBlockBase getItemBlock() {
		return new ItemBlockBase(this);
	}

	public boolean shouldAddCreative() {
		return false;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.displaceIfPossible(world, pos);
	}

}
