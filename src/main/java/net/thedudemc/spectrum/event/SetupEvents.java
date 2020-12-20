package net.thedudemc.spectrum.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.thedudemc.spectrum.client.SpectrumColors;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.init.ModConfigs;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {

    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        SpectrumColors.register();
        ModBlocks.registerTileEntityRenderers();
    }

    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {
        ModConfigs.register();
    }

    @SubscribeEvent
    public static void setupDedicatedServer(final FMLDedicatedServerSetupEvent event) {
    }

}
