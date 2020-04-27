package xyz.pixelatedw.mineminenomi.quests.swordsman.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.SwordKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class SwordKillRunningObjective extends SwordKillObjective implements IKillEntityObjective
{	
	public SwordKillRunningObjective(String title, int count)
	{
		super(title, count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		boolean runningFlag = player.isSprinting();
		
		return super.checkKill(player, target, source) && runningFlag;
	}

}
