package net.thedudemc.spectrum.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.thedudemc.spectrum.init.ModBlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpectrumTableTileEntity extends TileEntity {

    private int redAmount;
    private int greenAmount;
    private int blueAmount;

    private ItemStackHandler itemHandler = createItemHandler();
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private FluidTank tank = createFluidHandler();
    private LazyOptional<IFluidHandler> fluid = LazyOptional.of(() -> tank);

    public SpectrumTableTileEntity() {
        this(ModBlocks.SPECTRUM_TABLE_TILE_ENTITY);
    }

    public SpectrumTableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }


    @Override
    public CompoundNBT write(CompoundNBT compound) {

        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {

        super.read(state, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();


        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {

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

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() == Items.DIAMOND;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stack.getItem() != Items.DIAMOND) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private FluidTank createFluidHandler() {
        return null;
    }

    public void sendUpdates() {
        this.world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockState().getBlock());
        this.markDirty();
    }
}
