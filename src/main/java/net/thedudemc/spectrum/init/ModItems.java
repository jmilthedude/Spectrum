package net.thedudemc.spectrum.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.spectrum.Spectrum;

public class ModItems {

    public static ItemGroup SPECTRUM_BLOCKS = new ItemGroup(Spectrum.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.ALLIUM);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    };


//    public static ItemTraderCore TRADER_CORE = new ItemTraderCore(VAULT_MOD_GROUP, Vault.id("trader_core"));

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

//        registry.register(TRADER_CORE);
    }
}
