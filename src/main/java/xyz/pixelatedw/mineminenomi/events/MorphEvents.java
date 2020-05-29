package xyz.pixelatedw.mineminenomi.events;

import java.lang.reflect.Field;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
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
import xyz.pixelatedw.mineminenomi.api.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncZoanPacket;
import xyz.pixelatedw.mineminenomi.renderers.entities.ZoanMorphRenderer;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
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

		if(WyHelper.isNullOrEmpty(props.getDevilFruit()))
			return;
		
		float shadowSize = 0.5F;
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
				
				shadowSize = info.getShadowSize();
			}
		}
		else
		{
			shadowSize = 0.5F;
		}
		
		Field field = null;
		try
		{
			field = EntityRenderer.class.getDeclaredField("shadowSize");
			field.setAccessible(true);
			field.set(event.getRenderer(), shadowSize);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public static void onEntityConstructing(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity clientPlayer = Minecraft.getInstance().player;
			PlayerEntity player = (PlayerEntity) event.getEntity();

			// If the player that joins the world is different than our client player it sends a zoan sync packet
			// This packet will get the player who just joined the world from its entity id, get the devil fruit and ability capabilities
			// and send them to all players around it (250 blocks radius).
			// The SyncZoan packet is just a 'middleman' since we cannot send a sync to all packet from the client, so we're sending the player id
			// to the server which will then sync all.
			if(player != null && clientPlayer != player)
			{
				WyNetwork.sendToServer(new CSyncZoanPacket(player.getEntityId()));
			}
		}
	}

	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		PlayerEntity player = Minecraft.getInstance().player;

		boolean renderHandFlag = false;
		boolean hasEmptyHand = player.getHeldItemMainhand().isEmpty();

		ZoanInfo info = MorphHelper.getZoanInfo(player);
		if (info != null)
			renderHandFlag = true;

		if (event.getHand() == Hand.MAIN_HAND && hasEmptyHand && renderHandFlag)
		{
			event.setCanceled(true);
			ResourceLocation texture = MorphHelper.getTextureFromMorph((ClientPlayerEntity) player, Minecraft.getInstance().getRenderManager());
			MorphHelper.renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress(), HandSide.RIGHT, texture);
		}
	}

	@SubscribeEvent
	public static void onZoanSizeChange(EyeHeight event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();
		IDevilFruit props = DevilFruitCapability.get(player);

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
		
		if(player.isSneaking())
			eyeHeight = eyeHeight - 0.3F;
		
		event.setNewHeight(eyeHeight);
	}

	@SubscribeEvent
	public static void onZoanSizeChange(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			PlayerEntity player = event.player;
			IDevilFruit props = DevilFruitCapability.get(player);

			double posX = player.posX;
			double posY = player.posY;
			double posZ = player.posZ;

			double width = 0.6F / 2;
			double height = 1.8F;

			if(WyHelper.isNullOrEmpty(props.getDevilFruit()))
				return;
			else if(WyHelper.isNullOrEmpty(props.getZoanPoint()))
			{
				player.setBoundingBox(new AxisAlignedBB(posX - width, posY, posZ - width, posX + width, posY + height, posZ + width));
				return;
			}
								
			ZoanInfo info = MorphHelper.getZoanInfo(player);
			if (info != null)
			{
				width = info.getWidth() / 2;
				height = info.getHeight();
			}

			if(player.isSneaking())
				height = height - 0.2F;
			
			player.setBoundingBox(new AxisAlignedBB(posX - width, posY, posZ - width, posX + width, posY + height, posZ + width));
		}
	}
}
