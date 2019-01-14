package net.thedudemc.spectrum.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.thedudemc.spectrum.common.block.BlockDyeingTable;

public class TileDyeingTableFluidInput extends TileEntity {

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			TileDyeingTableFluidInput te = TileDyeingTableFluidInput.this;
			World world = te.getWorld();
			IBlockState state = world.getBlockState(this.getPos());
			TileDyeingTableController controller = (TileDyeingTableController) world.getTileEntity(this.getPos().up());
			if (state.getValue(BlockDyeingTable.FACING) == facing) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(controller.getTank());
			}
		}
		return super.getCapability(capability, facing);
	}
}
