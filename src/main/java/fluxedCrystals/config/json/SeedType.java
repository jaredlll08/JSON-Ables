package fluxedCrystals.config.json;

import thaumcraft.api.aspects.Aspect;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.SeedCrystalRecipe;

public class SeedType implements ISeedType {

	public String name = "null";
	public int color = 0xFFFFFF;
	public String ingredient = "minecraft:portal";
	public String drop = "minecraft:portal";
	public int dropMin = 1;
	public int dropMax = 1;
	public int growthTime = 1200;
	public int tier = 0;
	public int ingredientAmount = 32;
	public int powerPerStage = 2000;
	public boolean decorationBlocks = true;
	public String weightedDrop = "minecraft:portal";
	public int weightedDropChance = 0;
	public boolean prettyPrettyArmor = true;
	public int refinerAmount = 4;
	public String lore = "null";
	public boolean sharp = true;
	public String aspectNeeded;
	public int aspectNeededAmount = 16;
	public int seedReturn = 1;
	public int entityID;

	public void register() {
		try {
			RecipeRegistry.addCrop(new SeedCrystalRecipe(this));
			FluxedCrystals.logger.info("Registering Seed for " + name + ", whose color is " + color + " and that is crafted with " + ingredient);
		} catch (IllegalArgumentException e) {
			FluxedCrystals.logger.info("Skipping seed type with name {} as its drop was not found.", name);
		}
	}
}
