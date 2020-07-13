package xyz.pixelatedw.mineminenomi.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class BellyPouchItem extends Item
{
	private IItemPropertyGetter sizeProperty = (itemStack, world, livingEntity) ->
	{
		if(livingEntity == null)
			return 0;
		
		long belly = itemStack.getOrCreateTag().getLong("belly");
		int size = 0;
		
		if(belly > 100 && belly <= 500)
			size = 1;
		else if(belly > 500)
			size = 2;
		
		return size;
	};
	
	public BellyPouchItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
		this.addPropertyOverride(new ResourceLocation("size"), this.sizeProperty);
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
	{
		if(!world.isRemote)
		{
			if(!itemStack.hasTag())
			{
				itemStack.getOrCreateTag().putLong("belly", (int) WyHelper.randomWithRange(5, 100));
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    { 
		IEntityStats props = EntityStatsCapability.get(player);
		
		if(!world.isRemote)
		{
			long amount = player.getHeldItemMainhand().getOrCreateTag().getLong("belly");		

			if(props.getBelly() <= ModValues.MAX_GENERAL - amount)
			{
				props.alterBelly(amount);
				WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_BELLY_GAINED, amount).getFormattedText());
				player.inventory.deleteStack(player.getHeldItemMainhand());
			}
			else
				props.setBelly(ModValues.MAX_GENERAL);	
			
			WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
		}
				
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		if(itemStack.hasTag())
		{
			list.add(new StringTextComponent("Belly: " + itemStack.getOrCreateTag().getLong("belly")));
		}
	}
}
