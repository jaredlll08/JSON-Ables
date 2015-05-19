package fluxedCrystals.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.blocks.BlockSeedInfuser;
import fluxedCrystals.tileEntity.TileEntityGlass;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;

public class GlassRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		renderer.setOverrideBlockTexture(block.getIcon(0, metadata));
		renderer.renderBlockAsItem(Blocks.stone, 0, 1.0f);
		renderer.clearOverrideBlockTexture();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		renderer.renderStandardBlock(block, x, y, z);
		TileEntityGlass tile = (TileEntityGlass) world.getTileEntity(x, y, z);
		int index = tile.getIndex();
		if (index >= 0) {
			int color = RecipeRegistry.getColor(index);
			Tessellator tess = Tessellator.instance;
			tess.setColorOpaque_I(color);
			float red = (color >> 16 & 255) / 255.0F;
	        float green = (color >> 8 & 255) / 255.0F;
	        float blue = (color & 255) / 255.0F;
	        renderer.setOverrideBlockTexture(block.getIcon(0, world.getBlockMetadata(x, y, z)));
			renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, red, green, blue);
			renderer.clearOverrideBlockTexture();
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return FluxedCrystals.glassRenderID;
	}
}
