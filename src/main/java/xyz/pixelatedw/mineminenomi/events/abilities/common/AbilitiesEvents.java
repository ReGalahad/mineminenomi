package xyz.pixelatedw.mineminenomi.events.abilities.common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class AbilitiesEvents
{
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IAbilityData props = AbilityDataCapability.get(player);

			for (Ability ability : props.getHotbarAbilities())
			{
				if (ability == null)
					continue;

				if (ability instanceof ChargeableAbility && ability.isCharging())
					((ChargeableAbility) props.getAbility(ability)).charging(player);

				if (ability instanceof ContinuousAbility && ability.isContinuous())
					((ContinuousAbility) props.getAbility(ability)).tick(player);

				if (ability instanceof PassiveAbility)
					((PassiveAbility) props.getAbility(ability)).tick(player);

				if (ability.isOnCooldown())
					props.getAbility(ability).cooldown(player);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			IAbilityData props = AbilityDataCapability.get(player);
			LivingEntity target = event.getEntityLiving();
			ItemStack heldItem = player.getHeldItemMainhand();

			for (Ability ability : props.getHotbarAbilities())
			{
				if (ability == null)
					continue;

				if (ability instanceof PunchAbility && ability.isContinuous() && heldItem.isEmpty())
				{
					float damage = ((PunchAbility) props.getAbility(ability)).hitEntity(player, target);
					event.setAmount(damage);
				}
			}
		}
	}
}
