package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.wypi.quests.objectives.IHitEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class HitEntityObjective extends Objective implements IHitEntityObjective
{
	protected EntityType target;
	
	public HitEntityObjective(String title, int count, EntityType type)
	{
		super(title);
		this.setMaxProgress(count);
		this.target = type;
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		return target.getType() == this.target;
	}
}
