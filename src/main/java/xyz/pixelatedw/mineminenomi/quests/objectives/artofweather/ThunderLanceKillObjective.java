package xyz.pixelatedw.mineminenomi.quests.objectives.artofweather;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ThunderLanceKillObjective extends Objective implements IKillEntityObjective
{
	public ThunderLanceKillObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		ItemStack stack = player.getHeldItem(player.getActiveHand());
		if(stack.isEmpty() || !(stack.getItem() instanceof ClimaTactItem))
			return false;

		ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
	
		return climaTact.isCharged(stack);
	}
}
