package jsonAbles.commands;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jsonAbles.JsonAbles;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.tools.ToolMaterial;
import thaumcraft.api.aspects.Aspect;
import cpw.mods.fml.common.Loader;

public class CommandLog extends CommandBase {

	public static PrintWriter writer = null;

	@Override
	public String getCommandName() {
		return "log";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		if (args.length == 1) {
			if (Loader.isModLoaded("Thaumcraft"))
				list.add("Aspects");
			list.add("Fluids");
			if (Loader.isModLoaded("TConstruct"))
				list.add("Materials");
			list.add("Potions");
		}
		return list;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Usages: /log <args>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		try {
			if (args[0].equalsIgnoreCase("fluids")) {
				for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
					JsonAbles.logger.info(fluid.getUnlocalizedName());
					writer.print(fluid.getUnlocalizedName() + "\n");
				}
				sender.addChatMessage(new ChatComponentText("List generated, look at /logs/JSONAbles-log.txt"));
			}
			if (Loader.isModLoaded("TConstruct"))
				if (args[0].equalsIgnoreCase("materials")) {
					for (ToolMaterial set : TConstructRegistry.toolMaterialStrings.values()) {
						JsonAbles.logger.info("Tinkers Material:[ LocalizedName Name: " + set.localizedName() + ", Material Name: " + set.materialName + "]");
						writer.print("Tinkers Material:[ LocalizedName Name: " + set.localizedName() + ", Material Name: " + set.materialName + "]\n");
					}
					sender.addChatMessage(new ChatComponentText("List generated, look at /logs/JSONAbles-log.txt"));
				}
			if (Loader.isModLoaded("Thaumcraft"))
				if (args[0].equalsIgnoreCase("aspects")) {
					ArrayList<Aspect> aspects = new ArrayList<Aspect>();
					aspects.addAll(Aspect.getPrimalAspects());
					aspects.addAll(Aspect.getCompoundAspects());
					for (Aspect set : aspects) {
						if (!set.isPrimal()) {
							JsonAbles.logger.info("Aspect:[ AspectName: " + set.getName() + ", description" + set.getLocalizedDescription() + ", Components:[" + set.getComponents()[0].getName() + ", " + set.getComponents()[1].getName() + "]\n");
							writer.print("Aspect:[ AspectName: " + set.getName() + ", description" + set.getLocalizedDescription() + ", Components:[" + set.getComponents()[0].getName() + ", " + set.getComponents()[1].getName() + "]\n");
						} else {
							JsonAbles.logger.info("Aspect:[ AspectName: " + set.getName() + ", description" + set.getLocalizedDescription() + ", ]\n");
							writer.print("Aspect:[ AspectName: " + set.getName() + ", description" + set.getLocalizedDescription() + ", ]\n");

						}
					}
					sender.addChatMessage(new ChatComponentText("List generated, look at /logs/JSONAbles-log.txt"));
				}

			if (args[0].equalsIgnoreCase("potions")) {
				ArrayList<Potion> potions = toArrayList(Potion.potionTypes);
				for (Potion potion : potions) {
					if (potion != null) {
						JsonAbles.logger.info("Potion:[ PotionName: " + ", ID: " + potion.getId() + "]\n");
						writer.print("Potion:[ PotionName: " + potion.getName() + ", ID: " + potion.getId() + "]\n");
					}
				}
				sender.addChatMessage(new ChatComponentText(potions.size() + "List generated, look at /logs/JSONAbles-log.txt"));

			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <E> ArrayList<E> toArrayList(E[] array) {
		ArrayList<E> list = new ArrayList<E>();
		for (E e : array) {
			list.add(e);
		}
		return list;
	}
}
