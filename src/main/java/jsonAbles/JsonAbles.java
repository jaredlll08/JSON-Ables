package jsonAbles;

import static jsonAbles.ModProps.modid;
import static jsonAbles.ModProps.name;
import static jsonAbles.ModProps.version;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.WandCapSet;
import jsonAbles.commands.CommandLog;
import jsonAbles.config.ConfigHandler;
import jsonAbles.proxy.CommonProxy;
import jsonAbles.world.OreBerryWorldGen;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import thaumcraft.api.wands.WandCap;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = modid, name = name, version = version, dependencies = "after:TConstruct;")
public class JsonAbles {

	public static int liquidRender;

	public static File configDir = null;
	@Instance(modid)
	public static JsonAbles instance;

	public static final Logger logger = LogManager.getLogger(name);
	@SidedProxy(clientSide = "jsonAbles.proxy.ClientProxy", serverSide = "jsonAbles.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configDir = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + "/" + modid);
		ConfigHandler config = new ConfigHandler();
		config.init();
		proxy.initRenderers();
		if (Loader.isModLoaded("TConstruct"))
			MinecraftForge.EVENT_BUS.register(new ToolEventHandler());

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (Loader.isModLoaded("Thaumcraft")) {
			RecipeRegistry.registerAspects();
		}
		if (Loader.isModLoaded("TConstruct")) {
			RecipeRegistry.registerToolMaterials();
			RecipeRegistry.registerOreBerries();
			RecipeRegistry.registerSmelteryFuels();
			GameRegistry.registerWorldGenerator(new OreBerryWorldGen(), 0);
		}
		RecipeRegistry.registerFluids();

		if (Loader.isModLoaded("Botania")) {
			RecipeRegistry.registerBrews();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		try {
			CommandLog.writer = new PrintWriter(new File("logs/JSONAbles-Log.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		event.registerServerCommand(new CommandLog());

	}

}
