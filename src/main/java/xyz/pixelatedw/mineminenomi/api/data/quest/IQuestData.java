package xyz.pixelatedw.mineminenomi.api.data.quest;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public interface IQuestData
{

	boolean addCompletedQuest(String questId);
	boolean removeCompletedQuest(String questId);
	boolean hasCompletedQuest(String questId);
	List<String> getCompletedQuests();
	void clearCompletedQuests();
	
	boolean addInProgressQuest(Quest quest);
	boolean removeInProgressQuest(Quest quest);
	boolean hasInProgressQuest(Quest quest);
	List<Quest> getInProgressQuests();
	void clearInProgressQuests();
	
	public default boolean startQuest(PlayerEntity player, Quest quest)
	{
		Quest nQuest = null;

		if(this.getInProgressQuests().size() + 1 >= ModValues.MAX_QUESTS)
		{
			WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_TOO_MANY));
			return false;
		}
		
		String formatedQuestName = I18n.format( String.format("quest.%s.name", quest.getQuestId()) );
		
		if(this.hasCompletedQuest(quest.getQuestId()) && !quest.isRepeatable())
		{
			WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_ALREADY_COMPLETED, formatedQuestName));
			return false;
		}
		
		if(this.hasInProgressQuest(quest))
		{
			WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_IN_PROGRESS, formatedQuestName));
			return false;
		}
		
		if(!quest.canStart(player))	
			return false;
		
		nQuest = quest.clone();
		
		if(nQuest == null)
		{
			WyDebug.error("New Quest is null...big problem.");
			return false;
		}
		
		this.addInProgressQuest(nQuest);
		nQuest.start(player);
		return true;
	}
	
	public default boolean completeQuest(PlayerEntity player, Quest quest)
	{
		String formatedQuestName = I18n.format( String.format("quest.%s.name", quest.getQuestId() ));
		
		if(quest.getProgress() < quest.getMaxProgress() && !player.world.isRemote)
		{
			//WyHelper.sendMsgToPlayer(player, I18n.format(ModI18n.QUEST_MESSAGE_NOT_COMPLETED, formatedQuestName));
			return false;
		}
			
		if(!quest.canComplete(player))
			return false;
		
		this.removeInProgressQuest(quest);
		this.addCompletedQuest(quest.getQuestId());
		quest.complete(player);
		return true;
	}
	
	public default boolean isPartofQuestline(Quest quest, Questline questline)
	{		
		for(Quest questlineQuest : questline.getQuests())
		{
			if(questlineQuest.getQuestId().equalsIgnoreCase(quest.getQuestId()))
				return true;
		}
		
		return false;		
	}
	
	public default Quest getQuestlineCurrentQuest(Questline questline)
	{	
		if(this.hasCompletedQuest( questline.getQuests().get(questline.getQuests().size() - 1).getQuestId() ))
			return null;
		
		for(Quest quest : questline.getQuests())
		{
			if(!this.hasCompletedQuest( quest.getQuestId() ))
				return quest;
		}
		
		return questline.getQuests().get(0);
	}
}
