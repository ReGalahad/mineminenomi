package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.lapahn;

import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;

public class LapahnJumpGoal extends CooldownGoal
{
	private LapahnEntity entity;
	private int hitCount, maxCount;
	private float prevHealth;

	public LapahnJumpGoal(LapahnEntity entity)
	{
		super(entity, 40, 5);
		this.entity = entity;
		this.maxCount = 2;
		this.prevHealth = this.entity.getHealth();
	}
	
	@Override
	public boolean shouldExecute()
	{
		if (this.entity.getAttackTarget() == null)
			return false;

		if (this.entity.getHealth() < this.prevHealth)
		{
			this.hitCount++;
			this.prevHealth = this.entity.getHealth();
		}

		float distance = this.entity.getDistance(this.entity.getAttackTarget());
		if (distance > 10 && distance < 2)
			return false;

		if (this.hitCount < this.maxCount)
			return false;

		this.execute();
		return true;		
	}

	public void execute()
	{
		this.entity.setMotion(0, 2, 0);
		this.hitCount = 0;
	}
}
