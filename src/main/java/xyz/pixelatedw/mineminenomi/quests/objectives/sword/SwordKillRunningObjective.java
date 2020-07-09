package xyz.pixelatedw.mineminenomi.quests.objectives.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.IRunningKillObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ISwordKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordKillRunningObjective extends Objective implements ISwordKillObjective, IRunningKillObjective
{	
	public SwordKillRunningObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		return this.checkSwordKill(player, target) && this.checkRunningKill(player, target);
	}

}
