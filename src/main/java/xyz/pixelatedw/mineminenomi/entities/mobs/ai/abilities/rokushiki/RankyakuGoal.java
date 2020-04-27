package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki;

import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RankyakuProjectile;

public class RankyakuGoal extends CooldownGoal
{
	private GenericNewEntity entity;

	public RankyakuGoal(GenericNewEntity entity)
	{
		super(entity, 120, entity.getRNG().nextInt(10));
		this.entity = entity;
		this.entity.addThreat(20);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.entity.getAttackTarget() == null)
			return false;

		if (this.entity.getDistance(this.entity.getAttackTarget()) < 5)
			return false;

		if (this.isOnCooldown())
		{
			this.cooldownTick();
			return false;
		}

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
		double d0 = entity.getDistance(entity.getAttackTarget());
		float f = MathHelper.sqrt(d0);
		double d1 = entity.getAttackTarget().posX - entity.posX;
		double d2 = entity.getAttackTarget().getBoundingBox().minY + entity.getAttackTarget().getHeight() / 2.0F - (entity.posY + entity.getHeight() / 2.0F);
		double d3 = entity.getAttackTarget().posZ - entity.posZ;

		RankyakuProjectile projectile = new RankyakuProjectile(this.entity.world, this.entity);

		projectile.posY = entity.posY + entity.getHeight() / 2.0F + 0.5D;
		projectile.shoot(d1 + entity.getRNG().nextGaussian(), d2, d3 + entity.getRNG().nextGaussian(), 1.5F, 0);
		entity.world.addEntity(projectile);

		this.entity.setCurrentGoal(this);
		this.setOnCooldown(true);
	}
}
