package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.config.CommonConfig.HaoshokuUnlockLogic;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModValues;
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
			IHakiData hakiProps = HakiDataCapability.get(player);
			
			KenbunshokuHakiAuraAbility ability = props.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
			if(ability != null && ability.isContinuous() && hakiProps.getKenbunshokuHakiExp() >= 50 && player.ticksExisted % 600 == 0 && player.getHealth() < WyHelper.percentage(20, player.getMaxHealth()))
			{
				hakiProps.alterKenbunshokuHakiExp(0.1F);
			}

			if(CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.EXPERIENCE && player.ticksExisted % 1200 == 0 && !props.hasUnlockedAbility(HaoshokuHakiAbility.INSTANCE))
			{
				float totalPossible = ModValues.KENBUNSHOKU_MAX_EXP + ModValues.BUSOSHOKU_HARDENING_MAX_EXP + ModValues.BUSOSHOKU_IMBUING_MAX_EXP;
				
				float totalExp = HakiHelper.getTotalHakiExp(player);
				float totalCheck = (float) (totalExp + WyHelper.randomWithRange(-20, 70));
				totalCheck = MathHelper.clamp(totalCheck, 0, totalPossible);
				
				if(totalExp >= totalCheck)
					this.giveHakiAbility(player, HaoshokuHakiAbility.INSTANCE);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLoggedIn(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity && CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.RANDOM)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			int isKing = (int) (player.getUniqueID().getMostSignificantBits() % 100);

			// That moment when your entire chance of getting haoshoku haki is based on the time when you bought minecraft. Design 101
			if (isKing == 0)
				this.giveHakiAbility(player, HaoshokuHakiAbility.INSTANCE);
		}
	}
	
	private void giveHakiAbility(PlayerEntity player, Ability ability)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		if(!props.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability))
		{
			props.addUnlockedAbility(ability);
			WyHelper.sendMsgToPlayer(player, "Obtained " + ability.getName());
		}
	}
}
