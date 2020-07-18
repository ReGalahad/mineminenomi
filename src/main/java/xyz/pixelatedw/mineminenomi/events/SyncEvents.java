package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SyncEvents
{
	@SubscribeEvent
	public static void onDorikiGained(DorikiEvent event)
	{
		if (event.player != null && CommonConfig.instance.isExtraHeartsEnabled())
		{
			IAttributeInstance maxHpAttribute = event.player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

			if (event.props.getDoriki() / 100 <= 20)
				maxHpAttribute.setBaseValue(20);
			else
				maxHpAttribute.setBaseValue(event.props.getDoriki() / 100);
			
			((ServerPlayerEntity) event.player).connection.sendPacket(new SUpdateHealthPacket(event.player.getHealth(), event.player.getFoodStats().getFoodLevel(), event.player.getFoodStats().getSaturationLevel()));
			System.out.println("@@@@@@2");
		}
	}

	
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
		PlayerEntity player = event.getPlayer();
		
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
		WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);
		
		// Updating the eye height
		MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight(player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
		WyNetwork.sendTo(new SRecalculateEyeHeightPacket(), player);
	}
	
	@SubscribeEvent
	public static void onPlayerStartsTracking(PlayerEvent.StartTracking event)
	{
		if(event.getTarget() instanceof PlayerEntity)
		{
			PlayerEntity targetPlayer = (PlayerEntity) event.getTarget();			

			IDevilFruit devilFruitProps = DevilFruitCapability.get(targetPlayer);
			IAbilityData abilityDataProps = AbilityDataCapability.get(targetPlayer);
			IHakiData hakiDataProps = HakiDataCapability.get(targetPlayer);

			WyNetwork.sendToAllTracking(new SSyncDevilFruitPacket(targetPlayer.getEntityId(), devilFruitProps), targetPlayer);
			WyNetwork.sendToAllTracking(new SSyncAbilityDataPacket(targetPlayer.getEntityId(), abilityDataProps), targetPlayer);
			WyNetwork.sendToAllTracking(new SSyncHakiDataPacket(targetPlayer.getEntityId(), hakiDataProps), targetPlayer);
		}
	}
}
