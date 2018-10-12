package xyz.pixelatedw.MineMineNoMi3.quests;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;

public class QuestLogicHelper
{

	public static int turnInQuestlineQuest(EntityPlayer player, Quest[] questline)
	{
		QuestProperties questProps = QuestProperties.get(player);
		int turnedInQuests = 0;
		
		for(int i = 0; i < Values.MAX_ACTIVITIES; i++)
		{
			if(questProps.getQuestIndexFromTracker(i) != null && questProps.getQuestIndexFromTracker(i).isFinished(player) && isQuestPartofQuestline(questProps.getQuestIndexFromTracker(i), questline))
			{
				questProps.getQuestIndexFromTracker(i).finishQuest(player);
				turnedInQuests++;
			}
		}
		
		return turnedInQuests;
	}
	
	public static Quest getQuestlineCurrentQuest(Quest[] questline, QuestProperties questProps)
	{	
		if(questProps.hasQuestCompleted(questline[questline.length - 1]))
				return null;
		
		for(int i = 0; i < questline.length; i++)
		{
			if(!questProps.hasQuestCompleted(questline[i]))
				return questline[i];
		}
		
		return questline[0];		
	}
	
	public static boolean checkForITimedQuests(QuestProperties questProps)
	{			
		if(questProps.questsInProgress() <= 0)
			return false;
		
		for(int i = 0; i < questProps.questsInProgress(); i++)
		{
			if(questProps.getQuestIndexFromTracker(i) instanceof ITimedQuest)
				return true;
		}
		
		return false;		
	}
	
	public static boolean isQuestPartofQuestline(Quest quest, Quest[] questline)
	{		
		for(int i = 0; i < questline.length; i++)
		{
			if(questline[i].getQuestID().toLowerCase().equals(quest.getQuestID().toLowerCase()))
				return true;
		}
		
		return false;		
	}
	
}
