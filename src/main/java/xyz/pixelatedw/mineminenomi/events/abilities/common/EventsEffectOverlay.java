package xyz.pixelatedw.mineminenomi.events.abilities.common;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyRenderHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.models.effects.CandleLockModel;
import xyz.pixelatedw.mineminenomi.models.effects.ChainsModel;
import xyz.pixelatedw.mineminenomi.renderers.effects.CandleLockRenderer;
import xyz.pixelatedw.mineminenomi.renderers.effects.ChainsRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, value = Dist.CLIENT)
public class EventsEffectOverlay
{
	private static CandleLockRenderer candleLock = new CandleLockRenderer(new CandleLockModel());
	private static ChainsRenderer oriBind = new ChainsRenderer(new ChainsModel());

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		IExtraEffect props = ExtraEffectCapability.get(event.getEntity());

		if(props == null)
			return;
		
		/*if (props.hasExtraEffect(ID.EXTRAEFFECT_MERO))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#5d6060");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffect(ID.EXTRAEFFECT_HIE))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#1be2e2");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffect(ID.EXTRAEFFECT_NORO))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#ce83d3");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffect(ID.EXTRAEFFECT_DORULOCK))
		{
			candleLock.doRender(event.getEntity(), event.getX(), event.getY(), event.getZ(), 0F, 0.0625F);
		}
		else if (props.hasExtraEffect(ID.EXTRAEFFECT_RUSTOVERLAY))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#a04921");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffect(ID.EXTRAEFFECT_SPIDEROVERLAY))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#606875");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffect(ID.EXTRAEFFECT_ORIBIND))
		{
			oriBind.doRender(event.getEntity(), event.getX(), event.getY(), event.getZ(), 0F, 0.0625F);
		}*/
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;

		if (player == null)
			return;

		IAbilityData abilityProps = AbilityDataCapability.get(player);

		/*
		 * if (props.hasExtraEffects(ID.EXTRAEFFECT_MERO))
		 * WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#5d6060").getRGB(), 100, 0, 0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 100);
		 * else if (props.hasExtraEffects(ID.EXTRAEFFECT_HIE))
		 * WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#1be2e2").getRGB(), 100, 0, 0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 100);
		 * else if (props.hasExtraEffects(ID.EXTRAEFFECT_NORO))
		 * WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#ce83d3").getRGB(), 100, 0, 0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 100);
		 * else if (props.hasExtraEffects(ID.EXTRAEFFECT_RUSTOVERLAY))
		 * WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#a04921").getRGB(), 100, 0, 0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 100);
		 * else if (props.hasExtraEffects(ID.EXTRAEFFECT_SPIDEROVERLAY))
		 * WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#3e4247").getRGB(), 100, 0, 0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 200);
		 */
		
		if (abilityProps.isPassiveActive(ModAttributes.AIR_DOOR))
			WyRenderHelper.drawColourOnScreen(0, 50, 0, 100, 0, 0, mc.mainWindow.getWidth(), mc.mainWindow.getHeight(), 100);
	}

}
