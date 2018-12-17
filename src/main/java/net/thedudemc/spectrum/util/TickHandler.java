package net.thedudemc.spectrum.util;

import net.minecraft.util.math.BlockPos;

public class TickHandler {

	private int ticks;
	private BlockPos pos;

	public TickHandler(int ticks, BlockPos pos) {
		this.ticks = ticks;
		this.pos = pos;
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public void update() {
		ticks--;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

}
