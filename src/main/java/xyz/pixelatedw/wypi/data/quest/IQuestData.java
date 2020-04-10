package xyz.pixelatedw.wypi.data.quest;

import java.util.List;

import xyz.pixelatedw.wypi.quests.Quest;

public interface IQuestData
{
	boolean addQuestInTracker(Quest quest);
	boolean removeQuestFromTracker(Quest quest);
	boolean hasQuestInTracker(Quest quest);
	<T extends Quest> T getQuestInTracker(T quest);
	List<Quest> getQuestsInTracker();
	void clearTracker(); 
	int countQuestsInTracker();
	
	boolean addFinishedQuest(Quest quest);
	boolean removeFinishedQuest(Quest quest);
	boolean hasFinishedQuest(Quest quest);
	<T extends Quest> T getFinishedQuest(T quest);
	List<Quest> getFinishedQuests();
	void clearFinishedQuests(); 
	int countFinishedQuests();
}
