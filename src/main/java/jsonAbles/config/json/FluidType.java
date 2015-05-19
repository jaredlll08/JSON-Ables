package jsonAbles.config.json;

import jsonAbles.JsonAbles;
import jsonAbles.api.FluidSet;
import jsonAbles.api.RecipeRegistry;

public class FluidType implements IJSONObject {
	public String unlocalizedName = "unloc";
	public int density = 100;
	public boolean gaseous = false;
	public int luminosity = 15;
	public int temperature = 300;
	public int viscosity = 50;
	public 	int color = 0xFFFFFF;
	public 	String[] meltingItems = new String[]{};
	public int[] meltingItemsTemperature = new int[] {};
	public int[] meltingItemsOutputAmount = new int[] {};
	public int castingMaterialID;

	@Override
	public void register() {
		try {
			RecipeRegistry.registerFluidSet(new FluidSet(this));
			JsonAbles.logger.info("Registering fluid for " + unlocalizedName + ", whose color is " + color);
		} catch (IllegalArgumentException e) {
			JsonAbles.logger.info("Skipping fluid type with name {} as its resource was not found.", unlocalizedName);
		}
	}

}
