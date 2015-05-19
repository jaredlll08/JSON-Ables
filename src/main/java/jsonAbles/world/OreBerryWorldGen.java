package jsonAbles.world;

import java.util.Random;

import jsonAbles.api.OreBerrySet;
import jsonAbles.api.RecipeRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class OreBerryWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.isHellWorld) {
			generateNether(random, chunkX * 16, chunkZ * 16, world);
		} else if (world.provider.terrainType != WorldType.FLAT) {
			if (world.provider.dimensionId == 0)
				generateOreBushes(random, chunkX * 16, chunkZ * 16, world);
		}

	}

	public void generateOreBushes(Random random, int xChunk, int zChunk, World world) {
		int xPos, yPos, zPos;
		for (OreBerrySet set : RecipeRegistry.oreBerries)
			for (int i = 0; i < set.generationDensity; i++) {
				xPos = xChunk + random.nextInt(16);
				xPos = xChunk + random.nextInt(16);
                yPos = (set.generationHightMax+ set.generationHightMin) / 2;
                zPos = zChunk + random.nextInt(16);
				yPos = findAdequateLocation(world, xPos, yPos, zPos, set.generationHightMax, set.generationHightMin);
				if (yPos != -1) {

					set.generation.generate(world, random, xPos, yPos, zPos);
				}
			}
	}
	
	public int findAdequateLocation (World world, int x, int y, int z, int heightLimit, int depthLimit)
    {
        int height = y;
        do
        {
            if (world.getBlock(x, height, z) == Blocks.air && world.getBlock(x, height + 1, z) != Blocks.air)
                return height + 1;
            height++;
        } while (height < heightLimit);

        height = y;
        do
        {
            if (world.getBlock(x, height, z) == Blocks.air && world.getBlock(x, height - 1, z) != Blocks.air)
                return height - 1;
            height--;
        } while (height > depthLimit);

        return -1;
    }

	private void generateNether(Random random, int xChunk, int zChunk, World world) {
		int xPos, yPos, zPos;
		for (OreBerrySet set : RecipeRegistry.oreBerries) {
			if (set.nether)
				for (int i = 0; i < set.generationDensity; i++) {
					xPos = xChunk + random.nextInt(16);
					xPos = xChunk + random.nextInt(16);
	                yPos = (set.generationHightMax+ set.generationHightMin) / 2;
	                zPos = zChunk + random.nextInt(16);
					yPos = findAdequateLocation(world, xPos, yPos, zPos, set.generationHightMax, set.generationHightMin);
					set.generation.generate(world, random, xPos, yPos, zPos);
				}
		}
	}

}
