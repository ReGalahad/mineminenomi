package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.abilities.goro.ShinzoMassageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class GoroPassiveEvents {

    public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.GORO, GoroPassiveEvents::goroDamage, DamageSource.LIGHTNING_BOLT, DamageSource.IN_FIRE);

    public static boolean goroDamage(LivingEntity target, LivingEntity attacker) {
        boolean attackerHasGomu = DevilFruitHelper.hasDevilFruit(attacker, ModAbilities.GOMU_GOMU_NO_MI);
        if(!attackerHasGomu) {
            attacker.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) target), 8);
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if(!(event.getEntity() instanceof PlayerEntity)) return;

        PlayerEntity entity = (PlayerEntity) event.getEntityLiving();
        IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
        IAbilityData AbilityProps = AbilityDataCapability.get(entity);

        if (!devilFruitProps.getDevilFruit().equals("goro_goro") || entity.world.isRemote)
            return;

        ShinzoMassageAbility ability = AbilityProps.getUnlockedAbility(ShinzoMassageAbility.INSTANCE);

        if (!ability.isOnCooldown()) {
            if (entity.getHealth() - event.getAmount() <= 0) {
                event.setCanceled(true);
                ability.startCooldown(entity);
                entity.setHealth(entity.getMaxHealth() / 20);
                entity.hurtTime = 300;
            }
        }
    }

}
