package xyz.pixelatedw.MineMineNoMi3;

import net.minecraft.util.ResourceLocation;

public class ID 
{ 
	
	public static final ResourceLocation
	
	ICON_HARROW = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_arrow.png"),
	
	ICON_PIRATE = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_pirate.png"),
	ICON_MARINE = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_marine.png"),
	ICON_BOUNTYHUNTER = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_bountyhunter.png"),
	
	ICON_HUMAN = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_human.png"),
	ICON_FISHMAN = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_fishman.png"),
	ICON_CYBORG = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_cyborg.png"),
	
	ICON_SWORDSMAN = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_swordsman.png"),
	ICON_SNIPER = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_sniper.png"),
	ICON_MEDIC = new ResourceLocation(ID.PROJECT_ID, "textures/gui/icons/icon_medic.png"),
	 
	TEXTURE_STRINGS1 = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_strings.png"),
	TEXTURE_BOUNTYPOSTER = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_bounty.png"),
	TEXTURE_WIDGETS = new ResourceLocation(ID.PROJECT_ID, "textures/gui/widgets.png"),
	TEXTURE_CURRENCIES = new ResourceLocation(ID.PROJECT_ID, "textures/gui/currencies.png"),
	TEXTURE_BLANK = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_blank.png"),
	TEXTURE_JOB = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_job.png"),
	TEXTURE_RACE = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_race.png"),
	TEXTURE_FACTION = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_fac.png"),
	TEXTURE_COMBATMODE = new ResourceLocation(ID.PROJECT_ID, "textures/gui/combatmode.png");
	
	
	public static final String
	
	PROJECT_ID = "mineminenomi",
	PROJECT_NAME = "Mine Mine no Mi",
	PROJECT_VERSION = "0.3.0",
	PROJECT_MCVERSION = "1.7.10",
	PROJECT_SOURCEFOLDER = "D:/Programming/Minecraft Modding/Mine Mine no Mi/src/main/resources/",

	CREW_ARLONG = "Arlong Pirates",
	CREW_BAROQUEWORKS = "Baroque Works",
	CREW_ALVIDAPIRATES = "Alvida Pirates",
	CREW_KRIEGPIRATES = "Krieg Pirates",
	
	FACTION_PIRATE = "Pirate",
	FACTION_MARINE = "Marine",
	FACTION_BOUNTYHUNTER = "Bounty Hunter",
	FACTION_REVOLUTIONARY = "Revolutionary",
	FACTION_BANDIT = "Bandit",
	
	RACE_HUMAN = "Human",
	RACE_FISHMAN = "Fishman",
	RACE_CYBORG = "Cyborg",
	
	FSTYLE_SWORDSMAN = "Swordsman",
	FSTYLE_SNIPER = "Sniper",
	FSTYLE_DOCTOR = "Doctor",
	FSTYLE_OKAMA = "Okama",
	FSTYLE_HASSHOKEN = "Hasshoken",
	
	LANG_KEYS_CATEGORY = "category.mmnmkeys", //Mine Mine no Mi Keys
	LANG_KEY_PLAYER = "key.playerui", //Player UI
	LANG_KEY_COMBATMODE = "key.combatmode", //Combat Mode
	LANG_GUI_FACTION = "gui.faction.name",
	LANG_GUI_RACE = "gui.race.name",
	LANG_GUI_STYLE = "gui.style.name",
	LANG_GUI_ABILITIES = "gui.abilities.name",

	
	NULL = "null";
	
	public static final int 
	
	ENTITY_STATE = 27,
	
	GUI_DIALTABLE = 0,
	GUI_PLAYER = 1,
	GUI_CHARACTERCREATOR = 2,
	GUI_ABILITIES = 3;

	
	public static final boolean
	
	DEV_EARLYACCESS = false;
}
