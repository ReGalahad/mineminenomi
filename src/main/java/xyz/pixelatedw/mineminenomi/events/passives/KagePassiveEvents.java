package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.kage.DoppelmanAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
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
	
	@SubscribeEvent
	public static void onEntityDamaged(LivingDamageEvent event)
	{
		if(!(event.getEntityLiving() instanceof DoppelmanEntity))
			return;
		
		DoppelmanEntity entity = (DoppelmanEntity) event.getEntityLiving();
		PlayerEntity owner = entity.getOwner();
		
		if(owner == null)
			return;
		
		IDevilFruit props = DevilFruitCapability.get(owner);
		
		if(!props.getDevilFruit().equalsIgnoreCase("kage_kage") || entity.getHealth() - event.getAmount() >= 0)
			return;
		
		IAbilityData abilityProps = AbilityDataCapability.get(owner);
		
		DoppelmanAbility ability = abilityProps.getEquippedAbility(DoppelmanAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive)
			return;
		
		ability.setMaxCooldown(60);
		ability.stopContinuity(owner);
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityPreRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();

		if (!entity.isPotionActive(ModEffects.BLACK_BOX))
			return;

		entity.renderYawOffset = 0;
		entity.prevRenderYawOffset = 0;
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityPostRendered(RenderLivingEvent.Post event)
	{
		LivingEntity entity = event.getEntity();

		if (!entity.isPotionActive(ModEffects.BLACK_BOX))
			return;

		entity.renderYawOffset = 0;
		entity.prevRenderYawOffset = 0;
	}
}
