package fluxedCrystals.config;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import org.apache.commons.io.filefilter.FileFilterUtils;

import thaumcraft.api.aspects.Aspect;
import tterrag.core.common.config.AbstractConfigHandler;
import tterrag.core.common.config.JsonConfigReader;
import tterrag.core.common.config.JsonConfigReader.ModToken;
import tterrag.core.common.util.TTFileUtils;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.api.recipe.RecipeGemCutter;
import fluxedCrystals.api.recipe.RecipeGemRefiner;
import fluxedCrystals.api.recipe.RecipeSeedInfuser;
import fluxedCrystals.api.recipe.SeedCrystalRecipe;
import fluxedCrystals.config.json.ISeedType;
import fluxedCrystals.config.json.SeedType;
import fluxedCrystals.items.FCItems;
import fluxedCrystals.utils.ModUtils;

public class ConfigHandler extends AbstractConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	private ConfigHandler() {
		super(ModProps.modid);
	}

	@Override
	protected void init() {
		addSection(ConfigProps.addonCategory, "Addons");
		addSection(ConfigProps.dropCategory, "Drops");

	}

	@Override
	protected void reloadNonIngameConfigs() {
	}

	@Override
	public void reloadIngameConfigs() {
		activateSection(ConfigProps.addonCategory);
		ConfigProps.enderioAddon = getProperty("EnderIO Addon Support", true).getBoolean(true);
		ConfigProps.aspectString = getProperty("Override Aspect for Crystals. (null for nothing)", "null").getString();
		ConfigProps.aspectRange = getProperty("Override Aspect Range for Crystals. (0 for nothing)", "0").getInt();
		if (!ConfigProps.aspectString.equals("null"))
			ConfigProps.aspect = Aspect.getAspect(ConfigProps.aspectString);

		activateSection(ConfigProps.dropCategory);
		ConfigProps.normalShardRecipes = getProperty("Should materials be crafted in a normal crafting table?", false).getBoolean(false);
		ConfigProps.shard3x3 = getProperty("Should shards craft into the ingredients with 9 of the drops, or with 4 of the drop?", true).getBoolean(true);
		RecipeRegistry.reset();

		ModToken token = new ModToken(FluxedCrystals.class, ModProps.modid + "/misc/");

        String basePath = FluxedCrystals.configDir.getAbsolutePath();
        List<File> cropFiles = Arrays.asList(FluxedCrystals.configDir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json")));

		File crops = new File(basePath + "/crystal.json");
		File thermalCrops = new File(basePath + "/thermalCrystal.json");

		if (!crops.exists()) {
			TTFileUtils.copyFromJar(FluxedCrystals.class, ModProps.modid + "/misc/" + "crystal.json", crops);
		}
		if (!thermalCrops.exists() && ModUtils.isModLoaded("ThermalFoundation")) {
			TTFileUtils.copyFromJar(FluxedCrystals.class, ModProps.modid + "/misc/thermalCrystal.json", thermalCrops);
		}

		if (ModUtils.isModLoaded("EnderIO") && ConfigProps.enderioAddon) {
			File enderioCrops = new File(basePath + "/enderioCrystal.json");
			if (!enderioCrops.exists()) {
				TTFileUtils.copyFromJar(FluxedCrystals.class, ModProps.modid + "/misc/" + "enderioCrystal.json", enderioCrops);
			}
		}

		Collections.sort(cropFiles, new Comparator<File>() {
			public int compare(File o1, File o2) {
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
		});

		JsonConfigReader<SeedType> cropReader;
		for (int i = 0; i < cropFiles.size(); i++) {
			if (cropFiles.get(i) != null) {
				cropReader = new JsonConfigReader<SeedType>(token, cropFiles.get(i), SeedType.class);
				registerAll(cropReader.getElements());
			}
		}
		for (int i = 0; i < RecipeRegistry.getSeedCropRecipes().size(); i++) {
			SeedCrystalRecipe r = RecipeRegistry.getSeedCropRecipes().get(i);
			RecipeRegistry.registerGemCutterRecipe(new RecipeGemCutter(new ItemStack(FCItems.roughShard, 1, i), new ItemStack(FCItems.shard, 1, i), 1, 1));
			RecipeRegistry.registerSeedInfuserRecipe(new RecipeSeedInfuser(new ItemStack(FCItems.universalSeed), r.getIngredient(), new ItemStack(FCItems.seed, 1, i), RecipeRegistry.getIngredientAmount(i)));
			if (!(Block.getBlockFromName("minecraft:portal") == Block.getBlockFromItem(r.getDrop().getItem()))) {
				RecipeRegistry.registerGemRefinerRecipe(new RecipeGemRefiner(new ItemStack(FCItems.shard, 1, i), r.getDrop(), r.getRefinerAmount(), RecipeRegistry.getDropAmount(r.getDropMin(), r.getDropMax())));
			} else {
				RecipeRegistry.registerGemRefinerRecipe(new RecipeGemRefiner(new ItemStack(FCItems.shard, 1, i), r.getIngredient(), r.getRefinerAmount(), RecipeRegistry.getDropAmount(r.getDropMin(), r.getDropMax())));

			}
		}
	}

	public static void registerAll(Collection<? extends ISeedType> types) {
		for (ISeedType type : types) {
			type.register();
		}
	}
}
