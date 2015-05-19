package fluxedCrystals.blocks.solarFlux;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrystals.client.RenderParticles;
import fluxedCrystals.tileEntity.solarFlux.TileEntitySolarGenerator;

public class BlockSolarGenerator extends Block implements ITileEntityProvider {

	public BlockSolarGenerator() {
		super(Material.iron);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			player.addChatComponentMessage(new ChatComponentText(((TileEntitySolarGenerator) world.getTileEntity(x, y, z)).getEnergyStored() + ""));
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
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		RenderParticles.spawnSolarFlux(x + .5, y + .5, z + .5, y + 15);
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySolarGenerator();
	}

}
