package fluxedCrystals.compat.minetweaker;

import vazkii.botania.api.BotaniaAPI;
import minetweaker.MineTweakerAPI;

public class FluxedCrystalsMT {
	public FluxedCrystalsMT() {
//		MineTweakerAPI.registerClass(SeedInfuser.class);
		MineTweakerAPI.registerClass(GemCutter.class);
		MineTweakerAPI.registerClass(GemRefiner.class);
	}
}
