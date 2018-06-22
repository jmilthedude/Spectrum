package net.thedudemc.spectrum.util.handlers;

public class RenderHandler {
/*
	@SubscribeEvent
	public void highlightGhostBlock(DrawBlockHighlightEvent event)
	{
		EntityPlayer player = event.getPlayer();
		BlockPos position = event.getTarget().getBlockPos().offset(player.getHorizontalFacing());
		
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() instanceof Blocks.STONE)
		{
			IBlockState stateToRender = ((Blocks.STONE)stack.getItem()).getDetails(player.world, position, player, 0, 0, 0, event.getTarget().sideHit).getSecondElement();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			
			GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.enableAlpha();
            //GlStateManager.alphaFunc(0, 0);
            
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer vertexbuffer = tessellator.getBuffer();
            position.add(0.5, 0, 0.5);
            vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
            BlockPos blockpos = new BlockPos(position.getX(), position.getY(), position.getZ());
            GlStateManager.translate(position.getX(), position.getY(), position.getZ());
            
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            blockrendererdispatcher.getBlockModelRenderer().renderModel(event.getPlayer().world, blockrendererdispatcher.getModelForState(stateToRender), stateToRender, blockpos, vertexbuffer, false, MathHelper.getPositionRandom(position));
            tessellator.draw();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
		}
	}
	*/
}
