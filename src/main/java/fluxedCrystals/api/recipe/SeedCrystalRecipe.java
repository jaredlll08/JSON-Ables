package fluxedCrystals.api.recipe;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import tterrag.core.common.json.JsonUtils;
import fluxedCrystals.config.json.SeedType;

public class SeedCrystalRecipe {

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public ItemStack getIngredient() {
		return ingredient;
	}

	public int getDropMin() {
		return dropMin;
	}

	public int getDropMax() {
		return dropMax;
	}

	public int getGrowthTime() {
		return growthTime;
	}

	public int getTier() {
		return tier;
	}

	public int getIngredientAmount() {
		return ingredientAmount;
	}

	public int getPowerPerStage() {
		return powerPerStage;
	}

	public boolean isDecorationBlocks() {
		return decorationBlocks;
	}

	public ItemStack getWeightedDrop() {
		return weightedDrop;
	}

	public int getWeightedDropChance() {
		return weightedDropChance;
	}

	public boolean isPrettyPrettyArmor() {
		return prettyPrettyArmor;
	}

	public int getRefinerAmount() {
		return refinerAmount;
	}

	public String getLore() {
		return lore;
	}

	public boolean isSharp() {
		return sharp;
	}

	public Aspect getAspectNeeded() {
		return aspectNeeded;
	}

	public int getAspectNeededAmount() {
		return aspectNeededAmount;
	}

	public int getSeedReturn() {
		return seedReturn;
	}

	public int getEntityID() {
		return entityID;
	}

	public ItemStack getDrop() {
		return drop;
	}

	private String name;
	private int color;
	private ItemStack ingredient;
	private ItemStack drop;
	private int dropMin;
	private int dropMax;
	private int growthTime;
	private int tier;
	private int ingredientAmount;
	private int powerPerStage;
	private boolean decorationBlocks;
	private ItemStack weightedDrop;
	private int weightedDropChance;
	private boolean prettyPrettyArmor;
	private int refinerAmount;
	private String lore;
	private boolean sharp;
	private Aspect aspectNeeded;
	private int aspectNeededAmount;
	private int seedReturn;
	private int entityID;

	public SeedCrystalRecipe(SeedType type) {
		this(type.name, type.color, JsonUtils.parseStringIntoItemStack(type.ingredient), type.dropMin, type.dropMax, type.growthTime, type.tier, type.ingredientAmount, type.powerPerStage, type.decorationBlocks, JsonUtils.parseStringIntoItemStack(type.weightedDrop), type.weightedDropChance, type.prettyPrettyArmor, type.refinerAmount, type.lore, type.sharp, Aspect.getAspect(type.aspectNeeded), type.aspectNeededAmount, type.seedReturn, type.entityID, JsonUtils.parseStringIntoItemStack(type.drop));
	}

	public SeedCrystalRecipe(String name, int color, ItemStack ingredient, int dropMin, int dropMax, int growthTime, int tier, int ingredientAmount, int powerPerStage, boolean decorationBlock, ItemStack weightedDrop, int weightedDropChance, boolean prettyPrettyArmor, int refinerAmount, String lore, boolean sharp, Aspect aspectNeeded, int aspectNeededAmount, int seedReturn, int entityID, ItemStack drop) {
		this.name = name;
		this.color = color;
		this.ingredient = ingredient;
		this.dropMin = dropMin;
		this.dropMax = dropMax;
		this.growthTime = growthTime;
		this.tier = tier;
		this.ingredientAmount = ingredientAmount;
		this.powerPerStage = powerPerStage;
		this.decorationBlocks = decorationBlock;
		this.weightedDrop = weightedDrop;
		this.weightedDropChance = weightedDropChance;
		this.prettyPrettyArmor = prettyPrettyArmor;
		this.refinerAmount = refinerAmount;
		this.lore = lore;
		this.sharp = sharp;
		this.aspectNeeded = aspectNeeded;
		this.aspectNeededAmount = aspectNeededAmount;
		this.seedReturn = seedReturn;
		this.entityID = entityID;
		this.drop = drop;
	}
}
