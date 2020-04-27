package xyz.pixelatedw.mineminenomi.quests.swordsman.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.SwordKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class SwordKillWithCriticalObjective extends SwordKillObjective implements IKillEntityObjective
{	
	public SwordKillWithCriticalObjective(String title, int count)
	{
		super(title, count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		boolean criticalFlag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPassenger();
		
		return super.checkKill(player, target, source) && criticalFlag;
	}

}
