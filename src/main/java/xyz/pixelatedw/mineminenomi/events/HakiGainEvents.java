package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.config.CommonConfig.HaoshokuUnlockLogic;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HakiGainEvents
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IAbilityData props = AbilityDataCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();
			
			if(CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.EXPERIENCE)
			{
				
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLoggedIn(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity && CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.RANDOM)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IAbilityData props = AbilityDataCapability.get(player);
			int isKing = (int) (player.getUniqueID().getMostSignificantBits() % 100);

			// That moment when your entire chance of getting haoshoku haki is based on the time when you bought minecraft. Design 101
			if (isKing == 0)
				this.giveHakiAbility(props, HaoshokuHakiAbility.INSTANCE, player);
		}
	}
	
	private void giveHakiAbility(IAbilityData props, Ability ability, PlayerEntity player)
	{
		if(!props.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability))
		{
			props.addUnlockedAbility(ability);
			WyHelper.sendMsgToPlayer(player, "Obtained " + ability.getName());
		}
	}
}
