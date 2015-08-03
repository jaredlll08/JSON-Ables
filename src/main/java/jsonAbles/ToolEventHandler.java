package jsonAbles;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.RecipeRegistry.Refrence;
import jsonAbles.api.json.EnchantHelper;
import jsonAbles.api.json.EnchantHelper.EnchantmentWithLevel;
import mantle.world.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.Sys;
import tconstruct.library.crafting.ModifyBuilder;
import tconstruct.library.event.ToolCraftEvent;
import tconstruct.library.tools.ToolCore;

import java.util.*;

public class ToolEventHandler {
	@SubscribeEvent
	public void bucketFill(FillBucketEvent evt) {
		if (evt.current.getItem() == Items.bucket && evt.target.typeOfHit == MovingObjectType.BLOCK) {
			int hitX = evt.target.blockX;
			int hitY = evt.target.blockY;
			int hitZ = evt.target.blockZ;

			if (evt.entityPlayer != null && !evt.entityPlayer.canPlayerEdit(hitX, hitY, hitZ, evt.target.sideHit, evt.current)) {
				return;
			}

			Block bID = evt.world.getBlock(hitX, hitY, hitZ);
			for (int id = 0; id < RecipeRegistry.Refrence.fluidBlocks.size(); id++) {
				if (bID == RecipeRegistry.Refrence.fluidBlocks.get(id)) {
					if (evt.entityPlayer.capabilities.isCreativeMode) {
						WorldHelper.setBlockToAir(evt.world, hitX, hitY, hitZ);
					} else {
						WorldHelper.setBlockToAir(evt.world, hitX, hitY, hitZ);

						evt.setResult(Result.ALLOW);
						evt.result = new ItemStack(Refrence.fluidBuckets.get(id));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void lore(ItemTooltipEvent event) {
		if (event.itemStack.getItem() instanceof ToolCore) {
			if (event.itemStack.stackTagCompound != null) {
				if (event.itemStack.stackTagCompound.getCompoundTag("InfiTool") != null) {
					NBTTagCompound toolTag = event.itemStack.stackTagCompound.getCompoundTag("InfiTool");
					ArrayList<String> list = new ArrayList<String>();

					if (!list.contains(RecipeRegistry.getToolLore(toolTag.getInteger("Head"))))
						list.add(RecipeRegistry.getToolLore(toolTag.getInteger("Head")));
					if (!list.contains(RecipeRegistry.getToolLore(toolTag.getInteger("Handle"))))
						list.add(RecipeRegistry.getToolLore(toolTag.getInteger("Handle")));
					if (!list.contains(RecipeRegistry.getToolLore(toolTag.getInteger("Accessory"))))
						list.add(RecipeRegistry.getToolLore(toolTag.getInteger("Accessory")));
					if (!list.contains(RecipeRegistry.getToolLore(toolTag.getInteger("Extra"))))
						list.add(RecipeRegistry.getToolLore(toolTag.getInteger("Extra")));
					for (String str : list) {
						if (str != null)
							event.toolTip.add(str);
					}
					
					boolean toolTip = false;
					if(!toolTip){
						toolTip = RecipeRegistry.isJsonables(toolTag.getInteger("Head"));
					}
					if(!toolTip){
						toolTip = RecipeRegistry.isJsonables(toolTag.getInteger("Handle"));
					}
					if(!toolTip){
						toolTip = RecipeRegistry.isJsonables(toolTag.getInteger("Accessory"));
					}
					if(!toolTip){
						toolTip = RecipeRegistry.isJsonables(toolTag.getInteger("Extra"));
					}
					
					if(toolTip){
						event.toolTip.add(EnumChatFormatting.DARK_RED + "This tool contains a part added by JSONAbles.");
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOW) // Low priority, so that Tickers' WeaponryHandler can do its thing first
	public void craftTool(ToolCraftEvent.NormalTool event) {
		NBTTagCompound toolTag = event.toolTag.getCompoundTag("InfiTool");

		ItemStack result = new ItemStack(event.tool); // Used to apply native modifiers
		result.stackTagCompound = event.toolTag;

		boolean override = false;
		int modifiers = toolTag.getInteger("Modifiers"); // Saved for later
		List<EnchantmentWithLevel> list = new ArrayList<EnchantmentWithLevel>();

		toolTag.setInteger("Modifiers", Integer.MAX_VALUE / 2); // Allows native modifiers to be applied without using up real modifiers
		for (int materialID : new int[] {toolTag.getInteger("Head"), toolTag.getInteger("Handle"), toolTag.getInteger("Accessory"), toolTag.getInteger("Extra")}) {
			if (materialID == 0) continue; // No part

			// Extra modifier counter
			modifiers += RecipeRegistry.getToolModifiers(materialID); // Extra modifiers, added to the amount saved before.

			// Native TC modifiers
			ItemStack[][] nativeModifiers = RecipeRegistry.getNativeModifiers(materialID);
			if (nativeModifiers != null && nativeModifiers.length != 0) {
				override = true; // We have at least 1 native modifier so we override the result
				for (ItemStack[] items : nativeModifiers) {
					if (items == null || items.length == 0) continue; // null items
					ItemStack tmp = ModifyBuilder.instance.modifyItem(result, items); // Do the actual modification
					if (tmp == null) continue; // Unable to comply
					result = tmp;
				}
			}

			// Add native vanilla enchants to list so that there levels add up, instead of the enchantment being applied multiple times.
			EnchantmentWithLevel[] nativeEnchantments = RecipeRegistry.getNativeEnchantments(materialID);
			if (nativeEnchantments != null && nativeEnchantments.length != 0) {
				for (EnchantmentWithLevel enchantment : nativeEnchantments) {
					if (enchantment != null) {
						EnchantHelper.addToList(enchantment, list);
					}
				}
			}
		}

		// The enchant applies to the itemstack, so we override
		if (!list.isEmpty()) {
			override = true;
			for (EnchantmentWithLevel enchantment : list) {
				enchantment.applyTo(result);
			}
		}


		if (override) {
			result.stackTagCompound.getCompoundTag("InfiTool").setInteger("Modifiers", modifiers); // set back the proper amount of modifiers
			event.overrideResult(result);
		}
		else {
			toolTag.setInteger("Modifiers", modifiers);
		}
	}
}
