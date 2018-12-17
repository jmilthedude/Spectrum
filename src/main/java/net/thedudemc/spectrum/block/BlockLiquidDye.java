package net.thedudemc.spectrum.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.fluid.LiquidDyeType;
import net.thedudemc.spectrum.item.ItemBlockBase;
import net.thedudemc.spectrum.util.EventHandler;
import net.thedudemc.spectrum.util.RegistryHandler;
import net.thedudemc.spectrum.util.TickHandler;

public class BlockLiquidDye extends BlockFluidClassic {

	private final String name;
	private final LiquidDyeType type;

	public BlockLiquidDye(Fluid fluid, Material material, String unlocalizedName, LiquidDyeType type) {
		super(fluid, material);
		this.name = unlocalizedName;
		this.type = type;
		this.displacements.put(this, true);

		this.registerBlock(this, this.getItemBlock(), this.getBaseName(), this.shouldAddCreative());
	}

	public LiquidDyeType getType() {
		return type;
	}

	public void registerBlock(Block block, ItemBlockBase itemBlock, String name, boolean addTab) {
		block.setUnlocalizedName(Constants.MODID + "." + name);

		block.setRegistryName(Constants.MODID, name);
		RegistryHandler.BLOCKS_TO_REGISTER.add(block);

		itemBlock.setRegistryName(block.getRegistryName());
		RegistryHandler.ITEMS_TO_REGISTER.add(itemBlock);

		block.setCreativeTab(addTab ? Spectrum.SPECTRUM_TAB : null);

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

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (state.getValue(LEVEL) == 0) { // liquid source block
			if (EventHandler.tickHandler != null) {
				world.setBlockToAir(EventHandler.tickHandler.getPos()); // if timer hasn't ended already, set previously placed to air.
			}
			EventHandler.tickHandler = new TickHandler(40, pos); // set timer for newly added liquid block
		}
		super.onBlockAdded(world, pos, state);
	}

}
