package fluxedCrystals.nei;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.minecraft.item.ItemStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.items.FCItems;

public class FluxedCrystalsNEIConfig implements IConfigureNEI {

	@Override
	public String getName() {
		return "Fluxed-Crystals-NEI";
	}

	@Override
	public String getVersion() {
		return ModProps.version;
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new GemCutterHandler());
		API.registerUsageHandler(new GemCutterHandler());
		API.registerRecipeHandler(new GemRefinerHandler());
		API.registerUsageHandler(new GemRefinerHandler());
		API.registerRecipeHandler(new InfuserRecipeHandler());
		API.registerUsageHandler(new InfuserRecipeHandler());
	}

}
