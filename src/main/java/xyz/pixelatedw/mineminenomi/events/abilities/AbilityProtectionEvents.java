package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.events.AbilityProjectileEvents;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class AbilityProtectionEvents
{
	@SubscribeEvent
	public static void onAbilityHit(AbilityProjectileEvents.Hit event)
	{
		if (event.getHit().getType() == RayTraceResult.Type.ENTITY)
		{
			EntityRayTraceResult entityHit = (EntityRayTraceResult) event.getHit();

			if (entityHit.getEntity() instanceof LivingEntity)
			{
				LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
				
				if(AbilityHelper.checkForRestriction(hitEntity.world, (int) hitEntity.posX, (int) hitEntity.posY, (int) hitEntity.posZ))
				{
					event.setCanceled(true);
				}
			}
		}
	}
}
