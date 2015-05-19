package jsonAbles.config.json;

import jsonAbles.JsonAbles;
import jsonAbles.api.AspectSet;
import jsonAbles.api.RecipeRegistry;

public class AspectType implements IJSONObject {

	public String tag = "tag";
	public String aspect1 = "aer";
	public String aspect2 = "aer";
	public int color = 0xFFFFFF;

	@Override
	public void register() {
		try {
			RecipeRegistry.registerAspectSet(new AspectSet(this));
			JsonAbles.logger.info("Registering aspect for " + tag +  ", whose color is " + color);
		} catch (IllegalArgumentException e) {
			JsonAbles.logger.info("Skipping aspect type with name {} as its aspects was not found.", tag);
		}
	}

}
