package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityDoppelman;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class KagePassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof LivingEntity))
			return;

		LivingEntity entity = event.getEntityLiving();
		IEntityStats statsProps = EntityStatsCapability.get(entity);

		if (!statsProps.hasShadow() && entity.getBrightness() > 0.8F)
			entity.setFire(3);
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

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("kagekage"))
			return;

		EntityDoppelman doppelman = (EntityDoppelman) WyHelper.getEntitiesNear(attacker, 20, EntityDoppelman.class).stream().findFirst().orElse(null);

		if (doppelman != null)
			doppelman.forcedTargets.add(attacked);
	}
}
