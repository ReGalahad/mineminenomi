package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class GasuPassiveEvents {
    public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.GASU, GasuPassiveEvents::gasuDamage);

    public static boolean gasuDamage(LivingEntity target, LivingEntity attacker) {
        attacker.addPotionEffect(new EffectInstance(Effects.POISON, 40, 0));
        return true;
    }

    @SubscribeEvent
    public static void onPotionApplicable(PotionEvent.PotionApplicableEvent event)
    {
        if (!(event.getEntity() instanceof PlayerEntity))
            return;

        PlayerEntity entity = (PlayerEntity) event.getEntity();
        IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
        EffectInstance potion = event.getPotionEffect();

        if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("gasu_gasu"))
            return;

        if (potion.getPotion().getEffect().equals(Effects.POISON))
        {
            event.setResult(Event.Result.DENY);
        }
    }

}
