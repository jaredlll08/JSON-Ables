package jsonAbles.config.json;

import jsonAbles.JsonAbles;
import net.minecraft.util.EnumChatFormatting;

public class StarType implements IJSONObject {
	public String name = "star";
	public String starTier = "LOW";
	public int color = 0xFFFFFF;
	public String formatting = EnumChatFormatting.AQUA.getFriendlyName();
	public int maxPower = 10000;
	public int powerPerTick = 20;
	public int fuse = 20;

	@Override
	public void register() {
		try {
//			RecipeRegistry.registerStarSet(new StarSet(this));
			JsonAbles.logger.info("Registering star for " + name + ", whose color is " + color);
		} catch (IllegalArgumentException e) {
			JsonAbles.logger.info("Skipping Star type with name {} as its resource was not found.", name);
		}
	}

}
