package net.thedudemc.spectrum.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ConfigSync implements IMessage {
	public ConfigSync() {
	}

	private int canisterMax;

	public ConfigSync(int toSend) {
		this.canisterMax = toSend;
	}

	public int getCanisterMax() {
		return canisterMax;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(canisterMax);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.canisterMax = buf.readInt();
	}
}