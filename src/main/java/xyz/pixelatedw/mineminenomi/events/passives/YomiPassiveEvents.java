package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
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
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (event.getType() == ElementType.FOOD && props.getDevilFruit().equalsIgnoreCase("yomi_yomi") && props.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM))
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event)
	{
		if (event.isWasDeath())
		{
			IDevilFruit oldPlayerProps = DevilFruitCapability.get(event.getOriginal());
			IDevilFruit newPlayerProps = DevilFruitCapability.get(event.getPlayer());

			YomiTriggerEvent yomiEvent = new YomiTriggerEvent(event.getPlayer(), oldPlayerProps, newPlayerProps);
			if (MinecraftForge.EVENT_BUS.post(yomiEvent))
				return;
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();

		if (player.world.isRemote)
			return;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("yomi_yomi") || !devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM))
			return;

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
				if (ySpeed > 0.008)
					ySpeed = 0.008;
				player.setMotion(speed.x, ySpeed, speed.z);
				((ServerPlayerEntity) player).connection.sendPacket(new SEntityVelocityPacket(player));
				player.fallDistance = 0.0F;
			}

			BlockState blockState = player.world.getBlockState(player.getPosition().down());
			for (int i = 0; i < 10; i++)
			{
				double newPosX = player.posX + WyHelper.randomDouble();
				double newPosY = player.posY;
				double newPosZ = player.posZ + WyHelper.randomDouble();

				((ServerWorld) player.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), newPosX, newPosY, newPosZ, 1, 0, 0, 0, 0);
			}
		}
	}

	@SubscribeEvent
	public static void onSpiritUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("yomi_yomi") || player.isCreative() || player.isSpectator())
			return;

		if (!isSpirit(player))
		{
			player.noClip = false;
			player.abilities.isFlying = false;
			if (player instanceof ServerPlayerEntity)
				((ServerPlayerEntity) player).sendPlayerAbilities();
			return;
		}

		player.onGround = false;
		player.noClip = true;
		player.abilities.isFlying = true;
		if (player instanceof ServerPlayerEntity)
			((ServerPlayerEntity) player).sendPlayerAbilities();
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();

		if (!isSpirit(player))
			return;

		GlStateManager.color4f(0.3F, 0.9F, 0.5F, 0.6F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.DST_COLOR, DestFactor.ONE_MINUS_CONSTANT_COLOR);
	}

	@SubscribeEvent
	public static void onEntityHits(AttackEntityEvent event)
	{
		PlayerEntity player = event.getPlayer();

		if (!isSpirit(player))
			return;

		event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityLeftClickBlocks(LeftClickBlock event)
	{
		PlayerEntity player = event.getPlayer();

		if (!isSpirit(player))
			return;

		event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityRightClickBlocks(RightClickBlock event)
	{
		PlayerEntity player = event.getPlayer();

		if (!isSpirit(player))
			return;

		event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityBreaksBlocks(BreakEvent event)
	{
		PlayerEntity player = event.getPlayer();

		if (!isSpirit(player))
			return;

		event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityPlaceBlocks(EntityPlaceEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();

		if (!isSpirit(player))
			return;

		event.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();

		if (!isSpirit(player))
			return;

		event.setCanceled(true);
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
			// new DFEffectHieSlowness(attacker, 100);
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

	private static boolean isSpirit(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("yomi_yomi") || player.isCreative() || player.isSpectator())
			return false;

		Ability ability = abilityProps.getEquippedAbility(YomiNoReikiAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();

		if (!isActive)
			return false;

		return true;
	}
}
