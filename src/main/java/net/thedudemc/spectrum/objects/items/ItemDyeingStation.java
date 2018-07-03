package net.thedudemc.spectrum.objects.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.init.ModBlocks;
import net.thedudemc.spectrum.init.ModItems;
import net.thedudemc.spectrum.objects.blocks.BlockTable;
import net.thedudemc.spectrum.util.IHasModel;
import net.thedudemc.spectrum.util.handlers.EventHandler;

public class ItemDyeingStation extends Item implements IHasModel {

	public ItemDyeingStation(String name) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Spectrum.spectrumtab);

		ModItems.ITEMS.add(this);
	}

	@SuppressWarnings("deprecation")
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (worldIn.isRemote) {
			System.out.println("isRemote statement true");
			return EnumActionResult.SUCCESS;
		} else {

			// if (!flag) {
			// pos = pos.up();
			// }

			EnumFacing enumfacing = player.getHorizontalFacing();
			BlockPos frontCenter = new BlockPos(pos.up());
			BlockPos frontRight = frontCenter.offset(enumfacing.rotateY());
			BlockPos frontLeft = frontCenter.offset(enumfacing.rotateYCCW());
			BlockPos backCenter = frontCenter.offset(enumfacing);
			BlockPos backRight = frontCenter.offset(enumfacing).offset(enumfacing.rotateY());
			BlockPos backLeft = frontCenter.offset(enumfacing).offset(enumfacing.rotateYCCW());
			ItemStack itemstack = player.getHeldItem(hand);
			System.out.println("isRemote statement false, time to check for air blocks");
			boolean flag = EventHandler.checkAirSurrounding(worldIn, frontCenter, player, enumfacing);
			
			System.out.println("Did it make it here before the exception??");

			if (flag == true && worldIn.getBlockState(frontCenter.down()).isTopSolid() && worldIn.getBlockState(frontRight.down()).isTopSolid() && worldIn.getBlockState(frontLeft.down()).isTopSolid()
					&& worldIn.getBlockState(backCenter.down()).isTopSolid() && worldIn.getBlockState(backRight.down()).isTopSolid() && worldIn.getBlockState(backLeft.down()).isTopSolid()) {
				System.out.println("Long ass if statement was true");
				IBlockState tableFrontCenter = ModBlocks.TABLE_FRONT_CENTER.getDefaultState().withProperty(BlockTable.FACING, enumfacing);
				IBlockState tableFrontRight = ModBlocks.TABLE_FRONT_RIGHT.getDefaultState().withProperty(BlockTable.FACING, enumfacing);
				IBlockState tableFrontLeft = ModBlocks.TABLE_FRONT_LEFT.getDefaultState().withProperty(BlockTable.FACING, enumfacing);
				IBlockState tableBackCenter = ModBlocks.TABLE_BACK_CENTER.getDefaultState().withProperty(BlockTable.FACING, enumfacing);
				IBlockState tableBackRight = ModBlocks.TABLE_BACK_RIGHT.getDefaultState().withProperty(BlockTable.FACING, enumfacing);
				IBlockState tableBackLeft = ModBlocks.TABLE_BACK_LEFT.getDefaultState().withProperty(BlockTable.FACING, enumfacing);
				worldIn.setBlockState(frontCenter, tableFrontCenter, 10);
				worldIn.setBlockState(frontRight, tableFrontRight, 10);
				worldIn.setBlockState(frontLeft, tableFrontLeft, 10);
				worldIn.setBlockState(backCenter, tableBackCenter, 10);
				worldIn.setBlockState(backRight, tableBackRight, 10);
				worldIn.setBlockState(backLeft, tableBackLeft, 10);
				SoundType soundtype = tableFrontCenter.getBlock().getSoundType(Blocks.PLANKS.getDefaultState(), worldIn, pos, player);
				worldIn.playSound((EntityPlayer) null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

				if (player instanceof EntityPlayerMP) {
					CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
				}

				itemstack.shrink(1);
				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.FAIL;
			}
		}

	}

	// worldIn.addBlockEvent(pos, ModBlocks.TABLE_SINGLE, 101, 1);

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public void registerModels() {
		Spectrum.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
