package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class UshiBisonPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit props = DevilFruitCapability.get(player);

		if (!props.getDevilFruit().equalsIgnoreCase("ushi_ushi_bison"))
			return;

		if (props.getZoanPoint().equalsIgnoreCase(BisonWalkZoanInfo.FORM))
		{
			List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.5);
			targets.remove(player);
			
			for(LivingEntity target : targets)
			{
				Vec3d speed = WyHelper.propulsion(player, 2, 2);
	
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
				target.setMotion(speed.x, 0.2, speed.z);
			}
		}
	}
}
