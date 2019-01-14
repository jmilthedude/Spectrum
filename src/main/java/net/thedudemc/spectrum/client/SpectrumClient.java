package net.thedudemc.spectrum.client;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.client.renderer.ControllerRenderer;
import net.thedudemc.spectrum.client.renderer.FluidRenderer;
import net.thedudemc.spectrum.common.Constants;
import net.thedudemc.spectrum.common.init.SpectrumBlocks;
import net.thedudemc.spectrum.common.init.SpectrumFluids;
import net.thedudemc.spectrum.common.init.SpectrumItems;
import net.thedudemc.spectrum.common.tileentity.TileDyeingTableController;
import net.thedudemc.spectrum.common.tileentity.TileDyeingTableFluidInput;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Constants.MODID, value = Side.CLIENT)
public class SpectrumClient {

	public static void initBlockColorHandler() {
		IBlockColor blockColorHandler = new ColorHandler();
		IItemColor itemColorHandler = new ColorHandler();
		for (Block block : SpectrumBlocks.BLOCKS) {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(blockColorHandler, block);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, block);
		}
	}

	@SubscribeEvent
	static void onRegisterModels(final ModelRegistryEvent event) {
		registerItemModel(SpectrumItems.ITEM_DYEING_TABLE, 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_STONE), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_DIRT), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_COBBLESTONE), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_PLANK), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_SAND), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_GRAVEL), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_OAK_LOG), 0, "inventory");
		registerItemModel(Item.getItemFromBlock(SpectrumBlocks.SPECTRUM_OAK_LEAVES), 0, "inventory");
		registerFluidBlockModel(SpectrumFluids.fluidRedLiquidDye.getBlock());
		registerFluidBlockModel(SpectrumFluids.fluidGreenLiquidDye.getBlock());
		registerFluidBlockModel(SpectrumFluids.fluidBlueLiquidDye.getBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileDyeingTableFluidInput.class, new FluidRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileDyeingTableController.class, new ControllerRenderer());
	}

	private static void registerItemModel(final Item item, final int meta, final String variant) {
		if (Items.AIR == item) {
			throw new IllegalStateException("Empty item");
		}
		@Nullable
		final ResourceLocation name = item.getRegistryName();
		if (name == null) {
			throw new IllegalStateException("Missing registry name");
		}
		if (variant.isEmpty()) {
			throw new IllegalStateException("Empty variant string");
		}
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(name, variant));
	}

	private static void registerFluidBlockModel(Block block) {
		StateMapperBase customState = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
				return new ModelResourceLocation(new ResourceLocation(Constants.MODID, "fluids"), block.getRegistryName().getPath());
			}
		};
		ModelLoader.setCustomStateMapper(block, customState);
	}
}
