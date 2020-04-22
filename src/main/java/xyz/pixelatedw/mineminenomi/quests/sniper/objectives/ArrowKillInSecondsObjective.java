package xyz.pixelatedw.mineminenomi.quests.sniper.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.ArrowKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class ArrowKillInSecondsObjective extends ArrowKillObjective implements IKillEntityObjective
{
	private long lastKill = 0;
	private int threshold = 0;
	
	public ArrowKillInSecondsObjective(String title, int count, int threshold)
	{
		super(title, count);
		this.threshold = threshold;
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		if(this.lastKill == 0)
			this.lastKill = player.world.getGameTime();
		
		long killTime = player.world.getGameTime();

		if(killTime - this.threshold <= this.lastKill)
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
