package xyz.pixelatedw.mineminenomi.events;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent.EyeHeight;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.models.effects.AbareHimatsuriModel;
import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
import xyz.pixelatedw.mineminenomi.renderers.ZoanFirstPersonRenderer;
import xyz.pixelatedw.mineminenomi.renderers.effects.AbareHimatsuriRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, value = Dist.CLIENT)
public class EventsMorphs
{

	/**
	 * Disclaimer
	 * This class is a mess...there's a lot of reused code and overall badly designed code
	 * You have been warned
	 * 
	 * Update 28-Aug-2019: Ok it's a bit better now...still not perfect
	 * Update 17-Dec-2019: Nvm...after porting it to 1.14 it's absolute shit, ignore if possible
	 */

	private Minecraft mc;

	private static AbareHimatsuriRenderer abareHimatsuri = new AbareHimatsuriRenderer(new AbareHimatsuriModel());

	public EventsMorphs()
	{
		this.mc = Minecraft.getInstance();
	}

//	@SubscribeEvent
//	public void onRenderPlayerEvent(RenderPlayerEvent.Pre event)
//	{
//		ExtendedEntityData propz = ExtendedEntityData.get(event.PlayerEntity);
//		if (propz.isInAirWorld())
//		{
//			event.setCanceled(true);
//		}
//	}

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		LivingEntity entity = event.getEntity();
		IDevilFruit props = DevilFruitCapability.get(entity);
		IAbilityData abilityProps = AbilityDataCapability.get(entity);
		LivingRenderer renderer = event.getRenderer();

//		if (abilityProps.isPassiveActive(ModAttributes.BUSOSHOKU_HAKI))
//		{
//			GlStateManager.pushMatrix();
//			{
//				GlStateManager.translatef((float) event.getX(), (float) event.getY() + 1.45F, (float) event.getZ());
//				Minecraft.getInstance().textureManager.bindTexture(ID.HANDTEXTURE_ZOANMORPH_BUSO);
//				GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
//				GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
//				GlStateManager.scaled(1.05, 1.05, 1.05);
//				float ageInTicks = entity.ticksExisted + (float) event.getY();
//				float headYawOffset = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, (float) event.getY());
//				float headYaw = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, (float) event.getY());
//				float headPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * (float) event.getY();
//				this.rotateCorpse(entity, ageInTicks, headYawOffset, (float) event.getY());
//				float limbSwingAmount = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * (float) event.getY();
//				float limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - (float) event.getY());
//				renderer.getEntityModel().boxList.get(3).isHidden = true;
//				renderer.getEntityModel().boxList.get(11).render(0.0625F);
//			}
//			GlStateManager.popMatrix();
//		}
//		else
//		{
//			GlStateManager.pushMatrix();
//			{
//				renderer.getEntityModel().boxList.get(3).isHidden = false;
//			}
//			GlStateManager.popMatrix();
//		}

		if (!WyHelper.isNullOrEmpty(props.getZoanPoint()))
		{
			if (event.getEntity().hurtTime > 0)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1.0f, 0, 0);
				GL11.glPopMatrix();
			}

			event.setCanceled(true);

			ZoanInfo info = DevilFruitsHelper.getZoanInfo((PlayerEntity) entity);
			if (info != null)
			{
				ZoanMorphRenderer render = info.getFactory().createRenderFor(Minecraft.getInstance().getRenderManager());
				doRenderZoanMorph(render, event.getX(), event.getY(), event.getZ(), event.getEntity());
			}
		}

		if (props.getDevilFruit().equalsIgnoreCase("sukesuke") && event.getEntity().isInvisible())
			event.setCanceled(true);

		IExtraEffect extraEffectProps = ExtraEffectCapability.get(event.getEntity());

		if (event.getEntity() instanceof PlayerEntity)
		{
			if (abilityProps.isPassiveActive(ModAttributes.ABARE_HIMATSURI))
			{
				if (event.getEntity().onGround)
				{
					BlockState state = event.getEntity().world.getBlockState(new BlockPos(event.getEntity().posX, event.getEntity().posY - 2, event.getEntity().posZ));
					String textureName = state.getBlock().getTranslationKey().replace("block.", "").replace("minecraft.", "");
					abareHimatsuri.setTextureAndTint(textureName, WyHelper.hexToRGB("#FFFFFF").getRGB());
				}

				if (!event.getEntity().onGround)
					abareHimatsuri.doRender(event.getEntity(), event.getX(), event.getY(), event.getZ(), 0F, 0.0625F);
			}
		}
	}

	private static void doRenderZoanMorph(ZoanMorphRenderer render, double x, double y, double z, LivingEntity entity)
	{
		if (Minecraft.getInstance().player.equals(entity))
			render.doRender(entity, x, y, z, 0F, 0.0625F);
		else
			render.doRender(entity, x, y + 1.2, z, 0F, 0.0625F);
	}

	@SubscribeEvent
	public static void onEntityConstructing(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity owner = (PlayerEntity) event.getEntity();
			IDevilFruit props = DevilFruitCapability.get(owner);

			if (!WyHelper.isNullOrEmpty(props.getZoanPoint()) && !props.getZoanPoint().equalsIgnoreCase("yomi"))
			{
				props.setZoanPoint("");

				ModNetwork.sendToAll(new SDevilFruitSyncPacket(owner.getEntityId(), props));
			}
		}
	}

	@SubscribeEvent
	public static void morphHandRendering(RenderSpecificHandEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		boolean renderHandFlag = false;
		boolean renderHandEffectFlag = false;
		boolean hasEmptyHand = player.getHeldItemMainhand().isEmpty();

		boolean hasHotBoilingSpecial = abilityDataProps.isPassiveActive(ModAttributes.HOT_BOILING_SPECIAL);
		boolean hasBusoshokuHaki = abilityDataProps.isPassiveActive(ModAttributes.BUSOSHOKU_HAKI);

		if (hasEmptyHand && (hasBusoshokuHaki || hasHotBoilingSpecial))
		{
			renderHandFlag = true;
		}

		ZoanInfo info = DevilFruitsHelper.getZoanInfo(player);
		if (info != null)
			renderHandFlag = true;

		if (event.getHand() == Hand.MAIN_HAND && hasEmptyHand && renderHandFlag)
		{
			event.setCanceled(true);
			ZoanFirstPersonRenderer.renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress(), HandSide.RIGHT);
		}
	}

	@SubscribeEvent
	public static void onZoanSizeChange(EyeHeight event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();
		IDevilFruit props = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		double posX = player.posX;
		double posY = player.posY;
		double posZ = player.posZ;

		float eyeHeight = player.getEyeHeight(player.getPose());

		if (!WyHelper.isNullOrEmpty(props.getZoanPoint()))
		{
			ZoanInfo info = DevilFruitsHelper.getZoanInfo(player);
			if (info != null)
			{
				eyeHeight = (float) (1.62 * (info.getHeight() / 1.75));
				eyeHeight = MathHelper.clamp(eyeHeight, 0.22F, eyeHeight);
			}
		}

		event.setNewHeight(eyeHeight);
	}

	@SubscribeEvent
	public static void onZoanSizeChange(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.END)
		{
			PlayerEntity player = event.player;
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			if (WyHelper.isNullOrEmpty(props.getDevilFruit()))
				return;

			double posX = player.posX;
			double posY = player.posY;
			double posZ = player.posZ;

			double width = 0.6F / 2;
			double height = 1.8F;

			ZoanInfo info = DevilFruitsHelper.getZoanInfo(player);
			if (info != null)
			{
				width = info.getWidth() / 2;
				height = info.getHeight();
			}

			player.setBoundingBox(new AxisAlignedBB(posX - width, posY, posZ - width, posX + width, posY + height, posZ + width));
		}
	}

	protected static void rotateCorpse(LivingEntity entityLiving, float ageInTicks, float headYawOffset, float v)
	{
		GL11.glRotatef(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0)
		{
			float f3 = (entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
			f3 = MathHelper.sqrt(f3);

			if (f3 > 1.0F)
			{
				f3 = 1.0F;
			}
		}
	}

	private static float interpolateRotation(float lowerLimit, float upperLimit, float range)
	{
		float f3;

		for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return lowerLimit + range * f3;
	}

}
