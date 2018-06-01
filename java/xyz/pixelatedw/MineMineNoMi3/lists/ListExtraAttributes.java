package xyz.pixelatedw.MineMineNoMi3.lists;

import java.awt.Color;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelCube;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelSphere;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.EffectType;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelGomuBazooka;

public class ListExtraAttributes
{

	public static AbilityAttribute LIBERATIONBLOCK = new AbilityAttribute("Liberation Block").setProjectileModel(new ModelCube()).setProjectileColor(Color.BLACK).setProjectileDamage(20).setProjectileSize(3, 3, 3);
	 
	public static AbilityAttribute METEOR = new AbilityAttribute("Meteor").setProjectileTicks(500).setProjectileModel(new ModelSphere()).setProjectileSize(20, 20, 20).setProjectileColor("56494B").setProjectileExplosion(13);
	
	public static AbilityAttribute ELTHORTHUNDER = new AbilityAttribute("El Thor Thunder").setProjectileTicks(500).setProjectileModel(new ModelCube()).setProjectileSize(60, 10, 10).setProjectileColor("77abff").setProjectileExplosion(2);
	
	public static AbilityAttribute TSUNOTOKAGEPILLAR = new AbilityAttribute("Tsuno-Tokage Pillar").setProjectileDamage(30).setProjectileModel(new ModelCube()).setProjectileSize(20, 4, 4).setProjectileColor(Color.BLACK).setProjectileTicks(5);
	
	public static AbilityAttribute GOMUGOMUNOPISTOL = new AbilityAttribute("Gomu Gomu no Pistol").setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileSize(3, 1, 1).setProjectileDamage(6);
	public static AbilityAttribute GOMUGOMUNOJETPISTOL = new AbilityAttribute("Gomu Gomu no Jet Pistol").setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileSize(3, 1, 1).setProjectileSpeed(4).setProjectileDamage(12);
	public static AbilityAttribute GOMUGOMUNOELEPHANTGUN = new AbilityAttribute("Gomu Gomu no Elephant Gun").setProjectileModel(new ModelCube()).setProjectileColor("491325").setProjectileSize(7, 5, 5).setProjectileDamage(20);
	public static AbilityAttribute GOMUGOMUNOKONGGUN = new AbilityAttribute("Gomu Gomu no Kong Gun").setProjectileModel(new ModelCube()).setProjectileColor("491325").setProjectileSize(7, 5, 5).setProjectileDamage(30);
	
	public static AbilityAttribute GOMUGOMUNOBAZOOKA = new AbilityAttribute("Gomu Gomu no Bazooka").setProjectileModel(new ModelGomuBazooka()).setProjectileColor("F5DEB3").setProjectileSize(3, 1, 1).setProjectileDamage(10);
	public static AbilityAttribute GOMUGOMUNOJETBAZOOKA = new AbilityAttribute("Gomu Gomu no Jet Bazooka").setProjectileModel(new ModelGomuBazooka()).setProjectileColor("F5DEB3").setProjectileSize(3, 1, 1).setProjectileSpeed(4).setProjectileDamage(16);
	public static AbilityAttribute GOMUGOMUNOGRIZZLYMAGNUM = new AbilityAttribute("Gomu Gomu no Grizzly Magnum").setProjectileModel(new ModelGomuBazooka()).setProjectileColor("F5DEB3").setProjectileSize(7, 3, 5).setProjectileDamage(30);
	public static AbilityAttribute GOMUGOMUNOLEOBAZOOKA = new AbilityAttribute("Gomu Gomu no Leo Bazooka").setProjectileModel(new ModelGomuBazooka()).setProjectileColor("2A3439").setProjectileSize(7, 5, 5).setProjectileDamage(50);
	
	public static AbilityAttribute GOMUGOMUNOGATLING = new AbilityAttribute("Gomu Gomu no Gatling").setProjectileTicks(16).setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileSize(3, 1, 1).setProjectileDamage(5);
	public static AbilityAttribute GOMUGOMUNOJETGATLING = new AbilityAttribute("Gomu Gomu no Jet Gatling").setProjectileTicks(16).setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileSize(3, 1, 1).setProjectileSpeed(4).setProjectileDamage(7);
	public static AbilityAttribute GOMUGOMUNOELEPHANTGATLING = new AbilityAttribute("Gomu Gomu no Elephant Gatling").setProjectileTicks(16).setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileSize(5, 3, 3).setProjectileDamage(12);
	public static AbilityAttribute GOMUGOMUNOKONGORGAN = new AbilityAttribute("Gomu Gomu no Kong Organ").setProjectileTicks(16).setProjectileModel(new ModelCube()).setProjectileColor("2A3439").setProjectileSize(5, 3, 3).setProjectileDamage(15);

	public static AbilityAttribute NORMALBULLET = new AbilityAttribute("Bullet").setProjectileModel(new ModelSphere()).setProjectileDamage(2).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#D3D3D3");
	public static AbilityAttribute KAIROSEKIBULLET = new AbilityAttribute("Kairoseki Bullet").setProjectileModel(new ModelSphere()).setProjectileDamage(4).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#F3F3F3");
	
	public static AbilityAttribute GRAVITO = new AbilityAttribute("Gravito").setProjectileModel(new ModelCube()).setProjectileDamage(5).setProjectileSize(1.5, 0.4, 6).setProjectileTicks(100).setProjectileColor("E590DF").setProjectileAlpha(50).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 550, 1), new PotionEffect(Potion.weakness.id, 550, 1));                       
	
}
