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
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.compat.minetweaker.helper.InputHelper;
import fluxedCrystals.compat.minetweaker.helper.StackHelper;

@ZenClass("mods.fluxedcrystals.infuser")
public class SeedInfuser {
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack leftInput, IItemStack rightInput, int rightInputAmount, int ID) {
		MineTweakerAPI.apply(new Add(new RecipeSeedInfuser(InputHelper.toStack(leftInput), InputHelper.toStack(rightInput), InputHelper.toStack(output), rightInputAmount)));
	}

	public static class Add extends BaseListAddition {
		public Add(RecipeSeedInfuser recipe) {
			super("Fluxed-Crystals Seed Infuser", RecipeRegistry.getSeedRecipes(), recipe);
		}
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output) {
		MineTweakerAPI.apply(new Remove(InputHelper.toStack(output)));
	}

	public static class Remove extends BaseListRemoval {
		public Remove(ItemStack stack) {
			super("Fluxed-Crystals Seed Infuser", RecipeRegistry.getSeedRecipes(), stack);
		}

		@Override
		public void apply() {
			for (Object o : RecipeRegistry.getSeedRecipes()) {
				if (o instanceof RecipeSeedInfuser) {
					RecipeSeedInfuser r = (RecipeSeedInfuser) o;
					if (r.getOutput() != null && r.getOutput() instanceof ItemStack && StackHelper.areEqual((ItemStack) r.getOutput(), stack)) {
						recipe = r;
						break;
					}
				}
			}

			RecipeRegistry.getSeedRecipes().remove(recipe);
		}
	}
}
