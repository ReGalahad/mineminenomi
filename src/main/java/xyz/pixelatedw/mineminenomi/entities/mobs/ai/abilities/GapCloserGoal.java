package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;

public class GapCloserGoal extends Goal
{
	private GenericNewEntity entity;
	private int hitCount, maxCount;
	private float prevHealth;
	private double speed;

	public GapCloserGoal(GenericNewEntity entity)
	{
		this.entity = entity;
		this.speed = 1.7;
		this.maxCount = 3;
		this.prevHealth = this.entity.getHealth();
	}
	
	public GapCloserGoal(GenericNewEntity entity, double speed, int hitCount)
	{
		this.entity = entity;
		this.speed = speed;
		this.maxCount = hitCount;
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
		if (distance > 14 && distance < 2)
			return false;

		if (this.hitCount < this.maxCount)
			return false;

		this.execute();
		return true;
	}

	public void execute()
	{
		double mX = -MathHelper.sin(this.entity.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.entity.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
		double mZ = MathHelper.cos(this.entity.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.entity.rotationPitch / 180.0F * (float) Math.PI) * 0.4;

		double f2 = MathHelper.sqrt(mX * mX + this.entity.getMotion().y * this.entity.getMotion().y + mZ * mZ);
		mX /= f2;
		mZ /= f2;
		mX += this.entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mZ += this.entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mX *= this.speed;
		mZ *= this.speed;

		this.entity.setMotion(new Vec3d(mX, 0.3, mZ));

		this.hitCount = 0;
	}
}
