package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HealthUpdateEvent
{
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();

			if (!player.world.isRemote && player.ticksExisted < 5)
				((ServerPlayerEntity) player).connection.sendPacket(new SUpdateHealthPacket(player.getHealth(), player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()));
		}
	}

	@SubscribeEvent
	public static void onPlayerChangeDimensions(PlayerChangedDimensionEvent event)
	{
		/*
		 * PlayerEntity player = event.getPlayer();
		 * IEntityStats props = EntityStatsCapability.get(player);
		 * IAttributeInstance maxHpAttribute = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
		 * if (props.getDoriki() / 100 <= 20)
		 * maxHpAttribute.setBaseValue(20);
		 * else
		 * maxHpAttribute.setBaseValue(props.getDoriki() / 100);
		 * ((ServerPlayerEntity)player).connection.sendPacket(new SUpdateHealthPacket(player.getHealth(), player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()));
		 * WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), props), (ServerPlayerEntity) player);
		 */
	}
}
