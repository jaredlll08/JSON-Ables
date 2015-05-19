package jsonAbles;

import java.util.ArrayList;

import jsonAbles.api.RecipeRegistry;
import jsonAbles.api.RecipeRegistry.Refrence;
import mantle.world.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tconstruct.library.event.ToolCraftEvent;
import tconstruct.library.tools.ToolCore;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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
				}
			}
		}
	}

	@SubscribeEvent
	public void craftTool(ToolCraftEvent.NormalTool event) {
		NBTTagCompound toolTag = event.toolTag.getCompoundTag("InfiTool");
		int modifiers = toolTag.getInteger("Modifiers");
		modifiers += RecipeRegistry.getToolModifiers(toolTag.getInteger("Head"));
		modifiers += RecipeRegistry.getToolModifiers(toolTag.getInteger("Handle"));
		modifiers += RecipeRegistry.getToolModifiers(toolTag.getInteger("Accessory"));
		modifiers += RecipeRegistry.getToolModifiers(toolTag.getInteger("Extra"));
		toolTag.setInteger("Modifiers", modifiers);
	}

}
