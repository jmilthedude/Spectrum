package net.thedudemc.spectrum.common.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.thedudemc.spectrum.common.util.NBTUtility;

public class TileDyeable extends TileEntity {

	private short red;
	private short green;
	private short blue;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		int[] colors = this.getColors();
		compound.setIntArray(NBTUtility.RGB_TAG, colors);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey(NBTUtility.RGB_TAG)) {
			int[] colors = compound.getIntArray(NBTUtility.RGB_TAG);
			this.red = (short) colors[0];
			this.green = (short) colors[1];
			this.blue = (short) colors[2];
		}
	}

	public ItemStack getItemStack() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setIntArray(NBTUtility.RGB_TAG, getColors());
		return new ItemStack(Item.getItemFromBlock(this.getBlockType()), 1, 0, compound);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setIntArray(NBTUtility.RGB_TAG, this.getColors());
		return new SPacketUpdateTileEntity(this.getPos(), 1, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		int[] colors = pkt.getNbtCompound().getIntArray(NBTUtility.RGB_TAG);
		this.setRGB(colors);
		super.onDataPacket(net, pkt);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setIntArray(NBTUtility.RGB_TAG, this.getColors());
		nbt.setInteger("x", this.pos.getX());
		nbt.setInteger("y", this.pos.getY());
		nbt.setInteger("z", this.pos.getZ());

		return nbt;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		if (tag.hasKey(NBTUtility.RGB_TAG)) {
			int[] colors = tag.getIntArray(NBTUtility.RGB_TAG);
			this.red = (short) colors[0];
			this.green = (short) colors[1];
			this.blue = (short) colors[2];
			super.handleUpdateTag(tag);
		}
	}

	public int[] getColors() {
		int[] colors = new int[] { this.red, this.green, this.blue };
		return colors;
	}

	public void setRGB(int[] colors) {
		this.red = (short) colors[0];
		this.green = (short) colors[1];
		this.blue = (short) colors[2];
		this.markDirty();
	}

}
