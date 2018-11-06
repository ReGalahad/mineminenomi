package xyz.pixelatedw.MineMineNoMi3.world.scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;

public class ScenarioManager
{

	public static HashMap<String, Scenario> scenarios = createDefaultMap();
	
	public static int instanceSpawnX = -20000;
	public static int instanceSpawnY = 64;
	public static int instanceSpawnZ = -20000;
	
    private static HashMap<String, Scenario> createDefaultMap()
    {
    	HashMap<String, Scenario> scenarioMap = new HashMap<String, Scenario>();
    	
        scenarioMap.put(ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN, new BasicScenario());
        
        return scenarioMap;
    }

	public static boolean canSpawn(World world, int posX, int posY, int posZ)
	{
		if(world.getBlock(posX, posY, posZ) == Blocks.air)
			return true;

		ScenarioManager.instanceSpawnX += 2000;
		return canSpawn(world, posX + 2000, posY, posZ);
	}
	
    
}
