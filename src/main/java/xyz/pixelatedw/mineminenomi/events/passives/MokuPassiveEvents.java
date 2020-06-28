package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MokuPassiveEvents {
    public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.MOKU2, MokuPassiveEvents::mokuDamage, DamageSource.IN_FIRE);

    public static boolean mokuDamage(LivingEntity target, LivingEntity attacker) {
        attacker.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 1));
        return true;
    }
}
