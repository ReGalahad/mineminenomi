package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ChiyuPassiveEvents
{
	@SubscribeEvent
	public static void onEntityAttack(LivingHurtEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);

		if (!devilFruitProps.getDevilFruit().equals("chiyu_chiyu"))
			return;

		ItemStack waterCan = null;
		for (int i = 0; i < attacked.inventory.mainInventory.size(); ++i)
		{
			if (attacked.inventory.mainInventory.get(i) != null && attacked.inventory.mainInventory.get(i).getItem() == ModItems.WATERING_CAN)
			{
				waterCan = attacked.inventory.mainInventory.get(i);
			}
		}
		
		if(waterCan == null)
        	return;
        
        if(!waterCan.hasTag())
			waterCan.setTag(new CompoundNBT());
        
		int bonusTears = (int) (event.getAmount() / 3);
		if (bonusTears <= 0)
			bonusTears = 1;

		int tears = waterCan.getTag().getInt("tears");
		waterCan.getTag().putInt("tears", tears + bonusTears);
	}
}
