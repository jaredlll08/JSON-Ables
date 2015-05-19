package jsonAbles.config.json;

import net.minecraft.creativetab.CreativeTabs;

public class ItemType implements IJSONObject{
	public String creativeTab;
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
	
	@Override
	public void register() {
		
	}
	
	

}
