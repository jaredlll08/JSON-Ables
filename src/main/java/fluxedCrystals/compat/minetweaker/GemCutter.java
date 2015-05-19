package fluxedCrystals.compat.minetweaker;

import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import scala.collection.script.Remove;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.IArcaneRecipe;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.compat.minetweaker.helper.InputHelper;
import fluxedCrystals.compat.minetweaker.helper.StackHelper;

@ZenClass("mods.fluxedcrystals.cutter")
public class GemCutter {
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack input, int inputAmount, int outputAmount) {
		MineTweakerAPI.apply(new Add(new RecipeGemCutter(InputHelper.toStack(input), InputHelper.toStack(output), inputAmount, outputAmount)));
	}

	public static class Add extends BaseListAddition {
		public Add(RecipeGemCutter recipe) {
			super("Fluxed-Crystals Gem Cutter", RecipeRegistry.getGemCutterRecipes(), recipe);
		}
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output) {
		MineTweakerAPI.apply(new Remove(InputHelper.toStack(output)));
	}

	public static class Remove extends BaseListRemoval {
		public Remove(ItemStack stack) {
			super("Fluxed-Crystals Gem Cutter", RecipeRegistry.getGemCutterRecipes(), stack);
		}

		@Override
		public void apply() {
			for (Object o : RecipeRegistry.getGemCutterRecipes()) {
				if (o instanceof RecipeGemCutter) {
					RecipeGemCutter r = (RecipeGemCutter) o;
					if (r.getOutput() != null && r.getOutput() instanceof ItemStack && StackHelper.areEqual((ItemStack) r.getOutput(), stack)) {
						recipe = r;
						break;
					}
				}
			}

			RecipeRegistry.getGemCutterRecipes().remove(recipe);
		}
	}
}
