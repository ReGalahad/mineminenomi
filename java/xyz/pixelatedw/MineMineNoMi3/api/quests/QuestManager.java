package xyz.pixelatedw.MineMineNoMi3.api.quests;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;

public class QuestManager
{

	private static QuestManager instance;
	public static QuestManager instance()
	{
		if(instance == null) instance = new QuestManager();
		return instance;
	}

	public Quest startQuest(EntityPlayer player, Quest quest)
	{
		Quest nQuest = null;
		QuestProperties questProps = QuestProperties.get(player);
		
		try
		{
			nQuest = quest.getClass().newInstance();
		} 
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		questProps.addQuestInTracker(nQuest);
		nQuest.startQuest(player);
		return nQuest;
	}
	
	public Quest getQuestByNameFromList(HashMap<String, Quest> list, String questId)
	{
		for(Quest q : list.values())
		{
			if(q.getQuestID().toLowerCase().equals(questId.toLowerCase()))
				return q;
		}
		
		return null;
	}
}
