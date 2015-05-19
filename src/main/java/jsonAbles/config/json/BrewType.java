package jsonAbles.config.json;

import jsonAbles.api.BrewSet;
import jsonAbles.api.RecipeRegistry;

public class BrewType implements IJSONObject{
	public String name = "You need a name!";
	public String key = "You need a key!";
	public int color = 696969;
	public int cost = 3;
	public String[] potionEffectNames = new String[]{};
	public int[] potionEffectDuration = new int[]{};
	public int[] potionEffectAmplifier = new int[]{};
	public boolean[] potionEffectParticles = new boolean[]{};
	@Override
	public void register() {
		RecipeRegistry.registerBrew(new BrewSet(this));
	}
	
}
