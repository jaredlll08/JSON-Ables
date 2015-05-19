package jsonAbles.items;

import java.util.List;
import java.util.Set;

import jsonAbles.ModProps;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemCustomTool extends ItemTool {
	public CreativeTabs creativeTab;
	public int lifeSpan;
	public String unlocalizedName;
	public String useAction;
	public String[] lore;
	public boolean loreFormat;
	public boolean hasRenderColor;
	public int renderColor;
	public boolean render3DItem;
	public int maxStackSize;
	public String textureName;
	public boolean hasEffect;

	protected ItemCustomTool(float p_i45333_1_, ToolMaterial p_i45333_2_, Set p_i45333_3_) {
		super(p_i45333_1_, p_i45333_2_, p_i45333_3_);
	}

	
	
//	public ItemCustomTool(CreativeTabs creativeTab, int lifeSpan, String unlocalizedName, String useAction, String[] lore, boolean loreFormat, boolean hasRenderColor, int renderColor, boolean render3dItem, int maxStackSize, String textureName) {
//		this.creativeTab = creativeTab;
//		this.lifeSpan = lifeSpan;
//		this.unlocalizedName = unlocalizedName;
//		this.useAction = useAction;
//		this.lore = lore;
//		this.loreFormat = loreFormat;
//		this.renderColor = renderColor;
//		render3DItem = render3dItem;
//		this.maxStackSize = maxStackSize;
//		this.textureName = textureName;
//		this.setMaxStackSize(maxStackSize);
//	}
	
	@Override
	public boolean hasEffect(ItemStack p_77636_1_) {
		return hasEffect;
	}

	@Override
	public void registerIcons(IIconRegister p_94581_1_) {
		this.itemIcon = p_94581_1_.registerIcon(ModProps.modid + ":" + textureName);
	}

	@Override
	public boolean isFull3D() {
		return render3DItem;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (loreFormat) {
			for (String l : lore) {

				if (l.contains("{itemNameDisplay}")) {
					l = l.replaceAll("{itemNameDisplay}", stack.getDisplayName());
				}
				if (l.contains("{itemNameUnlocalized}")) {
					l = l.replaceAll("{itemNameUnlocalized}", stack.getUnlocalizedName());
				}
				if (l.contains("{itemStackSize}")) {
					l = l.replaceAll("{itemStackSize}", String.valueOf(stack.stackSize));
				}
				if (l.contains("{itemStackSizeMax}")) {
					l = l.replaceAll("{itemStackSizeMax}", String.valueOf(stack.getMaxStackSize()));
				}
				if (l.contains("{itemDamage}")) {
					l = l.replaceAll("{itemDamage}", String.valueOf(stack.getItemDamage()));
				}
				if (l.contains("{playerX}")) {
					l = l.replace("{playerX}", String.valueOf(player.posX));
				}
				if (l.contains("{playerY}")) {
					l = l.replace("{playerY}", String.valueOf(player.posY));
				}
				if (l.contains("{playerZ}")) {
					l = l.replace("{playerZ}", String.valueOf(player.posZ));
				}
				if (l.contains("{playerDimension}")) {
					l = l.replace("{playerDimension}", String.valueOf(player.dimension));
				}
				if (l.contains("{playerHealth}")) {
					l = l.replace("{playerHealth}", String.valueOf(player.getHealth()));
				}
				if (l.contains("{playerScore}")) {
					l = l.replace("{playerScore}", String.valueOf(player.getScore()));
				}
				if (l.contains("{playerArmor}")) {
					l = l.replace("{playerHealth}", String.valueOf(player.getTotalArmorValue()));
				}

				list.add(l);
			}

		}
	}

	@Override
	public int getColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_) {
		if (hasRenderColor)
			return renderColor;
		return 0xFFFFFF;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return creativeTab;
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return lifeSpan;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.valueOf(useAction);
	}

	@Override
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
}
