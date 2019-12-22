package net.thedudemc.spectrum.init;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;
import net.thedudemc.spectrum.block.entity.TileDyeingTableFluidInput;
import net.thedudemc.spectrum.block.render.ControllerRenderer;
import net.thedudemc.spectrum.block.render.FluidRenderer;

public class InitModel {

	public static void registerItemModels() {
		registerSimpleItemModel(InitItem.ITEM_DYEING_TABLE, 0);

		registerBlockModel(InitBlock.SPECTRUM_STONE, 0);
		registerBlockModel(InitBlock.SPECTRUM_GRASS, 0);
		registerBlockModel(InitBlock.SPECTRUM_DIRT, 0);
		registerBlockModel(InitBlock.SPECTRUM_COBBLESTONE, 0);
		registerBlockModel(InitBlock.SPECTRUM_SAND, 0);
		registerBlockModel(InitBlock.SPECTRUM_GRAVEL, 0);
		registerBlockModel(InitBlock.SPECTRUM_OAK_LOG, 0);
		registerBlockModel(InitBlock.SPECTRUM_OAK_LEAVES, 0);

		registerFluidBlockModel(InitFluid.fluidRedLiquidDye.getBlock());
		registerFluidBlockModel(InitFluid.fluidGreenLiquidDye.getBlock());
		registerFluidBlockModel(InitFluid.fluidBlueLiquidDye.getBlock());
		
		registerTileEntityRenderers();

		// ModelLoader.setCustomMeshDefinition(InitItem.SPAWN_EGG_TRADER, new
		// TraderEggMesh(InitItem.SPAWN_EGG_TRADER));
	}

	private static void registerFluidBlockModel(Block block) {
		StateMapperBase customState = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
				return new ModelResourceLocation(new ResourceLocation(Spectrum.MODID, "fluids"), block.getRegistryName().getPath());
			}
		};
		ModelLoader.setCustomStateMapper(block, customState);
	}

	public static void registerTileEntityRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileDyeingTableFluidInput.class, new FluidRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileDyeingTableController.class, new ControllerRenderer());
	}

	/* ---------------------------------- */

	private static void registerSimpleItemModel(Item item, int metadata) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private static void registerBlockModel(Block block, int metadata) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(Spectrum.getResource(block.getTranslationKey().substring(5)), "inventory"));
	}
}
