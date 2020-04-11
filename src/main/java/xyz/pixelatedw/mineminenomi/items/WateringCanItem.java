package xyz.pixelatedw.mineminenomi.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class WateringCanItem extends Item
{

	public WateringCanItem()
	{
		super(new Properties().maxStackSize(1).group(ModCreativeTabs.MISC));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getHeldItem(hand);
		if (!itemStack.hasTag())
			itemStack.setTag(new CompoundNBT());

		int currentTears = itemStack.getTag().getInt("tears");

		if (currentTears > 0)
			player.setActiveHand(hand);
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity livingEntity)
	{
		if (!(livingEntity instanceof PlayerEntity))
			return itemStack;

		int currentTears = itemStack.getTag().getInt("tears");

		livingEntity.heal(5);
		itemStack.getTag().putInt("tears", currentTears - 1);

		return itemStack;
	}

	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 32;
	}

	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4)
	{
		if (!itemStack.hasTag())
			itemStack.setTag(new CompoundNBT());

		int currentTears = itemStack.getTag().getInt("tears");
		list.add(new StringTextComponent("Tears: " + currentTears));
	}

	public void addTears(ItemStack itemStack, LivingEntity entity)
	{
		if (!itemStack.hasTag())
			itemStack.setTag(new CompoundNBT());

		int currentTears = itemStack.getTag().getInt("tears");

		itemStack.getTag().putInt("tears", currentTears + 1);
	}
}
