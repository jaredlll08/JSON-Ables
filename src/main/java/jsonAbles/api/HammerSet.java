package jsonAbles.api;

import jsonAbles.api.json.StackHelper;
import jsonAbles.config.json.HammerType;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class HammerSet {
	public int color;
	public String name;
	public int harvestLevel;
	public int maxUses;
	public float efficiency;
	public float damage;
	public int enchantability;
	public ItemStack resource;

	public HammerSet(HammerType type) {
		this(type.color, type.name, type.harvestLevel, type.maxUses, type.efficiency, type.damage, type.enchantability, StackHelper.getStackFromString(type.resource));
	}

	public HammerSet(int color, String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, ItemStack resource) {
		this.color = color;
		this.name = name;
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency/100;
		this.damage = damage;
		this.enchantability = enchantability;
		this.resource = resource;
	}

}
