package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.kage.DoppelmanAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
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
	public static void onEntityHurt(LivingAttackEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		LivingEntity attacked = event.getEntityLiving();

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("kage_kage"))
			return;

		DoppelmanAbility ability = abilityProps.getEquippedAbility(DoppelmanAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
			return;
		
		DoppelmanEntity doppelman = WyHelper.getEntitiesNear(attacker.getPosition(), attacker.world, 20, DoppelmanEntity.class).stream().findFirst().orElse(null);

		if (doppelman != null && doppelman.getOwner() == attacker)
			doppelman.forcedTargets.add(attacked);
	}
}
