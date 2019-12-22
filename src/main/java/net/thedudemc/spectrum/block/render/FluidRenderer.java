package net.thedudemc.spectrum.block.render;

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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.block.BlockDyeingTable;
import net.thedudemc.spectrum.block.entity.TileDyeingTableController;
import net.thedudemc.spectrum.block.entity.TileDyeingTableFluidInput;

@SideOnly(Side.CLIENT)
public class FluidRenderer extends TileEntitySpecialRenderer<TileDyeingTableFluidInput> {

	public FluidRenderer() {
	}

	@Override
	public void render(TileDyeingTableFluidInput tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableBlend();
		GlStateManager.translate((float) x, (float) y, (float) z);

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		renderFluid(tileEntity);

		GlStateManager.popMatrix();
	}

	private void renderFluid(TileDyeingTableFluidInput te) {
		if (te == null) {
			return;
		}
		TileDyeingTableController controller = (TileDyeingTableController) te.getWorld().getTileEntity(BlockDyeingTable.getControllerPos(te.getWorld(), te.getPos()));
		if (controller == null) {
			return;
		}
		FluidStack fluid = controller.getTank().getFluid();
		if (fluid == null) {
			return;
		}
		Fluid renderFluid = fluid.getFluid();
		if (renderFluid == null) {
			return;
		}
		IBlockState state = controller.getWorld().getBlockState(controller.getPos());
		if (state == null) {
			return;
		}
		EnumFacing facing = state.getValue(BlockDyeingTable.FACING);
		if (facing == null) {
			return;
		}
		float scale = (.43f * fluid.amount / (controller.getTank().getCapacity())) + 0.4375f;

		if (scale > 0.4375f) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder renderer = tessellator.getBuffer();
			ResourceLocation still = renderFluid.getStill();
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(still.toString());

			RenderHelper.disableStandardItemLighting();

			GlStateManager.color(1, 1, 1, .5f);
			renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

			float u1 = sprite.getMinU();
			float v1 = sprite.getMinV();
			float u2 = sprite.getMaxU();
			float v2 = sprite.getMaxV();

			switch (facing) {
			case NORTH:
				renderer.pos(0.25d, scale, 0.05d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
				renderer.pos(0.25d, scale, 0.8125d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(0.8125d, scale, 0.8125d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(0.8125d, scale, 0.05d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
				break;
			case EAST:
				renderer.pos(0.1875d, scale, 0.25d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
				renderer.pos(0.1875d, scale, 0.8125d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(0.95d, scale, 0.8125d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(0.95d, scale, 0.25d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
				break;
			case SOUTH:
				renderer.pos(1 - 0.8125d, scale, 1 - 0.8125d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
				renderer.pos(1 - 0.8125d, scale, 1 - 0.05d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(1 - 0.25d, scale, 1 - 0.05d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(1 - 0.25d, scale, 1 - 0.8125d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
				break;
			case WEST:
				renderer.pos(1 - 0.1875d, scale, 1 - 0.25d).tex(u1, v1).color(255, 255, 255, 128).endVertex();
				renderer.pos(1 - 0.1875d, scale, 1 - 0.8125d).tex(u1, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(1 - 0.95d, scale, 1 - 0.8125d).tex(u2, v2).color(255, 255, 255, 128).endVertex();
				renderer.pos(1 - 0.95d, scale, 1 - 0.25d).tex(u2, v1).color(255, 255, 255, 128).endVertex();
				break;
			default:
				break;
			}
			tessellator.draw();

			RenderHelper.enableStandardItemLighting();
		}
	}

}
