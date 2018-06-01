		package xyz.pixelatedw.MineMineNoMi3.lists;

import java.awt.Color;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelCube;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelSphere;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.EffectType;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelBird;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelHeart;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelMiniHollow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelNegativeHollow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelTokuHollow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelX;

public class ListAttributes 
{
	public static AbilityAttribute POWERPOINT = new AbilityAttribute("Power Point").setAbilityCooldown(1);
	public static AbilityAttribute SPEEDPOINT = new AbilityAttribute("Speed Point").setAbilityCooldown(1);
	
	public static AbilityAttribute FIDDLEBANFF = new AbilityAttribute("Fiddle Banff").setAbilityCooldown(7);
	public static AbilityAttribute KOKUTEICROSS = new AbilityAttribute("Kokutei Cross").setAbilityCooldown(7).setAbilityPassive().setAbilityPunch();
	
//	public static AbilityAttribute SAGARINORYUSEI = new AbilityAttribute("Sagari no Ryusei").setAbilityCooldown(500).addTasks(Tasks.sagariNoRyusei);
//	public static AbilityAttribute MOKO = new AbilityAttribute("Moko").setAbilityCooldown(150).setProjectileModel(new ModelCube()).setProjectileSize(new double[] {0, 0, 0}).addTasks(Tasks.moko);
	
	public static AbilityAttribute DARKMATTER = new AbilityAttribute("Dark Matter").setAbilityCooldown(12).setProjectileModel(new ModelSphere()).setProjectileColor("000000").setProjectileSize(7, 7, 7);
	public static AbilityAttribute KUROUZU = new AbilityAttribute("Kurouzu").setAbilityCooldown(6);
	public static AbilityAttribute LIBERATION = new AbilityAttribute("Liberation").setAbilityCooldown(5);
	public static AbilityAttribute BLACKHOLE = new AbilityAttribute("Black Hole").setAbilityCooldown(8);
	
//	public static AbilityAttribute TENSIONHORMONE = new AbilityAttribute("Tension Hormone").setAbilityCooldown(500).addTasks(Tasks.tensionHormone);
//	public static AbilityAttribute CHIYUHORMONE = new AbilityAttribute("Chiyu Hormone").setAbilityCooldown(500).addTasks(Tasks.chiyuHormone);
	
//	public static AbilityAttribute KILOPRESS = new AbilityAttribute("Kilo Press").setAbilityCooldown(80).addTasks(Tasks.kilopress);
	
	public static AbilityAttribute TODOROKI = new AbilityAttribute("Todoroki").setAbilityCooldown(30).setProjectileModel(new ModelCube()).setProjectileColor("#87CEFA").setProjectileSize(5, 3, 3).setProjectileTicks(10).setProjectileDamage(15).setAbilityRepeater().setAbilityRepeaterFrequency(120);
	
	public static AbilityAttribute PISTOLKISS = new AbilityAttribute("Pistol Kiss").setAbilityCooldown(100).setProjectileModel(new ModelCube()).setProjectileSize(.3, .3, .3).setProjectileColor("#FFC0DB").setProjectileDamage(5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 100, 1), new PotionEffect(Potion.digSlowdown.id, 100, 1));
//	public static AbilityAttribute PERFUMEFEMUR = new AbilityAttribute("Perfume Femur").setAbilityCooldown(200).setItemDamage(15).addTasks(Tasks.parfumefemur);
	public static AbilityAttribute SLAVEARROW = new AbilityAttribute("Slave Arrow").setAbilityCooldown(100).setProjectileModel(new ModelCube()).setProjectileSize(.3, .3, .3).setProjectileColor("#FFC0DB").setProjectileDamage(5).setAbilityRepeater();
	public static AbilityAttribute MEROMEROMELLOW = new AbilityAttribute("Mero Mero Mellow").setAbilityCooldown(150).setProjectileModel(new ModelHeart()).setProjectileSize(3, 3, 3).setProjectileColor("#FFC0DB").setProjectileDamage(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 200, 1)).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.digSlowdown.id, 200, 1));
	
	public static AbilityAttribute SPIRALHOLLOW = new AbilityAttribute("Spiral Hollow").setAbilityCooldown(200).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileColor("#F8F8FF").setProjectileSize(5, 3, 3).setProjectileTicks(3);
	public static AbilityAttribute ATOMICSPAR = new AbilityAttribute("Atomic Spar").setAbilityCooldown(120).setProjectileModel(new ModelX()).setProjectileSize(2, 2, 2).setProjectileColor("#F8F8FF").setProjectileDamage(10);
//	public static AbilityAttribute SPARCLAW = new AbilityAttribute("Spar Claw").setAbilityCooldown(120).addTasks(Tasks.sparclaw);
	public static AbilityAttribute SPIDER = new AbilityAttribute("Spider").addEffects(EffectType.USER,new PotionEffect(Potion.resistance.id, 10, 100), new PotionEffect(Potion.moveSlowdown.id, 10, 100));
	
	public static AbilityAttribute NEGATIVEHOLLOW = new AbilityAttribute("Negative Hollow").setAbilityCooldown(6).setProjectileModel(new ModelNegativeHollow()).setProjectileYRotation(-90).setProjectileTexture("negativehollow").setProjectileAlpha(100).setProjectileSize(2, 2, 2).setProjectileDamage(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.confusion.id, 200, 1), new PotionEffect(Potion.moveSlowdown.id, 200, 1));
	public static AbilityAttribute MINIHOLLOW = new AbilityAttribute("Mini Hollow").setAbilityCooldown(3).setProjectileModel(new ModelMiniHollow()).setProjectileSize(0.4, 0.4, 0.4).setProjectileXRotation(90).setProjectileZRotation(90).setProjectileColor("#F8F8FF").setProjectileAlpha(100).setProjectileDamage(2).setProjectileExplosion(2, false).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.confusion.id, 200, 0), new PotionEffect(Potion.moveSlowdown.id, 200, 0)).setAbilityRepeater();
	public static AbilityAttribute TOKUHOLLOW = new AbilityAttribute("Toku Hollow").setAbilityCooldown(12).setProjectileModel(new ModelTokuHollow()).setProjectileTexture("tokuhollow").setProjectileYRotation(-90).setProjectileAlpha(100).setProjectileSize(4, 4, 4).setProjectileDamage(10).setProjectileExplosion(7, false, false).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.confusion.id, 400, 1), new PotionEffect(Potion.moveSlowdown.id, 400, 1));
	
//	public static AbilityAttribute BLACKKNIGHT = new AbilityAttribute("Black Knight");
	public static AbilityAttribute OVERHEAT = new AbilityAttribute("Overheat").setAbilityCooldown(12).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileSize(5, 1, 1).setProjectileExplosion(3, false).setProjectileColor("#f77c25");
	public static AbilityAttribute SORANOMICHI = new AbilityAttribute("Sora no Michi").setAbilityCooldown(1);
	public static AbilityAttribute PARASITE = new AbilityAttribute("Parasite").setAbilityCooldown(7);
	
//	public static AbilityAttribute BARIBARINOPISTOL = new AbilityAttribute("Bari Bari no Pistol").setItemDamage(20);
	public static AbilityAttribute BARRIERBALL = new AbilityAttribute("Barrier Ball").setAbilityCooldown(2);
	public static AbilityAttribute BARRIERCRASH = new AbilityAttribute("Barrier Crash").setAbilityCooldown(5).setProjectileTicks(20).setProjectileModel(new ModelCube()).setProjectileColor("#87CEFA").setProjectileSize(.3, 9, 9).setProjectileDamage(15);
	public static AbilityAttribute BARRIER = new AbilityAttribute("Barrier").setAbilityCooldown(1.5);
	
	public static AbilityAttribute FUBUKI = new AbilityAttribute("Fubuki").setAbilityCooldown(12);//.addTasks(Tasks.fubuki);
	public static AbilityAttribute TABIRAYUKI = new AbilityAttribute("Tabira Yuki").setAbilityPassive();
//	public static AbilityAttribute MANNENYUKI = new AbilityAttribute("Mannen Yuki");
	public static AbilityAttribute KAMAKURAJUSSOSHI = new AbilityAttribute("Kamakura Jussoshi").setAbilityCooldown(18);//.addTasks(Tasks.kamakurajusshoshi);
	public static AbilityAttribute YUKIRABI = new AbilityAttribute("Yuki Rabi").setAbilityCooldown(3).setProjectileColor(Color.WHITE).setProjectileDamage(5).setProjectileModel(new ModelCube()).setProjectileSize(1, 1, 1).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 50, 1)).setAbilityRepeater();
	public static AbilityAttribute KAMAKURA = new AbilityAttribute("Kamakura").setAbilityCooldown(6);
	
//	public static AbilityAttribute SHINOKUNI = new AbilityAttribute("Shinokuni");
	public static AbilityAttribute KARAKUNI = new AbilityAttribute("Karakuni").setAbilityCooldown(20);
	public static AbilityAttribute BLUESWORD = new AbilityAttribute("Blue Sword").setAbilityPassive();
	public static AbilityAttribute GASTANET = new AbilityAttribute("Gastanet").setAbilityCooldown(6).setAbilityExplosion(5, false);
	public static AbilityAttribute GASTILLE = new AbilityAttribute("Gastille").setAbilityCooldown(8).setProjectileSpeed(6).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileColor("324AB2").setProjectileSize(2, 1, 1).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1)).setAbilityRepeater().setProjectileExplosion(1, false);
	public static AbilityAttribute GASROBE = new AbilityAttribute("Gas Robe").setAbilityCooldown(6).setProjectileSpeed(6).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setAbilityRepeater();
	
	public static AbilityAttribute DOKUFUGU = new AbilityAttribute("Doku Fugu").setAbilityCooldown(15).setProjectileDamage(15).setProjectileModel(new ModelSphere()).setProjectileColor("A020F0").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1)).setAbilityRepeaterFrequency(27).setAbilityRepeater();
	public static AbilityAttribute VENOMDEMON = new AbilityAttribute("Venom Demon").setAbilityCooldown(20).setAbilityPassive();
	public static AbilityAttribute VENOMROAD = new AbilityAttribute("Venom Road").setAbilityCooldown(8).setProjectileDamage(30).setProjectileModel(new ModelCube()).setProjectileColor("A020F0").setProjectileSize(6, 4, 4).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1));
	public static AbilityAttribute CHLOROBALL = new AbilityAttribute("Chloro Ball").setAbilityCooldown(6).setProjectileDamage(10).setProjectileModel(new ModelSphere()).setProjectileColor("A020F0").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1));
	public static AbilityAttribute HYDRA = new AbilityAttribute("Hydra").setAbilityCooldown(8).setProjectileDamage(30).setProjectileModel(new ModelCube()).setProjectileColor("A020F0").setProjectileSize(6, 4, 4).setProjectileTicks(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1));
	
	public static AbilityAttribute CANDLEHOUSE = new AbilityAttribute("Candle House").setAbilityCooldown(30);
	public static AbilityAttribute CANDLEWALL = new AbilityAttribute("Candle Wall").setAbilityCooldown(8);
	public static AbilityAttribute DORUDORUARTSKEN = new AbilityAttribute("Doru Doru Arts : Ken").setAbilityPassive();
	public static AbilityAttribute DORUDORUARTSMORI = new AbilityAttribute("Doru Doru Arts : Mori").setAbilityCooldown(5).setProjectileDamage(15).setProjectileModel(new ModelCube()).setProjectileColor("A2ADD0").setProjectileSize(5, .5, .5);
	
	public static AbilityAttribute BAKURETSUKAZAN = new AbilityAttribute("Bakuretsu Kazan").setAbilityCooldown(17);
	public static AbilityAttribute RYUSEIKAZAN = new AbilityAttribute("Ryusei Kazan").setAbilityCooldown(15).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileSize(4, 2, 2).setProjectileColor("CF1020").setAbilityRepeater();
	public static AbilityAttribute MEIGO = new AbilityAttribute("Meigo").setAbilityCooldown(12).setProjectileDamage(40).setProjectileModel(new ModelCube()).setProjectileColor("CF1020").setProjectileSize(6, 2, 2).setProjectileTicks(3);
	public static AbilityAttribute DAIFUNKA = new AbilityAttribute("Dai Funka").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileSize(4, 2, 2).setProjectileColor("CF1020");
	
	public static AbilityAttribute GROUNDDEATH = new AbilityAttribute("Ground Death").setAbilityCooldown(15);
	public static AbilityAttribute BARJAN = new AbilityAttribute("Barjan").setAbilityCooldown(5).setProjectileDamage(15).setProjectileModel(new ModelCube()).setProjectileColor("967117").setProjectileSize(1.5, 0.4, 6).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.hunger.id, 500, 1));
	public static AbilityAttribute SABLES = new AbilityAttribute("Sables").setAbilityCooldown(3).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute DESERTSPADA = new AbilityAttribute("Desert Spada").setAbilityCooldown(10);
	
	public static AbilityAttribute TSUNOTOKAGE = new AbilityAttribute("Tsuno-Tokage").setAbilityCooldown(10);
//	public static AbilityAttribute SHADOWSASGARD = new AbilityAttribute("Shadow's Asgard").setAbilityCooldown(400).addTasks(Tasks.shadowsasgard);
	public static AbilityAttribute BLACKBOX = new AbilityAttribute("Black Box").setAbilityCooldown(6).setProjectileModel(new ModelCube()).setProjectileColor(Color.black).setProjectileSize(2, 2, 2);
	public static AbilityAttribute DOPPELMAN = new AbilityAttribute("Doppelman").setAbilityCooldown(20).setAbilityPassive();
	public static AbilityAttribute BRICKBAT = new AbilityAttribute("Brick Bat").setAbilityCooldown(4).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileSize(1,	1, 1).setProjectileColor(Color.black).setAbilityRepeater();
	
	public static AbilityAttribute SHIMAYURASHI = new AbilityAttribute("Shima Yurashi").setAbilityCooldown(11).setProjectileDamage(20).setProjectileExplosion(2, false).setAbilityRepeater();
	public static AbilityAttribute KABUTOWARI = new AbilityAttribute("Kabutowari").setAbilityCooldown(7).setAbilityExplosion(5, false);
	public static AbilityAttribute KAISHIN = new AbilityAttribute("Kaishin").setAbilityCooldown(7).setProjectileDamage(50).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setProjectileExplosion(1, false, false);
	
	public static AbilityAttribute KICKBOMB = new AbilityAttribute("Kick Bomb").setAbilityCooldown(7).setAbilityExplosion(7, false);
	public static AbilityAttribute NOSEFANCYCANNON = new AbilityAttribute("Nose Fancy Cannon").setAbilityCooldown(3).setProjectileModel(new ModelCube()).setProjectileColor("3D2B1F").setProjectileSize(.8, .4, .4).setProjectileDamage(10).setProjectileExplosion(3, false);
	
	public static AbilityAttribute URSUSSHOCK = new AbilityAttribute("Ursus Shock").setAbilityCooldown(15).setProjectileModel(new ModelCube()).setProjectileColor("F8F8FF").setProjectileSize(3.5, 3.5, 3.5).setProjectileDamage(50).setProjectileExplosion(4, false, true).setAbilityCharges(40);
	public static AbilityAttribute PADHO = new AbilityAttribute("Pad Ho").setAbilityCooldown(8).setProjectileModel(new ModelCube()).setProjectileColor("F8F8FF").setProjectileSize(1, 1, 1).setProjectileDamage(10).setProjectileExplosion(1, false, true);
	public static AbilityAttribute TSUPPARIPADHO = new AbilityAttribute("Tsuppari Pad Ho").setAbilityCooldown(8).setProjectileModel(new ModelCube()).setProjectileColor("F8F8FF").setProjectileSize(1, 1, 1).setProjectileDamage(10).setProjectileExplosion(1, false, true).setAbilityRepeater().setAbilityRepeaterFrequency(30);
	public static AbilityAttribute HANPATSU = new AbilityAttribute("Hanpatsu").setAbilityCooldown(4).setAbilityPassive().setAbilityPunch();

	public static AbilityAttribute WHITELAUNCHER = new AbilityAttribute("White Launcher").setAbilityCooldown(7).setAbilityCharges(20);
	public static AbilityAttribute WHITESNAKE = new AbilityAttribute("White Snake").setAbilityCooldown(7).setProjectileTicks(120).setProjectileModel(new ModelCube()).setProjectileSpeed(5).setProjectileSize(0, 0, 0).setProjectileDamage(30).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 120, 0));
	public static AbilityAttribute WHITEOUT = new AbilityAttribute("White Out").setAbilityCooldown(4).setProjectileModel(new ModelCube()).setProjectileSpeed(5).setProjectileSize(0, 0, 0).setProjectileDamage(5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 500, 5)).setAbilityRepeater().setAbilityRepeaterFrequency(15);
	
	public static AbilityAttribute SANGO = new AbilityAttribute("Sango").setAbilityCooldown(10).setProjectileTicks(10).setProjectileModel(new ModelCube()).setProjectileSize(10, 3, 3).setProjectileColor("7CB9E8").setProjectileDamage(15);
	public static AbilityAttribute KARI = new AbilityAttribute("Kari").setAbilityCooldown(6).setAbilityExplosion(6, false, false);
	public static AbilityAttribute RAIGO = new AbilityAttribute("Raigo").setAbilityCooldown(17).setProjectileModel(new ModelCube()).setProjectileColor("5D8AA8").setProjectileSize(8, 8, 8).setProjectileDamage(40).setProjectileExplosion(7, false);
	public static AbilityAttribute VOLTVARI = new AbilityAttribute("Volt Vari").setAbilityCooldown(7).setProjectileModel(new ModelSphere()).setProjectileModel(new ModelSphere()).setProjectileSize(3, 3, 3).setProjectileDamage(5).setProjectileColor("7CB9E8");
	public static AbilityAttribute ELTHOR = new AbilityAttribute("El Thor").setAbilityCooldown(10);
	 
	public static AbilityAttribute GAMMAKNIFE = new AbilityAttribute("Gamma Knife").setAbilityCooldown(30).setProjectileTicks(20).setProjectileModel(new ModelCube()).setProjectileColor("00AB66").setProjectileDamage(100).setProjectileSize(5, 1, 1);
	public static AbilityAttribute MES = new AbilityAttribute("Mes").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch(); 
	public static AbilityAttribute COUNTERSHOCK = new AbilityAttribute("Counter Shock").setAbilityCooldown(8).setProjectileTicks(7).setProjectileModel(new ModelCube()).setProjectileSize(1, 1, 1).setProjectileDamage(40).setProjectileExplosion(1, false, false);
	public static AbilityAttribute ROOM = new AbilityAttribute("Room").setAbilityCooldown(5);
	
	public static AbilityAttribute NORONOROBEAM = new AbilityAttribute("Noro Noro Beam").setAbilityCooldown(5).setProjectileModel(new ModelCube()).setProjectileSize(.6, .6, .6).setProjectileColor("FF1DCE").setProjectileSpeed(5);
	public static AbilityAttribute KYUBIRUSH = new AbilityAttribute("Kyubi Rush").setAbilityCooldown(10).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute NORONOROBEAMSWORD = new AbilityAttribute("Noro Noro Beam Sword").setAbilityPassive();
	
	public static AbilityAttribute SHISHANOTE = new AbilityAttribute("Shisha no Te").setAbilityCooldown(4).setProjectileDamage(5).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setProjectileExplosion(3, false);
	public static AbilityAttribute SKATTING = new AbilityAttribute("Skatting").addEffects(EffectType.USER, new PotionEffect(Potion.invisibility.id, 30, 0)).setAbilityPassive();  	
	 
	public static AbilityAttribute GEARSECOND = new AbilityAttribute("Gear Second").setAbilityPassive();
	public static AbilityAttribute GEARTHIRD = new AbilityAttribute("Gear Third").setAbilityPassive();
	public static AbilityAttribute GEARFOURTH = new AbilityAttribute("Gear Forth").setAbilityPassive();
	public static AbilityAttribute GOMUGOMUNOBAZOOKA = new AbilityAttribute("Gomu Gomu no Bazooka").setAbilityCharges(10);
	public static AbilityAttribute GOMUGOMUNOGATLING = new AbilityAttribute("Gomu Gomu no Gatling").setAbilityRepeater();
	public static AbilityAttribute GOMUGOMUNOPISTOL = new AbilityAttribute("Gomu Gomu no Pistol");
	
	public static AbilityAttribute AMANOMURAKUMO = new AbilityAttribute("Ama no Murakumo").setAbilityPassive();
	public static AbilityAttribute AMATERASU = new AbilityAttribute("Amaterasu").setAbilityCooldown(15).setProjectileTicks(150).setProjectileModel(new ModelCube()).setProjectileSize(5, 1.2, 1.2).setProjectileColor("FFFF00").setProjectileSpeed(7).setProjectileDamage(35).setProjectileExplosion(6, false);
	public static AbilityAttribute YASAKANINOMAGATAMA = new AbilityAttribute("Yasakani no Magatama").setAbilityCooldown(2.5).setProjectileModel(new ModelCube()).setProjectileSize(3, .5, .5).setProjectileColor("FFFF00").setProjectileSpeed(5).setAbilityRepeater().setProjectileDamage(2).setProjectileExplosion(1, false);
	public static AbilityAttribute YATANOKAGAMI = new AbilityAttribute("Yata no Kagami").setAbilityCooldown(4);
	 
	public static AbilityAttribute SPRINGDEATHKNOCK = new AbilityAttribute("Spring Death Knock").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileColor("E6E8FA").setProjectileSize(3, 1, 1).setProjectileTicks(3);
	public static AbilityAttribute SPRINGSNIPE = new AbilityAttribute("Spring Snipe").setAbilityCooldown(6).setAbilityCharges(20);
	public static AbilityAttribute SPRINGHOPPER = new AbilityAttribute("Spring Hopper").setAbilityCooldown(0.6).setAbilityCharges(10);

	public static AbilityAttribute ICETIMECAPSULE = new AbilityAttribute("Ice Time Capsule").setAbilityCooldown(15);
	public static AbilityAttribute ICESABER = new AbilityAttribute("Ice Saber").setAbilityPassive();
	public static AbilityAttribute ICEBALL = new AbilityAttribute("Ice Ball").setAbilityCooldown(6).setProjectileDamage(5).setProjectileModel(new ModelSphere()).setProjectileColor("00FFFF").setProjectileSize(5, 5, 5);
	public static AbilityAttribute ICEAGE = new AbilityAttribute("Ice Age").setAbilityCooldown(15).addEffects(EffectType.AOE, new PotionEffect(Potion.moveSlowdown.id, 200, 100), new PotionEffect(Potion.digSlowdown.id, 200, 100)).setEffectRadius(20);
	public static AbilityAttribute ICEBLOCKPARTISAN = new AbilityAttribute("Ice Block : Partisan").setAbilityCooldown(7).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileColor("00FFFF").setProjectileSize(5, .5, .5).setAbilityRepeater();
	public static AbilityAttribute ICEBLOCKPHEASANT = new AbilityAttribute("Ice Block : Pheasant").setAbilityCooldown(25).setProjectileDamage(45).setProjectileModel(new ModelBird()).setProjectileColor("00FFFF").setProjectileSize(5, 5, 5);
	
	public static AbilityAttribute ENJOMO  = new AbilityAttribute("Enjomo").setAbilityCooldown(10);
	public static AbilityAttribute JUJIKA = new AbilityAttribute("Jujika").setAbilityCooldown(6).setProjectileDamage(5).setProjectileModel(new ModelSphere()).setProjectileColor("FF0000").setProjectileSize(.2, .2, .2);
	public static AbilityAttribute HIDARUMA = new AbilityAttribute("Hidaruma").setAbilityCooldown(6).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0);
	public static AbilityAttribute DAIENKAIENTEI = new AbilityAttribute("Dai Enkai : Entei").setAbilityCooldown(25).setProjectileModel(new ModelSphere()).setProjectileDamage(45).setProjectileColor("FF0000").setProjectileSize(9, 9, 9).setProjectileExplosion(7).setAbilityCharges(72);
	public static AbilityAttribute HIGAN = new AbilityAttribute("Higan").setAbilityCooldown(4).setProjectileModel(new ModelCube()).setProjectileDamage(5).setProjectileColor("FF0000").setProjectileSize(.3, .3, .3).setAbilityRepeater().setAbilityRepeaterFrequency(4);
	public static AbilityAttribute HIKEN = new AbilityAttribute("Hiken").setAbilityCooldown(10).setProjectileModel(new ModelCube()).setProjectileDamage(20).setProjectileColor("FF0000").setProjectileSize(2, 1, 1).setProjectileExplosion(2);
 
	public static AbilityAttribute SORU = new AbilityAttribute("Soru").addEffects(EffectType.USER, new PotionEffect(Potion.moveSpeed.id, 30, 5)).setAbilityPassive();
	public static AbilityAttribute TEKKAI = new AbilityAttribute("Tekkai").addEffects(EffectType.USER,new PotionEffect(Potion.resistance.id, 30, 100), new PotionEffect(Potion.moveSlowdown.id, 30, 100), new PotionEffect(Potion.digSlowdown.id, 30, 100)).setAbilityPassive();
	public static AbilityAttribute GEPPO = new AbilityAttribute("Geppo").setAbilityCooldown(0.9);
	public static AbilityAttribute RANKYAKU = new AbilityAttribute("Rankyaku").setAbilityCooldown(9).setProjectileTicks(100).setProjectileModel(new ModelCube()).setProjectileSize(1.5, 0.4, 6).setProjectileColor("69E3FF").setProjectileDamage(20).setProjectileExplosion(5, false);
	public static AbilityAttribute SHIGAN = new AbilityAttribute("Shigan").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute KAMIE = new AbilityAttribute("Kamie").addEffects(EffectType.USER,new PotionEffect(Potion.resistance.id, 20, 100)).setAbilityPassive();
	
	public static AbilityAttribute UCHIMIZU = new AbilityAttribute("Uchimizu").setAbilityCooldown(5).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(1.3, 1, 1).setProjectileDamage(5).setAbilityRepeater().setAbilityRepeaterFrequency(4);
	public static AbilityAttribute SOSHARK = new AbilityAttribute("Soshark").setAbilityCooldown(8).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(1.2, .8, .8).setProjectileDamage(25);
	public static AbilityAttribute KACHIAGEHAISOKU = new AbilityAttribute("Kachiage Haisoku").setAbilityCooldown(15).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SAMEHADASHOTEI = new AbilityAttribute("Samehada Shotei").addEffects(EffectType.USER, new PotionEffect(Potion.resistance.id, 10, 120), new PotionEffect(Potion.moveSlowdown.id, 10, 120)).setAbilityPassive();	
	public static AbilityAttribute KARAKUSAGAWARASEIKEN = new AbilityAttribute("Karakusagawara Seiken").setAbilityCooldown(25);
	
	//public static AbilityAttribute MURASAME = new AbilityAttribute("Murasame").setAbilityCooldown(250).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(1.8, 1, 1).setProjectileDamage(15).setAbilityRepeater();
	//public static AbilityAttribute YARINAMI = new AbilityAttribute("Yarinami").setAbilityCooldown(150).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(3, 1, 1).setProjectileDamage(15);
	
	public static AbilityAttribute FRESHFIRE = new AbilityAttribute("Fresh Fire").setAbilityCooldown(1.5).setProjectileTicks(7).setProjectileSize(.01, .01, .01).setProjectileDamage(1);
	public static AbilityAttribute COLAOVERDRIVE = new AbilityAttribute("Cola Overdrive").setAbilityCooldown(7);
	public static AbilityAttribute RADICALBEAM = new AbilityAttribute("Radical Beam").setAbilityCooldown(5).setProjectileModel(new ModelCube()).setProjectileColor("FFFF00").setProjectileSize(1, .5, .5).setProjectileDamage(10).setProjectileExplosion(4, false);
	public static AbilityAttribute STRONGRIGHT = new AbilityAttribute("Strong Right").setAbilityCooldown(2.5).setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileTicks(5).setProjectileSize(1.5, 1, 1).setProjectileDamage(10);
	public static AbilityAttribute COUPDEVENT = new AbilityAttribute("Coup de Vent").setAbilityCooldown(10).setProjectileTicks(7).setProjectileSize(.01, .01, .01).setProjectileDamage(10).setAbilityCharges(30);
	
	public static AbilityAttribute KENBUNSHOKUHAKI = new AbilityAttribute("Kenbunshoku Haki").setAbilityPassive();
	public static AbilityAttribute BUSOSHOKUHAKI = new AbilityAttribute("Busoshoku Haki").setAbilityPassive();
	public static AbilityAttribute HAOSHOKUHAKI = new AbilityAttribute("Haoshoku Haki");
/*
	public static AbilityAttribute DIALREJECT = new AbilityAttribute("Reject Dial").setItemMaxStack(16).setProjectileExplosion(4, false).setItemMaxDamage(1).addTasks(Tasks.rejectDial, Tasks.consumable);
	public static AbilityAttribute DIALBREATH = new AbilityAttribute("Breath Dial").setItemMaxStack(16).setItemMaxDamage(4).addTasks(Tasks.breathDial, Tasks.consumable);
	public static AbilityAttribute DIALIMPACT = new AbilityAttribute("Impact Dial").setItemMaxStack(16).setProjectileExplosion(4, false).setItemMaxDamage(1).addTasks(Tasks.consumable);
	public static AbilityAttribute DIALFIRE = new AbilityAttribute("Fire Dial").setItemMaxStack(16).setProjectileModel(new ModelCube()).setProjectileDamage(8).setProjectileColor("FF0000").setProjectileSize(1, .8, .8).setProjectileExplosion(1).setItemMaxDamage(2).addTasks(Tasks.consumable);
	public static AbilityAttribute DIALMILKY = new AbilityAttribute("Milky Road").setItemMaxStack(16).setProjectileModel(new ModelCube()).setProjectileSize(.1, .1, .1).setProjectileColor(Color.BLUE).setProjectileTicks(20).setItemMaxDamage(1).addTasks(Tasks.milkydial, Tasks.consumable);
	public static AbilityAttribute DIALAXE = new AbilityAttribute("Axe Dial").setItemMaxStack(16).setProjectileModel(new ModelCube()).setProjectileDamage(5).setProjectileSize(1.5, 0.4, 6).setProjectileTicks(100).setProjectileColor("69E3FF").setItemMaxDamage(4).addTasks(Tasks.consumable);
	*/
	public static AbilityAttribute NULL = new AbilityAttribute("n/a");
}
