package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.FoodHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
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

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

        if (!devilFruitProps.getDevilFruit().equals("mera_mera"))
            return;

        if (!AbilityHelper.isNearbyKairoseki(player))
        {
            ItemStack heldStack = player.getHeldItem(player.getActiveHand() != null ? player.getActiveHand() : Hand.MAIN_HAND);

            if(player.isSneaking() && player.isSwingInProgress && FoodHelper.canBeCooked(heldStack))
            {
                ItemStack cookedStack = FoodHelper.cookStack(heldStack);
                player.setHeldItem(Hand.MAIN_HAND, cookedStack);
            }
        }
    }


}
