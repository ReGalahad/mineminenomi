package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.EntityRegistry;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.WyRegistry;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityArlong;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityChew;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityKuroobi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityDonKrieg;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityGin;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityPearl;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirate;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirateWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityBlueno;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityFukuro;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityJabra;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityJabraL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKaku;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKakuL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKalifa;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKumadori;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityLucci;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityLucciL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntitySpandam;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanBisonPower;

public class ListEntities 
{
	
	public static void init()
	{
		int ids = 0;
		for(int i = 0; i < ListDevilFruits.ALL_ENTITIES.length; i++)
		{
			for(int j = 0; j < ((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).size(); j++)
			{
				EntityRegistry.registerModEntity(((Class)((Object[])((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).get(j))[0]), ((AbilityAttribute)((Object[])((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).get(j))[1]).getAttributeName(), ids++, MainMod.getMineMineNoMi(), 64, 10, true);
			}
		}
		
		//Marines
		WyRegistry.registerMob("Marine with Sword", EntityMarine.class, 0x02258e, 0xFFFFFF);
		WyRegistry.registerMob("Marine with Gun", EntityMarineWithGun.class, 0x02258e, 0xFFFFFF);
		 
		//W.GOV
		WyRegistry.registerMob("Lucci", EntityLucci.class);
		WyRegistry.registerMob("LucciL", EntityLucciL.class);
		WyRegistry.registerMob("Kaku", EntityKaku.class);
		WyRegistry.registerMob("KakuL", EntityKakuL.class);
		WyRegistry.registerMob("Jabra", EntityJabra.class);
		WyRegistry.registerMob("JabraL", EntityJabraL.class);
		WyRegistry.registerMob("Fukuro", EntityFukuro.class);
		WyRegistry.registerMob("Kalifa", EntityKalifa.class);
		WyRegistry.registerMob("Kumadori", EntityKumadori.class);
		WyRegistry.registerMob("Blueno", EntityBlueno.class);
		WyRegistry.registerMob("Spandam", EntitySpandam.class);
		
		//Pirates
		WyRegistry.registerMob("Pirate with Sword", EntityPirate.class, 0x960606, 0xFFFFFF);
		WyRegistry.registerMob("Pirate with Gun", EntityPirateWithGun.class, 0x960606, 0xFFFFFF);
		WyRegistry.registerMob("Pirate Captain", EntityPirateCaptain.class, 0x960606, 0xFFFFFF);
		//Arlong Pirates
		WyRegistry.registerMob("Arlong", EntityArlong.class);
		WyRegistry.registerMob("Chew", EntityChew.class);
		WyRegistry.registerMob("Kuroobi", EntityKuroobi.class);
		//Krieg Pirates
		WyRegistry.registerMob("Don Krieg", EntityDonKrieg.class);
		WyRegistry.registerMob("Gin", EntityGin.class);
		WyRegistry.registerMob("Pearl", EntityPearl.class);
		
		//Zoan
		WyRegistry.registerMob("Zoan Bison Power Point", EntityZoanBisonPower.class);
		
		//Others
	}
	
}
