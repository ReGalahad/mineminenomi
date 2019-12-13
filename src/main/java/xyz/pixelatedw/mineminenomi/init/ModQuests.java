package xyz.pixelatedw.mineminenomi.init;

import java.util.HashMap;

import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.quests.questlines.swordsman.SwordsmanProgression01Quest;
import xyz.pixelatedw.mineminenomi.quests.questlines.swordsman.SwordsmanProgression02Quest;
import xyz.pixelatedw.mineminenomi.quests.questlines.swordsman.SwordsmanProgression03Quest;
import xyz.pixelatedw.mineminenomi.quests.questlines.swordsman.SwordsmanProgression04Quest;

public class ModQuests
{

	private static HashMap<String, Quest> allQuests = new HashMap<String, Quest>();
	
	
	// Quest Line : Swordsman Progression
	public static final Quest SWORDSMAN_PROGRESSION_01 = new SwordsmanProgression01Quest();
	public static final Quest SWORDSMAN_PROGRESSION_02 = new SwordsmanProgression02Quest();
	public static final Quest SWORDSMAN_PROGRESSION_03 = new SwordsmanProgression03Quest();
	public static final Quest SWORDSMAN_PROGRESSION_04 = new SwordsmanProgression04Quest();

	public static void init()
	{
		// Quest Line : Swordsman Progression
		registerQuest(SWORDSMAN_PROGRESSION_01);
		registerQuest(SWORDSMAN_PROGRESSION_02);
		registerQuest(SWORDSMAN_PROGRESSION_03);
		registerQuest(SWORDSMAN_PROGRESSION_04);
	}
	
	public static HashMap<String, Quest> getAllQuests()
	{
		return allQuests;
	}
	
	private static void registerQuest(Quest quest)
	{
		WyRegistry.registerName("quest." + quest.getQuestId() + ".name", quest.getQuestName());
		for(int i = 0; i < quest.getQuestDescription().length; i++)
		{
			if(!quest.getQuestDescription()[i].isEmpty())
				WyRegistry.registerName("quest." + quest.getQuestId() + ".desc." + i, quest.getQuestDescription()[i]);
		}
		allQuests.put(quest.getQuestId(), quest);
	}
	
}
