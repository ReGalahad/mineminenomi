package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.wypi.WyRegistry;

public class ModI18n
{
	public static final String CATEGORY_GENERAL = WyRegistry.registerName("category.mmnmkeys", "Mine Mine no Mi Keys");
	public static final String KEY_PLAYER = WyRegistry.registerName("key.playerui", "Player Stats");
	public static final String KEY_COMBATMODE = WyRegistry.registerName("key.combatmode", "Combat Mode");
	public static final String KEY_COMBATSLOT1 = WyRegistry.registerName("key.combatslot.1", "Ability Slot 1");
	public static final String KEY_COMBATSLOT2 = WyRegistry.registerName("key.combatslot.2", "Ability Slot 2");
	public static final String KEY_COMBATSLOT3 = WyRegistry.registerName("key.combatslot.3", "Ability Slot 3");
	public static final String KEY_COMBATSLOT4 = WyRegistry.registerName("key.combatslot.4", "Ability Slot 4");
	public static final String KEY_COMBATSLOT5 = WyRegistry.registerName("key.combatslot.5", "Ability Slot 5");
	public static final String KEY_COMBATSLOT6 = WyRegistry.registerName("key.combatslot.6", "Ability Slot 6");
	public static final String KEY_COMBATSLOT7 = WyRegistry.registerName("key.combatslot.7", "Ability Slot 7");
	public static final String KEY_COMBATSLOT8 = WyRegistry.registerName("key.combatslot.8", "Ability Slot 8");

	public static final String FACTION_NAME = WyRegistry.registerName("faction.name", "Faction");
	public static final String FACTION_EMPTY = WyRegistry.registerName("faction.empty", "No Faction");
	public static final String FACTION_PIRATE = WyRegistry.registerName("faction.pirate", "Pirate");
	public static final String FACTION_MARINE = WyRegistry.registerName("faction.marine", "Marine");
	public static final String FACTION_BOUNTY_HUNTER = WyRegistry.registerName("faction.bountyhunter", "Bounty Hunter");
	public static final String FACTION_REVOLUTIONARY = WyRegistry.registerName("faction.revolutionary", "Revolutionary");
	
	public static final String RACE_NAME = WyRegistry.registerName("race.name", "Race");
	public static final String RACE_EMPTY = WyRegistry.registerName("race.empty", "No Race");
	public static final String RACE_HUMAN = WyRegistry.registerName("race.human", "Human");
	public static final String RACE_FISHMAN = WyRegistry.registerName("race.fishman", "Fishman");
	public static final String RACE_CYBORG = WyRegistry.registerName("race.cyborg", "Cyborg");
	
	public static final String STYLE_NAME = WyRegistry.registerName("style.name", "Fighting Style");
	public static final String STYLE_EMPTY = WyRegistry.registerName("style.empty", "No Fighting Style");
	public static final String STYLE_SWORDSMAN = WyRegistry.registerName("style.swordsman", "Swordsman");
	public static final String STYLE_SNIPER = WyRegistry.registerName("style.sniper", "Sniper");
	public static final String STYLE_DOCTOR = WyRegistry.registerName("style.doctor", "Doctor");
	public static final String STYLE_ART_OF_WEATHER = WyRegistry.registerName("style.art_of_weather", "Art of Weather");
			
	public static final String GUI_ABILITIES = WyRegistry.registerName("gui.abilities", "Abilities");
	public static final String GUI_COLA = WyRegistry.registerName("gui.cola", "Cola");
	public static final String GUI_DORIKI = WyRegistry.registerName("gui.doriki", "Doriki");
			
	public static final String GUI_QUESTS = WyRegistry.registerName("gui.quests", "Quests");
	public static final String GUI_QUEST_PROGRESS = WyRegistry.registerName("gui.quests.progress", "Progress");
	public static final String GUI_QUEST_ACCEPT = WyRegistry.registerName("gui.quests.accept", "Accept this quest ?");
	
	public static final String GUI_ACCEPT = WyRegistry.registerName("gui.accept", "Accept");
	public static final String GUI_DECLINE = WyRegistry.registerName("gui.decline", "Decline");
	
	public static final String QUEST_NO_QUESTS_AVAILABLE = WyRegistry.registerName("quest.no_quests_available", "<%s> I don't have any quests for you at the moment.");
	public static final String QUEST_NO_TRIALS_AVAILABLE = WyRegistry.registerName("quest.no_trials_available", "<%s> I don't have any trials for you at the moment.");
	public static final String QUEST_TOO_MANY = WyRegistry.registerName("quest.too_many", "<%s> You cannot accept any more quests");
	public static final String QUEST_ALREADY_IN_PROGRESS = WyRegistry.registerName("quest.already_in_progress", "Already in progress!");
		
	public static final String ABILITY_MESSAGE_NEED_SWORD = WyRegistry.registerName("ability.item.message.need_sword", "You need a sword to use this ability!");
	public static final String ABILITY_MESSAGE_ONLY_IN_ROOM = WyRegistry.registerName("ability.item.message.only_in_room", "%s can only be used inside ROOM!");
	public static final String ABILITY_MESSAGE_NEED_MEDIC_BAG = WyRegistry.registerName("ability.item.message.need_medic_bag", "You need a medic bag equipped to use this ability!");
	public static final String ABILITY_MESSAGE_NOT_ENOUGH_COLA = WyRegistry.registerName("ability.message.not_enough_cola", "Not enough Cola!");
	public static final String ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS = WyRegistry.registerName("ability.message.not_enough_blocks", "Not enough blocks in the inventory!");

	public static void init()
	{
		// ItemGroups
		WyRegistry.registerName("itemGroup.devil_fruits", "Devil Fruits");
		WyRegistry.registerName("itemGroup.weapons", "Equipement");
	}
	
}
