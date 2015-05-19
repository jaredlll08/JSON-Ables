package jsonAbles.api;

import net.minecraft.creativetab.CreativeTabs;

public class ItemSet {
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
	public String name;
	public String modid;
	
	public ItemSet(){
		
	}
	
	
	public ItemSet(CreativeTabs creativeTab, int lifeSpan, String unlocalizedName, String useAction, String[] lore, boolean loreFormat, boolean hasRenderColor, int renderColor, boolean render3dItem, int maxStackSize, String textureName, boolean hasEffect, String name, String modid) {
		this.creativeTab = creativeTab;
		this.lifeSpan = lifeSpan;
		this.unlocalizedName = unlocalizedName;
		this.useAction = useAction;
		this.lore = lore;
		this.loreFormat = loreFormat;
		this.hasRenderColor = hasRenderColor;
		this.renderColor = renderColor;
		render3DItem = render3dItem;
		this.maxStackSize = maxStackSize;
		this.textureName = textureName;
		this.hasEffect = hasEffect;
		this.name = name;
		this.modid = modid;
	}
	
	
	
	
}
