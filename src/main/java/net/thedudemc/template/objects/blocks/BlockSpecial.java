package net.thedudemc.template.objects.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSpecial extends BlockBase {

	public BlockSpecial(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.GLASS);
		setHardness(0.5F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(1.0f);
		setLightOpacity(1);
	}

}
