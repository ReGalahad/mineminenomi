package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.HashMap;

import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestManager;
import xyz.pixelatedw.MineMineNoMi3.quests.bounties.BountyLowLevel01;
import xyz.pixelatedw.MineMineNoMi3.quests.questlines.swordsmanprogression.QuestSwordsmanProgression01;

public class ListQuests
{
	
	public static HashMap<String, Quest> allQuests = new HashMap<String, Quest>();
	
	public static HashMap<String, Quest> questsForPirates = new HashMap<String, Quest>();
	public static HashMap<String, Quest> questsForMarines = new HashMap<String, Quest>();
	public static HashMap<String, Quest> questsForBountyHunter = new HashMap<String, Quest>();
	
	public static HashMap<String, Quest> questsForSwordsman = new HashMap<String, Quest>();
	public static HashMap<String, Quest> questsForSnipers = new HashMap<String, Quest>();
	public static HashMap<String, Quest> questsForMedics = new HashMap<String, Quest>();

	
	// Quest Line : Swordsman Progression
	public static Quest swordsmanProgression01 = new QuestSwordsmanProgression01();	
		
	// Bounties
	public static Quest bountyLowLevel01 = new BountyLowLevel01();		
	
	
	
	
	
	static
	{
		allQuests.put(ListQuests.swordsmanProgression01.getQuestID(), ListQuests.swordsmanProgression01);
		
		// Swordsman Quests Bucket
		questsForSwordsman.put(swordsmanProgression01.getQuestID(), swordsmanProgression01);
		
		
		// Bounty Hunter Quests Bucket
		questsForBountyHunter.put(bountyLowLevel01.getQuestID(), bountyLowLevel01);
	}
	
	
}
