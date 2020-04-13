package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.SakeCupItem;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SakeEvents
{

	@SubscribeEvent
	public static void onPlayerInteract(EntityInteractSpecific event)
	{
		if(!(event.getTarget() instanceof PlayerEntity))
			return;
		
		PlayerEntity player = event.getPlayer();
		
		if(player.getHeldItemMainhand().getItem() != ModItems.SAKE_BOTTLE)
			return;
		
		PlayerEntity target = (PlayerEntity) event.getTarget();
		
		if(target.getHeldItemMainhand().getItem() != ModItems.SAKE_CUP)
			return;
		
		ItemStack itemStack = target.getHeldItemMainhand();
		((SakeCupItem)itemStack.getItem()).setLeader(itemStack, player);
		event.setCanceled(true);
	}
	
}
