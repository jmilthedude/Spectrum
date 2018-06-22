package net.thedudemc.spectrum.objects.items;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.objects.blocks.BlockMiniFull;
import net.thedudemc.spectrum.util.IHasModel;

public class ItemDyeingStation extends ItemBlock implements IHasModel {

	public ItemDyeingStation(Block block)
	{
        super(block);
        setMaxDamage(0);
	}
	
    @Override
    public boolean placeBlockAt (@Nonnull ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState newState) {
        newState = newState.cycleProperty(BlockMiniFull.PART);

        if (facing == EnumFacing.DOWN)
            return false;
        if (facing == EnumFacing.UP)
            facing = EnumFacing.fromAngle(player.rotationYaw).getOpposite();

        newState = newState.withProperty(BlockMiniFull.FACING, facing);

        int xOff = 0;
        int zOff = 0;

        if (facing == EnumFacing.NORTH)
            xOff = 1;
        if (facing == EnumFacing.SOUTH)
            xOff = -1;
        if (facing == EnumFacing.WEST)
            zOff = -1;
        if (facing == EnumFacing.EAST)
            zOff = 1;

        if (!world.isAirBlock(pos) || !world.isAirBlock(pos.add(0, 1, 0)))
            return false;
        if (!world.isAirBlock(pos.add(xOff, 0, zOff)) || !world.isAirBlock(pos.add(xOff, 1, zOff))) {
            if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH)
                xOff *= -1;
            if (facing == EnumFacing.WEST || facing == EnumFacing.EAST)
                zOff *= -1;
            if (!world.isAirBlock(pos.add(xOff, 0, zOff)) || !world.isAirBlock(pos.add(xOff, 1, zOff)))
                return false;

            newState = newState.withProperty(BlockFramingTable.RIGHT_SIDE, false);
        }

        if (!world.setBlockState(pos, newState, 3))
            return false;

        IBlockState altState = newState.withProperty(BlockFramingTable.RIGHT_SIDE, !newState.getValue(BlockFramingTable.RIGHT_SIDE));
        if (!world.setBlockState(pos.add(xOff, 0, zOff), altState, 3)) {
            world.setBlockToAir(pos);
            return false;
        }

        if (world.getBlockState(pos).getBlock() == block)
            block.onBlockPlacedBy(world, pos, newState, player, stack);

        return true;
    }
	
	
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

	@Override
	public void registerModels() {
		Spectrum.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

}
