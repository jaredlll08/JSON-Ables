package fluxedCrystals.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.gui.GUIHandler;
import fluxedCrystals.client.render.ChunkRenderer;
import fluxedCrystals.client.render.CrystalRenderer;
import fluxedCrystals.client.render.GlassRenderer;
import fluxedCrystals.client.render.RenderLightCollector;
import fluxedCrystals.client.render.RenderSolarGenerator;
import fluxedCrystals.client.render.SeedInfuserRenderer;
import fluxedCrystals.tileEntity.solarFlux.TileEntityRedirectionCube;
import fluxedCrystals.tileEntity.solarFlux.TileEntitySolarGenerator;
import fluxedCrystals.utils.EventHandler;
import fluxedCrystals.utils.Events;

public class ClientProxy extends CommonProxy {

	public void initGuis() {
		new GUIHandler();
	}

	@Override
	public void initRenderers() {
		FluxedCrystals.crystalRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.glassRenderID = RenderingRegistry.getNextAvailableRenderId();
		FluxedCrystals.chunkRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new CrystalRenderer());
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());
		RenderingRegistry.registerBlockHandler(new GlassRenderer());
		RenderingRegistry.registerBlockHandler(new ChunkRenderer());
		ClientRegistry.registerTileEntity(TileEntitySolarGenerator.class, "solarGen", new RenderSolarGenerator());
		ClientRegistry.registerTileEntity(TileEntityRedirectionCube.class, "redirectionCube", new RenderLightCollector());

	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public void renderTrans() {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void initEvents() {
	}
}
