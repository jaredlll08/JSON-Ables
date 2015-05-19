package jsonAbles.misc;

import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.WandCap;

public class CustomWandCap extends WandCap{
	
	public String key;
	public float multiplier;
	public ItemStack item;
	public int craftCost;
	public String textureName;

	public CustomWandCap(String tag, float discount, ItemStack item, int craftCost) {
		super(tag, discount, item, craftCost);
	}

}
