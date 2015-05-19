package jsonAbles.config.json;

import jsonAbles.api.OreBerrySet;
import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.json.StackHelper;

public class OreBerryType implements IJSONObject {
	public String[] textureNames = null;
	public int stages = 0;
	public String drop = "minecraft:dirt";
	public boolean overworld = false;
	public boolean nether = false;
	public int generationHightMin = 0;
	public int generationHightMax = 0;
	public int generationDensity = 0;
	public int generationChance = 0;

	@Override
	public void register() {
		RecipeRegistry.registerOreBerryBushes(new OreBerrySet(textureNames, stages, StackHelper.getStackFromString(drop), overworld, nether, generationHightMin, generationHightMax, generationDensity, generationChance));
	}

}
