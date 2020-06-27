package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.models.abilities.CandleLockModel;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

import java.awt.Color;

public class DoruPassiveEvents
{
	private static final String[] COLORS = new String[] { "#c21d1f", "#8f176b", "#4d178f", "#17508d", "#158d7b", "#128d21", "#c8cb17", "#5ae163" };
	private static Color randomColor1, randomColor2, randomColor3;

	static
	{
		randomColor1 = chooseRandomColor();
		randomColor2 = chooseRandomColor();
		randomColor3 = chooseRandomColor();
	}

	private static Color chooseRandomColor()
	{
		int i = (int) WyHelper.randomWithRange(0, COLORS.length - 1);
		String hex = COLORS[i];
		return WyHelper.hexToRGB(hex);
	}
	
	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
	public static class ClientEvents
	{
		private static final SphereModel DORU_BALL = new SphereModel();

		@SubscribeEvent
		public static void onEntityRendered(RenderLivingEvent.Pre event)
		{
			CandleLockModel candleLock = new CandleLockModel();
			LivingEntity entity = event.getEntity();
			Color color = Color.WHITE;
			if (!entity.isPotionActive(ModEffects.CANDLE_LOCK))
				return;

			if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getDuration() <= 0)
			{
				entity.removePotionEffect(ModEffects.CANDLE_LOCK);
				return;
			}

			if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getAmplifier() == 2)
				color = randomColor1;

			GlStateManager.pushMatrix();
			{
				GlStateManager.disableLighting();
				GlStateManager.translatef((float) event.getX(), (float) event.getY() - 0.8F, (float) event.getZ());

				GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
				GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick() - 180, 0.0F, 1.0F, 0.0F);

				GlStateManager.scaled(0.1, 0.1, 0.15);

				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CANDLE_LOCK);
				candleLock.render(entity, 0, 0, 0, 0, 0, 0.625F);
				GlStateManager.enableLighting();
				GlStateManager.color3f(1f, 1f, 1f);
			}
			GlStateManager.popMatrix();
		}
		
		@SubscribeEvent
		public static void onHandRendering(RenderSpecificHandEvent event)
		{
			if (event.getItemStack().isEmpty() || (event.getItemStack().getItem() != ModWeapons.DORU_DORU_ARTS_KEN && event.getItemStack().getItem() != ModItems.DORU_PICKAXE))
				return;

			PlayerEntity player = Minecraft.getInstance().player;

			Color color = Color.WHITE;
			
			if (player.inventory.hasItemStack(new ItemStack(ModItems.COLOR_PALETTE)))
			{
				color = randomColor3;
				if(event.getItemStack().getItem() == ModWeapons.DORU_DORU_ARTS_KEN)
					color = randomColor1;
			}
			
			event.setCanceled(true);
			GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
			float f1 = MathHelper.lerp(event.getPartialTicks(), player.prevRotationPitch, player.rotationPitch);
			GlStateManager.disableColorMaterial();
			Minecraft.getInstance().getFirstPersonRenderer().renderItemInFirstPerson((AbstractClientPlayerEntity) player, event.getPartialTicks(), f1, event.getHand(), event.getSwingProgress(), event.getItemStack(), event.getEquipProgress());
			GlStateManager.enableColorMaterial();
		}
		
		@SubscribeEvent
		public static void onPlayerRendered(RenderPlayerEvent.Pre event)
		{
			PlayerEntity player = event.getPlayer();
			IAbilityData data = AbilityDataCapability.get(player);
			Color color = Color.WHITE;

			Ability ability = data.getEquippedAbility(DoruDoruBallAbility.INSTANCE);
			boolean isActive = ability != null && ability.isContinuous();

			if (isActive)
			{
				event.setCanceled(true);
				if (event.getPlayer().inventory.hasItemStack(new ItemStack(ModItems.COLOR_PALETTE)))
					color = randomColor2;

				DoruDoruBallAbility abl = (DoruDoruBallAbility) ability;
				abl.rotateAngleX += player.getMotion().z;
				abl.rotateAngleZ -= player.getMotion().x;
				GlStateManager.pushMatrix();
				GlStateManager.disableLighting();
				GlStateManager.translated(event.getX(), event.getY() + player.getEyeHeight(), event.getZ());
				DORU_BALL.setRotateAngle(DORU_BALL.shape1, (float) abl.rotateAngleX, 0f, (float) abl.rotateAngleZ);
				GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CANDLE_LOCK);
				DORU_BALL.render(player, 0, 0, 0, 0, 0, 0.5625f);
				GlStateManager.scaled(1, 1, 1);
				GlStateManager.enableLighting();
				GlStateManager.popMatrix();
			}

		}
	}
	
	@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
	public static class CommonEvents
	{
		@SubscribeEvent
		public static void onDamaged(LivingHurtEvent event)
		{
			if (event.getEntityLiving() instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) event.getEntityLiving();
				IAbilityData data = AbilityDataCapability.get(player);

				Ability ability = data.getEquippedAbility(DoruDoruBallAbility.INSTANCE);
				boolean isActive = ability != null && ability.isContinuous();

				if (isActive)
					event.setCanceled(true);
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
			if (isDoruBallActive)
				player.setMotion(0, -5, 0);
		}
	}
}
