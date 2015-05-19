package jsonAbles.api;

import jsonAbles.blocks.CustomOreBerry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import tconstruct.world.gen.OreberryBushGen;

public class OreBerrySet {
	
	public String[] textureNames;
	public int stages;
	public ItemStack drop;
	public boolean overworld;
	public boolean nether;
	public int generationHightMin;
	public int generationHightMax;
	public int generationDensity;
	public int generationChance;
	
	public Block generationBlockBase;
	public OreberryBushGen generation;
	public Block oreBerry;
	
	public OreBerrySet(String[] textureNames, int stages, ItemStack drop, boolean overworld, boolean nether, int generationHightMin, int generationHightMax, int generationDensity, int generationChance) {
		this.textureNames = textureNames;
		this.stages = stages;
		this.drop = drop;
		this.overworld = overworld;
		this.nether = nether;
		this.generationHightMin = generationHightMin;
		this.generationHightMax = generationHightMax;
		this.generationDensity = generationDensity;
		this.generationChance = generationChance;
		oreBerry = new CustomOreBerry(textureNames, 4, 1, drop);
		generation = new OreberryBushGen(oreBerry, 0, generationChance);
	}


}
