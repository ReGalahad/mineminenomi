package xyz.pixelatedw.mineminenomi.quests.objectives.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.IArrowKillObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.IDistanceKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArrowKillFromDistanceObjective extends Objective implements IArrowKillObjective, IDistanceKillObjective
{
	private int distance = 0;
	
	public ArrowKillFromDistanceObjective(String title, int count, int distance)
	{
		super(title);
		this.distance = distance;
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{		
		return this.checkArrowKill(player, target, source) && this.checkDistanceKill(player, target, this.distance);
	}
}