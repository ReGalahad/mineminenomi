package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoVenomDemon;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class DokuPassiveEvents 
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilFruitProps.getDevilFruit().equals("dokudoku"))
			return;
		
		if (player.isPotionActive(Effects.POISON))
		{
			EffectInstance eff = player.getActivePotionEffect(Effects.POISON);
			
			int duration = eff.getDuration();
			int multiplier = eff.getAmplifier();
			
			player.removePotionEffect(Effects.POISON);
			
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, duration, multiplier, false, false));
		}
	}
	
	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		IEntityStats statProps = EntityStatsCapability.get(attacker);
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		LivingEntity attacked = event.getEntityLiving();
		IEntityStats statPropz = EntityStatsCapability.get(attacked);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("dokudoku"))
			return;
		
		if(devilFruitProps.getZoanPoint().equalsIgnoreCase(ZoanInfoVenomDemon.FORM))
			attacked.addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));
	}
}
