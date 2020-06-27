package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.api.IHasOverlay;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

import java.awt.Color;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HiePassiveEvents
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule().addApprovedBlocks(Blocks.WATER);

	public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.HIE, HiePassiveEvents::hieDamage);

	public static boolean hieDamage(LivingEntity target, LivingEntity attacker) {
		attacker.addPotionEffect(new EffectInstance(ModEffects.FROZEN, 40, 0));
		return true;
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				
		if (!devilFruitProps.getDevilFruit().equals("hie_hie"))
			return;
				
		if (!AbilityHelper.isNearbyKairoseki(player) && player.getRidingEntity() == null && (player.getHealth() > player.getMaxHealth() / 5 || player.abilities.isCreativeMode))
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

	@SubscribeEvent
	public static void onPotionApplicable(PotionEvent.PotionApplicableEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity entity = (PlayerEntity) event.getEntity();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
		EffectInstance potion = event.getPotionEffect();

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("hie_hie"))
			return;

		if (potion.getPotion().getEffect().equals(ModEffects.FROZEN))
		{
			event.setResult(Event.Result.DENY);
		}
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
