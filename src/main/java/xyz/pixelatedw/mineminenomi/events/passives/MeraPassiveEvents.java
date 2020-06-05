package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MeraPassiveEvents {

    @SubscribeEvent
    public static void onEntityAttackEvent(LivingAttackEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
        DamageSource damageSource = event.getSource();

        if(devilFruitProps.getDevilFruit().equalsIgnoreCase("mera_mera") && (damageSource.equals(DamageSource.IN_FIRE) || damageSource.equals(DamageSource.ON_FIRE)))
        {
            player.extinguish();
            event.setCanceled(true);
        }
    }
}
