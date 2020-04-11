package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;

public class BubblyCoralItem extends Item
{
	public BubblyCoralItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).defaultMaxDamage(5));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		/*
		ItemStack itemStack = player.getHeldItem(hand);
		
		if(player.isPotionActive(ModEffects.BUBBLY_CORAL))
			return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
		
		player.addPotionEffect(new EffectInstance(ModEffects.BUBBLY_CORAL, 2000, 0));		
		itemStack.damageItem(1, player, (user) -> {});
		 */
		
		QuestDataCapability.get(player).addInProgressQuest(ModQuests.SWORDSMAN_TRIAL_01);
		System.out.println(QuestDataCapability.get(player).getInProgressQuests());
		
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
