package xyz.pixelatedw.mineminenomi.events.passives;

import java.awt.Color;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.models.abilities.CandleLockModel;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class DoruPassiveEvents {
	private static final SphereModel DORU_BALL = new SphereModel();

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event) {
		CandleLockModel candleLock = new CandleLockModel();
		LivingEntity entity = event.getEntity();
		LivingRenderer renderer = event.getRenderer();
		Color color = Color.WHITE;
		if (!entity.isPotionActive(ModEffects.CANDLE_LOCK))
			return;

		if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getDuration() <= 0) {
			entity.removePotionEffect(ModEffects.CANDLE_LOCK);
		    return;
		}
		if(entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getAmplifier() == 2) {color = Color.RED;}
		GlStateManager.pushMatrix();
		{
			GlStateManager.disableTexture();
			GlStateManager.translatef((float) event.getX(), (float) event.getY() - 0.8F, (float) event.getZ());


			GlStateManager.color3f(color.getRed(), color.getGreen(), color.getBlue());
			GlStateManager.rotatef(
					entity.prevRotationYaw
							+ (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick() - 180,
					0.0F, 1.0F, 0.0F);

			GlStateManager.scaled(0.1, 0.1, 0.15);

			candleLock.render(entity, 0, 0, 0, 0, 0, 0.625F);
			GlStateManager.enableTexture();
			GlStateManager.color3f(1f,1f,1f);
		}
		GlStateManager.popMatrix();
	}

	@SubscribeEvent
	public static void doruBall(RenderPlayerEvent.Pre e) {
		PlayerEntity p = e.getPlayer();
		IAbilityData data = AbilityDataCapability.get(p);
		Color color = Color.WHITE;
		for (Ability a : data.getEquippedAbilities()) {
			if (a != null) {
				if (a.equals(DoruDoruBallAbility.INSTANCE)) {

					if(a.isContinuous()) {
					e.setCanceled(true);
					if(e.getPlayer().inventory.hasItemStack(new ItemStack(ModItems.COLOR_PALETTE))) {
						color = Color.MAGENTA;
						}
					GlStateManager.pushMatrix();
					GlStateManager.disableTexture();
					GlStateManager.translated(e.getX(), e.getY() + p.getEyeHeight(), e.getZ());
					DORU_BALL.setRotateAngle(DORU_BALL.shape1, DORU_BALL.shape1.rotateAngleX + (float)(p.getMotion().z),0f, DORU_BALL.shape1.rotateAngleZ +(float)(-(p.getMotion().x)) );
					GlStateManager.color3f(color.getRed(), color.getGreen(), color.getBlue());
					DORU_BALL.render(p, 0, 0, 0, 0, 0, 0.5625f);
					GlStateManager.scaled(1, 1, 1);
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

	
	@SubscribeEvent
	public static void particleUpdate(RenderPlayerEvent.Post event)
	{
		
	/*	PlayerEntity p = event.getPlayer();
		float pitch = p.rotationPitch;
		float yaw = MathHelper.wrapDegrees(p.getRotationYawHead()) + 180;
		double yawScale = 0.5 * Math.cos(yaw * 2) + 0.5;
		double x = p.posX + -((0.5 * (1-(0.5 * Math.cos(yaw) + 0.5))) * (pitch / 180));
		double y = p.posY + p.getEyeHeight() + Math.abs((0.3 * ((pitch / 180) / 2) * 2));
		double z = p.posZ + -((0.5 * (0.5 * Math.cos(yaw) + 0.5)) * (pitch /180));
		p.world.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0.01, 0);
 */	
	}
	
}
