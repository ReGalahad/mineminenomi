package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class ShadowItem extends Item
{

	public ShadowItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).food(Foods.DRIED_KELP));
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
			IEntityStats props = EntityStatsCapability.get(entity);
			if (!props.hasShadow())
			{
				props.setShadow(true);
				WyNetwork.sendToServer(new SEntityStatsSyncPacket(entity.getEntityId(), props));
			}
			else
			{
				if (entity.getActivePotionEffect(Effects.STRENGTH) == null || entity.getActivePotionEffect(Effects.RESISTANCE) == null)
				{
					entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 100, 0, false, false));
					entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 100, 0, false, false));
				}
				else
				{
					int duration = entity.getActivePotionEffect(Effects.STRENGTH).getDuration();
					int amplifier = 0;
	
					duration += 100;
	
					if (duration > 500)
						amplifier = 1;

					if(duration > 1200)
						duration = 1200;
	
					entity.removePotionEffect(Effects.STRENGTH);
					entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, duration, amplifier));
					
					duration = entity.getActivePotionEffect(Effects.RESISTANCE).getDuration();
					amplifier = 1;
	
					duration += 100;
	
					if (duration > 500)
						amplifier = 2;
	
					if(duration > 1200)
						duration = 1200;
					
					entity.removePotionEffect(Effects.RESISTANCE);
					entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, duration, amplifier));
				}
			}
	
			itemStack.shrink(1);
		}
		
		return itemStack;
	}
	
	
	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.EAT;
	}
}
