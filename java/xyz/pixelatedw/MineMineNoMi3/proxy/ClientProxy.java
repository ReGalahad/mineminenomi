package xyz.pixelatedw.MineMineNoMi3.proxy;

import java.util.ArrayList;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityRenderer;
import xyz.pixelatedw.MineMineNoMi3.blocks.renderers.RenderBlockDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.MobRenderer;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityArlong;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityChew;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityKuroobi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.models.ModelArlong;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.models.ModelChew;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.models.ModelKuroobi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityDonKrieg;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityGin;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityPearl;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.models.ModelGin;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.models.ModelKrieg;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.models.ModelPearl;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarineCaptain;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMorgan;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarineCaptain;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarineWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMorgan;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.models.ModelDenDenMushi;
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
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelBlueno;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelFukuro;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelJabra;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelJabraWolf;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelKaku;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelKakuGiraffe;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelKalifa;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelKumadori;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelLucci;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelLucciLeopard;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.models.ModelSpandam;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;

public class ClientProxy extends CommonProxy
{

	public void preInit()
	{

	}
	
	public void init()
	{	
		for(int i = 0; i < ListDevilFruits.ALL_ENTITIES.length; i++)
		{
			for(int j = 0; j < ((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).size(); j++)
			{
				RenderingRegistry.registerEntityRenderingHandler(((Class)((Object[])((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).get(j))[0]), new AbilityRenderer(((AbilityAttribute)((Object[])((ArrayList)ListDevilFruits.ALL_ENTITIES[i]).get(j))[1])));
			}
		}
		
		//Marines
		RenderingRegistry.registerEntityRenderingHandler(EntityMarine.class, new MobRenderer(new ModelMarine()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMarineWithGun.class, new MobRenderer(new ModelMarineWithGun()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMarineCaptain.class, new MobRenderer(new ModelMarineCaptain()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMorgan.class, new MobRenderer(new ModelMorgan(), "morgan"));
		
		//W.GOV
		RenderingRegistry.registerEntityRenderingHandler(EntityLucci.class, new MobRenderer(new ModelLucci(), "lucci"));
		RenderingRegistry.registerEntityRenderingHandler(EntityLucciL.class, new MobRenderer(new ModelLucciLeopard(), "luccil"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKaku.class, new MobRenderer(new ModelKaku(), "kaku"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKakuL.class, new MobRenderer(new ModelKakuGiraffe(), "kakul"));
		RenderingRegistry.registerEntityRenderingHandler(EntityJabra.class, new MobRenderer(new ModelJabra(), "jabra"));
		RenderingRegistry.registerEntityRenderingHandler(EntityJabraL.class, new MobRenderer(new ModelJabraWolf(), "jabral"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKalifa.class, new MobRenderer(new ModelKalifa(), "kalifa"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKumadori.class, new MobRenderer(new ModelKumadori(), "kumadori"));
		RenderingRegistry.registerEntityRenderingHandler(EntityFukuro.class, new MobRenderer(new ModelFukuro(), "fukuro"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlueno.class, new MobRenderer(new ModelBlueno(), "blueno"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpandam.class, new MobRenderer(new ModelSpandam(), "spandam")); 
				
		//Pirates
		RenderingRegistry.registerEntityRenderingHandler(EntityPirate.class, new MobRenderer(new ModelMarine()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateWithGun.class, new MobRenderer(new ModelMarineWithGun()));		
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateCaptain.class, new MobRenderer(new ModelMarine()));
		//Arlong Pirates
		RenderingRegistry.registerEntityRenderingHandler(EntityArlong.class, new MobRenderer(new ModelArlong(), "arlong"));
		RenderingRegistry.registerEntityRenderingHandler(EntityChew.class, new MobRenderer(new ModelChew(), "chew"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKuroobi.class, new MobRenderer(new ModelKuroobi(), "kuroobi"));
		//Krieg Pirates
		RenderingRegistry.registerEntityRenderingHandler(EntityDonKrieg.class, new MobRenderer(new ModelKrieg(), "krieg"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGin.class, new MobRenderer(new ModelGin(), "gin"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPearl.class, new MobRenderer(new ModelPearl(), "pearl"));

		//Zoan / Morphs
		/*RenderingRegistry.registerEntityRenderingHandler(EntityMorphVenomDemon.class, new RenderZoanMorph(new ModelVenomDemon(), "venomdemon"));
		RenderingRegistry.registerEntityRenderingHandler(EntityZoanPowerBison.class, new RenderZoanMorph(new ModelPowerBison(), "bisonpower", 1.4, new float[] {0, 0.8f, 0}));
		RenderingRegistry.registerEntityRenderingHandler(EntityZoanSpeedBison.class, new RenderZoanMorph(new ModelSpeedBison(), "bisonspeed", 1.4, new float[] {0, 0.8f, 0}));*/

		//Others
		RenderingRegistry.registerEntityRenderingHandler(EntityDoppelman.class, new MobRenderer(new ModelMarine(), "doppelman"));
		RenderingRegistry.registerEntityRenderingHandler(EntityDenDenMushi.class, new MobRenderer(new ModelDenDenMushi(), "dendenmushi"));

		//Special Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDenDenMushi.class, new RenderBlockDenDenMushi());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ListMisc.DenDenMushi), new RenderItemDenDenMushi(new RenderBlockDenDenMushi(), new TileEntityDenDenMushi()));
		
	}
	
	public void spawnCustomParticles(Entity theEntity, String particleType, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{ 			
		EntityFX fx = null;

		switch(particleType)
		{
			case "pika":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_PIKA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(4).setParticleGravity(0).setParticleAge(20); break;				
			case "yuki":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_YUKI, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(3).setParticleGravity(3).setParticleAge(300); break;
			case "mera":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MERA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(10); break;
			case "moku":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MOKU, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(20); break;
			case "mera2":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MERA2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(10); break;
			case "moku2":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MOKU2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(3F).setParticleGravity(0).setParticleAge(20); break;	
			case "moku3":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MOKU3, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(20); break;
			case "suna":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_SUNA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(30); break;
			case "suna2":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_SUNA2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(30); break;
			case "gasu":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GASU, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(1); break;
			case "gasu2":
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GASU2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(1); break;
		}

		Minecraft.getMinecraft().effectRenderer.addEffect(fx);  
				
	}
}
