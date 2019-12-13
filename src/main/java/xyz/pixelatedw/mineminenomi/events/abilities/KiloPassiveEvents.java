package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID)
public class KiloPassiveEvents
{

	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		/*if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		IEntityStats statProps = EntityStatsCapability.get(attacker);
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		LivingEntity attacked = event.getEntityLiving();
		IEntityStats statPropz = EntityStatsCapability.get(attacked);
		
		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("kilokilo"))
			return;
		
		if (attacker.isPotionActive(Effects.STRENGTH))
		{
			ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_KILO, attacked.posX, attacked.posY, attacked.posZ), attacker);
			attacker.removePotionEffect(Effects.STRENGTH);
		}*/
	}
}
