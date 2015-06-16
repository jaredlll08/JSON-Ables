package jsonAbles.client.render;

import jsonAbles.JsonAbles;
import jsonAbles.blocks.MoltenFluid;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class LiquidRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		MoltenFluid fluid = (MoltenFluid) block;
		int color = fluid.color;
		renderer.renderBlockAsItem(fluid, color, color);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		MoltenFluid fluid = (MoltenFluid) block;
		int color = fluid.color;
		Tessellator tess = Tessellator.instance;
		renderer.renderBlockLiquid(fluid, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return JsonAbles.liquidRender;
	}
}
