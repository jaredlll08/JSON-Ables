package jsonAbles.proxy;

import jsonAbles.JsonAbles;
import jsonAbles.client.render.LiquidRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void initRenderers() {
		JsonAbles.liquidRender = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new LiquidRenderer());
	}
}
