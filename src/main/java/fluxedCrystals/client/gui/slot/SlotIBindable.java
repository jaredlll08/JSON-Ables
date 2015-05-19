package fluxedCrystals.client.gui.slot;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaItem;

public class SlotIBindable extends Slot{
	public SlotIBindable(IInventory inventory, int number, int x, int y) {
		super(inventory, number, x, y);

	}

	public boolean isItemValid(ItemStack stack) {

		if (stack.getItem() instanceof IBindable) {
			return true;
		}
		return false;
	}
}
