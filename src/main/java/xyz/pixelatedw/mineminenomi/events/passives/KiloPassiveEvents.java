package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class KiloPassiveEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		IDevilFruit devilProps = DevilFruitCapability.get(player);
		
		if (!devilProps.getDevilFruit().equalsIgnoreCase("kilo_kilo"))
			return;
	
		Ability ability = abilityProps.getEquippedAbility(KiloPress1Ability.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		boolean hasUmbrella = player.getHeldItemMainhand().getItem() == ModWeapons.UMBRELLA;
		boolean inAir = !player.onGround && player.getMotion().y < 0;
		
		if (isActive && hasUmbrella && inAir)
			player.setMotion(player.getMotion().x, player.getMotion().y / 2, player.getMotion().z);
	}	
}
