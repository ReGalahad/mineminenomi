package xyz.pixelatedw.mineminenomi.quests.swordsman.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.SwordKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class SwordKillInSecondsObjective extends SwordKillObjective implements IKillEntityObjective
{
	private long lastKill = 0;
	
	public SwordKillInSecondsObjective(String title, int count)
	{
		super(title, count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		if(this.lastKill == 0)
			this.lastKill = player.world.getGameTime();
		
		long killTime = player.world.getGameTime();

		if(killTime - 100 <= this.lastKill)
		{
			this.lastKill = killTime;
			return super.checkKill(player, target, source);			
		}
		else
		{
			this.setProgress(0);
			this.lastKill = 0;
			return false;
		}	
	}

}
