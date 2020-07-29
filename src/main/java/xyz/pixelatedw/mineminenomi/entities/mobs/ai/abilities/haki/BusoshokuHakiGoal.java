package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.haki;

import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;

public class BusoshokuHakiGoal extends Goal
{
	private GenericNewEntity entity;

	public BusoshokuHakiGoal(GenericNewEntity entity)
	{
		this.entity = entity;
		this.entity.addThreat(10);
	}

	@Override
	public boolean shouldExecute()
	{
		return true;
	}

	@Override
	public void tick()
	{
		if(this.entity.getAttackTarget() != null && this.entity.getDistance(this.entity.getAttackTarget()) < 20)
			this.entity.setBusoHaki(true);
		else
			this.entity.setBusoHaki(false);
	}
}
