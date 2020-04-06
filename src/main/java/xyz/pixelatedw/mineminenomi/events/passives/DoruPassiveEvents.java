package xyz.pixelatedw.mineminenomi.events.passives;

import java.awt.Color;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.models.effects.CandleLockModel;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class DoruPassiveEvents {
	private static final CandleLockModel CANDLE_LOCK = new CandleLockModel();
	private static final SphereModel DORU_BALL = new SphereModel();

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event) {
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		if (!entity.isPotionActive(ModEffects.CANDLE_LOCK))
			return;

		if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getDuration() <= 0)
			entity.removePotionEffect(ModEffects.CANDLE_LOCK);

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY() - 0.8F, (float) event.getZ());

			Minecraft.getInstance().textureManager.bindTexture(
					new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/zoanmorph/candlelock.png"));

			GlStateManager.rotatef(
					entity.prevRotationYaw
							+ (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick() - 180,
					0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(0.1, 0.1, 0.15);

			CANDLE_LOCK.render(entity, 0, 0, 0, 0, 0, 0.625F);
		}
		GlStateManager.popMatrix();
	}

	@SubscribeEvent
	public static void doruBall(RenderPlayerEvent.Pre e) {
		PlayerEntity p = e.getPlayer();
		IAbilityData data = AbilityDataCapability.get(p);
		for (Ability a : data.getEquippedAbilities()) {
			if (a != null) {
				if (a.equals(DoruDoruBallAbility.INSTANCE)) {

					if(a.isContinuous()) {
					e.setCanceled(true);
					GlStateManager.pushMatrix();
					GlStateManager.disableTexture();
					GlStateManager.translated(e.getX(), e.getY() + p.getEyeHeight(), e.getZ());
					DORU_BALL.setRotateAngle(DORU_BALL.shape1, DORU_BALL.shape1.rotateAngleX + (float)(p.getMotion().z),0f, DORU_BALL.shape1.rotateAngleZ +(float)(-(p.getMotion().x)) );
					DORU_BALL.render(p, 0, 0, 0, 0, 0, 0.5625f);
					GlStateManager.scaled(1, 1, 1);
					GlStateManager.color3f(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue());
					GlStateManager.enableTexture();
					GlStateManager.popMatrix();
					} else {
						   DORU_BALL.setRotateAngle(DORU_BALL.shape1, 0f, 0f, 0f);
					}
				} 
			}
		}
	}
	@SubscribeEvent
	public static void onDamaged(LivingHurtEvent e) {
	  if(e.getEntityLiving() instanceof PlayerEntity) {
		  PlayerEntity p = (PlayerEntity) e.getEntityLiving();
		  IAbilityData data = AbilityDataCapability.get(p);
		  for(Ability a : data.getEquippedAbilities()) {
			  if(a != null) {
				  if(a.equals(DoruDoruBallAbility.INSTANCE) && a.isContinuous()) {
					  e.setCanceled(true);
				  }
			  }
		  }
		  
	  }
	}
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IAbilityData abilityProps = AbilityDataCapability.get(player);
			
		Ability doruBallAbility = abilityProps.getEquippedAbility(DoruDoruBallAbility.INSTANCE);
		boolean isDoruBallActive = doruBallAbility != null && doruBallAbility.isContinuous();
		if(isDoruBallActive)
			player.setMotion(0, -5, 0);
	}

	
}
