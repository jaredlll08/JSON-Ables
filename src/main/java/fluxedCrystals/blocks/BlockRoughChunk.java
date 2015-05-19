package fluxedCrystals.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockIce;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityGlass;
import fluxedCrystals.tileEntity.TileEntityRoughChunk;

public class BlockRoughChunk extends Block implements ITileEntityProvider {

	public BlockRoughChunk() {
		super(Material.glass);
		setBlockTextureName(ModProps.modid + ":roughChunk");
		setHardness(0.5F);
		setHarvestLevel("pickaxe", 1);
	}

	public int getRenderType() {
		return FluxedCrystals.chunkRenderID;
	}

	public void setData(ItemStack shard, IBlockAccess world, int x, int y, int z) {
		TileEntityRoughChunk tile = (TileEntityRoughChunk) world.getTileEntity(x, y, z);
		if (tile != null) {
			tile.init(shard.getItemDamage());
		}
	}

	public void doDrop(TileEntityRoughChunk crop, World world, int x, int y, int z) {

		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shard, 4, crop.getIndex()));

		if (RecipeRegistry.getWeightedDrop(crop.getIndex()) != null) {
			if (RecipeRegistry.getWeightedDropChance(crop.getIndex()) == world.rand.nextInt(9) + 1) {
				dropBlockAsItem(world, x, y, z, RecipeRegistry.getWeightedDrop(crop.getIndex()));
			}
		}
	}
	  /**
     * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
     */
    public void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
    {
        if (!p_149642_1_.isRemote && p_149642_1_.getGameRules().getGameRuleBooleanValue("doTileDrops") && !p_149642_1_.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
        {
            if (captureDrops.get())
            {
                capturedDrops.get().add(p_149642_5_);
                return;
            }
            float f = 0.7F;
            double d0 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d2 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(p_149642_1_, (double)p_149642_2_ + d0, (double)p_149642_3_ + d1, (double)p_149642_4_ + d2, p_149642_5_);
            entityitem.delayBeforeCanPickup = 10;
            p_149642_1_.spawnEntityInWorld(entityitem);
        }
    }
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1
	 * for alpha
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityRoughChunk();
	}
}
