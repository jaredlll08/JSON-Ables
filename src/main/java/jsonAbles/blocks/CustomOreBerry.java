package jsonAbles.blocks;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tconstruct.library.tools.AbilityHelper;
import tconstruct.world.blocks.OreberryBush;

public class CustomOreBerry extends OreberryBush {
	ItemStack drop;

	public CustomOreBerry(String[] textureNames, int meta, int stages, ItemStack drop) {
		super(textureNames, meta, stages, new String[] { drop.getUnlocalizedName() });
		this.drop = drop;
	}

	public boolean harvest(World world, int x, int y, int z, EntityPlayer player) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= 12) {
			if (world.isRemote)
				return false;

			world.setBlock(x, y, z, this, meta - 4, 3);
			AbilityHelper.spawnItemAtPlayer(player, drop.copy());
		}

		return false;
	}
}
