package xyz.pixelatedw.mineminenomi.events.passives;

import java.awt.Color;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HiePassiveEvents
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule().addApprovedBlocks(Blocks.WATER); 

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				
		if (!devilFruitProps.getDevilFruit().equals("hie_hie"))
			return;
				
		if (!AbilityHelper.isNearbyKairoseki(player) && (player.getHealth() > player.getMaxHealth() / 5 || player.abilities.isCreativeMode))
			AbilityHelper.createFilledSphere(player.world, (int) player.posX - 1, (int) player.posY, (int) player.posZ - 1, 2, Blocks.ICE, GRIEF_RULE);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityPreRendered(RenderLivingEvent.Pre event)
	{
		LivingEntity entity = event.getEntity();

		if (!entity.isPotionActive(ModEffects.FROZEN))
			return;

		entity.renderYawOffset = 0;
		entity.prevRenderYawOffset = 0;
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityPostRendered(RenderLivingEvent.Post event)
	{
		LivingEntity entity = event.getEntity();

		if (!entity.isPotionActive(ModEffects.FROZEN))
			return;

		entity.renderYawOffset = 0;
		entity.prevRenderYawOffset = 0;
	}
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;

		if (player == null)
			return;
		
		if (!player.isPotionActive(ModEffects.FROZEN))
			return;

		if (player.getActivePotionEffect(ModEffects.FROZEN).getDuration() <= 0)
			player.removePotionEffect(ModEffects.FROZEN);
		
		float[] colors = ((IHasOverlay) ModEffects.FROZEN).getOverlayColor();
		Color color = new Color(colors[0], colors[1], colors[2]);
		WyHelper.drawColourOnScreen(color.getRGB(), 200, 0, 0, mc.mainWindow.getScaledWidth(), mc.mainWindow.getScaledHeight(), 500);
	}
}
