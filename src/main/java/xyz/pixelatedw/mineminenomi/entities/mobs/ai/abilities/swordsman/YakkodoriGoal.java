package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.swordsman;

import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;

public class YakkodoriGoal extends CooldownGoal
{
	private GenericNewEntity entity;

	public YakkodoriGoal(GenericNewEntity entity)
	{
		super(entity, 120, entity.getRNG().nextInt(20));
		this.entity = entity;
		this.entity.addThreat(10);
	}

	@Override
	public boolean shouldExecute()
	{
		ItemStack itemStack = this.entity.getHeldItemMainhand();

		if (itemStack == null || itemStack.isEmpty() || this.entity.getAttackTarget() == null)
			return false;
		
		if (this.entity.getDistance(this.entity.getAttackTarget()) < 10)
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
		double d1 = this.entity.getAttackTarget().posX - this.entity.posX;
		double d2 = this.entity.getAttackTarget().getBoundingBox().minY + this.entity.getAttackTarget().getHeight() / 2.0F - (this.entity.posY + this.entity.getHeight() / 2.0F);
		double d3 = this.entity.getAttackTarget().posZ - this.entity.posZ;

		YakkodoriProjectile projectile = new YakkodoriProjectile(this.entity.world, this.entity);

		projectile.posY = this.entity.posY + this.entity.getHeight() / 2.0F + 0.5D;
		projectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0);
		this.entity.world.addEntity(projectile);

		this.entity.setCurrentGoal(this);
		this.setOnCooldown(true);
	}
}
