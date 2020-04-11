package xyz.pixelatedw.wypi.data.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import xyz.pixelatedw.wypi.quests.Quest;

public class QuestDataBase implements IQuestData
{
	
	private List<Quest> inProgressQuests = new ArrayList<Quest>();
	private List<Quest> finishedQuests = new ArrayList<Quest>();

	@Override
	public boolean addInProgressQuest(Quest quest)
	{
		Quest ogQuest = this.getInProgressQuest(quest);
		if (ogQuest == null)
		{
			this.inProgressQuests.add(quest);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeInProgressQuest(Quest quest)
	{
		Quest ogQuest = this.getInProgressQuest(quest);
		if (ogQuest != null)
		{
			this.inProgressQuests.remove(ogQuest);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasInProgressQuest(Quest quest)
	{
		this.inProgressQuests.removeIf(qst -> qst == null);
		return this.inProgressQuests.parallelStream().anyMatch(qst -> qst.equals(quest));
	}

	@Override
	public <T extends Quest> T getInProgressQuest(T quest)
	{
		this.inProgressQuests.removeIf(qst -> qst == null);
		return (T) this.inProgressQuests.parallelStream().filter(qst -> qst.equals(quest)).findFirst().orElse(null);
	}

	@Override
	public List<Quest> getInProgressQuests()
	{
		this.inProgressQuests.removeIf(qst -> qst == null);
		return this.inProgressQuests.parallelStream().collect(Collectors.toList());
	}

	@Override
	public void clearInProgressQuests()
	{
		this.inProgressQuests.removeIf(qst -> qst == null);
	}

	@Override
	public int countInProgressQuests()
	{
		this.inProgressQuests.removeIf(qst -> qst == null);
		return this.inProgressQuests.size();
	}

	@Override
	public boolean addFinishedQuest(Quest quest)
	{
		Quest ogQuest = this.getFinishedQuest(quest);
		if (ogQuest == null)
		{
			this.finishedQuests.add(quest);
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
