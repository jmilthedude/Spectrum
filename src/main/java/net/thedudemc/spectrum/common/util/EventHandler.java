package net.thedudemc.spectrum.common.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.thedudemc.spectrum.common.Spectrum;
import net.thedudemc.spectrum.common.config.Config;
import net.thedudemc.spectrum.common.network.ConfigSync;

@EventBusSubscriber
public class EventHandler {

	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {
		Spectrum.PACKET.sendTo(new ConfigSync(Config.maxAmountDyeUnits), (EntityPlayerMP) event.player);
	}

}
