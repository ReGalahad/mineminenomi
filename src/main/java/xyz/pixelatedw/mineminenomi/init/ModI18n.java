package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.api.WyRegistry;

public class ModI18n
{

	public static final String
	
	CATEGORY_GENERAL = "category.mmnmkeys", //Mine Mine no Mi Keys
	KEY_PLAYER = "key.playerui", //Player UI
	KEY_COMBATMODE = "key.combatmode", //Combat Mode
	KEY_COMBATSLOT1 = "key.combatslot.1",
	KEY_COMBATSLOT2 = "key.combatslot.2",
	KEY_COMBATSLOT3 = "key.combatslot.3",
	KEY_COMBATSLOT4 = "key.combatslot.4",
	KEY_COMBATSLOT5 = "key.combatslot.5",
	KEY_COMBATSLOT6 = "key.combatslot.6",
	KEY_COMBATSLOT7 = "key.combatslot.7",
	KEY_COMBATSLOT8 = "key.combatslot.8",
	
	FACTION_NAME = "faction.name",
	FACTION_EMPTY = "faction.empty",
	FACTION_PIRATE = "faction.pirate",
	FACTION_MARINE = "faction.marine",
	FACTION_BOUNTY_HUNTER = "faction.bountyhunter",
	FACTION_REVOLUTIONARY = "faction.revolutionary",
	
	RACE_NAME = "race.name",
	RACE_EMPTY = "race.empty",
	RACE_HUMAN = "race.human",
	RACE_FISHMAN = "race.fishman",
	RACE_CYBORG = "race.cyborg",
	
	STYLE_NAME = "style.name",
	STYLE_EMPTY = "style.empty",
	STYLE_SWORDSMAN = "style.swordsman",
	STYLE_SNIPER = "style.sniper",
	STYLE_DOCTOR = "style.doctor",
	
	GUI_ABILITIES = "gui.abilities",
	GUI_COLA = "gui.cola",
	GUI_DORIKI = "gui.doriki",
			
	GUI_QUESTS = "gui.quests",
	GUI_QUEST_PROGRESS = "gui.quests.progress",
	GUI_QUEST_ACCEPT = "gui.quests.accept",
	
	GUI_ACCEPT = "gui.accept",
	GUI_DECLINE = "gui.decline",
	
	QUEST_SWORDSMAN_PROGRESSION_01_START = "quest.swordsman_progression_01.start",
	QUEST_SWORDSMAN_PROGRESSION_01_WEAK_SWORD = "quest.swordsman_progression_01.weak_sword",
	QUEST_SWORDSMAN_PROGRESSION_01_STRONG_SWORD = "quest.swordsman_progression_01.strong_sword",
	
	QUEST_SWORDSMAN_PROGRESSION_02_SUNSET_START = "quest.swordsman_progression_02.sunset_start",
	QUEST_SWORDSMAN_PROGRESSION_02_START = "quest.swordsman_progression_02.start",
	QUEST_SWORDSMAN_PROGRESSION_02_SURVIVED = "quest.swordsman_progression_02.survived",
	QUEST_SWORDSMAN_PROGRESSION_02_THREE_DAYS_LATER = "quest.swordsman_progression_02.three_days_later",
	
	QUEST_SWORDSMAN_PROGRESSION_03_START = "quest.swordsman_progression_03.start",
	QUEST_SWORDSMAN_PROGRESSION_03_COMPLETE = "quest.swordsman_progression_03.complete",
			
	QUEST_SWORDSMAN_PROGRESSION_04_START = "quest.swordsman_progression_04.start",

	QUEST_NOT_COMPLETED = "quest.not_completed",
	QUEST_TOO_MANY = "quest.too_many",
	QUEST_COMPLETED = "quest.completed",
	QUEST_STARTED = "quest.started",
	QUEST_ALREADY_COMPLETED = "quest.already_completed",
	QUEST_IN_PROGRESS = "quest.in_progress";

	public static void init()
	{
		// Quests
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_01_START, "<%s> If you want to be trained here you'll need a strong blade to fight with. Show me your best blade you've got.");
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_01_WEAK_SWORD, "<%s> That weapon is a joke. You'll need something stronger than that.");
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_01_STRONG_SWORD, "<%s> This one will do for my training.");
		
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_02_SUNSET_START, "<%s> We're starting when the sun sets, you're free to do as you please until then.");
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_02_START, "<%s> Your first test will be to survive the night. Good luck.");
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_02_SURVIVED, "<%s> Congratulations you've survived the night, let's proceed then.");
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_02_THREE_DAYS_LATER, "<%s> I was starting to wonder if you died out there. But nonetheless you did survive the night like asked.");

		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_03_START, "<%s> Let the real training commmence then. Show me how proficient you are with your blade, go to any mountainous area and kill 20 creatures.");
		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_03_COMPLETE, "<%s> Very good work there. I reckon that you have some talent with that sword.");

		WyRegistry.registerName(QUEST_SWORDSMAN_PROGRESSION_04_START, "<%s> So you can kill things without a problem, congratulations, how well can you handle hitting you enemy with precision then ?");
		
		WyRegistry.registerName(QUEST_NOT_COMPLETED, "%s does not meet the completion requirements.");
		WyRegistry.registerName(QUEST_TOO_MANY, "Cannot accept any more quests");
		WyRegistry.registerName(QUEST_ALREADY_COMPLETED, "%s was completed and cannot be started again!");
		WyRegistry.registerName(QUEST_IN_PROGRESS, "%s is already in progress!");
		WyRegistry.registerName(QUEST_STARTED, "Started %s");
		WyRegistry.registerName(QUEST_COMPLETED, "Completed %s");
		
		// ItemGroups
		WyRegistry.registerName("itemGroup.devilfruits", "Devil Fruits");
		WyRegistry.registerName("itemGroup.weapons", "Equipement");
		
		// GUI
		WyRegistry.registerName(FACTION_NAME, "Faction");
		WyRegistry.registerName(RACE_NAME, "Race");
		WyRegistry.registerName(STYLE_NAME, "Fighting Style");
		WyRegistry.registerName(GUI_ABILITIES, "Abilities");
		WyRegistry.registerName(GUI_COLA, "Cola");
		WyRegistry.registerName(GUI_DORIKI, "Doriki");
		WyRegistry.registerName(GUI_QUESTS, "Quests");
		WyRegistry.registerName(GUI_QUEST_PROGRESS, "Progress");
		WyRegistry.registerName(GUI_QUEST_ACCEPT, "Accept this quest ?");
		WyRegistry.registerName(GUI_ACCEPT, "Accept");
		WyRegistry.registerName(GUI_DECLINE, "Decline");
		
		// Faction | Race | Fighting Style
		WyRegistry.registerName(FACTION_EMPTY, "No Faction");
		WyRegistry.registerName(RACE_EMPTY, "No Race");
		WyRegistry.registerName(STYLE_EMPTY, "No Fighting Style");
		
		WyRegistry.registerName(FACTION_PIRATE, "Pirate");
		WyRegistry.registerName(FACTION_MARINE, "Marine");
		WyRegistry.registerName(FACTION_BOUNTY_HUNTER, "Bounty Hunter");
		
		WyRegistry.registerName(RACE_HUMAN, "Human");
		WyRegistry.registerName(RACE_FISHMAN, "Fishman");
		WyRegistry.registerName(RACE_CYBORG, "Cyborg");
		
		WyRegistry.registerName(STYLE_SWORDSMAN, "Swordsman");
		WyRegistry.registerName(STYLE_SNIPER, "Sniper");
		WyRegistry.registerName(STYLE_DOCTOR, "Doctor");
		
		// Keys
		WyRegistry.registerName(CATEGORY_GENERAL, "Mine Mine no Mi Keys");
		WyRegistry.registerName(KEY_COMBATMODE, "Combat Mode");
		WyRegistry.registerName(KEY_PLAYER, "Player UI");
		for(int i = 1; i < 9; i++)
			WyRegistry.registerName("key.combatslot." + i, "Ability Slot " + i);
	}
	
}
