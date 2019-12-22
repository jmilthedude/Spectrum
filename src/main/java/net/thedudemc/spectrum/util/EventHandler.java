package net.thedudemc.spectrum.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.config.Config;
import net.thedudemc.spectrum.network.ConfigSync;

@EventBusSubscriber
public class EventHandler {

	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {
		Spectrum.PACKET.sendTo(new ConfigSync(Config.maxAmountDyeUnits), (EntityPlayerMP) event.player);
	}

}
