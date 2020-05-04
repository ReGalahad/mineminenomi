package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.events.custom.YomiTriggerEvent;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class YomiPassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		
		if(player.world.isRemote)
			return;
		
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("yomi_yomi"))
			return;

		if (devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM))
		{
			player.getFoodStats().addStats(9999, 9999);

			player.addPotionEffect(new EffectInstance(Effects.SPEED, 100, 0, false, false));

			if (WyHelper.getEntitiesNear(player.getPosition(), player.world, 100, PlayerEntity.class).size() > 0 && player.ticksExisted % 500 == 0)
				WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);

			if (player.world.getBlockState(player.getPosition().down()).getFluidState().isSource() && player.isSprinting())
			{
				player.onGround = true;
				if (player.getMotion().y <= 0.0D && player.getMotion().y < 0.009)
				{
					Vec3d speed = WyHelper.propulsion(player, 0.4, 0.4);
					double ySpeed = 0 - player.getMotion().y;
					if(ySpeed > 0.008)
						ySpeed = 0.008;
					player.setMotion(speed.x, ySpeed, speed.z);
					((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
					player.fallDistance = 0.0F;
				}

				BlockState blockState = player.world.getBlockState(player.getPosition().down());
				for (int i = 0; i < 10; i++)
				{
					double newPosX = player.posX + WyHelper.randomDouble();
					double newPosY = player.posY;
					double newPosZ = player.posZ + WyHelper.randomDouble();

					((ServerWorld) player.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), 
							newPosX,
							newPosY,
							newPosZ,
							1, 0, 0, 0, 0);
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
		IAbilityData abilityProps = AbilityDataCapability.get(attacked);
		
		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("yomi_yomi"))
			return;

		Ability ability = abilityProps.getEquippedAbility(SoulParadeAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();

		if (devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM) && isActive)
		{
			attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1));
			attacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 1));
			//new DFEffectHieSlowness(attacker, 100);
			ExplosionAbility explosion = AbilityHelper.newExplosion(attacked, attacker.posX, attacker.posY, attacker.posZ, 2);
			explosion.setDamageOwner(false);
			explosion.setDestroyBlocks(false);
			explosion.setSmokeParticles(SoulParadeAbility.PARTICLES);
			explosion.doExplosion();
		}
	}

	@SubscribeEvent
	public static void onDrinkMilk(LivingEntityUseItemEvent.Finish event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getEntityLiving());

		if (!devilFruitProps.getDevilFruit().equals("yomi_yomi"))
			return;

		if (event.getItem().getItem() == Items.MILK_BUCKET && devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM))
			event.getEntityLiving().heal(4);
	}

	@SubscribeEvent
	public static void onYomiDeath(YomiTriggerEvent event)
	{
		if (event.oldPlayerData.getDevilFruit().equalsIgnoreCase("yomi_yomi") && !event.oldPlayerData.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM))
		{
			event.newPlayerData.setDevilFruit("yomi_yomi");
			event.newPlayerData.setZoanPoint(YomiZoanInfo.FORM);

			PlayerEntity player = (PlayerEntity) event.entity;

			WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), event.newPlayerData));
		}
	}
}
