package net.thedudemc.spectrum.util;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;
import net.thedudemc.spectrum.client.gui.GuiDyeingTable;
import net.thedudemc.spectrum.container.ContainerDyeingTable;

public class GuiHandler implements IGuiHandler {

	@Nullable
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileDyeingTableController) {
			return new ContainerDyeingTable(player.inventory, (TileDyeingTableController) te);
		}
		return null;
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileDyeingTableController) {
			TileDyeingTableController containerTileEntity = (TileDyeingTableController) te;
			return new GuiDyeingTable(containerTileEntity, new ContainerDyeingTable(player.inventory, containerTileEntity));
		}
		return null;
	}
}
