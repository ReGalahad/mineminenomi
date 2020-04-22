package xyz.pixelatedw.mineminenomi.quests.sniper.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.ArrowKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class ArrowKillFromDistanceObjective extends ArrowKillObjective implements IKillEntityObjective
{
	private int distance = 0;
	
	public ArrowKillFromDistanceObjective(String title, int count, int distance)
	{
		super(title, count);
		this.distance = distance;
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		boolean distanceFlag = player.getDistance(target) >= this.distance; 
				
		return super.checkKill(player, target, source) && distanceFlag;
	}
}