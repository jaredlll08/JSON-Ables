package fluxedCrystals.compat.minetweaker;

import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.compat.minetweaker.helper.InputHelper;
import fluxedCrystals.compat.minetweaker.helper.StackHelper;

@ZenClass("mods.fluxedcrystals.refiner")
public class GemRefiner {
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack input, int inputAmount, int outputAmount) {
		MineTweakerAPI.apply(new Add(new RecipeGemRefiner(InputHelper.toStack(input), InputHelper.toStack(output), inputAmount, outputAmount)));
	}

	public static class Add extends BaseListAddition {
		public Add(RecipeGemRefiner recipe) {
			super("Fluxed-Crystals Gem Refiner", RecipeRegistry.getGemRefinerRecipes(), recipe);
		}
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output) {
		MineTweakerAPI.apply(new Remove(InputHelper.toStack(output)));
	}

	public static class Remove extends BaseListRemoval {
		public Remove(ItemStack stack) {
			super("Fluxed-Crystals Gem Refiner", RecipeRegistry.getGemRefinerRecipes(), stack);
		}

		@Override
		public void apply() {
			for (Object o : RecipeRegistry.getGemRefinerRecipes()) {
				if (o instanceof RecipeGemRefiner) {
					RecipeGemRefiner r = (RecipeGemRefiner) o;
					if (r.getOutput() != null && r.getOutput() instanceof ItemStack && StackHelper.areEqual((ItemStack) r.getOutput(), stack)) {
						recipe = r;
						break;
					}
				}
			}

			RecipeRegistry.getGemRefinerRecipes().remove(recipe);
		}
	}
}
