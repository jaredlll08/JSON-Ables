package fluxedCrystals.compat.minetweaker.helper;

import net.minecraft.item.ItemStack;

public class StackHelper {
	// Stack is the stack that is part of a recipe, stack2 is the one input
	// trying to match
	public static boolean areEqual(ItemStack stack, ItemStack stack2) {
		if (stack == null || stack2 == null)
			return false;
		else
			return stack.isItemEqual(stack2);
	}
}