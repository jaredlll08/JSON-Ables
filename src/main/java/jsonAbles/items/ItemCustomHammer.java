package jsonAbles.items;

import jsonAbles.ModProps;
import jsonAbles.api.FluidSet;
import jsonAbles.api.HammerSet;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exnihilo.items.hammers.ItemHammerBase;

public class ItemCustomHammer extends ItemHammerBase {

	public IIcon handle;
	public IIcon head;

	public HammerSet set;

	public ItemCustomHammer(HammerSet set) {
		super(EnumHelper.addToolMaterial(set.name, set.harvestLevel, set.maxUses, set.efficiency, set.damage, set.enchantability));
		this.set = set;
		setUnlocalizedName(ModProps.modid + ":" + set.name);
		setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		return par2 == 1 ? this.handle : head;
	}

	public void registerIcons(IIconRegister icon) {
		this.handle = icon.registerIcon(ModProps.modid + ":HammerStick");
		this.head = icon.registerIcon(ModProps.modid + ":HammerHead");

	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if (pass == 0)
			return set.color;
		return 0xFFFFFF;
	}
}
