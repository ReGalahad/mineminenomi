package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.UUID;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class FishmanPassiveEvents
{

	private static final AttributeModifier SWIN_SPEED = new AttributeModifier(UUID.fromString("a11440ee-5d84-4c36-960b-992e13b66aff"), "Fishman Speed Multiplier", 2.5, AttributeModifier.Operation.MULTIPLY_BASE).setSaved(false);


	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!statsProps.isFishman())
			return;

		if (AbilityHelper.isAffectedByWater(player) && (!devilFruitProps.hasDevilFruit() || event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL)))
		{
			player.setAir(300);
			if(player.getRidingEntity() == null)
				player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 50, 0, false, false));
			if(!player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier(SWIN_SPEED))
				player.getAttribute(LivingEntity.SWIM_SPEED).applyModifier(SWIN_SPEED);

		} else {
			if(player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier(SWIN_SPEED))
				player.getAttribute(LivingEntity.SWIM_SPEED).removeModifier(SWIN_SPEED);
		}

		Ability samehadaAbility = abilityProps.getEquippedAbility(SamehadaShoteiAbility.INSTANCE);
		boolean isSamehadaActive = samehadaAbility != null && samehadaAbility.isContinuous();
		if(isSamehadaActive)
			player.setMotion(0, -5, 0);
	}

	@SubscribeEvent
	public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		PlayerEntity player = event.getPlayer();
		IEntityStats statsProps = EntityStatsCapability.get(player);
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!statsProps.isFishman())
			return;

		float speed = event.getOriginalSpeed();
		if (AbilityHelper.isAffectedByWater(player) && (!devilFruitProps.hasDevilFruit() || event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))) {
				if(!player.onGround)
					speed *= 5;
			speed *= 5;
			event.setNewSpeed(speed);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityInWater(EntityViewRenderEvent.FogDensity event)
	{
		PlayerEntity player = Minecraft.getInstance().player;
		IEntityStats statsProps = EntityStatsCapability.get(player);

		if (!statsProps.isFishman())
			return;

		if(player.areEyesInFluid(FluidTags.WATER))
		{
			event.setCanceled(true);
			GlStateManager.fogMode(GlStateManager.FogMode.LINEAR);
			event.setDensity(0.005f);
		}
	}
}
