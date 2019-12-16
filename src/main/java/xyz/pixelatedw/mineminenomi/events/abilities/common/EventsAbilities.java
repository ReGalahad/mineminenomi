package xyz.pixelatedw.mineminenomi.events.abilities.common;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID)
public class EventsAbilities
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			IEntityStats statProps = EntityStatsCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();
			
			for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
			{
				if (abilityProps.getHotbarAbilityFromSlot(i) != null && !abilityProps.getHotbarAbilityFromSlot(i).isOnCooldown() && !abilityProps.getHotbarAbilityFromSlot(i).isPassiveActive() && !abilityProps.getHotbarAbilityFromSlot(i).isCharging())
				{
					abilityProps.getHotbarAbilityFromSlot(i).tick(player);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityShootProjectile(ArrowLooseEvent event)
	{
		if (event.getPlayer() != null)
		{
			PlayerEntity player = event.getPlayer();
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			IEntityStats statProps = EntityStatsCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
			{
				if (abilityProps.getHotbarAbilityFromSlot(i) != null && !abilityProps.getHotbarAbilityFromSlot(i).isOnCooldown() && abilityProps.getHotbarAbilityFromSlot(i).getAttribute().isPassive() && abilityProps.getHotbarAbilityFromSlot(i).isPassiveActive() && DevilFruitsHelper.isSniperAbility(abilityProps.getHotbarAbilityFromSlot(i)))
				{
					abilityProps.getHotbarAbilityFromSlot(i).use(player);
					event.setCanceled(true);
				}
			}
		}
	}
/*
	@SubscribeEvent
	public void onPlayerAction(PlayerInteractEvent event)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(event.getPlayer());

		if(abilityProps.isPassiveActive(ModAttributes.AIR_DOOR))
			event.setCanceled(true);
	}

	@SubscribeEvent
	public void onAttack(AttackEntityEvent event)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getPlayer());
		IAbilityData abilityProps = AbilityDataCapability.get(event.getPlayer());

		if(abilityProps.isPassiveActive(ModAttributes.AIR_DOOR))
			event.setCanceled(true);
		
		if (devilFruitProps.getDevilFruit().equalsIgnoreCase("kachikachi"))
		{			
			if(abilityProps.isPassiveActive(ModAttributes.HOT_BOILING_SPECIAL))
				event.getTarget().setFire(4);
		}

	}

	@SubscribeEvent
	public void onDamage(LivingAttackEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			IAbilityData abilityProps = AbilityDataCapability.get(event.getEntityLiving());

			if(abilityProps.isPassiveActive(ModAttributes.AIR_DOOR))
				event.setCanceled(true);
		}
	}
*/
}
