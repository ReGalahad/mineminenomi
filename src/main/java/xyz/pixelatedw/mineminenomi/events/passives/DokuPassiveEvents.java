package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class DokuPassiveEvents
{
	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof PlayerEntity))
			return;

		PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
		LivingEntity attacked = event.getEntityLiving();

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("doku_doku"))
			return;

		if (devilFruitProps.getZoanPoint().equalsIgnoreCase(VenomDemonZoanInfo.FORM))
			attacked.addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));
	}

	@SubscribeEvent
	public static void onPotionApplicable(PotionEvent.PotionApplicableEvent event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity entity = (PlayerEntity) event.getEntity();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
		EffectInstance potion = event.getPotionEffect();

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("doku_doku"))
			return;

		if (potion.getPotion().getEffect().equals(Effects.POISON))
		{
			entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, potion.getDuration(), potion.getAmplifier()));
			event.setResult(Event.Result.DENY);
		}
	}
}
