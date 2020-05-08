package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class MorphHelper
{
	private static List<ZoanInfo> zoanInfoList = new ArrayList<ZoanInfo>();
	
	private static final CubeModel COATED_ARM = new CubeModel();

	public static void renderArmThirdPerson(RenderLivingEvent.Post event, PlayerEntity player, ResourceLocation texture)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((float) event.getX(), (float) event.getY(), (float) event.getZ());

			GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);

			float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
			float headYawOffset = WyHelper.interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, event.getPartialRenderTick());

			WyHelper.rotateCorpse(player, ageInTicks, headYawOffset, event.getPartialRenderTick());

			if (player.shouldRenderSneaking())
				GlStateManager.translatef(0.0F, 0.2F, 0.0F);

			GlStateManager.translatef(0.02F, -1.45F, 0F);
			
			((IHasArm) event.getRenderer().getEntityModel()).postRenderArm(0.0625F, HandSide.RIGHT);
			GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(1 / 16.0F, 0.125F, -0.625F);

			GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
			
			double sizeXZ = 0.057;
			double sizeY = 0.10;
			GlStateManager.scaled(sizeXZ - 0.003, sizeY, sizeXZ + 0.002);
			
			float zTranslate = 2.1F;
			
			if(!player.getHeldItemMainhand().isEmpty())
				zTranslate = 2.4F;
			
			GlStateManager.translatef(0F, 2.4F, zTranslate);

			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			COATED_ARM.render(player, 0, 0, 0, 0, 0, 0.625F);
		}
		GlStateManager.popMatrix();
	}
	
	public static void renderArmFirstPerson(float equippedProgress, float swingProgress, HandSide side, ResourceLocation texture)
	{
		Minecraft mc = Minecraft.getInstance();
		EntityRendererManager renderManager = mc.getRenderManager();
		IDevilFruit props = DevilFruitCapability.get(mc.player);

		GlStateManager.pushMatrix();
		{
			boolean flag = side != HandSide.LEFT;
			float f = flag ? 1.0F : -1.0F;
			float f1 = MathHelper.sqrt(swingProgress);
			float f2 = -0.3F * MathHelper.sin(f1 * (float) Math.PI);
			float f3 = 0.4F * MathHelper.sin(f1 * ((float) Math.PI * 2F));
			float f4 = -0.4F * MathHelper.sin(swingProgress * (float) Math.PI);
			GlStateManager.translatef(f * (f2 + 0.64000005F), f3 + -0.6F + equippedProgress * -0.6F, f4 + -0.71999997F);
			GlStateManager.rotatef(f * 45.0F, 0.0F, 1.0F, 0.0F);
			float f5 = MathHelper.sin(swingProgress * swingProgress * (float) Math.PI);
			float f6 = MathHelper.sin(f1 * (float) Math.PI);
			GlStateManager.rotatef(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotatef(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
			AbstractClientPlayerEntity clientPlayer = mc.player;
			
			mc.getTextureManager().bindTexture(texture);
			
			GlStateManager.translatef(f * -1.0F, 3.6F, 3.5F);
			GlStateManager.rotatef(f * 120.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotatef(200.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(f * -135.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(f * 5.6F, 0.0F, 0.0F);
			PlayerRenderer playerrenderer = renderManager.getRenderer(clientPlayer);
			GlStateManager.disableCull();
	
			boolean flag2 = mc.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity)mc.getRenderViewEntity()).isSleeping();
			if (mc.gameSettings.thirdPersonView != 0 || flag2 || mc.gameSettings.hideGUI)
				return;

			if (WyHelper.isNullOrEmpty(props.getZoanPoint()))
			{
				if (flag)
					playerrenderer.renderRightArm(clientPlayer);
				else
					playerrenderer.renderLeftArm(clientPlayer);
			}
			else
			{
				ZoanMorphRenderer render = MorphHelper.getZoanInfoList().get(0).getFactory().createRenderFor(renderManager);
	
				ZoanInfo info = MorphHelper.getZoanInfo(clientPlayer);
				if(info != null)
					render = info.getFactory().createRenderFor(renderManager);
	
				if (render != null)
				{
					ZoanMorphRenderer renderZoan = render;
					float i = 1.0F;
					GL11.glColor3f(1, 1, 1);
					GL11.glScalef(i, i, i);
					GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
					GL11.glTranslatef(0.2f, 0.0f, -0.5f);
					renderZoan.renderFirstPersonArm(mc.player);
				}
			}

			GlStateManager.enableCull();
		}
		GlStateManager.popMatrix();
	}

	public static ResourceLocation getTextureFromMorph(ClientPlayerEntity player, EntityRendererManager renderManager)
	{
		ZoanMorphRenderer render = null;

		ZoanInfo info = MorphHelper.getZoanInfo(player);
		if(info != null)
			render = info.getFactory().createRenderFor(renderManager);

		if (render != null)
			return render.getEntityTexture(null);

		return player.getLocationSkin();
	}
	
	public static boolean canMorph(PlayerEntity player, String... points)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		boolean flag = false;
		
		for(String point : points)
		{
			if(props.getZoanPoint().equals(point))
			{
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	public static ZoanInfo getZoanInfo(PlayerEntity player)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()) || WyHelper.isNullOrEmpty(devilFruitProps.getZoanPoint()))
			return null;
		
		for (ZoanInfo info : MorphHelper.getZoanInfoList())
		{
			if (!info.getDevilFruit().equalsIgnoreCase(devilFruitProps.getDevilFruit()))
				continue;
			
			if (!info.getForm().equalsIgnoreCase(devilFruitProps.getZoanPoint()))
				continue;

			if (devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM) || (abilityProps.getEquippedAbility(info.getAbility()) != null && abilityProps.getEquippedAbility(info.getAbility()).isContinuous()))
				return info;
		}
		
		return null;
	}	
	
	public static void updateEyeView(PlayerEntity player)
	{
		MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight(player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
		WyNetwork.sendTo(new SRecalculateEyeHeightPacket(), (ServerPlayerEntity) player);
	}

	public static List<ZoanInfo> getZoanInfoList()
	{
		return zoanInfoList;
	}
	
	static
	{
		// Bison Zoan Points
		zoanInfoList.add(new BisonHeavyZoanInfo());
		zoanInfoList.add(new BisonWalkZoanInfo());
		
		// Phoenix Zoan Points
		zoanInfoList.add(new PhoenixFlyZoanInfo());
		zoanInfoList.add(new PhoenixAssaultZoanInfo());
		
		// Zou Zoan Points
		zoanInfoList.add(new ZouGuardZoanInfo());
		zoanInfoList.add(new ZouHeavyZoanInfo());
		
		// Mogu Zoan Points
		zoanInfoList.add(new MoguHeavyZoanInfo());
	
		// Giraffe Zoan Points
		zoanInfoList.add(new GiraffeHeavyZoanInfo());
		zoanInfoList.add(new GiraffeWalkZoanInfo());
		
		// Non-zoan Morphs
		zoanInfoList.add(new VenomDemonZoanInfo());
		zoanInfoList.add(new YomiZoanInfo());
	}
	
}
