package net.thedudemc.spectrum.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.thedudemc.spectrum.Constants;
import net.thedudemc.spectrum.block.BlockDyeingTable;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.item.ItemDyeingTable;

public class RenderHandler {
	public static void highlightGhostBlock(DrawBlockHighlightEvent event) {
		EntityPlayer player = event.getPlayer();
		BlockPos position = event.getTarget().getBlockPos().offset(player.getHorizontalFacing());

		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof ItemDyeingTable) {
			System.out.println("Yes.");
			IBlockState stateToRender = ModBlocks.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, event.getPlayer().getHorizontalFacing()).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumSide.RIGHT);
			IBlockState stateSideToRender = ModBlocks.DYEING_TABLE.getDefaultState().withProperty(BlockDyeingTable.FACING, event.getPlayer().getHorizontalFacing()).withProperty(BlockDyeingTable.PART, BlockDyeingTable.EnumSide.LEFT);

			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Constants.MODID, "block_mini_full"));

			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			GlStateManager.enableAlpha(); // GlStateManager.alphaFunc(0, 0);

			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder vertexbuffer = tessellator.getBuffer();
			position.add(0.5, 1, 0.5);
			vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
			BlockPos blockpos = new BlockPos(position.getX(), position.getY(), position.getZ());
			GlStateManager.translate(position.getX(), position.getY(), position.getZ());

			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			blockrendererdispatcher.getBlockModelRenderer().renderModel(event.getPlayer().world, blockrendererdispatcher.getModelForState(stateToRender), stateToRender, blockpos, vertexbuffer, false, MathHelper.getPositionRandom(position));
			blockrendererdispatcher.getBlockModelRenderer().renderModel(event.getPlayer().world, blockrendererdispatcher.getModelForState(stateSideToRender), stateSideToRender, blockpos.offset(player.getHorizontalFacing().rotateYCCW()), vertexbuffer,
					false, MathHelper.getPositionRandom(position));
			tessellator.draw();

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

}