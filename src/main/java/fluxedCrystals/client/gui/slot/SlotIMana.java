package fluxedCrystals.client.gui.slot;

import java.util.ArrayList;

import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.mana.ManaNetworkEvent;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotIMana extends Slot {

	public SlotIMana(IInventory inventory, int number, int x, int y) {
		super(inventory, number, x, y);

	}

	public boolean isItemValid(ItemStack stack) {

		if (stack.getItem() instanceof IManaItem) {
			return true;
		}
		return false;
	}
}
