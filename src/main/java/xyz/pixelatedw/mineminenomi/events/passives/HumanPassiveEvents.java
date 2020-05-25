package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HumanPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!statsProps.isHuman())
			return;
	
		Ability tekkaiAbility = abilityProps.getEquippedAbility(TekkaiAbility.INSTANCE);
		boolean isTekkaiActive = tekkaiAbility != null && tekkaiAbility.isContinuous();
		if(isTekkaiActive)
			player.setMotion(0, -5, 0);
		
		GeppoAbility geppoAbility = abilityProps.getEquippedAbility(GeppoAbility.INSTANCE);
		boolean blockFallDamage = geppoAbility != null && !geppoAbility.hasFallDamage();
				
		if(blockFallDamage)
		{
			player.fallDistance = 0;
			if(geppoAbility.getCooldown() < geppoAbility.getMaxCooldown() - 10 || geppoAbility.getCooldown() == geppoAbility.getMaxCooldown())
			{
				if(player.onGround)		
					geppoAbility.resetFallDamage();
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event)
	{	
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
				
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!statsProps.isHuman())
			return;

		Ability kamieAbility = abilityProps.getEquippedAbility(KamieAbility.INSTANCE);
		boolean isKamiEActive = kamieAbility != null && kamieAbility.isContinuous();
		if(isKamiEActive)
			event.setCanceled(true);
	}
}
