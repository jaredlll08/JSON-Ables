package jsonAbles.config.json;

import com.google.common.base.Strings;
import jsonAbles.JsonAbles;
import jsonAbles.api.MaterialSet;
import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.json.EnchantHelper;
import jsonAbles.api.json.EnchantHelper.EnchantmentWithLevel;
import jsonAbles.api.json.StackHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MaterialType implements IJSONObject {

	public String key = "key", name = "name", style = "style";
	public String resource = "minecraft:bedrock";
	public int materialID = 0, harvestLevel = 0, durability = 0, miningSpeed = 0, attack = 0, reinforced = 0, primaryColor = 0, value = 0;
	public float handleModifier = 0, stonebound = 0;
	public boolean buildParts = true;
	public String castingFluid = null;
	public int modifiers = 0;
	public String lore = "";
	public float arrowMass = 0;
	public float arrowBreakChance = 0;
	public int bowDrawSpeed = 0;
	public float bowSpeedMax = 0;
	public String[][] nativeModifiers = null;
	public String[] nativeEnchantments = null;

	public void register() {
		try {
			RecipeRegistry.registerMaterialSet(new MaterialSet(this));
			JsonAbles.logger.info("Registering Material for " + name + ", whose color is " + primaryColor + " and that is crafted with " + resource);
		} catch (IllegalArgumentException e) {
			JsonAbles.logger.info("Skipping material type with name {} as its resource was not found.", name);
		}
	}

	/**
	 * Sorts the double String array into a double array of Itemstacks, with max stacksize of 1.
	 * That way it get properly applied to the tool crafted.
	 * For the modifiers to work, they need to be specified separate per 1 modifier.
	 *
	 * Be aware that the modifiers need to be able to be allied in the game itself for this trick to work!
	 * You cannot bypass 2 modifiers that lock each other out with this!
	 *
	 * For example, to apply a total of 150 redstone to a tool, you have to do it in increments of 50 (the max per modifier)
	 * Like so:
	 * Option 1 - 3 times 5 x block (9) + 5 x dust:
	 * <code>
	 *     "nativeModifiers": [
	 *         [
	 *             "minecraft:redstone_block *5",
	 *             "minecraft:redstone *5"
	 *         ],
	 *         [
	 *             "minecraft:redstone_block *5",
	 *             "minecraft:redstone *5"
	 *         ],
	 *         [
	 *             "minecraft:redstone_block *5",
	 *             "minecraft:redstone *5"
	 *         ]
	 *     ]
	 * </code>
	 * Option 2 - 3 times 50 redstone dust:
	 * <code>
	 *     "nativeModifiers": [
	 *         [
	 *             "minecraft:redstone *50"
	 *         ],
	 *         [
	 *             "minecraft:redstone *50"
	 *         ],
	 *         [
	 *             "minecraft:redstone *50"
	 *         ]
	 *     ]
	 * </code>
	 */
	public ItemStack[][] getNativeModifiers() {
		if (nativeModifiers == null) return new ItemStack[0][];
		if (nativeModifiers.length == 0) return new ItemStack[0][];
		List<ItemStack[]> output = new ArrayList<ItemStack[]>();
		for (String[] stackGroups : nativeModifiers) {
			if (stackGroups == null || stackGroups.length == 0) continue;
			List<ItemStack> group = new ArrayList<ItemStack>();
			for (String stackString : stackGroups) {
				if (stackString == null) continue;
				ItemStack stack = StackHelper.getStackFromString(stackString);
				if (stack.stackSize <= 0) continue;
				while (stack.stackSize > 1) {
					group.add(stack.splitStack(1));
				}
				group.add(stack);
			}
			output.add(group.toArray(new ItemStack[group.size()]));
		}
		return output.toArray(new ItemStack[output.size()][]);
	}

	/**
	 * Sorts the String array into an array of EnchantmentWithLevel's
	 *
	 * The proper json formatting is a string consisting of an enchantment ID and optionally an enchantment level.
	 * Like so: "id" or "id level".
	 * By default a level of 1 will be assumed
	 *
	 * In json:
	 * <code>
	 *     "nativeEnchantments": [
	 *         "id level"
	 *     ]
	 * </code>
	 *
	 * Or:
	 * <code>
	 *     "nativeEnchantments": [
	 *         "id"
	 *     ]
	 * </code>
	 *
	 */
	public EnchantmentWithLevel[] getNativeEnchantments() {
		if (nativeEnchantments == null) return new EnchantmentWithLevel[0];
		if (nativeEnchantments.length == 0) return new EnchantmentWithLevel[0];
		List<EnchantmentWithLevel> out = new ArrayList<EnchantmentWithLevel>();
		for (String string : nativeEnchantments) {
			if (Strings.isNullOrEmpty(string)) continue;
			EnchantHelper.addToList(EnchantHelper.getEnchantmentWithLevelFromString(string), out);
		}
		return out.toArray(new EnchantmentWithLevel[out.size()]);
	}
}
