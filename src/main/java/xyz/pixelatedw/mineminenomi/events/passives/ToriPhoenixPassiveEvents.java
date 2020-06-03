package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.PhoenixFlyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.TenseiNoSoenAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ToriPhoenixPassiveEvents {

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IAbilityData AbilityProps = AbilityDataCapability.get(player);
        IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

        if (!devilFruitProps.getDevilFruit().equals("tori_tori_phoenix"))
            return;

        System.out.println(player.getMotion());

        PhoenixFlyPointAbility ability = AbilityProps.getEquippedAbility(PhoenixFlyPointAbility.INSTANCE);
        TenseiNoSoenAbility TenseiNoSoen = AbilityProps.getEquippedAbility(TenseiNoSoenAbility.INSTANCE);


        if (ability != null && ability.isContinuous()) {
            if (TenseiNoSoen != null && 5 > TenseiNoSoen.getChargeTime()) return;

            float maxSpeed = 2.0f;
            float acceleration = 0.01f;

            acceleration *= (ability.speed > 0 ? (1 - ability.speed / maxSpeed) : 1);
            acceleration = (player.moveForward > 0 && !player.collided) ? acceleration : -maxSpeed / 10;
            ability.speed = MathHelper.clamp(ability.speed + acceleration, acceleration > 0 ? maxSpeed / 4 : 0, maxSpeed);

            int d1 = player.onGround ? 1 : 0;
            int d2 = player.moveForward > 0F ? 1 : 0;
            int d3 = player.isSneaking() ? 1 : 0;

            Vec3d vec = player.getLookVec();
            double x = (vec.x * ability.speed) * (1 - d1) * d2 + d3 * player.getMotion().z;
            double y = ((vec.y * ability.speed) +  (Math.cos(player.ticksExisted / 4f) / 5f)) * (1 - d1) * d2 + (1 - d2) * (Math.cos(player.ticksExisted / 4f) / 5f) + d3 * -0.5f + d1 * 5f;
            double z = (vec.z * ability.speed) * (1 - d1) * d2 + d3 * player.getMotion().z;
            player.setMotion(x, y, z);
        }
        player.fallDistance = 0;
    }

}
