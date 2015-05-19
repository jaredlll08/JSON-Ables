package fluxedCrystals.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.items.seeds.ItemSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.items.upgrades.Upgrade;

public class FCItems {

	public static Item universalSeed = new ItemUniversalSeed();
	public static Item seed = new ItemSeed();
	public static Item shard = new ItemShard();
	public static Item crystalHammer = new ItemCrystalHammer();
	public static Item scytheWood = new ItemScythe();
	public static Item scytheStone = new ItemScythe();
	public static Item scytheIron = new ItemScythe();
	public static Item scytheGold = new ItemScythe();
	public static Item scytheDiamond = new ItemScythe();

	public static Item upgradeEffeciency = new Upgrade();
	public static Item upgradeNight = new Upgrade();
	public static Item upgradeSpeed = new Upgrade();
	public static Item upgradeAutomation = new Upgrade();

	public static Item upgradeMana = new Upgrade();
	public static Item upgradeLP = new Upgrade();
	public static Item upgradeEssentia = new Upgrade();
	
	public static Item upgradeRangeBasic = new Upgrade();
	public static Item upgradeRangeGreater = new Upgrade();
	public static Item upgradeRangeAdvanced= new Upgrade();

	public static Item roughChunk = new ItemRoughChunk();
	public static Item gemCutter = new ItemGemCutter();
	public static Item roughShard = new ItemRoughShard();

	// public static Item armorHelm = new ItemShardHelm();
	// public static Item armorChest= new ItemShardChest();
	// public static Item armorLegs= new ItemShardLegs();
	// public static Item armorBoots= new ItemShardBoots();
	//
	public static void init() {
		registerItems();
		registerNBT();
		registerRecipes();

	}

	private static void registerRecipes() {
	}

	private static void registerItems() {
		seed.setCreativeTab(FluxedCrystals.tab);
		shard.setCreativeTab(FluxedCrystals.tab);
		roughChunk.setCreativeTab(FluxedCrystals.tab);
		universalSeed.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":seed").setUnlocalizedName("universalSeed");

		upgradeEffeciency.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeEffeciency").setUnlocalizedName("upgradeEfficiency");
		upgradeNight.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeNight").setUnlocalizedName("upgradeNight");
		upgradeSpeed.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeSpeed").setUnlocalizedName("upgradeSpeed");
		upgradeAutomation.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeAutomation").setUnlocalizedName("upgradeAutomation");

		upgradeMana.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeMana").setUnlocalizedName("upgradeMana");
		upgradeEssentia.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeEssentia").setUnlocalizedName("upgradeEssentia");
		upgradeLP.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeLP").setUnlocalizedName("upgradeLP");
		
		upgradeRangeBasic.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeRangeBasic").setUnlocalizedName("upgradeRangeBasic");
		upgradeRangeGreater.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeRangeGreater").setUnlocalizedName("upgradeRangeGreater");
		upgradeRangeAdvanced.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":UpgradeRangeAdvanced").setUnlocalizedName("upgradeRangeAdvanced");
		

		registerItem(crystalHammer, "Crystal Hammer", "hammerCrystal");
		registerItem(scytheWood, "Wooden Scythe", "scytheWood");
		registerItem(scytheStone, "Stone Scythe", "scytheStone");
		registerItem(scytheIron, "Iron Scythe", "scytheIron");
		registerItem(scytheGold, "Gold Scythe", "scytheGold");
		registerItem(scytheDiamond, "Diamond Scythe", "scytheDiamond");

		GameRegistry.registerItem(upgradeEffeciency, "upgradeEffeciency");
		GameRegistry.registerItem(upgradeNight, "upgradeNight");
		GameRegistry.registerItem(upgradeSpeed, "upgradeSpeed");
		GameRegistry.registerItem(upgradeAutomation, "upgradeAutomation");

		GameRegistry.registerItem(upgradeMana, "upgradeMana");
		GameRegistry.registerItem(upgradeEssentia, "upgradeEssentia");
		GameRegistry.registerItem(upgradeLP, "upgradeLP");
		GameRegistry.registerItem(upgradeRangeBasic, "upgradeRangeBasic");
		GameRegistry.registerItem(upgradeRangeGreater, "upgradeRangeGreater");
		GameRegistry.registerItem(upgradeRangeAdvanced, "upgradeRangeAdvanced");

		GameRegistry.registerItem(universalSeed, "universalSeed");
		GameRegistry.registerItem(seed, "seed");
		GameRegistry.registerItem(shard, "shard");
		GameRegistry.registerItem(roughChunk, "roughChunk");
		registerItem(gemCutter, "Gem Cutter", "gemCutter");
		registerItem(roughShard, "Rough Shard", "roughShard");

		// registerArmor(armorHelm, "shardHelm", "shardHelm", "Helm");
		// registerArmor(armorChest, "shardChest", "shardChest", "Chest");
		// registerArmor(armorLegs, "shardLegs", "shardLegs", "Legs");
		// registerArmor(armorBoots, "shardBoots", "shardBoots", "Boots");
		//
	}

	private static void registerNBT() {
	}

	private static void registerArmor(Item item, String name, String key, String type) {
		item.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":shard" + type).setUnlocalizedName(key);
		GameRegistry.registerItem(item, key);
	}

	private static void registerItem(Item item, String name, String key) {
		item.setCreativeTab(FluxedCrystals.tab).setTextureName(ModProps.modid + ":" + key).setUnlocalizedName(key);
		GameRegistry.registerItem(item, key);
	}

}
