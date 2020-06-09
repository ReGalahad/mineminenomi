package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.goro.ShinzoMassageAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class GoroPassiveEvents {

    @SubscribeEvent
    public static void onEntityAttackEvent(LivingAttackEvent event) {
        LivingEntity entity = event.getEntityLiving();
        IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);

        if (!devilFruitProps.getDevilFruit().equals("goro_goro"))
            return;

        DamageSource damageSource = event.getSource();
        if (damageSource.equals(DamageSource.LIGHTNING_BOLT) || damageSource.equals(DamageSource.IN_FIRE)) {
            entity.extinguish();
            event.setCanceled(true);
        }
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
                entity.hurtTime = 300;
            }
        }
    }

}
