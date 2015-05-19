package jsonAbles.config.json;

import jsonAbles.api.HammerSet;
import jsonAbles.api.RecipeRegistry;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class HammerType implements IJSONObject{
	
	public int color;
	public String name;
	public int harvestLevel;
	public int maxUses;
	public float efficiency;
	public float damage;
	public int enchantability;
	public String resource;
	
	@Override
	public void register() {
		RecipeRegistry.registerHammer(new HammerSet(this));
	}

}
