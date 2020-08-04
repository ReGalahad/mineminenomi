package xyz.pixelatedw.mineminenomi.events;

import java.awt.Color;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.SpecialEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class SpecialPotionEffectEvents
{
	@SubscribeEvent
	public static void onEntityPreRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();
		
		for(EffectInstance instance : entity.getActivePotionEffects())
		{
			if(instance.getPotion() instanceof SpecialEffect)			
			{
				if(((SpecialEffect)instance.getPotion()).isBlockingMovement())
				{
					entity.renderYawOffset = 0;
					entity.prevRenderYawOffset = 0;
					return;
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityPostRendered(RenderLivingEvent.Post event)
	{
		LivingEntity entity = event.getEntity();
		
		for(EffectInstance instance : entity.getActivePotionEffects())
		{
			if(instance.getPotion() instanceof SpecialEffect)			
			{
				if(((SpecialEffect)instance.getPotion()).isBlockingMovement())
				{
					entity.renderYawOffset = 0;
					entity.prevRenderYawOffset = 0;
					return;
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		
		if (player == null)
			return;
		
		Iterator iterator = player.getActivePotionEffects().iterator();
		if(iterator.hasNext())
		{
			EffectInstance instance = (EffectInstance) iterator.next();
			if (instance.getDuration() <= 0)
				player.removePotionEffect(instance.getPotion());
			
			if(instance.getPotion() instanceof SpecialEffect)			
			{
				float[] colors = ((SpecialEffect)instance.getPotion()).getOverlayColor();
				Color color = new Color(colors[0], colors[1], colors[2]);
				WyHelper.drawColourOnScreen(color.getRGB(), (int) (colors[3] * 255), 0, 0, mc.mainWindow.getScaledWidth(), mc.mainWindow.getScaledHeight(), 500);
			}
		}
	}	
}