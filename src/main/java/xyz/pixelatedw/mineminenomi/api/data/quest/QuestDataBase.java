package xyz.pixelatedw.mineminenomi.api.data.quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.pixelatedw.mineminenomi.api.quests.Quest;

public class QuestDataBase implements IQuestData
{

	private List<String> completedQuests = new ArrayList<String>();
	private HashMap<String, Quest> inprogressQuests = new HashMap<String, Quest>();

	// Completed Quests
	
	@Override
	public boolean addCompletedQuest(String questId)
	{
		if(!this.completedQuests.contains(questId))
		{
			this.completedQuests.add(questId);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean removeCompletedQuest(String questId)
	{
		if(this.completedQuests.contains(questId))
		{
			this.completedQuests.remove(questId);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean hasCompletedQuest(String questId)
	{
		return this.completedQuests.contains(questId);
	}
	
	@Override
	public List<String> getCompletedQuests()
	{
		return this.completedQuests;
	}

	@Override
	public void clearCompletedQuests()
	{
		this.completedQuests.clear();
	}	
	
	// In Progress Quests
	
	@Override
	public boolean addInProgressQuest(Quest quest)
	{
		if(!this.inprogressQuests.containsKey(quest.getQuestId()))
		{
			this.inprogressQuests.put(quest.getQuestId(), quest);
			return true;
		}
		
		return false;
	}
	@Override
	public boolean removeInProgressQuest(Quest quest)
	{
		if(this.inprogressQuests.containsKey(quest.getQuestId()))
		{
			this.inprogressQuests.remove(quest.getQuestId());
			return true;
		}
		
		return false;
	}
	@Override
	public boolean hasInProgressQuest(Quest quest)
	{
		return this.inprogressQuests.containsKey(quest.getQuestId());
	}
	
	@Override
	public List<Quest> getInProgressQuests()
	{
		return new ArrayList(this.inprogressQuests.values());
	}
	
	@Override
	public void clearInProgressQuests()
	{
		this.inprogressQuests.clear();
	}

	
}
