package xyz.pixelatedw.MineMineNoMi3.entities.mobs.animals;

import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityDummy;

public class EntityKungFuDugong extends TempEntityDummy
{

	public EntityKungFuDugong(World world)
	{
		super(world);
		// this.tasks.addTask(1, new EntityAILookIdle(this));
		// this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
		// this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
	}

	/*@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
	}*/

}
