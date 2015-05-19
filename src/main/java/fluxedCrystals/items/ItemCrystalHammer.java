package fluxedCrystals.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fluxedCrystals.blocks.BlockGlass;
import fluxedCrystals.blocks.BlockRoughChunk;
import fluxedCrystals.blocks.FCBlocks;
import fluxedCrystals.tileEntity.TileEntityRoughChunk;

public class ItemCrystalHammer extends Item {
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 250;
	}

	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		if (player.worldObj.getBlock(x, y, z) instanceof BlockRoughChunk) {
			TileEntityRoughChunk tile = (TileEntityRoughChunk) player.worldObj.getTileEntity(x, y, z);
			((BlockRoughChunk) player.worldObj.getBlock(x, y, z)).dropBlockAsItem(player.worldObj, x, y, z, new ItemStack(FCItems.roughShard, 4, tile.getIndex()));
			player.worldObj.setBlockToAir(x, y, z);
			stack.damageItem(1, player);
		}
		return false;
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
		if (block instanceof BlockRoughChunk)
			return 5;
		return 0;
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			if (world.getBlock(x, y, z) == FCBlocks.infusedGlass) {
				int newMeta = world.getBlockMetadata(x, y, z) + 1;
				if (newMeta >= ((BlockGlass) world.getBlock(x, y, z)).getIcons().length) {
					newMeta = 0;
				}
				world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
				stack.damageItem(1, player);
				return true;
			}
		return false;
	}
}
