package net.thedudemc.spectrum.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CleanBlockPacket implements IMessage {
	public CleanBlockPacket() {
	}

	private BlockPos position;

	public CleanBlockPacket(BlockPos posOut) {
		this.position = posOut;
	}

	public BlockPos getPosition() {
		return position;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, NBTUtil.createPosTag(position));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.position = NBTUtil.getPosFromTag(ByteBufUtils.readTag(buf));
	}
}