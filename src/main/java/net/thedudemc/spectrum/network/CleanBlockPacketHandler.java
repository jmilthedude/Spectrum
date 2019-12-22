package net.thedudemc.spectrum.network;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;

public class CleanBlockPacketHandler implements IMessageHandler<CleanBlockPacket, IMessage> {

	@Override
	public IMessage onMessage(CleanBlockPacket message, MessageContext ctx) {
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
		TileDyeingTableController te = (TileDyeingTableController) world.getTileEntity(message.getPosition());
		te.setDoClean(true);
		return null;
	}

}