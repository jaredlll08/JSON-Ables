package jsonAbles.config.json;

import jsonAbles.JsonAbles;
import jsonAbles.api.MaterialSet;
import jsonAbles.api.RecipeRegistry;

public class MaterialType implements IJSONObject {

	public String key = "key", name = "name", style = "style";
	public String resource = "minecraft:bedrock";
	public int materialID = 0, harvestLevel = 0, durability = 0, miningSpeed = 0, attack = 0, reinforced = 0, primaryColor = 0, value = 0;
	public float handleModifier = 0, stonebound = 0;
	public boolean buildParts = true;
	public String castingFluid = null;
	public int modifiers = 0;
	public String lore = "";
	
	public float arrowMass = 0;
	public float arrowBreakChance = 0;
	public int bowDrawSpeed = 0;
	public float bowSpeedMax = 0;

	public void register() {
		try {
			RecipeRegistry.registerMaterialSet(new MaterialSet(this));
			JsonAbles.logger.info("Registering Material for " + name + ", whose color is " + primaryColor + " and that is crafted with " + resource);
		} catch (IllegalArgumentException e) {
			JsonAbles.logger.info("Skipping material type with name {} as its resource was not found.", name);
		}
	}
}
