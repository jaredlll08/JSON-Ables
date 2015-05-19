package fluxedCrystals.utils;

import cpw.mods.fml.common.Loader;

public class ModUtils {

	public static boolean isModLoaded(String modid) {
		return Loader.isModLoaded(modid);
	}
}
