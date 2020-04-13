package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModEffects;

public class SakeBottleItem extends Item
{

	public SakeBottleItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(16).food(Foods.APPLE));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		player.setActiveHand(hand);
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity)
	{
		if(!world.isRemote && entity instanceof PlayerEntity)
		{		
			PlayerEntity player = (PlayerEntity) entity;
			
			if (entity.isPotionActive(ModEffects.DRUNK))
			{
				EffectInstance effect = entity.getActivePotionEffect(ModEffects.DRUNK);
					
				int amp = effect.getAmplifier();
				int duration = effect.getDuration();
				
				if(amp < 4)
					amp += 1;
				
				entity.removePotionEffect(ModEffects.DRUNK);
				entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, duration + 200, amp));
			}
			else
			{
				entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, 400, 0));
			}
			
			if(!player.isCreative())
				itemStack.shrink(1);
		}
		
		return itemStack;
	}
}
