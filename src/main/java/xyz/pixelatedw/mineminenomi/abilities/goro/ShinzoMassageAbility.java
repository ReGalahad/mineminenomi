package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;

public class ShinzoMassageAbility extends PassiveAbility {
    public static final ShinzoMassageAbility INSTANCE = new ShinzoMassageAbility();

    public ShinzoMassageAbility() {
        super("Shinzo Massage", APIConfig.AbilityCategory.DEVIL_FRUIT);
        this.setMaxCooldown(30);
        this.setDescription("Launches a huge concentrated chunk of electricity at the opponent");
        this.duringPassiveEvent = this::duringPassiveEvent;
    }

    private void duringPassiveEvent(PlayerEntity playerEntity) {
        if(this.isOnCooldown()) {
            this.cooldown--;
        }
    }
}
