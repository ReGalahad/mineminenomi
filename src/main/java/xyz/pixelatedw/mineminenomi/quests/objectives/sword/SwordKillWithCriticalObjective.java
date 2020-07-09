package xyz.pixelatedw.mineminenomi.quests.objectives.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.ICriticalKillObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ISwordKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordKillWithCriticalObjective extends Objective implements ISwordKillObjective, ICriticalKillObjective
{	
	public SwordKillWithCriticalObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		return this.checkSwordKill(player, target) && this.checkCriticalKill(player);
	}

}
