package xyz.pixelatedw.MineMineNoMi3;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.EnumHelper;

public class ID 
{ 
	
	public static final ResourceLocation
	
	PARTICLE_ICON_YUKI = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_yuki.png"),
	PARTICLE_ICON_PIKA = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_pika.png"),
	PARTICLE_ICON_MERA = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_mera.png"),
	PARTICLE_ICON_MOKU = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_moku.png"),
	PARTICLE_ICON_MERA2 = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_mera2.png"),
	PARTICLE_ICON_MOKU2 = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_moku2.png"),
	PARTICLE_ICON_MOKU3 = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_moku3.png"),
	PARTICLE_ICON_SUNA = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_suna.png"),
	PARTICLE_ICON_SUNA2 = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_suna2.png"),
	PARTICLE_ICON_GASU = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_gasu.png"),
	PARTICLE_ICON_GASU2 = new ResourceLocation(ID.PROJECT_ID, "textures/particles/particle_gasu2.png"),	
	
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
	
	HANDTEXTURE_ZOANMORPH_BUSO = new ResourceLocation(ID.PROJECT_ID, "textures/models/zoanmorph/hand_buso.png"),
	
	TEXTURE_NULL = new ResourceLocation(ID.PROJECT_ID, "textures/gui/null.png"),
	
	TEXTURE_STRINGS1 = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_strings.png"),
	TEXTURE_BOUNTYPOSTER = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_bounty.png"),
	TEXTURE_WIDGETS = new ResourceLocation(ID.PROJECT_ID, "textures/gui/widgets.png"),
	TEXTURE_CURRENCIES = new ResourceLocation(ID.PROJECT_ID, "textures/gui/currencies.png"),
	TEXTURE_BLANK = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_blank.png"),
	TEXTURE_JOB = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_job.png"),
	TEXTURE_RACE = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_race.png"),
	TEXTURE_FACTION = new ResourceLocation(ID.PROJECT_ID, "textures/gui/gui_fac.png"),
	TEXTURE_COMBATMODE = new ResourceLocation(ID.PROJECT_ID, "textures/gui/combatmode.png");
	
	
	public static final ArmorMaterial
	
	ARMORMAT_COLABACKPACK = EnumHelper.addArmorMaterial("colabackpack", 400, new int[] {0, 4, 0, 0}, 0),
	ARMORMAT_USELESS = EnumHelper.addArmorMaterial("useless", 100, new int[] {1, 2, 1, 1}, 0);
	
	public static final String
	
	PROJECT_ID = "mineminenomi",
	PROJECT_NAME = "Mine Mine no Mi",
	PROJECT_VERSION = "0.4.0",
	PROJECT_MCVERSION = "1.7.10",
	PROJECT_SOURCEFOLDER = "D:/Programming/Minecraft Modding/Mine Mine no Mi/src/main/resources/",
	
	PARTICLE_NAME_PIKA = "pika",
	PARTICLE_NAME_YUKI = "yuki",
	PARTICLE_NAME_MERA = "mera",
	PARTICLE_NAME_MOKU = "moku",	
	PARTICLE_NAME_MERA2 = "mera2",
	PARTICLE_NAME_MOKU2 = "moku2",
	PARTICLE_NAME_MOKU3 = "moku3",
	PARTICLE_NAME_SUNA = "suna",	
	PARTICLE_NAME_SUNA2 = "suna2",
	PARTICLE_NAME_GASU = "gasu",	
	PARTICLE_NAME_GASU2 = "gasu2",		
	
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
	
	ZOANMORPH_POWER = "power",
	ZOANMORPH_SPEED = "speed",
	ZOANMORPH_DOKU = "doku",
	ZOANMORPH_PHOENIX = "phoenix",
	
	LANG_KEYS_CATEGORY = "category.mmnmkeys", //Mine Mine no Mi Keys
	LANG_KEY_PLAYER = "key.playerui", //Player UI
	LANG_KEY_COMBATMODE = "key.combatmode", //Combat Mode
	LANG_KEY_COMBATSLOT1 = "key.combatslot.1",
	LANG_KEY_COMBATSLOT2 = "key.combatslot.2",
	LANG_KEY_COMBATSLOT3 = "key.combatslot.3",
	LANG_KEY_COMBATSLOT4 = "key.combatslot.4",
	LANG_KEY_COMBATSLOT5 = "key.combatslot.5",
	LANG_KEY_COMBATSLOT6 = "key.combatslot.6",
	LANG_KEY_COMBATSLOT7 = "key.combatslot.7",
	LANG_KEY_COMBATSLOT8 = "key.combatslot.8",
	LANG_GUI_FACTION = "gui.faction.name",
	LANG_GUI_RACE = "gui.race.name",
	LANG_GUI_STYLE = "gui.style.name",
	LANG_GUI_ABILITIES = "gui.abilities.name",
	LANG_GUI_QUESTS = "gui.quests.name",
	LANG_GUI_CURRENTQUEST = "gui.currentquest.name",
	
	LANG_QUEST_SWORDSMANPROGRESSION_01 = "quest.swordsmanprogression01.name",
	
	LANG_QUEST_BOUNTYLOWLEVEL_01 = "quest.bountylowlevel01.name",
			
	LANG_QUEST_SWORDSMANPROGRESSION_01_DESC = "quest.swordsmanprogression01.desc",
	
	
	DIMENSION_NAME_SCENARIOARENA = "scenarioarena",
	
	SCENARIO_ROMANCEDAWN_CAPTAINMORGAN = "scenario_cptmorgan",
	
	NULL = "null";
	
	public static final int 
	
	DIMENSION_ID_SCENARIOARENA = DimensionManager.getNextFreeDimId(),
	
	ENTITY_STATE = 27,
	
	GUI_DIALTABLE = 0,
	GUI_PLAYER = 1,
	GUI_CHARACTERCREATOR = 2,
	GUI_ABILITIES = 3;	
	
	public static final boolean
	
	DEV_EARLYACCESS = false;
}
