package fluxedCrystals.client.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import fluxedCrystals.blocks.FCBlocks;

public class SlotPowerBlock extends Slot {

	public SlotPowerBlock(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	public boolean isItemValid(ItemStack stack) {
		if (stack.isItemEqual(new ItemStack(FCBlocks.powerBlock)))
			return true;
		return false;
	}
}
