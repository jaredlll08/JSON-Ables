package fluxedCrystals.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import fluxedCrystals.client.gui.GUIHandler;
import fluxedCrystals.utils.Events;

public class CommonProxy {

	public void initGuis() {
		new GUIHandler();
	}

	public void initRenderers() {

	}

	public void initEvents() {
	}

	public World getClientWorld() {
		return null;
	}

	public void renderTrans() {

	}

	public EntityPlayer getClientPlayer() {
		return null;
	}
}
