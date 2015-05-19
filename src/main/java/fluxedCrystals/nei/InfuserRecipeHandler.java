package fluxedCrystals.nei;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;

public class InfuserRecipeHandler extends TemplateRecipeHandler {

	@Override
	public String getGuiTexture() {
		return ModProps.modid + ":textures/gui/SeedInfuser.png";
	}

	@Override
	public String getRecipeName() {
		return "Seed Infuser";
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	private final ResourceLocation texture = new ResourceLocation(getGuiTexture());

	@Override
	public void drawBackground(int recipe) {

		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(5, 5, 0, 166, 18, 18);
		GuiDraw.drawTexturedModalRect(142, 5, 0, 166, 18, 18);
		GuiDraw.drawTexturedModalRect(5, 43, 0, 166, 18, 18);

	}

	@Override
	public void drawExtras(int recipe) {
		CachedInfusionRecipe r = (CachedInfusionRecipe) arecipes.get(recipe);
		int coords2[] = { 0, 0 };
		GL11.glScalef(.08f, .08f, .08f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glScalef(15.625f, 15.625f, 15.625f);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		List<RecipeSeedInfuser> recipes = RecipeRegistry.getSeedRecipes();

		for (int j = 0; j < recipes.size(); j++) {
			RecipeSeedInfuser recipe = recipes.get(j);
			if (recipe.getOutput().isItemEqual(result)) {
				if (checkDupe(recipe)) {
					this.arecipes.add(new CachedInfusionRecipe(recipe));
				}
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		List<RecipeSeedInfuser> recipes = RecipeRegistry.getSeedRecipes();

		for (int j = 0; j < recipes.size(); j++) {
			RecipeSeedInfuser recipe = recipes.get(j);
			if (recipe.getInput().isItemEqual(ingredient) || recipe.getIngredient().isItemEqual(ingredient)) {
				if (checkDupe(recipe)) {
					this.arecipes.add(new CachedInfusionRecipe(recipe));
				}
			}
		}
	}

	private boolean checkDupe(RecipeSeedInfuser recipe) {
		for (Object o : this.arecipes.toArray()) {
			if (o instanceof CachedInfusionRecipe) {
				CachedInfusionRecipe r = (CachedInfusionRecipe) o;
				if (r.recipe.getInput() == recipe.getInput()) {
					if (r.recipe.getOutput() == recipe.getOutput()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public class CachedInfusionRecipe extends CachedRecipe {

		private PositionedStack output;
		private PositionedStack inputs;
		private List<PositionedStack> other = new ArrayList<PositionedStack>();

		public RecipeSeedInfuser recipe;

		public CachedInfusionRecipe(RecipeSeedInfuser recipe) {
			this.output = new PositionedStack(recipe.getOutput(), 6, 44);
			this.recipe = recipe;
			this.inputs = new PositionedStack(recipe.getInput(), 143, 6);
			this.other.add(new PositionedStack(recipe.getIngredient(), 6, 6));
			inputs.setMaxSize(recipe.getInputamount());
		}

		@Override
		public PositionedStack getResult() {
			return this.output;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.inputs;
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			return other;
		}
	}

	@Override
	public String getOverlayIdentifier() {
		return "seedInfuser";
	}

}
