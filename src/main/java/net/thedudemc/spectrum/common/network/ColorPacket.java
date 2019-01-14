package net.thedudemc.spectrum.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ColorPacket implements IMessage {
	public ColorPacket() {
	}

	private NBTTagCompound nbt;
	private BlockPos pos;

	public ColorPacket(NBTTagCompound toSend, BlockPos pos) {
		this.nbt = toSend;
		this.pos = pos;
	}

	public NBTTagCompound getNBT() {
		return nbt;
	}

	public NBTTagCompound getPos() {
		return NBTUtil.createPosTag(pos);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
		ByteBufUtils.writeTag(buf, NBTUtil.createPosTag(pos));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbt = ByteBufUtils.readTag(buf);
		this.pos = NBTUtil.getPosFromTag(ByteBufUtils.readTag(buf));
	}
}