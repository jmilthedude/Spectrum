package net.thedudemc.spectrum;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.thedudemc.spectrum.init.ModCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Spectrum.MOD_ID)
public class Spectrum {

    public static final String MOD_ID = "spectrum";
    public static final Logger LOGGER = LogManager.getLogger();


    public Spectrum() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::onCommandRegister);
    }

    public void onCommandRegister(RegisterCommandsEvent event) {
        ModCommands.registerCommands(event.getDispatcher(), event.getEnvironment());
    }

    public static String sId(String name) {
        return MOD_ID + ":" + name;
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

}