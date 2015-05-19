package jsonAbles.api;

import jsonAbles.api.json.StackHelper;
import jsonAbles.config.json.WandCapType;
import net.minecraft.item.ItemStack;

public class WandCapSet {
	public String key;
	public float multiplier;
	public ItemStack item;
	public int craftCost;
	public String textureName;

	public WandCapSet(WandCapType type) {
		this(type.key, type.multiplier, StackHelper.getStackFromString(type.item), type.craftCost, type.textureName);
	}

	public WandCapSet(String key, float multiplier, ItemStack item, int craftCost, String textureName) {
		this.key = key;
		this.multiplier = multiplier;
		this.item = item;
		this.craftCost = craftCost;
		this.textureName = textureName;
	}

}
