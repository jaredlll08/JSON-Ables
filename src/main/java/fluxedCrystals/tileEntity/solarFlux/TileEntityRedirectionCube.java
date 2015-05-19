package fluxedCrystals.tileEntity.solarFlux;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.api.solarFlux.TileSolarFlux;
import fluxedCrystals.client.RenderParticles;
import fluxedCrystals.network.MessageSolarFluxSync;
import fluxedCrystals.network.PacketHandler;

public class TileEntityRedirectionCube extends TileSolarFlux {

	public TileEntityRedirectionCube() {
		super(20000, 50, 10);
	}

	@Override
	public void generateEnergy(World world, int x, int y, int z, boolean night, boolean canSeeSky) {
		return;
	}

	public boolean canRecieveEnergy() {
		return true;
	}

	@Override
	public void sendEnergy(World world, int x, int y, int z, int range) {
		if (getEnergyStored() >= 50) {
			for (int xRange = -range; xRange <= range; xRange++) {
				if (!(xRange == 0)) {
					world.spawnParticle("reddust", x + xRange + .5, y + .5, z + .5, 255, 0, 0);
					if (world.getBlock(x + xRange, y, z) != null && world.getTileEntity(x + xRange, y, z) != null && world.getTileEntity(x + xRange, y, z) instanceof TileSolarFlux) {
						if (((TileSolarFlux) world.getTileEntity(x + xRange, y, z)).getEnergyStored() + 50 <= ((TileSolarFlux) world.getTileEntity(x + xRange, y, z)).getMaxEnergy() && ((TileSolarFlux) world.getTileEntity(x + xRange, y, z)).canRecieveEnergy()) {
							((TileSolarFlux) world.getTileEntity(x + xRange, y, z)).recieveEnergy(50, false);
							recieveEnergy(-50, false);
						}
					}
					world.spawnParticle("reddust", x + .5, y + .5, z + xRange + .5, 255, 0, 0);
					if (world.getBlock(x, y, z + xRange) != null && world.getTileEntity(x, y, z + xRange) != null && world.getTileEntity(x, y, z + xRange) instanceof TileSolarFlux) {
						if (((TileSolarFlux) world.getTileEntity(x, y, z + xRange)).getEnergyStored() + 50 <= ((TileSolarFlux) world.getTileEntity(x, y, z + xRange)).getMaxEnergy() && ((TileSolarFlux) world.getTileEntity(x, y, z + xRange)).canRecieveEnergy()) {
							((TileSolarFlux) world.getTileEntity(x, y, z + xRange)).recieveEnergy(50, false);
							recieveEnergy(-50, false);
						}
					}
				}
			}
			PacketHandler.INSTANCE.sendToDimension(new MessageSolarFluxSync(xCoord, yCoord, zCoord, getEnergyStored()), worldObj.provider.dimensionId);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}

}
