package xyz.pixelatedw.mineminenomi.items;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class VivreCardItem extends Item
{

	public VivreCardItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{	
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if(world.isRemote)
			return;

		if(itemStack.getTag() != null)
		{
			LivingEntity owner = (LivingEntity) ((ServerWorld) world).getEntityByUuid(UUID.fromString(itemStack.getTag().getString("ownerUUID")));
			
			if(owner == null)
				itemStack.setCount(0);
		}
	}
	
    @Override
	public void onCreated(ItemStack itemStack, World world, PlayerEntity player) 
    {
    	this.setOwner(itemStack, player);
    	String itemName = itemStack.getDisplayName().getFormattedText();
    	itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + player.getDisplayName().getFormattedText() + "'s " + itemName));
    }
    
    public void setOwner(ItemStack itemStack, LivingEntity e)
	{
		itemStack.setTag(new CompoundNBT());
		itemStack.getTag().putString("ownerUUID", e.getUniqueID().toString());
		itemStack.getTag().putString("ownerUsername", e.getDisplayName().getFormattedText());
	}
}
