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

    public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModResources.GORO, GoroPassiveEvents::goroDamage, DamageSource.LIGHTNING_BOLT, DamageSource.IN_FIRE, DamageSource.HOT_FLOOR);

    public static boolean goroDamage(LivingEntity target, LivingEntity attacker) {
        boolean attackerHasGomu = DevilFruitHelper.hasDevilFruit(attacker, ModAbilities.GOMU_GOMU_NO_MI);
        if(!attackerHasGomu)
        {
            attacker.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 8);
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if(!(event.getEntity() instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
        IAbilityData AbilityProps = AbilityDataCapability.get(player);
        ShinzoMassageAbility ability = AbilityProps.getUnlockedAbility(ShinzoMassageAbility.INSTANCE);

        if (ability == null || !devilFruitProps.getDevilFruit().equals("goro_goro") || player.world.isRemote)
            return;

		if (!ability.isOnCooldown() && !event.getSource().equals(DamageSource.OUT_OF_WORLD)) {
			if (player.getHealth() - event.getAmount() <= 0) {
				event.setCanceled(true);
				ability.startCooldown(player);
				player.setHealth(player.getMaxHealth() / 10);
				player.hurtTime = 300;
			}
		}
    }

}
