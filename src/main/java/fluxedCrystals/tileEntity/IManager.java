package fluxedCrystals.tileEntity;

import cofh.api.energy.EnergyStorage;
import tterrag.core.common.util.BlockCoord;

public interface IManager {
	
	public void placePowerBlocks(int size);
	
	public BlockCoord getCordinates();
	
}
