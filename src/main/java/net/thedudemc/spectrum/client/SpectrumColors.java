package net.thedudemc.spectrum.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thedudemc.spectrum.block.entity.SpectrumBlockTileEntity;
import net.thedudemc.spectrum.init.ModBlocks;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpectrumColors implements IBlockColor, IItemColor {

    public static void register() {
        IBlockColor blockColor = new SpectrumColors();
        IItemColor itemColor = new SpectrumColors();
        for (Block block : ModBlocks.BLOCKS) {
            Minecraft.getInstance().getBlockColors().register(blockColor, block);
            Minecraft.getInstance().getItemColors().register(itemColor, block);
        }
    }


    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int tintIndex) {
        TileEntity te = reader.getTileEntity(pos);
        if (te instanceof SpectrumBlockTileEntity) {
            return ((SpectrumBlockTileEntity) te).getColor();
        }
        return 0xffffff;
    }

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        CompoundNBT nbt = stack.getOrCreateTag();
        return nbt.getInt("Color");
    }
}
