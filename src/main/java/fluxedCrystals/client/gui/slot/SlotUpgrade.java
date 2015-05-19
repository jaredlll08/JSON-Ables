package fluxedCrystals.client.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrystals.api.ISeed;
import fluxedCrystals.api.IUpgrade;
import fluxedCrystals.items.FCItems;

public class SlotUpgrade extends Slot {

	public SlotUpgrade(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	public int getSlotStackLimit() {
		return 1;
	}

	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IUpgrade
				&& stack.getItem() != FCItems.upgradeRangeAdvanced
				&& stack.getItem() != FCItems.upgradeRangeBasic
				&& stack.getItem() != FCItems.upgradeRangeGreater;
	}
}
