package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class HiePassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				
		if (!devilFruitProps.getDevilFruit().equals("hie_hie"))
			return;
				
		if (!AbilityHelper.isNearbyKairoseki(player) && (player.getHealth() > player.getMaxHealth() / 5 || player.abilities.isCreativeMode))
			AbilityHelper.createFilledSphere(player.world, (int) player.posX - 1, (int) player.posY, (int) player.posZ - 1, 2, Blocks.ICE, "liquid");
	}
}
