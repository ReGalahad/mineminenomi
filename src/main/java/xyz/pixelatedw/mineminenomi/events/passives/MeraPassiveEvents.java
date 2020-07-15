package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MeraPassiveEvents {
    public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.MERA, MeraPassiveEvents::meraDamage, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.HOT_FLOOR);

    public static boolean meraDamage(LivingEntity target, LivingEntity attacker) {
		SetOnFireEvent event = new SetOnFireEvent(attacker, target, 10);
		if (!MinecraftForge.EVENT_BUS.post(event))
			attacker.setFire(5);
        return true;
    }
}
