package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordKillObjective extends Objective implements IKillEntityObjective
{	
	public SwordKillObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		ItemStack heldItem = player.getHeldItemMainhand();

		boolean swordFlag = ItemsHelper.isSword(heldItem);
				
		return swordFlag;
	}

}
