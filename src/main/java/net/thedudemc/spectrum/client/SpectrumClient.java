package net.thedudemc.spectrum.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.init.ModFluids;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.util.FluidStateMapper;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Constants.MODID, value = Side.CLIENT)
public class SpectrumClient {

	@SubscribeEvent
	static void onRegisterModels(final ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(ModItems.DYEING_TABLE, 0, new ModelResourceLocation(ModItems.DYEING_TABLE.getRegistryName(), "inventory"));
		registerCustomFluidBlockRenderer(ModFluids.fluidRedLiquidDye);
		registerCustomFluidBlockRenderer(ModFluids.fluidGreenLiquidDye);
		registerCustomFluidBlockRenderer(ModFluids.fluidBlueLiquidDye);
	}

	private static void registerCustomFluidBlockRenderer(Fluid fluid) {
		Block block = fluid.getBlock();
		Item item = Item.getItemFromBlock(block);
		FluidStateMapper mapper = new FluidStateMapper(fluid);
		ModelLoader.registerItemVariants(item);
		ModelLoader.setCustomMeshDefinition(item, mapper);
		ModelLoader.setCustomStateMapper(block, mapper);
	}
}
