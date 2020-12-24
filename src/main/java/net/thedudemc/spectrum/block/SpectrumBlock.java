package net.thedudemc.spectrum.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.thedudemc.spectrum.block.entity.SpectrumBlockTileEntity;
import net.thedudemc.spectrum.init.ModBlocks;

import javax.annotation.Nullable;

public class SpectrumBlock extends Block {

    public SpectrumBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModBlocks.SPECTRUM_BLOCK_TILE_ENTITY.create();
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (worldIn.isRemote) super.onBlockHarvested(worldIn, pos, state, player);

        if (!player.isCreative()) {
            TileEntity te = worldIn.getTileEntity(pos);
            ItemStack item = new ItemStack(getBlock());

            if (te instanceof SpectrumBlockTileEntity) {
                CompoundNBT nbt = item.getOrCreateTag();
                nbt.putInt("Color", ((SpectrumBlockTileEntity) te).getColor());
                item.setTag(nbt);
            }

            worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, item));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof SpectrumBlockTileEntity) {
            SpectrumBlockTileEntity spectrumTile = (SpectrumBlockTileEntity) te;
            CompoundNBT nbt = stack.getOrCreateTag();
            spectrumTile.setColor(nbt.getInt("Color"));
            spectrumTile.sendUpdates();
        }

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }
}
