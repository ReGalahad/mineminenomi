package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.GenericMarineEntity;
import xyz.pixelatedw.mineminenomi.events.custom.BountyEvent;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.network.WyNetwork;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class StatsGainEvents
{
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			IEntityStats props = EntityStatsCapability.get(player);
			LivingEntity target = event.getEntityLiving();

			IAttributeInstance attrAtk = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
			IAttributeInstance attrHP = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

			long plusBelly = 0;
			long plusBounty = 0;
			double plusDoriki = 0;

			if (target instanceof PlayerEntity)
			{
				IEntityStats targetprops = EntityStatsCapability.get(player);

				plusDoriki = targetprops.getDoriki() / 4;
				plusBounty = targetprops.getBounty() / 2;
				plusBelly = targetprops.getBelly();
			}
			else
			{
				if (props.isMarine() && target instanceof GenericMarineEntity)
					return;

				if (target instanceof GenericNewEntity)
				{
					GenericNewEntity entity = (GenericNewEntity) target;

					if ((props.getDoriki() / 100) > entity.getDoriki() && CommonConfig.instance.isMinimumDorikiPerKillEnabled())
						plusDoriki = 1;
					else
						plusDoriki = entity.getDoriki();

					plusDoriki *= CommonConfig.instance.getDorikiRewardMultiplier();

					plusBounty = entity.getDoriki() * 2;
					plusBelly = entity.getBelly();
				}
				else
				{
					if (attrAtk != null && attrHP != null)
					{
						double i = attrAtk.getBaseValue();
						double j = attrHP.getBaseValue();

						plusDoriki = (int) Math.round(((i + j) / 10) / 2);
						plusBounty = (int) Math.round((i + j) / 10);
						plusBelly = 1;

						plusDoriki *= CommonConfig.instance.getDorikiRewardMultiplier();

					}
					else
					{
						plusDoriki = 0;
						plusBounty = 1;
						plusBelly = 0;
					}
				}

				if (plusDoriki > 0)
				{
					if (props.getDoriki() + plusDoriki <= ModValues.MAX_DORIKI)
					{
						props.alterDoriki((int) Math.round(plusDoriki));
						DorikiEvent e = new DorikiEvent(player);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}

				if (props.isPirate() || props.isBandit() || props.isRevolutionary())
				{
					if (plusBounty > 0 && props.getBounty() + plusBounty < ModValues.MAX_GENERAL)
					{
						props.alterBounty(plusBounty);
						BountyEvent e = new BountyEvent(player, plusBounty);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}

				if (props.getBelly() + plusBelly < ModValues.MAX_GENERAL)
					props.alterBelly(plusBelly);

			}

			WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (ServerPlayerEntity) player);
		}
	}

}
