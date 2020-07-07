package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MeraPassiveEvents {
    public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.MERA, MeraPassiveEvents::meraDamage, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.HOT_FLOOR);

    public static boolean meraDamage(LivingEntity target, LivingEntity attacker) {
        attacker.setFire(5);
        return true;
    }
}
