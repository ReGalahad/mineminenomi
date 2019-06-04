package xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SwordsmanProjectiles.Yakkodori;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class EntityAIGapCloser extends EntityAIBase
{

	private EntityNewMob entity;
	private int hitCount;
	private float prevHealth;
	
	public EntityAIGapCloser(EntityNewMob entity)
	{
		this.entity = entity;
		this.prevHealth = this.entity.getHealth();
	}

	public boolean shouldExecute()
	{		
		if(this.entity.getAttackTarget() == null)
			return false;
		
		if(this.entity.getHealth() < this.prevHealth)
		{
			this.hitCount++;
			this.prevHealth = this.entity.getHealth();
		}

		float distance = this.entity.getDistanceToEntity(this.entity.getAttackTarget());
		if(distance > 14 && distance < 2)
			return false;

		if(this.hitCount < 3)
			return false;
		
		this.execute();
		return true;
	} 
	
    public void execute()
    {
		double mX = (double)(-MathHelper.sin(this.entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.entity.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
		double mZ = (double)(MathHelper.cos(this.entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.entity.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
			
		double f2 = MathHelper.sqrt_double(mX * mX + this.entity.motionY * this.entity.motionY + mZ * mZ);
		mX /= (double)f2;
		mZ /= (double)f2;
		mX += this.entity.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mZ += this.entity.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mX *= 1.5;
		mZ *= 1.5;
		
		this.entity.motionX = mX;
		this.entity.motionZ = mZ;
		
		this.hitCount = 0;
    }

}
