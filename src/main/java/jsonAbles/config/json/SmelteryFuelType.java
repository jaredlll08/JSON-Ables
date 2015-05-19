package jsonAbles.config.json;

import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.SmelteryFuelSet;

public class SmelteryFuelType implements IJSONObject{

	public String fluidName = "water";
	public int temperature = 0;
	public int duration = 0;
	@Override
	public void register() {
		RecipeRegistry.registerSmelteryFuelSet(new SmelteryFuelSet(fluidName, temperature, duration));
	}
	
	
}
