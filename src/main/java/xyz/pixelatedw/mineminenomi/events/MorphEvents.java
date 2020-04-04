package xyz.pixelatedw.mineminenomi.events;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent.EyeHeight;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.renderers.ZoanFirstPersonRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class MorphEvents
{

	/**
	 * Disclaimer
	 * This class is a mess...there's a lot of reused code and overall badly designed code
	 * You have been warned
	 * 
	 * Update 28-Aug-2019: Ok it's a bit better now...still not perfect
	 * Update 17-Dec-2019: Nvm...after porting it to 1.14 it's absolute shit, ignore if possible
	 * Update 03-Apr-2020: Ye it's better now, cleaned a lot of useless stuff and it's now a zoan dedicated events class
	 */

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		LivingEntity entity = event.getEntity();
		IDevilFruit props = DevilFruitCapability.get(entity);
		LivingRenderer renderer = event.getRenderer();

		if (!WyHelper.isNullOrEmpty(props.getZoanPoint()))
		{
			if (event.getEntity().hurtTime > 0)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1.0f, 0, 0);
				GL11.glPopMatrix();
			}

			event.setCanceled(true);

			ZoanInfo info = MorphHelper.getZoanInfo((PlayerEntity) entity);
			if (info != null)
			{
				ZoanMorphRenderer render = info.getFactory().createRenderFor(Minecraft.getInstance().getRenderManager());
				if (Minecraft.getInstance().player.equals(entity))
					render.doRender(entity, event.getX(), event.getY(), event.getZ(), 0F, 0.0625F);
				else
					render.doRender(entity, event.getX(), event.getY() + 1.2, event.getZ(), 0F, 0.0625F);
			}
		}
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

				WyNetwork.sendToAll(new SSyncDevilFruitPacket(owner.getEntityId(), props));
			}
		}
	}

	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		boolean renderHandFlag = false;
		boolean hasEmptyHand = player.getHeldItemMainhand().isEmpty();

		ZoanInfo info = MorphHelper.getZoanInfo(player);
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
			ZoanInfo info = MorphHelper.getZoanInfo(player);
			if (info != null)
			{
				eyeHeight = (float) (1.62 * (info.getHeight() / 1.75));
				if(eyeHeight < 0.22F)
					eyeHeight = 0.22F;
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

			if (WyHelper.isNullOrEmpty(props.getDevilFruit()) || WyHelper.isNullOrEmpty(props.getZoanPoint()))
				return;

			double posX = player.posX;
			double posY = player.posY;
			double posZ = player.posZ;

			double width = 0.6F / 2;
			double height = 1.8F;

			ZoanInfo info = MorphHelper.getZoanInfo(player);
			if (info != null)
			{
				width = info.getWidth() / 2;
				height = info.getHeight();
			}

			player.setBoundingBox(new AxisAlignedBB(posX - width, posY, posZ - width, posX + width, posY + height, posZ + width));
		}
	}
}
