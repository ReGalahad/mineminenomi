package xyz.pixelatedw.MineMineNoMi3.world.scenario;

import java.util.HashMap;

import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;

public class ScenarioManager
{

	public static HashMap<String, Scenario> scenarios = createMap();
	
    private static HashMap<String, Scenario> createMap()
    {
    	HashMap<String, Scenario> scenarioMap = new HashMap<String, Scenario>();
        scenarioMap.put(ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN, new BasicScenario());
        return scenarioMap;
    }
    
}
