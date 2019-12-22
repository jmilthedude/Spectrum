package net.thedudemc.spectrum.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.thedudemc.spectrum.tileentity.TileDyeingTableController;

public class ColorPacketHandler implements IMessageHandler<ColorPacket, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in
	// this case

	@Override
	public IMessage onMessage(ColorPacket message, MessageContext ctx) {
		NBTTagCompound nbt = message.getNBT();
		BlockPos pos = NBTUtil.getPosFromTag(message.getPos());
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
		TileDyeingTableController te = (TileDyeingTableController) world.getTileEntity(pos);
		te.setClientColor(nbt);
		te.setClientPos(pos);
		te.setDoDye(true);

		return null;
	}

}