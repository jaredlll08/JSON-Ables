package jsonAbles.api;

import java.util.ArrayList;

import jsonAbles.ModProps;
import jsonAbles.blocks.MoltenFluid;
import jsonAbles.items.ItemFluidBucket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.PatternBuilder;
import tconstruct.tools.TinkerTools;
import thaumcraft.api.aspects.Aspect;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry {

	public static ArrayList<MaterialSet> materials = new ArrayList<MaterialSet>();
	public static ArrayList<FluidSet> fluids = new ArrayList<FluidSet>();
	public static ArrayList<AspectSet> aspects = new ArrayList<AspectSet>();
	public static ArrayList<OreBerrySet> oreBerries = new ArrayList<OreBerrySet>();
	public static ArrayList<SmelteryFuelSet> smelteryFuels = new ArrayList<SmelteryFuelSet>();
	public static ArrayList<BrewSet> brews = new ArrayList<BrewSet>();

	public static ArrayList<ItemSet> items = new ArrayList<ItemSet>();

	public static void registerItem(ItemSet set) {
		for (ItemSet s : items) {
			if (s.unlocalizedName.equalsIgnoreCase(set.unlocalizedName))
				;
		}
	}


	public static void registerBrew(BrewSet set) {
		brews.add(set);
	}


	public static int getToolModifiers(int materialID) {
		for (MaterialSet set : materials) {
			if (set.materialID == materialID) {
				return set.modifiers;
			}
		}
		return 0;
	}

	public static ItemStack[][] getNativeModifiers(int materialID) {
		for (MaterialSet set : materials) {
			if (set.materialID == materialID) {
				return set.nativeModifiers;
			}
		}
		return null;
	}

	public static boolean getMaterialCasting(int materialID) {
		for (MaterialSet set : materials) {
			if (set.materialID == materialID) {
				return true;
			}
		}
		return false;
	}

	public static int getMaterialCastingFluidID(Fluid fluid) {
		for (MaterialSet set : materials) {
			for (FluidSet f : fluids)
				if (f.getCastingMaterialID() == set.materialID)
					return set.materialID;
		}
		return 0;
	}

	public static String getToolLore(int materialID) {
		for (MaterialSet set : materials) {
			if (set.materialID == materialID) {
				return set.lore;
			}
		}
		return null;

	}
	public static boolean isJsonables(int materialID){
		for (MaterialSet set : materials) {
			if (set.materialID == materialID) {
				return true;
			}
		}
		return false;
	}

	public static boolean registerMaterialSet(MaterialSet set) {
		for (MaterialSet mat : materials) {
			if (mat.getKey().equals(set.getKey())) {
				return false;
			}
			if (mat.getMaterialID() == set.getMaterialID()) {
				return false;
			}

		}
		materials.add(set);
		return true;
	}

	public static boolean registerSmelteryFuelSet(SmelteryFuelSet set) {
		for (SmelteryFuelSet mat : smelteryFuels) {
			if (mat.fluidName.equalsIgnoreCase(set.fluidName)) {
				return false;
			}
		}
		smelteryFuels.add(set);
		return true;
	}

	public static boolean registerOreBerryBushes(OreBerrySet set) {
		for (OreBerrySet s : oreBerries)
			if (s.drop.isItemEqual(set.drop))
				return false;
		oreBerries.add(set);
		return true;
	}

	public static boolean registerFluidSet(FluidSet set) {
		for (FluidSet mat : fluids) {
			if (mat.getUnlocalizedName().equals(set.getUnlocalizedName())) {
				return false;
			}

		}
		fluids.add(set);
		return true;
	}

	public static boolean registerAspectSet(AspectSet set) {
		for (AspectSet mat : aspects) {
			if (mat.tag.equalsIgnoreCase(set.tag)) {
				return false;
			}
		}
		aspects.add(set);
		return true;
	}

	public static void registerOreBerries() {
		for (OreBerrySet set : oreBerries) {
			GameRegistry.registerBlock(set.oreBerry, set.drop.getDisplayName() + " bush");
		}
	}


	public static void registerBrews() {
		for (BrewSet set : brews) {
			if (set != null && set.potionEffectNames != null)
				if (set.potionEffectNames.length > 0) {
					PotionEffect[] potions = new PotionEffect[set.potionEffectNames.length];
					for (int i = 0; i < potions.length; i++) {
						PotionEffect e = new PotionEffect(getPotionID(set.potionEffectNames[i]), set.potionEffectDuration[i], set.potionEffectAmplifier[i], set.potionEffectParticles[i]);
						potions[i] = e;
					}
					BotaniaAPI.registerBrew(new Brew(set.key, set.name, set.color, set.cost, potions));
				}
		}
	}

	private static int getPotionID(String name) {
		for (Potion p : Potion.potionTypes) {
			if (p != null)
				if (p.getName().equalsIgnoreCase(name)) {
					return p.getId();
				}
		}
		return 0;
	}

	public static void registerSmelteryFuels() {
		for (SmelteryFuelSet set : smelteryFuels) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("FluidName", set.fluidName);
			tag.setInteger("Temperature", set.temperature);
			tag.setInteger("Duration", set.duration);
			FMLInterModComms.sendMessage("TConstruct", "addSmelteryFuel", tag);
		}
	}

	public static void registerAspects() {
		for (AspectSet set : aspects) {
			Aspect aspect = new Aspect(set.tag, set.color, new Aspect[] { set.aspect1, set.aspect2 }, new ResourceLocation(ModProps.modid, "textures/aspects/" + set.tag + ".png"), 1);
		}
	}

	public static void registerFluids() {
		for (FluidSet set : fluids) {
			Fluid moltenFluid;
			moltenFluid = new Fluid(set.unlocalizedName).setLuminosity(set.luminosity).setDensity(set.density).setViscosity(set.viscosity).setTemperature(set.temperature).setGaseous(set.gaseous);
			FluidRegistry.registerFluid(moltenFluid);
			Material mat= Material.air;
			if(set.setFire){
				mat = Material.lava;
			}
			if(!set.setFire){
				mat = Material.water;
			}
			MoltenFluid fluidBlock = new MoltenFluid(moltenFluid, set.color, mat, set);
			fluidBlock.setBlockName(set.unlocalizedName);
			GameRegistry.registerBlock(fluidBlock, set.unlocalizedName);

			Item bucket = new ItemFluidBucket(fluidBlock, set);
			bucket.setUnlocalizedName(set.unlocalizedName + "_bucket");
			GameRegistry.registerItem(bucket, set.unlocalizedName + "_bucket");
			NBTTagCompound tag = new NBTTagCompound();
			NBTTagCompound item = new NBTTagCompound();
			// if (set.meltingItems != null) {
			// for (int i = 0; i < set.meltingItems.length; i++) {
			// tag = new NBTTagCompound();
			// item = new NBTTagCompound();
			// set.meltingItems[i].writeToNBT(item);
			// tag.setTag("Item", item);
			// item = new NBTTagCompound();
			// (new ItemStack(fluidBlock)).writeToNBT(item);
			// tag.setTag("Block", item);
			// (new FluidStack(moltenFluid, 5000)).writeToNBT(tag);
			// tag.setInteger("Temperature", set.meltingItemsTemperature[i]);
			// FMLInterModComms.sendMessage("TConstruct", "addSmelteryMelting",
			// tag);
			// }
			// }
			if (set.castingMaterialID > 0) {
				tag = new NBTTagCompound();
				tag.setString("FluidName", set.unlocalizedName);
				(new FluidStack(moltenFluid, 1)).writeToNBT(tag);
				tag.setInteger("MaterialId", set.castingMaterialID);
				FMLInterModComms.sendMessage("TConstruct", "addPartCastingMaterial", tag);
			}
			Refrence.fluids.add(moltenFluid);
			Refrence.fluidBlocks.add(fluidBlock);
			Refrence.fluidBuckets.add(bucket);

		}
	}

	public static void registerToolMaterials() {
		PatternBuilder pb = PatternBuilder.instance;
		for (MaterialSet set : materials) {
			NBTTagCompound tag = new NBTTagCompound();
			NBTTagCompound item = new NBTTagCompound();
			tag.setInteger("Id", set.materialID);
			tag.setString("Name", set.key);
			tag.setInteger("HarvestLevel", set.harvestLevel);
			tag.setInteger("Durability", set.durability);
			tag.setInteger("MiningSpeed", set.miningSpeed);
			tag.setInteger("Attack", set.attack);
			tag.setFloat("HandleModifier", set.handleModifier);
			tag.setInteger("Reinforced", set.reinforced);
			tag.setFloat("Stonebound", set.stonebound);
			tag.setString("Style", set.style);
			tag.setInteger("Color", set.primaryColor);
			tag.setInteger("Bow_DrawSpeed", set.bowDrawSpeed);
			tag.setFloat("Bow_ProjectileSpeed", set.bowSpeedMax);
			tag.setFloat("Projectile_Mass", set.arrowMass);
			tag.setFloat("Projectile_Fragility", set.arrowBreakChance);
			FMLInterModComms.sendMessage("TConstruct", "addMaterial", tag);

			tag = new NBTTagCompound();
			tag.setInteger("MaterialId", set.materialID);
			tag.setInteger("Value", set.value);
			item = new NBTTagCompound();
			set.resource.writeToNBT(item);
			tag.setTag("Item", item);

			FMLInterModComms.sendMessage("TConstruct", "addMaterialItem", tag);

			if (set.buildParts) {
				tag = new NBTTagCompound();
				tag.setInteger("MaterialId", set.materialID);
				item = new NBTTagCompound();
				set.resource.writeToNBT(item);
				tag.setTag("Item", item);
				item = new NBTTagCompound();
				(new ItemStack(TinkerTools.toolShard, 1, set.materialID)).writeToNBT(item);
				tag.setTag("Shard", item);
				tag.setInteger("Value", set.value);
				FMLInterModComms.sendMessage("TConstruct", "addPartBuilderMaterial", tag);
			}
		}

	}

	public static void castMolten(Fluid fluid, int ID) {
		// .addCastingRecipe(output, fluid, cast, hardeningDelay)
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.toolRod, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("toolRodCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.pickaxeHead, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("pickaxeHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.shovelHead, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("shovelHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.hatchetHead, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("hatchetHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.swordBlade, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("swordBladeCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.fullGuard, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("fullGuardCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.wideGuard, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("wideGuardCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.crossbar, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("crossbarCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.binding, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("bindingCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.handGuard, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("handGuardCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.frypanHead, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("frypanHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.signHead, 1, ID), new FluidStack(fluid, (int) (144 * 1.0D)), TConstructRegistry.getItemStack("signHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.knifeBlade, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("knifeBladeCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.chiselHead, 1, ID), new FluidStack(fluid, (int) (144 * 0.5D)), TConstructRegistry.getItemStack("chiselHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.toughRod, 1, ID), new FluidStack(fluid, (int) (144 * 3.0D)), TConstructRegistry.getItemStack("toughRodCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.toughBinding, 1, ID), new FluidStack(fluid, (int) (144 * 3.0D)), TConstructRegistry.getItemStack("toughBindingCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.largePlate, 1, ID), new FluidStack(fluid, (int) (144 * 8.0D)), TConstructRegistry.getItemStack("largePlateCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.broadAxeHead, 1, ID), new FluidStack(fluid, (int) (144 * 8.0D)), TConstructRegistry.getItemStack("broadAxeHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.scytheBlade, 1, ID), new FluidStack(fluid, (int) (144 * 8.0D)), TConstructRegistry.getItemStack("scytheHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.excavatorHead, 1, ID), new FluidStack(fluid, (int) (144 * 8.0D)), TConstructRegistry.getItemStack("excavatorHeadCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.largeSwordBlade, 1, ID), new FluidStack(fluid, (int) (144 * 8.0D)), TConstructRegistry.getItemStack("largeBladeCast"), 50);
		TConstructRegistry.instance.getTableCasting().addCastingRecipe(new ItemStack(TinkerTools.hammerHead, 1, ID), new FluidStack(fluid, (int) (144 * 8.0D)), TConstructRegistry.getItemStack("hammerHeadCast"), 50);
	}

	public static class Refrence {
		public static ArrayList<Fluid> fluids = new ArrayList<Fluid>();
		public static ArrayList<Block> fluidBlocks = new ArrayList<Block>();
		public static ArrayList<Item> fluidBuckets = new ArrayList<Item>();

	}
}
