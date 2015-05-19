package fluxedCrystals;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import fluxedCrystals.api.RecipeRegistry;
import fluxedCrystals.items.FCItems;

public class CreativeTabFluxedCrystals extends CreativeTabs {

	public CreativeTabFluxedCrystals() {
		super("Fluxed-Crystals");
	}

	@Override
	public Item getTabIconItem() {
		return FCItems.scytheDiamond;
	}

}
