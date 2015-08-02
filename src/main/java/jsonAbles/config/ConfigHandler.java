package jsonAbles.config;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import jsonAbles.JsonAbles;
import jsonAbles.ModProps;
import jsonAbles.api.json.JSONParser;
import jsonAbles.config.json.AspectType;
import jsonAbles.config.json.BrewType;
import jsonAbles.config.json.FluidType;
import jsonAbles.config.json.HammerType;
import jsonAbles.config.json.IJSONObject;
import jsonAbles.config.json.MaterialType;
import jsonAbles.config.json.OreBerryType;
import jsonAbles.config.json.SmelteryFuelType;
import jsonAbles.config.json.StarType;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();
	private static ResourcePackAssembler assembler;

	public ConfigHandler() {
	}

	public void init() {
		assembler = new ResourcePackAssembler(new File(JsonAbles.configDir.getAbsolutePath() + "/JSONAbles-Resourcepack"), "JSONAbles Resource Pack", ModProps.modid);

		String basePath = JsonAbles.configDir.getAbsolutePath();

		if (!(new File(basePath + "/jsons/").exists())) {
			try {
				FileUtils.forceMkdir(new File(basePath + "/jsons/"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File template = new File(basePath + "/jsons/template.json");

		ArrayList<File> files = convertArrayToList(new File(basePath + "/jsons/").listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json")));
		ArrayList<String> KEYS = new ArrayList<String>();

		/** TiC **/
		KEYS.add("materials");
		KEYS.add("oreBerries");
		KEYS.add("smelteryFuels");
		/** Vanilla **/
		KEYS.add("fluids");
		/** TC **/
		KEYS.add("aspects");
		/** Botania **/
		KEYS.add("brews");
		KEYS.add("hammers");

		if (!template.exists()) {
			jsonAbles.api.utils.FileUtils.copyFromJar(JsonAbles.class, ModProps.modid + "/misc/template.json", template);
		}

		if (!files.isEmpty()) {
			JSONParser<MaterialType> readerMaterials;
			JSONParser<FluidType> readerFluids;
			JSONParser<AspectType> readerAspects;
			JSONParser<OreBerryType> readerOreBerries;
			JSONParser<SmelteryFuelType> readerSmelteryFuels;
			JSONParser<BrewType> readerBrews;
			JSONParser<HammerType> readerHammers;

			for (File f : files) {
				try {
					readerMaterials = new JSONParser<MaterialType>(f, MaterialType.class);
					readerFluids = new JSONParser<FluidType>(f, FluidType.class);
					readerAspects = new JSONParser<AspectType>(f, AspectType.class);
					readerOreBerries = new JSONParser<OreBerryType>(f, OreBerryType.class);
					readerSmelteryFuels = new JSONParser<SmelteryFuelType>(f, SmelteryFuelType.class);
					readerBrews = new JSONParser<BrewType>(f, BrewType.class);
					readerHammers = new JSONParser<HammerType>(f, HammerType.class);

					registerAll(readerMaterials.getElements("materials"));
					registerAll(readerFluids.getElements("fluids"));
					registerAll(readerAspects.getElements("aspects"));
					registerAll(readerOreBerries.getElements("oreBerries"));
					registerAll(readerSmelteryFuels.getElements("smelteryFuels"));
					registerAll(readerBrews.getElements("brews"));
					registerAll(readerHammers.getElements("hammers"));

				} catch (Exception e) {
					JsonAbles.logger.warn("Configuration error!", e);
				}
			}

		}

		addItems(assembler);
		addAspects(assembler);
		addBlocks(assembler);
		addLangs(assembler);

		assembler.assemble().inject();
	}

	private static void addItems(ResourcePackAssembler assembler) {
		initialize("icons/items");
		for (File f : new File(JsonAbles.configDir.getAbsolutePath() + "/icons/items").listFiles()) {
			assembler.addCustomFile("/assets/" + ModProps.modid + "/textures/items", f);
		}
	}

	private static void addBlocks(ResourcePackAssembler assembler) {
		initialize("icons/blocks");
		for (File f : new File(JsonAbles.configDir.getAbsolutePath() + "/icons/blocks").listFiles()) {
			assembler.addCustomFile("/assets/" + ModProps.modid + "/textures/blocks", f);
		}
	}

	private static void addAspects(ResourcePackAssembler assembler) {
		initialize("icons/aspects");
		for (File f : new File(JsonAbles.configDir.getAbsolutePath() + "/icons/aspects").listFiles()) {
			assembler.addCustomFile("/assets/" + ModProps.modid + "/textures/aspects", f);
		}
	}

	private static void addLangs(ResourcePackAssembler assembler) {
		initialize("lang");
		for (File f : new File(JsonAbles.configDir.getAbsolutePath() + "/lang").listFiles()) {
			assembler.addLang(f);
		}
	}

	public static void registerAll(Collection<? extends IJSONObject> types) {
		for (IJSONObject type : types) {
			type.register();
		}

	}

	public static void registerAllMaterials(Collection<? extends MaterialType> types) {
		for (MaterialType type : types) {
			type.register();
		}
	}

	public static void registerAllFluids(Collection<? extends FluidType> types) {
		for (FluidType type : types) {
			type.register();
		}
	}

	public static void registerAllAspects(Collection<? extends AspectType> types) {
		for (AspectType type : types) {
			type.register();
		}
	}

	public static void registerAllStars(Collection<? extends StarType> types) {
		for (StarType type : types) {
			type.register();
		}
	}

	public static void registerAllBerries(Collection<? extends OreBerryType> types) {
		for (OreBerryType type : types) {
			type.register();
		}
	}

	public static ArrayList<File> convertArrayToList(File[] files) {
		ArrayList<File> returnList = new ArrayList<File>();

		if (files != null && files.length > 0)
			for (File file : files) {
				if (file != null)
					returnList.add(file);
			}
		return returnList;
	}

	private static void initialize(String dir) {
		File temp = new File(JsonAbles.configDir.getAbsolutePath() + "/" + dir);
		temp.mkdirs();
	}
}
