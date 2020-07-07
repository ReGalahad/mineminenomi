package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class MoguPassiveEvents {

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData AbilityProps = AbilityDataCapability.get(player);
        IDevilFruit props = DevilFruitCapability.get(player);
        if (!props.getDevilFruit().equals("mogu_mogu"))
            return;

        MoguHeavyPointAbility ability = AbilityProps.getEquippedAbility(MoguHeavyPointAbility.INSTANCE);
        if (ability != null && ability.isContinuous()) {
            event.setNewSpeed(event.getOriginalSpeed() * 5);
        }
    }


}