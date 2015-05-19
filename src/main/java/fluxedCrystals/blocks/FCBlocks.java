package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.ModProps;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.blocks.solarFlux.BlockRedirectionCube;
import fluxedCrystals.blocks.solarFlux.BlockSolarGenerator;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import fluxedCrystals.tileEntity.TileEntityGlass;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;
import fluxedCrystals.tileEntity.TileEntityRoughChunk;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import fluxedCrystals.tileEntity.solarFlux.TileEntityRedirectionCube;
import fluxedCrystals.tileEntity.solarFlux.TileEntitySolarGenerator;

public class FCBlocks {

	public static Block crop = new BlockCrystal();

	public static Block powerBlock = new BlockPowerBlock();

	public static Block seedInfuser = new BlockSeedInfuser();
	public static Block infusedGlass = new BlockGlass();
	public static Block roughChunk = new BlockRoughChunk();
	public static Block gemRefiner = new BlockGemRefiner();
	public static Block gemCutter = new BlockGemCutter();
	
	public static Block solarGen = new BlockSolarGenerator();
	public static Block redirectionCube= new BlockRedirectionCube();
	

	public static void init() {

		registerBlocks();
		registerTileEntity();

	}

	private static void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, "powerBlock");
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, "seedInfuser");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "crop");
		GameRegistry.registerTileEntity(TileEntityGlass.class, "glass");
		GameRegistry.registerTileEntity(TileEntityRoughChunk.class, "roughChunk");
		GameRegistry.registerTileEntity(TileEntityGemRefiner.class, "gemRefiner");
		GameRegistry.registerTileEntity(TileEntityGemCutter.class, "gemCutter");
//		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "SFGen");
//		GameRegistry.registerTileEntity(TileEntityRedirectionCube.class, "SFCube");
		
		
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(crop, "crop");
		registerBlock(powerBlock, "Power Block", "power_block");
		registerBlock(seedInfuser, "Seed Infuser", "Seed_Infuser");
		registerBlock(gemRefiner, "Gem Refiner", "Gem_Refiner");
		registerBlock(gemCutter, "Gem Cutter", "Gem_Cutter");
		GameRegistry.registerBlock(infusedGlass.setBlockName("infusedGlass"), "infusedGlass");
		GameRegistry.registerBlock(roughChunk.setBlockName("roughChunk"), "roughChunkBlock");
//		registerBlock(solarGen, "Solar Gen", "gen_solar");
//		registerBlock(redirectionCube, "Redirection Cube", "redirection_cube");
		

	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(FluxedCrystals.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerTileEntitys(Class<? extends TileEntity> tile, String name, String modidActive, boolean configActive) {
		if (Loader.isModLoaded(modidActive) && configActive)
			GameRegistry.registerTileEntity(tile, name);
	}

	private static void registerBlock(Block block, String name, String key, String modidActive, boolean configActive) {
		if (Loader.isModLoaded(modidActive) && configActive) {
			block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(FluxedCrystals.tab);
			GameRegistry.registerBlock(block, key);
		}
	}

}
