package xyz.pixelatedw.MineMineNoMi3.proxy;

import java.util.ArrayList;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityRenderer;
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
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarineWithGun;
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
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFXTest;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanBisonPower;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanBisonPower;
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

		//Zoan
		RenderingRegistry.registerEntityRenderingHandler(EntityZoanBisonPower.class, new RenderZoanBisonPower());
	}
	
	public void generateTestParticles(Entity theEntity)
	{ 
		for(int i = 0; i < 32; i++)
		{
			//double motionX = theEntity.worldObj.rand.nextGaussian() * 0.02D;
			//double motionY = theEntity.worldObj.rand.nextGaussian() * 0.02D;
			//double motionZ = theEntity.worldObj.rand.nextGaussian() * 0.02D;			
			
			EntityFX fx = new EntityParticleFXTest
					(
						theEntity.worldObj, 
						theEntity.posX, 
						theEntity.posY - 1, 
						theEntity.posZ, 
						0, 
						0, 
						0
					);
				 
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);  
		}
	}
}
