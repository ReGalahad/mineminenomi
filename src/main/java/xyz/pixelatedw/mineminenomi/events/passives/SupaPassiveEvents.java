package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.supa.AtomicSpurtAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SupaPassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (!devilFruitProps.getDevilFruit().equals("supa_supa"))
			return;

		Ability ability = abilityProps.getEquippedAbility(AtomicSpurtAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(!isActive || !player.onGround)
			return;
		
		if(Math.abs(player.getMotion().getX()) < 0.2 || Math.abs( player.getMotion().getZ()) < 0.2)
			player.setMotion(player.getMotion().getX() * 1.4, player.getMotion().getY(), player.getMotion().getZ() * 1.4);
	}

}
