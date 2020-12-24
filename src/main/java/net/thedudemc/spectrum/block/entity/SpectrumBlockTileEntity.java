package net.thedudemc.spectrum.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.thedudemc.spectrum.init.ModBlocks;

import javax.annotation.Nullable;

public class SpectrumBlockTileEntity extends TileEntity {

    private int color;

    public SpectrumBlockTileEntity() {
        this(ModBlocks.SPECTRUM_BLOCK_TILE_ENTITY);
    }

    public SpectrumBlockTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("Color", getColor());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        setColor(nbt.getInt("Color"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        nbt.putInt("Color", getColor());
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        setColor(tag.getInt("Color"));
        if (this.world != null && this.world.isRemote) {
            this.world.notifyBlockUpdate(pos, state, state, 3);
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, -1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT nbt = pkt.getNbtCompound();
        handleUpdateTag(getBlockState(), nbt);
    }

    public void sendUpdates() {
        this.world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockState().getBlock());
        this.markDirty();
    }
}
