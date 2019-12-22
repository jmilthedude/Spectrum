package net.thedudemc.spectrum.client.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.item.ItemDyeingTable;

@Mod.EventBusSubscriber(modid = Spectrum.MODID)
public class PlacementRenderer {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void highlightGhostBlock(DrawBlockHighlightEvent event) {
		EntityPlayer player = event.getPlayer();
		if (event.getTarget().sideHit != EnumFacing.UP) {
			return;
		}
		EnumFacing facing = player.getHorizontalFacing();
		BlockPos itemIn = event.getTarget().getBlockPos().up();
		BlockPos fluidIn = itemIn.offset(facing.rotateYCCW());
		BlockPos controller = fluidIn.up();
		List<BlockPos> positions = new ArrayList<BlockPos>();
		positions.add(itemIn);
		positions.add(fluidIn);
		positions.add(controller);
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof ItemDyeingTable) {
			Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
			double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) event.getPartialTicks();
			double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) event.getPartialTicks();
			double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) event.getPartialTicks();
			Tessellator.getInstance().getBuffer().setTranslation(-d0, -d1, -d2);

			for (BlockPos pos : positions) {
				renderBox(Tessellator.getInstance(), Tessellator.getInstance().getBuffer(), pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1, 50, 0, 0);
			}
			Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);
		}
	}

	private static void renderBox(Tessellator tesselator, BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int alpha, int colorA, int colorB) {
		GlStateManager.glLineWidth(2.0F);
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		buffer.pos(minX, minY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, minY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, minY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, minY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, minY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, minY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, maxY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, maxY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, maxY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, maxY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, maxY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, maxY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(minX, minY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, minY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, maxY, maxZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, maxY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, minY, minZ).color(0, 0, 0, alpha).endVertex();
		buffer.pos(maxX, minY, minZ).color(0, 0, 0, alpha).endVertex();
		tesselator.draw();
		GlStateManager.glLineWidth(1.0F);
	}

}