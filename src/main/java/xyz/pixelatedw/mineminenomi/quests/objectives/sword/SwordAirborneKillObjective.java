package xyz.pixelatedw.mineminenomi.quests.objectives.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.quests.objectives.IAirborneKillObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ISwordKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordAirborneKillObjective extends Objective implements ISwordKillObjective, IAirborneKillObjective
{	
	public SwordAirborneKillObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		return this.checkAirborneKill(target) && this.checkSwordKill(player, target);
	}

}
