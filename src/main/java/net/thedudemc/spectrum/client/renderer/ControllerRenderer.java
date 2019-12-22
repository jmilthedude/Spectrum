package net.thedudemc.spectrum.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.thedudemc.spectrum.block.BlockDyeingTable;
import net.thedudemc.spectrum.init.SpectrumFluids;
import net.thedudemc.spectrum.tileentity.Canister;
import net.thedudemc.spectrum.tileentity.TileDyeingTableController;

public class ControllerRenderer extends TileEntitySpecialRenderer<TileDyeingTableController> {

	@Override
	public void render(TileDyeingTableController tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableBlend();
		GlStateManager.translate((float) x, (float) y, (float) z);

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		renderFluid(tileEntity);

		GlStateManager.popMatrix();
	}

	private void renderFluid(TileDyeingTableController te) {
		if (te == null) {
			return;
		}
		int[] colors = te.getDyeAmount();
		int redAmount = colors[0];
		int greenAmount = colors[1];
		int blueAmount = colors[2];
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if (state == null) {
			return;
		}
		EnumFacing facing = state.getValue(BlockDyeingTable.FACING);
		if (facing == null) {
			return;
		}
		float scaleRed = (.75f * redAmount / Canister.MAX_AMOUNT) + 0.1875f;
		float scaleGreen = (.75f * greenAmount / Canister.MAX_AMOUNT) + 0.1875f;
		float scaleBlue = (.75f * blueAmount / Canister.MAX_AMOUNT) + 0.1875f;

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder renderer = tessellator.getBuffer();
		RenderHelper.disableStandardItemLighting();
		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
		if (scaleRed > 0.1875f) {
			drawRed(renderer, facing, scaleRed);
		}
		if (scaleGreen > 0.1875f) {
			drawGreen(renderer, facing, scaleGreen);
		}
		if (scaleBlue > 0.1875f) {
			drawBlue(renderer, facing, scaleBlue);
		}
		tessellator.draw();
		RenderHelper.enableStandardItemLighting();

	}

	private void drawRed(BufferBuilder renderer, EnumFacing facing, float scale) {
		ResourceLocation redStill = SpectrumFluids.fluidRedLiquidDye.getStill();
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(redStill.toString());
		GlStateManager.color(1, 1, 1, .5f);

		double offset = 0;
		// @formatter:off
		switch (facing) {
		case EAST: break;
		case NORTH: break;
		case SOUTH: offset = 0.625d; break;
		case WEST: break;
		default: break;
		}
		// @formatter:on
		drawTextures(renderer, facing, scale, sprite, offset);

	}

	private void drawGreen(BufferBuilder renderer, EnumFacing facing, float scale) {
		ResourceLocation still = SpectrumFluids.fluidGreenLiquidDye.getStill();
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(still.toString());
		GlStateManager.color(1, 1, 1, .5f);

		double offset = 0.3125d;
		// @formatter:off
		switch (facing) {
		case EAST: break;
		case NORTH: break;
		case SOUTH: break;
		case WEST: break;
		default: break;
		}
		// @formatter:on
		drawTextures(renderer, facing, scale, sprite, offset);
	}

	private void drawBlue(BufferBuilder renderer, EnumFacing facing, float scale) {
		ResourceLocation still = SpectrumFluids.fluidBlueLiquidDye.getStill();
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(still.toString());
		GlStateManager.color(1, 1, 1, .5f);

		double offset = 0.625d;
		// @formatter:off
		switch (facing) {
		case EAST: break;
		case NORTH: break;
		case SOUTH: offset = 0d; break;
		case WEST: break;
		default: break;
		}
		// @formatter:on
		drawTextures(renderer, facing, scale, sprite, offset);
	}

	private void drawTextures(BufferBuilder renderer, EnumFacing facing, float scale, TextureAtlasSprite sprite, double offset) {

		float u1 = sprite.getMinU();
		float v1 = sprite.getMinV();
		float u2 = sprite.getMaxU();
		float v2 = sprite.getMaxV();

		switch (facing) {
		case NORTH:
			// top
			renderer.pos(offset + 0.0625d, scale, 0.0625d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.0625d, scale, 0.25d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, scale, 0.25d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, scale, 0.0625d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
			// side
			renderer.pos(offset + 0.0625d, scale, 0.25d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.0625d, 0.1875d, 0.25d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, 0.1875d, 0.25d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, scale, 0.25d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
			break;
		case SOUTH:
			// top
			renderer.pos(offset + 0.0625d, scale, 1 - 0.25d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.0625d, scale, 1 - 0.0625d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, scale, 1 - 0.0625d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, scale, 1 - 0.25d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
			// side
			renderer.pos(offset + 0.0625d, 0.1875d, 1 - 0.25d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, 0.1875d, 1 - 0.25d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.0625d, scale, 1 - 0.25d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(offset + 0.3125d, scale, 1 - 0.25d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
			break;
		case WEST:
			offset = -0.625;
			renderer.pos(0.0625d, scale, offset + 0.9375d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(0.0625d, scale, offset + 0.6875d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
			renderer.pos(0.25d, scale, offset + 0.9375d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
			renderer.pos(0.25d, scale, offset + 0.6875d).tex(u2, v2).color(255, 255, 255, 128).endVertex();

			break;
		default:
			break;
		}
	}
}
