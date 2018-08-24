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
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDojoSensei;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.models.ModelDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.models.ModelDojoSensei;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirate;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.EntityPirateWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityBazooka;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityBrickBat;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityFist;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityHydra;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityMeigo;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityNoroBeam;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPaw;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPheasant;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPhoenixFull;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityPhoenixHybrid;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityShark;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntitySpear;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityTrident;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityYukiRabi;
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
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelPhoenixFull;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelPhoenixHybrid;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;

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

		//Others
		RenderingRegistry.registerEntityRenderingHandler(EntityDoppelman.class, new MobRenderer(new ModelMarine(), "doppelman"));
		RenderingRegistry.registerEntityRenderingHandler(EntityDenDenMushi.class, new MobRenderer(new ModelDenDenMushi(), "dendenmushi"));
		RenderingRegistry.registerEntityRenderingHandler(EntityDojoSensei.class, new MobRenderer(new ModelDojoSensei(), "dojosensei"));

		//Special Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDenDenMushi.class, new RenderBlockDenDenMushi());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ListMisc.DenDenMushi), new RenderItemDenDenMushi(new RenderBlockDenDenMushi(), new TileEntityDenDenMushi()));
		
		/** TODO Delete any and all mentions of these */
		//Temp
		RenderingRegistry.registerEntityRenderingHandler(TempEntityFist.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.HIKEN).setModelOffsets(0, 2, 0)));		
		RenderingRegistry.registerEntityRenderingHandler(TempEntityBazooka.class, new AbilityRenderer(new AbilityAttribute(ListExtraAttributes.GOMUGOMUNOBAZOOKA).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityBrickBat.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.BRICKBAT).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityHydra.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.HYDRA).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityMeigo.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.MEIGO).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityNoroBeam.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.NORONOROBEAM).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityPaw.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.PADHO).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityPheasant.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.ICEBLOCKPHEASANT).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityShark.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.MURASAME).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntitySpear.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.DORUDORUARTSMORI).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityTrident.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.ICEBLOCKPARTISAN).setModelOffsets(0, 2, 0)));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityYukiRabi.class, new AbilityRenderer(new AbilityAttribute(ListAttributes.YUKIRABI).setModelOffsets(0, 2, 0)));
		
		RenderingRegistry.registerEntityRenderingHandler(TempEntityPhoenixFull.class, new RenderZoanMorph(new ModelPhoenixFull(), "phoenixfull"));
		RenderingRegistry.registerEntityRenderingHandler(TempEntityPhoenixHybrid.class, new RenderZoanMorph(new ModelPhoenixHybrid(), "phoenixhybrid"));
	}
	
	public void spawnCustomParticles(Entity theEntity, String particleType, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{ 			
		EntityFX fx = null;

		switch(particleType)
		{
			case ID.PARTICLE_NAME_PIKA:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_PIKA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(4).setParticleGravity(0).setParticleAge(20); break;	
			case ID.PARTICLE_NAME_PIKABIG:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_PIKA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(50).setParticleGravity(0).setParticleAge(10).setHasZoom(); break;
			case ID.PARTICLE_NAME_YUKI:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_YUKI, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(3).setParticleGravity(3).setParticleAge(300); break;
			case ID.PARTICLE_NAME_MERA:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MERA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(10); break;
			case ID.PARTICLE_NAME_MOKU:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MOKU, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(20); break;
			case ID.PARTICLE_NAME_MERA2:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MERA2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(10); break;
			case ID.PARTICLE_NAME_MOKU2:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MOKU2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(3F).setParticleGravity(0).setParticleAge(20); break;	
			case ID.PARTICLE_NAME_MOKU3:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_MOKU3, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.3F).setParticleGravity(0).setParticleAge(20); break;
			case ID.PARTICLE_NAME_SUNA:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_SUNA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(30); break;
			case ID.PARTICLE_NAME_SUNA2:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_SUNA2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(30); break;
			case ID.PARTICLE_NAME_GASU:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GASU, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(1); break;
			case ID.PARTICLE_NAME_GASU2:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GASU2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(1); break;
			case ID.PARTICLE_NAME_BLUEFLAME:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_BLUEFLAME, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.2F).setParticleGravity(0).setParticleAge(1); break;
			case ID.PARTICLE_NAME_DARKNESS:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_DARKNESS, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1.2F).setParticleGravity(-1 + (theEntity.worldObj.rand.nextInt(2) * -1)).setParticleAge(30 + theEntity.worldObj.rand.nextInt(10)); break;
			case ID.PARTICLE_NAME_GORO:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GORO, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(2F).setParticleGravity(0).setParticleAge(1); break;
			case ID.PARTICLE_NAME_DOKU:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_DOKU, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(1F).setParticleGravity(0).setParticleAge(1 + theEntity.worldObj.rand.nextInt(2)); break;
			case ID.PARTICLE_NAME_ITO:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_ITO, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(30).setParticleGravity(0).setParticleAge(10); break;
			case ID.PARTICLE_NAME_GURA:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GURA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(3).setParticleGravity(0).setParticleAge(10); break;
			case ID.PARTICLE_NAME_GURA2:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GURA2, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(3).setParticleGravity(0).setParticleAge(10); break;
			case ID.PARTICLE_NAME_GURABIG:
				fx = new EntityParticleFX(theEntity.worldObj, ID.PARTICLE_ICON_GURA, posX, posY, posZ, motionX, motionY, motionZ).setParticleScale(50).setParticleGravity(0).setParticleAge(10).setHasZoom(); break;	
		}

		Minecraft.getMinecraft().effectRenderer.addEffect(fx);  
				
	}
}
