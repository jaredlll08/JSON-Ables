package fluxedCrystals.utils;

import vazkii.botania.api.mana.IManaReceiver;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import fluxedCrystals.api.solarFlux.TileSolarFlux;
import fluxedCrystals.network.MessageSolarFluxSync;
import fluxedCrystals.tileEntity.TileEnergyBase;

public class ClientUtils {

	public static void updateSolarflux(MessageSolarFluxSync message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileSolarFlux) {
			((TileSolarFlux) te).setEnergy(message.stored);
		}
	}
	
}
