package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataBase;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.questdata.QuestDataBase;
import xyz.pixelatedw.mineminenomi.api.data.questdata.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitBase;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsBase;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectBase;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataBase;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;

public class ModCapabilities
{

	public static void init()
	{
		// API Capabilities
		AbilityDataCapability.register();
			
		// Mod Capabilities
		DevilFruitCapability.register();
		EntityStatsCapability.register();
		HakiDataCapability.register();
		ExtraEffectCapability.register();
		QuestDataCapability.register();
	}
	
	@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
	public static class Registry
	{
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
		{
			if(event.getObject() instanceof PlayerEntity)
			{
				final AbilityDataBase abilityData = new AbilityDataBase();
				event.addCapability(new ResourceLocation(Env.PROJECT_ID, "ability_data"), AbilityDataCapability.createProvider(abilityData));
				
				final DevilFruitBase devilFruitData = new DevilFruitBase();
				event.addCapability(new ResourceLocation(Env.PROJECT_ID, "devil_fruit"), DevilFruitCapability.createProvider(devilFruitData));
				
				final EntityStatsBase entityStatsData = new EntityStatsBase();
				event.addCapability(new ResourceLocation(Env.PROJECT_ID, "entity_stats"), EntityStatsCapability.createProvider(entityStatsData));
				
				final HakiDataBase hakiData = new HakiDataBase();
				event.addCapability(new ResourceLocation(Env.PROJECT_ID, "haki_data"), HakiDataCapability.createProvider(hakiData));
				
				final ExtraEffectBase extraEffectData = new ExtraEffectBase();
				event.addCapability(new ResourceLocation(Env.PROJECT_ID, "extra_effect"), ExtraEffectCapability.createProvider(extraEffectData));
				
				final QuestDataBase questData = new QuestDataBase();
				event.addCapability(new ResourceLocation(Env.PROJECT_ID, "quest_data"), QuestDataCapability.createProvider(questData));
			}
		}
	}
		
}
