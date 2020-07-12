package xyz.pixelatedw.mineminenomi.items;

import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class SakeCupItem extends Item
{

	private IItemPropertyGetter filledProperty = (itemStack, world, livingEntity) ->
	{
		if (livingEntity == null || !(livingEntity instanceof PlayerEntity))
		{
			return 0.0F;
		}
		else
		{
			return itemStack.getTag() != null && !WyHelper.isNullOrEmpty(itemStack.getTag().getString("leader")) ? 1 : 0;
		}
	};
	
	public SakeCupItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1).food(Foods.APPLE));
		this.addPropertyOverride(new ResourceLocation("filled"), this.filledProperty);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		if(this.getLeader(player.getHeldItemMainhand(), world) != null)
			player.setActiveHand(hand);
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity)
	{
		if(!world.isRemote && entity instanceof PlayerEntity)
		{		
			PlayerEntity player = (PlayerEntity) entity;
			PlayerEntity leader = this.getLeader(itemStack, player.world);
			
			if(leader != null)
			{
				ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
				Crew crew = worldProps.getCrewWithCaptain(leader.getUniqueID());
				if(crew == null)
					WyDebug.debug("Cannot find a crew for captain " + leader.getName().getFormattedText());
				else
				{
					if(!crew.hasMember(player.getUniqueID()))
					{
						crew.addMember(player.getUniqueID());
						WyHelper.sendMsgToPlayer(leader, new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_JOIN, player.getName().getFormattedText()).getFormattedText());
						itemStack.getOrCreateTag().putInt("leader", 0);
					}
				}
			}
			
			if(!player.isCreative())
				itemStack.shrink(1);
		}
		
		return itemStack;
	}
	
	public void setLeader(ItemStack itemStack, PlayerEntity leader)
	{
		itemStack.setTag(new CompoundNBT());
		itemStack.getTag().putString("leader", leader.getUniqueID().toString());
	}
	
	public PlayerEntity getLeader(ItemStack itemStack, World world)
	{
		if(!itemStack.hasTag())
			itemStack.setTag(new CompoundNBT());
		String leaderUUID = itemStack.getTag().getString("leader");
		if(!WyHelper.isNullOrEmpty(leaderUUID))
			return world.getPlayerByUuid(UUID.fromString(leaderUUID));
		return null;
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.DRINK;
	}
}
