package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.config.CommonConfig.HaoshokuUnlockLogic;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HakiGainEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity && !event.getEntityLiving().world.isRemote)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IAbilityData props = AbilityDataCapability.get(player);
			IHakiData hakiProps = HakiDataCapability.get(player);

			KenbunshokuHakiAuraAbility ability = props.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
			if (ability != null && ability.isContinuous() && hakiProps.getKenbunshokuHakiExp() >= 50 && player.ticksExisted % 600 == 0 && player.getHealth() < WyHelper.percentage(20, player.getMaxHealth()))
				hakiProps.alterKenbunshokuHakiExp(0.1F);

			if (CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.EXPERIENCE && player.ticksExisted % 1200 == 0 && !props.hasUnlockedAbility(HaoshokuHakiAbility.INSTANCE))
			{
				float totalPossible = ModValues.KENBUNSHOKU_MAX_EXP + ModValues.BUSOSHOKU_HARDENING_MAX_EXP + ModValues.BUSOSHOKU_IMBUING_MAX_EXP;

				float totalExp = HakiHelper.getTotalHakiExp(player);
				float totalCheck = (float) (150 + WyHelper.randomWithRange(-50, 120));
				totalCheck = MathHelper.clamp(totalCheck, 0, totalPossible);

				if (totalExp >= totalCheck)
					giveHakiAbility(player, HaoshokuHakiAbility.INSTANCE);
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity) || !(event.getSource().getTrueSource() instanceof LivingEntity) || event.getEntityLiving().world.isRemote)
			return;
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IHakiData hakiProps = HakiDataCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		float hakiMultiplier = (float) CommonConfig.instance.getHakiExpMultiplier();

		KenbunshokuHakiAuraAbility ability = abilityProps.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
		if((ability != null && ability.isContinuous()) || hakiProps.getKenbunshokuHakiExp() < 30)
		{
			float exp = event.getAmount() / 10;
			if(exp <= 0)
				exp = 0.1F;
			
			hakiProps.alterKenbunshokuHakiExp(exp * hakiMultiplier);
		}
		
		if(statsProps.getDoriki() > 1500 && hakiProps.getKenbunshokuHakiExp() > 30 + WyHelper.randomWithRange(-5, 20))
			giveHakiAbility(player, KenbunshokuHakiAuraAbility.INSTANCE);

		if(statsProps.getDoriki() > 5500 && hakiProps.getKenbunshokuHakiExp() > 60 + WyHelper.randomWithRange(0, 30))
			giveHakiAbility(player, KenbunshokuHakiFutureSightAbility.INSTANCE);

		// "Random" burts of Haoshoku Haki if the player has it unlocked (or if exp mode is enabled) and if the player is in danger.
		/*
		if(!player.world.isRemote && player.getHealth() < WyHelper.percentage(20, player.getMaxHealth()))
		{
			boolean releaseHaki = false;
			HaoshokuHakiAbility haoAbility = abilityProps.getUnlockedAbility(HaoshokuHakiAbility.INSTANCE);
			float totalExp = HakiHelper.getTotalHakiExp(player);
			boolean hasEnemiesNear = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20, CreatureEntity.class).size() > 0;
			
			if(hasEnemiesNear && totalExp > 60 + WyHelper.randomWithRange(-5, 10))
			{
				if(CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.EXPERIENCE)
					releaseHaki = true;
				else if(CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.RANDOM && haoAbility != null)
				{
					haoAbility = abilityProps.getEquippedAbility(HaoshokuHakiAbility.INSTANCE);
					if(haoAbility != null && haoAbility.isOnStandby())
					{
						haoAbility.use(player);
					}
				}
			}

			if(releaseHaki)
			{
				HaoshokuHakiAbility.PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
				
				List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40);
				targets.remove(player);
						
				for(LivingEntity target : targets)
				{
					EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, 200, 1);
					target.addPotionEffect(instance);
					((ServerPlayerEntity) player).connection.sendPacket(new SPlayEntityEffectPacket(target.getEntityId(), instance));
				}
			}
		}
		*/
	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity) || event.getSource().getTrueSource().world.isRemote)
			return;
		
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IHakiData hakiProps = HakiDataCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		ItemStack heldStack = player.getHeldItem(player.getActiveHand() != null ? player.getActiveHand() : Hand.MAIN_HAND);
		
		float hakiMultiplier = (float) CommonConfig.instance.getHakiExpMultiplier();
		
		if(!heldStack.isEmpty())
		{
			BusoshokuHakiImbuingAbility ability = abilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
			if((ability != null && ability.isContinuous()) || hakiProps.getBusoshokuImbuingHakiExp() < 60)
			{
				IAttributeInstance attrAtk = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
				IAttributeInstance attrHP = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

				double atk = attrAtk != null ? attrAtk.getBaseValue() : 0;
				double hp = attrHP.getBaseValue();
				
				float exp = (float) ((atk + hp) / 300);
				if(exp <= 0)
					exp = 0.1F;

				if(ItemsHelper.isSword(heldStack))
					hakiProps.alterBusoshokuImbuingHakiExp(exp * hakiMultiplier);
				else
					hakiProps.alterBusoshokuImbuingHakiExp(exp / 4);
			}
		}
		else
		{
			BusoshokuHakiHardeningAbility ability = abilityProps.getEquippedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
			if((ability != null && ability.isContinuous()) || hakiProps.getBusoshokuImbuingHakiExp() < 60)
			{
				IAttributeInstance attrAtk = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
				IAttributeInstance attrHP = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

				double atk = attrAtk != null ? attrAtk.getBaseValue() : 0;
				double hp = attrHP.getBaseValue();
				
				float exp = (float) ((atk + hp) / 200);
				if(exp <= 0)
					exp = 0.1F;
				
				hakiProps.alterBusoshokuHardeningHakiExp(exp * hakiMultiplier);
			}
		}
		
		if(statsProps.getDoriki() > 4000 && hakiProps.getBusoshokuImbuingHakiExp() > 40 + WyHelper.randomWithRange(-5, 20))
			giveHakiAbility(player, BusoshokuHakiImbuingAbility.INSTANCE);

		if(statsProps.getDoriki() > 3000 && hakiProps.getBusoshokuHardeningHakiExp() > 50 + WyHelper.randomWithRange(-2, 25))
		{
			giveHakiAbility(player, BusoshokuHakiHardeningAbility.INSTANCE);
			if(hakiProps.getBusoshokuHardeningHakiExp() > 80 + WyHelper.randomWithRange(0, 20))
			{
				giveHakiAbility(player, BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity && CommonConfig.instance.getHaoshokuUnlockLogic() == HaoshokuUnlockLogic.RANDOM)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			String[] bits = ("" + player.getUniqueID().getMostSignificantBits()).split("");
			int sum = 0;
			for(String bit : bits)
			{
				if(bit.equalsIgnoreCase("-"))
					continue;
				sum += Integer.parseInt(bit);
			}
			sum = MathHelper.clamp(sum & 10, 0, 10);
			boolean isKing = sum <= 1;

			// That moment when your entire chance of getting haoshoku haki is based on the time when you bought minecraft. Design 101
			if (isKing)
				giveHakiAbility(player, HaoshokuHakiAbility.INSTANCE);
		}
	}

	private static void giveHakiAbility(PlayerEntity player, Ability ability)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		if (!props.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability))
		{
			props.addUnlockedAbility(ability);
			WyHelper.sendMsgToPlayer(player, "Obtained " + ability.getName());
			if(!player.world.isRemote)
				WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
		}
	}
}
