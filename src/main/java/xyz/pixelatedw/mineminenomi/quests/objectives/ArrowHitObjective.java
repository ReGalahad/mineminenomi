package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.wypi.quests.objectives.IHitEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArrowHitObjective extends Objective implements IHitEntityObjective
{	
	public ArrowHitObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		System.out.println(source);
		
		return false;
	}

}
