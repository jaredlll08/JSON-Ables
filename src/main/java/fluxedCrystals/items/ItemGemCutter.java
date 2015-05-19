package fluxedCrystals.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemGemCutter extends Item {

	public ItemGemCutter() {
		setMaxStackSize(1);
		setMaxDamage(50);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copiedStack = itemStack.copy();
		return copiedStack;
	}

	public boolean getShareTag() {
		return true;
	}
	
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return false;
	}

}
