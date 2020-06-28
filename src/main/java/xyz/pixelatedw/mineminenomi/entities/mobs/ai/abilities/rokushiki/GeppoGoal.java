package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki;

import java.util.List;

import net.minecraft.entity.projectile.ThrowableEntity;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.wypi.WyHelper;

public class GeppoGoal extends CooldownGoal
{
	private GenericNewEntity entity;
	
	public GeppoGoal(GenericNewEntity entity)
	{
		super(entity, 80, (int) WyHelper.randomWithRange(2, 7));		
		this.entity = entity;
		this.entity.addThreat(10);
	}
	
	@Override
	public boolean shouldExecute()
	{
		this.entity.fallDistance = 0;
		
		List<ThrowableEntity> dangers = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 3, ThrowableEntity.class);
		
		if(dangers.size() > 0)
		{
			if(this.cooldown > this.maxCooldown / 2 && this.cooldown < this.maxCooldown)
				return false;
			
			this.execute();
			return true;
		}
		
		if(this.isOnCooldown())
		{
			this.cooldownTick();
			return false;
		}
		
		if (this.entity.getAttackTarget() == null)
			return false;

		float distance = this.entity.getDistance(this.entity.getAttackTarget());
		if (distance > 5 && this.entity.getHealth() > this.entity.getMaxHealth() / 4)
			return false;

		this.execute();
		return true;
	}

	@Override
	public void endCooldown()	
	{
		super.endCooldown();
		this.entity.setCurrentGoal(null);
		this.entity.setPreviousGoal(this);
	}
	
	public void execute()
	{
		this.entity.setMotion(0, 1.3, 0);
		
		GeppoAbility.PARTICLES.spawn(this.entity.world, this.entity.posX, this.entity.posY, this.entity.posZ, 0, 0, 0);
		
		this.entity.setCurrentGoal(this);
	    this.setOnCooldown(true);
	}
}
