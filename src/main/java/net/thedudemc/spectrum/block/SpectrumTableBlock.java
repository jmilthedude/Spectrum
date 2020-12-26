package net.thedudemc.spectrum.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.thedudemc.spectrum.init.ModBlocks;

import javax.annotation.Nullable;

public class SpectrumTableBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<TablePart> PART = EnumProperty.create("part", SpectrumTableBlock.TablePart.class, TablePart.BASIN, TablePart.TUBES, TablePart.TANK);


    public SpectrumTableBlock() {
        super(Properties.create(Material.IRON)
                .notSolid()
                .hardnessAndResistance(3.0f, 3.0f));

        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(PART, TablePart.BASIN));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        if (state.get(PART) != TablePart.BASIN) return false;
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (state.get(PART) != TablePart.BASIN) return null;
        return ModBlocks.SPECTRUM_TABLE_TILE_ENTITY.create();
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getWorld();
        Direction facing = context.getPlacementHorizontalFacing();

        BlockPos basinPos = context.getPos();
        BlockState basinState = world.getBlockState(basinPos);

        BlockPos tubesPos = getTubes(basinPos, facing);
        BlockState tubesState = world.getBlockState(tubesPos);

        BlockPos tankPos = getTank(tubesPos);
        BlockState tankState = world.getBlockState(tankPos);

        if (basinPos.getY() >= 255 || tankPos.getY() >= 255) return null;

        if (basinState.isReplaceable(context) && tubesState.isReplaceable(context) && tankState.isReplaceable(context)) {
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(PART, TablePart.BASIN);
        } else {
            return null;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(PART);
    }


    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (worldIn.isRemote) super.onBlockHarvested(worldIn, pos, state, player);

        TablePart part = state.get(PART);
        Direction facing = state.get(FACING);
        BlockPos basinPos = getBasin(part, pos, facing);
        BlockPos tubesPos = getTubes(basinPos, facing);
        BlockPos tankPos = getTank(tubesPos);
        switch (part) {
            case BASIN:
                worldIn.setBlockState(tubesPos, Blocks.AIR.getDefaultState(), 35);
                worldIn.setBlockState(tankPos, Blocks.AIR.getDefaultState(), 35);
                break;
            case TUBES:
                worldIn.setBlockState(basinPos, Blocks.AIR.getDefaultState(), 35);
                worldIn.setBlockState(tankPos, Blocks.AIR.getDefaultState(), 35);
                break;
            case TANK:
                worldIn.setBlockState(basinPos, Blocks.AIR.getDefaultState(), 35);
                worldIn.setBlockState(tubesPos, Blocks.AIR.getDefaultState(), 35);
                break;
        }
        worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.SPECTRUM_TABLE)));
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    private static BlockPos getBasin(TablePart part, BlockPos pos, Direction facing) {
        switch (part) {
            case BASIN:
                return pos;
            case TUBES:
                return pos.offset(facing.getOpposite(), 1);
            case TANK:
                return pos.down().offset(facing.getOpposite(), 1);
        }
        return pos;
    }

    private static BlockPos getTubes(BlockPos basin, Direction facing) {
        return basin.offset(facing, 1);
    }

    private static BlockPos getTank(BlockPos tubes) {
        return tubes.up();
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        BlockPos tubePos = getTubes(pos, placer.getHorizontalFacing());
        BlockPos tankPos = getTank(tubePos);
        worldIn.setBlockState(tubePos, state.with(PART, TablePart.TUBES), 3);
        worldIn.setBlockState(tankPos, state.with(PART, TablePart.TANK), 3);
        TileEntity te = worldIn.getTileEntity(pos);

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    public enum TablePart implements IStringSerializable {


        BASIN("basin", new BlockPos(0, 0, 0)),
        TUBES("tubes", TablePart.BASIN.getPos().south()),
        TANK("tank", TablePart.TUBES.getPos().up());

        private BlockPos pos;
        private String part;

        TablePart(String part, BlockPos pos) {
            this.part = part;
            this.pos = pos;
        }

        @Override
        public String getString() {
            return this.part;
        }

        public BlockPos getPos() {
            return pos;
        }
    }

}
