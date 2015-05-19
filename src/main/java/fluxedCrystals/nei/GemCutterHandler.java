package fluxedCrystals.nei;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;

public class GemCutterHandler extends TemplateRecipeHandler {

	@Override
	public String getGuiTexture() {
		return ModProps.modid + ":textures/gui/SeedInfuser.png";
	}

	@Override
	public String getRecipeName() {
		return "Gem Cutter";
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

		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void drawForeground(int recipe) {
		super.drawForeground(recipe);
	}

	@Override
	public void drawExtras(int recipe) {
		CachedCutterRecipe r = (CachedCutterRecipe) arecipes.get(recipe);
		int coords2[] = { 0, 0 };
		GL11.glScalef(.08f, .08f, .08f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glScalef(15.625f, 15.625f, 15.625f);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		List<RecipeGemCutter> recipes = RecipeRegistry.getGemCutterRecipes();

		for (int j = 0; j < recipes.size(); j++) {
			RecipeGemCutter recipe = recipes.get(j);
			if (recipe.getOutput().isItemEqual(result)) {
				if (checkDupe(recipe))
					this.arecipes.add(new CachedCutterRecipe(recipe));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		List<RecipeGemCutter> recipes = RecipeRegistry.getGemCutterRecipes();

		for (int j = 0; j < recipes.size(); j++) {
			RecipeGemCutter recipe = recipes.get(j);
			if (recipe.getInput().isItemEqual(ingredient)) {
				if (checkDupe(recipe))
					this.arecipes.add(new CachedCutterRecipe(recipe));
			}
		}
	}

	private boolean checkDupe(RecipeGemCutter recipe) {
		for (Object o : this.arecipes.toArray()) {
			if (o instanceof CachedCutterRecipe) {
				CachedCutterRecipe r = (CachedCutterRecipe) o;
				if (r.recipe.getInput().isItemEqual(recipe.getInput())) {
					if (r.recipe.getOutput().isItemEqual(recipe.getOutput())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public class CachedCutterRecipe extends CachedRecipe {

		private PositionedStack output;
		private PositionedStack inputs;

		public RecipeGemCutter recipe;

		public CachedCutterRecipe(RecipeGemCutter recipe) {
			this.output = new PositionedStack(recipe.getOutput(), 143, 6);
			this.recipe = recipe;
			this.inputs = new PositionedStack(recipe.getInput(), 6, 6);
			output.item.stackSize = 1;
			inputs.item.stackSize = 1;
			

		}

		@Override
		public PositionedStack getResult() {
			return this.output;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.inputs;
		}

	}

	@Override
	public String getOverlayIdentifier() {
		return "gemCutter";
	}

}
