package xyz.pixelatedw.wypi.data.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import xyz.pixelatedw.wypi.quests.Quest;

public class QuestDataBase implements IQuestData
{
	
	private List<Quest> questsInTracker = new ArrayList<Quest>();
	private List<Quest> finishedQuests = new ArrayList<Quest>();


	@Override
	public boolean addQuestInTracker(Quest quest)
	{
		Quest ogQuest = this.getQuestInTracker(quest);
		if (ogQuest == null)
		{
			this.questsInTracker.add(ogQuest);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeQuestFromTracker(Quest quest)
	{
		Quest ogQuest = this.getQuestInTracker(quest);
		if (ogQuest != null)
		{
			this.questsInTracker.remove(ogQuest);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasQuestInTracker(Quest quest)
	{
		this.questsInTracker.removeIf(qst -> qst == null);
		return this.questsInTracker.parallelStream().anyMatch(qst -> qst.equals(quest));
	}

	@Override
	public <T extends Quest> T getQuestInTracker(T quest)
	{
		this.questsInTracker.removeIf(qst -> qst == null);
		return (T) this.questsInTracker.parallelStream().filter(qst -> qst.equals(quest)).findFirst().orElse(null);
	}

	@Override
	public List<Quest> getQuestsInTracker()
	{
		this.questsInTracker.removeIf(qst -> qst == null);
		return this.questsInTracker.parallelStream().collect(Collectors.toList());
	}

	@Override
	public void clearTracker()
	{
		this.questsInTracker.removeIf(qst -> qst == null);
	}

	@Override
	public int countQuestsInTracker()
	{
		this.questsInTracker.removeIf(qst -> qst == null);
		return this.questsInTracker.size();
	}

	@Override
	public boolean addFinishedQuest(Quest quest)
	{
		Quest ogQuest = this.getFinishedQuest(quest);
		if (ogQuest == null)
		{
			this.finishedQuests.add(ogQuest);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeFinishedQuest(Quest quest)
	{
		Quest ogQuest = this.getFinishedQuest(quest);
		if (ogQuest != null)
		{
			this.finishedQuests.remove(ogQuest);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasFinishedQuest(Quest quest)
	{
		this.finishedQuests.removeIf(qst -> qst == null);
		return this.finishedQuests.parallelStream().anyMatch(qst -> qst.equals(quest));
	}

	@Override
	public <T extends Quest> T getFinishedQuest(T quest)
	{
		this.finishedQuests.removeIf(qst -> qst == null);
		return (T) this.finishedQuests.parallelStream().filter(qst -> qst.equals(quest)).findFirst().orElse(null);
	}

	@Override
	public List<Quest> getFinishedQuests()
	{
		this.finishedQuests.removeIf(qst -> qst == null);
		return this.finishedQuests.parallelStream().collect(Collectors.toList());
	}

	@Override
	public void clearFinishedQuests()
	{
		this.finishedQuests.removeIf(qst -> qst == null);		
	}

	@Override
	public int countFinishedQuests()
	{
		this.finishedQuests.removeIf(qst -> qst == null);
		return this.finishedQuests.size();
	}

}
