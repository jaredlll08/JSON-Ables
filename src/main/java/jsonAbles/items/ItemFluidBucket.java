package jsonAbles.items;

import jsonAbles.ModProps;
import jsonAbles.api.FluidSet;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFluidBucket extends ItemBucket {

	public IIcon bucket;
	public IIcon fluid;

	public FluidSet set;

	public ItemFluidBucket(Block block, FluidSet set) {
		super(block);
		this.set = set;
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		return par2 == 1 ? this.bucket : fluid;
	}

	public void registerIcons(IIconRegister icon) {
		this.bucket = icon.registerIcon(ModProps.modid + ":bucket");
		this.fluid = icon.registerIcon(ModProps.modid + ":bucket_overlay");

	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if (pass == 0)
			return set.getColor();
		return 0xFFFFFF;
	}
}
