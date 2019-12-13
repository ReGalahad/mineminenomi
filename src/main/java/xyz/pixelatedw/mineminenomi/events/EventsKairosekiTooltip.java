package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.values.ModValues;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID, value = Dist.CLIENT)
public class EventsKairosekiTooltip
{

	@SubscribeEvent
	public static void onKairosekiCheck(ItemTooltipEvent event)
	{
		for (Object obj : ModValues.KAIROSEKI_ITEMS)
		{
			Item itm = null;
			
			if(obj instanceof Item)
				itm = (Item) obj;
			else if(obj instanceof Block)
				itm = ((Block) obj).asItem();
						
			if ( event.getItemStack().getItem().equals(itm) )
			{
				StringTextComponent kairosekiString = new StringTextComponent(TextFormatting.YELLOW + "Kairoseki Item");
				if(!event.getToolTip().contains(kairosekiString))
				{
					event.getToolTip().add(new StringTextComponent(""));
					event.getToolTip().add(kairosekiString);
				}
		 	}
		}
	}
	
}
