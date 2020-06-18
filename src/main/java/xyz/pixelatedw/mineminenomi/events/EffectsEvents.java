package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class EffectsEvents
{
	private static final Effect[] UNREMOVEABLE_EFFECTS = new Effect[] { ModEffects.BLACK_BOX, ModEffects.ABILITY_OFF, ModEffects.DOOR_HEAD, ModEffects.CANDLE_LOCK, ModEffects.BUBBLY_CORAL, ModEffects.DRUNK, ModEffects.FROZEN, ModEffects.GANMEN_SEICHO_HORMONE, ModEffects.LOVESTRUCK, ModEffects.UNCONSCIOUS, ModEffects.NEGATIVE, ModEffects.MOVEMENT_BLOCKED, ModEffects.GUARDING };

	@SubscribeEvent
	public static void onDrinkMilk(PotionRemoveEvent event)
	{
		boolean isMilkBucket = event.getEntityLiving().getHeldItem(event.getEntityLiving().getActiveHand()).getItem() == Items.MILK_BUCKET;

		if (isMilkBucket)
		{
			for (Effect effect : UNREMOVEABLE_EFFECTS)
			{
				if (event.getPotion() == effect)
				{
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if(entity.getActivePotionEffect(ModEffects.GUARDING) != null) {
			EffectInstance instance = entity.getActivePotionEffect(ModEffects.GUARDING);
			float power = (instance.getAmplifier() + 1) * 10;
			if(event.getAmount() > power) {
				if(event.getAmount() * 0.4f - power >  0) event.setAmount(event.getAmount() * 0.4f - power);
				else event.setAmount(event.getAmount() - power);
			} else {
				event.setAmount(0);
			}
		}
	}
}