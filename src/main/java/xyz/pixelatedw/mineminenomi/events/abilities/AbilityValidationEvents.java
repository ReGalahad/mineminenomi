package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.yomi.KasuriutaFubukiGiriAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class AbilityValidationEvents
{
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity && CommonConfig.instance.isAbilityFraudChecksEnabled())
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			if (!player.world.isRemote)
			{
				if(!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
				{
					ItemStack df = AbilityHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
					
					abilityProps.clearUnlockedAbilities(AbilityCategory.DEVIL_FRUIT);

					if(df != null && !df.isEmpty())
					{
						if(devilFruitProps.hasYamiPower())
						{
							ItemStack yami = AbilityHelper.getDevilFruitItem("yami_yami");
							for(Ability a : ((AkumaNoMiItem)yami.getItem()).abilities)
								if(!AbilityHelper.verifyIfAbilityIsBanned(a))
									abilityProps.addUnlockedAbility(a);
						}
						
						for(Ability a : ((AkumaNoMiItem)df.getItem()).abilities)
							if(!AbilityHelper.verifyIfAbilityIsBanned(a))
								abilityProps.addUnlockedAbility(a);
					}
				}
				else
				{
					abilityProps.clearUnlockedAbilities(AbilityCategory.DEVIL_FRUIT);
				}
				
				AbilityHelper.validateRacialMoves(player);
				AbilityHelper.validateStyleMoves(player);
				
				for(Ability abl : abilityProps.getUnlockedAbilities(AbilityCategory.DEVIL_FRUIT))
				{
					if(abl instanceof KasuriutaFubukiGiriAbility || abl instanceof SoulParadeAbility || abl instanceof YomiNoReikiAbility)
					{
						if(!devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM))
							abilityProps.removeUnlockedAbility(abl);
					}
				}
				
				for(int i = 0; i < abilityProps.getEquippedAbilities().length; i++)
				{
					if(abilityProps.getEquippedAbility(i) != null)
					{
						if(AbilityHelper.verifyIfAbilityIsBanned(abilityProps.getEquippedAbility(i)))
							abilityProps.setEquippedAbility(i, null);
					}
				}
								
				WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityStatsProps), player);
				WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
			}
		}
	}
}
