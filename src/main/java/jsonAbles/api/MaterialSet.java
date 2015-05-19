package jsonAbles.api;

import jsonAbles.api.json.StackHelper;
import jsonAbles.config.json.MaterialType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class MaterialSet {
	public String key, name, style, lore;
	public ItemStack resource;
	public int materialID, harvestLevel, durability, miningSpeed, attack, reinforced, primaryColor, value;
	public float handleModifier, stonebound;
	public boolean buildParts;
	public int modifiers;
	public float arrowMass, arrowBreakChance, bowSpeedMax;
	public int bowDrawSpeed;

	public MaterialSet(MaterialType type) {
		this(type.key, type.name, EnumChatFormatting.getValueByName(type.style.toUpperCase()).toString(), StackHelper.getStackFromString(type.resource), type.materialID, type.harvestLevel, type.durability, type.miningSpeed, type.attack, type.reinforced, type.primaryColor, type.value, type.handleModifier, type.stonebound, type.buildParts,  type.modifiers, type.lore, type.arrowMass, type.arrowBreakChance, type.bowDrawSpeed, type.bowSpeedMax);
	}

	public MaterialSet(String key, String name, String style, ItemStack resource, int materialID, int harvestLevel, int durability, int miningSpeed, int attack, int reinforced, int primaryColor, int value, float handleModifier, float stonebound, boolean buildParts, int modifiers, String lore, float arrowMass, float arrowBreakChance, int bowDrawSpeed, float bowSpeedMax) {
		this.key = key;
		this.name = name;
		this.style = style;
		this.resource = resource;
		this.materialID = materialID;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.miningSpeed = miningSpeed;
		this.attack = attack;
		this.reinforced = reinforced;
		this.primaryColor = primaryColor;
		this.value = value;
		this.handleModifier = handleModifier;
		this.stonebound = stonebound;
		this.buildParts = buildParts;
		this.modifiers = modifiers;
		this.lore = lore;
		this.arrowMass = arrowMass;
		this.arrowBreakChance = arrowBreakChance;
		this.bowDrawSpeed = bowDrawSpeed;
		this.bowSpeedMax = bowSpeedMax;
	}

	public String getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}

	public ItemStack getResource() {
		return resource;
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public int getMaterialID() {
		return materialID;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public int getDurability() {
		return durability;
	}

	public int getMiningSpeed() {
		return miningSpeed;
	}

	public int getAttack() {
		return attack;
	}

	public int getReinforced() {
		return reinforced;
	}

	public int getPrimaryColor() {
		return primaryColor;
	}

	public float getHandleModifier() {
		return handleModifier;
	}

	public float getStonebound() {
		return stonebound;
	}

}
