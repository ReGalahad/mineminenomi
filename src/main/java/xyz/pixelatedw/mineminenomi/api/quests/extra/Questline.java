package xyz.pixelatedw.mineminenomi.api.quests.extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.pixelatedw.mineminenomi.api.quests.Quest;

public class Questline
{

	private List<Quest> quests = new ArrayList<Quest>();	
	private String name = "";
	private boolean isOrdered = true; 
	
	public Questline(String name, Quest... quests)
	{
		this.name = name;
		List newQuests = Arrays.asList(quests);
		this.quests.addAll(newQuests);
	}

	public List<Quest> getQuests()
	{
		return this.quests;
	}
	
	public Questline setUnordered()
	{
		this.isOrdered = false;
		return this;
	}
}
