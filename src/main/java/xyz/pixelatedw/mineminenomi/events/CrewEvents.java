package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.events.AbilityProjectileEvents;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class CrewEvents
{
	// Lmao you got bamboozeled there is no crew only us, the Dugongs
	// (ง •̀_•̀)ง
	// We got crews now but this is still a nice easter egg for y'all code dwellers, Dugongs still the best

	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (CommonConfig.instance.isFriendlyDamageDisabled() && event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
			LivingEntity target = event.getEntityLiving();

			boolean sameGroup = FactionHelper.getSameGroupPredicate(attacker).test(target);
			if (sameGroup)
			{
				event.setCanceled(true);
				return;
			}
		}
	}

	@SubscribeEvent
	public static void onProjectileHit(AbilityProjectileEvents.Hit event)
	{
		if (CommonConfig.instance.isFriendlyDamageDisabled() && event.getProjectile().getThrower() instanceof PlayerEntity && event.getHit().getType() == RayTraceResult.Type.ENTITY)
		{
			PlayerEntity attacker = (PlayerEntity) event.getProjectile().getThrower();
			EntityRayTraceResult hitResult = (EntityRayTraceResult) event.getHit();

			if (hitResult.getEntity() instanceof LivingEntity)
			{
				LivingEntity target = (LivingEntity) hitResult.getEntity();

				boolean sameGroup = FactionHelper.getSameGroupPredicate(attacker).test(target);
				if (sameGroup)
				{
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void onSetFire(SetOnFireEvent event)
	{
		if (CommonConfig.instance.isFriendlyDamageDisabled() && event.getAttacker() instanceof PlayerEntity)
		{
			PlayerEntity attacker = (PlayerEntity) event.getAttacker();
			LivingEntity target = event.getEntityLiving();

			boolean sameGroup = FactionHelper.getSameGroupPredicate(attacker).test(target);
			if (sameGroup)
			{
				event.setCanceled(true);
				return;
			}
		}
	}
	
	@SubscribeEvent
	public static void onTargetSet(LivingSetAttackTargetEvent event)
	{
		if(CommonConfig.instance.isFriendlyDamageDisabled() && event.getTarget() instanceof PlayerEntity)
		{		
			boolean sameGroup = FactionHelper.getSameGroupPredicate((PlayerEntity) event.getTarget()).test(event.getEntityLiving());
			if(sameGroup)
			{
				if(event.getEntityLiving() instanceof MobEntity)
					((MobEntity)event.getEntityLiving()).setAttackTarget(null);
				event.getEntityLiving().setRevengeTarget(null);
				event.getEntityLiving().setLastAttackedEntity(null);
			}
		}	
	}
}
