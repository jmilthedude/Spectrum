package net.thedudemc.spectrum.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.thedudemc.spectrum.common.tileentity.Canister;

public class ConfigSyncHandler implements IMessageHandler<ConfigSync, IMessage> {

	@Override
	public IMessage onMessage(ConfigSync message, MessageContext ctx) {
		Canister.MAX_AMOUNT = message.getCanisterMax();
		return null;
	}

}