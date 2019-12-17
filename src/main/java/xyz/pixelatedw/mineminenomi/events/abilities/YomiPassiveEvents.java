package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectHieSlowness;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoYomi;
import xyz.pixelatedw.mineminenomi.events.custom.YomiTriggerEvent;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID)
public class YomiPassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("yomiyomi"))
			return;

		if (devilFruitProps.getZoanPoint().equalsIgnoreCase(ZoanInfoYomi.FORM))
		{
			player.getFoodStats().addStats(9999, 9999);

			player.addPotionEffect(new EffectInstance(Effects.SPEED, 100, 0, true, true));

			if (WyHelper.getEntitiesNear(player, 100, PlayerEntity.class).size() > 0 && player.ticksExisted % 500 == 0)
				ModNetwork.sendToAllAround(new SDevilFruitSyncPacket(player.getEntityId(), devilFruitProps), player);
			
			if (player.world.getBlockState(player.getPosition().down()).getFluidState().isSource() && player.isSprinting())
			{
				player.onGround = true;
				if (player.getMotion().y < 0.0D)
				{
					player.setMotion(player.getMotion().x, 0, player.getMotion().z);
					player.fallDistance = 0.0F;
				}

				BlockState blockState = player.world.getBlockState(player.getPosition().down());
				for (int i = 0; i < 10; i++)
				{
					double newPosX = player.posX + WyMathHelper.randomDouble();
					double newPosY = player.posY;
					double newPosZ = player.posZ + WyMathHelper.randomDouble();

					//ModMain.proxy.spawnVanillaParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), newPosX, newPosY, newPosZ, 0, 0, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof LivingEntity) || !(event.getEntityLiving() instanceof PlayerEntity))
			return;

		LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);
		IEntityStats statProps = EntityStatsCapability.get(attacked);
		IAbilityData abilityProps = AbilityDataCapability.get(attacked);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("yomiyomi"))
			return;

		if (devilFruitProps.getZoanPoint().equalsIgnoreCase(ZoanInfoYomi.FORM) && abilityProps.isPassiveActive(ModAttributes.SOUL_PARADE))
		{
			attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1));
			attacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 1));
			new DFEffectHieSlowness(attacker, 100);
			AbilityExplosion explosion = WyHelper.newExplosion(attacked, attacker.posX, attacker.posY, attacker.posZ, 2);
			explosion.setDamageOwner(false);
			explosion.setDestroyBlocks(false);
			//explosion.setSmokeParticles(ID.PARTICLEFX_SOULPARADE);
			explosion.doExplosion();
		}
	}

	@SubscribeEvent
	public static void onDrinkMilk(LivingEntityUseItemEvent.Finish event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getEntityLiving());

		if (!devilFruitProps.getDevilFruit().equals("yomiyomi"))
			return;

		if (event.getItem().getItem() == Items.MILK_BUCKET && devilFruitProps.getZoanPoint().equalsIgnoreCase(ZoanInfoYomi.FORM))
			event.getEntityLiving().heal(4);
	}

	@SubscribeEvent
	public static void onYomiDeath(YomiTriggerEvent event)
	{
		if (event.oldPlayerData.getDevilFruit().equalsIgnoreCase("yomiyomi") && !event.oldPlayerData.getZoanPoint().equalsIgnoreCase(ZoanInfoYomi.FORM))
		{
			event.newPlayerData.setDevilFruit("yomiyomi");
			event.newPlayerData.setZoanPoint(ZoanInfoYomi.FORM);

			PlayerEntity player = (PlayerEntity) event.entity;

			ModNetwork.sendToAll(new SDevilFruitSyncPacket(player.getEntityId(), event.newPlayerData));
		}
	}
}
