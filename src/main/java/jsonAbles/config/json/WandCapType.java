package jsonAbles.config.json;

import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.WandCapSet;
import net.minecraft.item.ItemStack;

public class WandCapType implements IJSONObject {

	public String key;
	public float multiplier;
	public String item;
	public int craftCost;
	public String textureName;

	@Override
	public void register() {
		RecipeRegistry.registerWandCap(new WandCapSet(this));
	}

}
