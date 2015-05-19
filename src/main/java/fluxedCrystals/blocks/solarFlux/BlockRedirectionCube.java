package fluxedCrystals.blocks.solarFlux;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrystals.tileEntity.solarFlux.TileEntityRedirectionCube;

public class BlockRedirectionCube extends Block implements ITileEntityProvider {

	public BlockRedirectionCube() {
		super(Material.iron);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			player.addChatComponentMessage(new ChatComponentText(((TileEntityRedirectionCube) world.getTileEntity(x, y, z)).getEnergyStored() + ""));
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityRedirectionCube();
	}

}
