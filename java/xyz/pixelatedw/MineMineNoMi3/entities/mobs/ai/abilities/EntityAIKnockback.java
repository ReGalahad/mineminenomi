package xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.EntityAICooldown;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class EntityAIKnockback extends EntityAICooldown
{
	private EntityNewMob entity;
	
	public EntityAIKnockback(EntityNewMob entity)
	{
		super(entity, 100, entity.getRNG().nextInt(20));
		this.entity = entity;
	}

	public boolean shouldExecute()
	{
		ItemStack itemStack = this.entity.getHeldItem();

		if(itemStack != null || this.entity.getAttackTarget() == null)
			return false;
	
		if(this.entity.getDistanceToEntity(this.entity.getAttackTarget()) > 3)
			return false;

		if(this.isOnCooldown())
		{
			this.countDownCooldown();
			return false;
		}
		
		this.execute();
		return true;
	} 
	
	public void endCooldown()	
	{
		super.endCooldown();
		this.entity.setCurrentAI(null);
		this.entity.setPreviousAI(this);
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
		mX *= 4;
		mZ *= 4;
		
		this.entity.getAttackTarget().motionX = mX;
		this.entity.getAttackTarget().motionZ = mZ;
    	
		this.entity.setCurrentAI(this);
	    this.setOnCooldown(true);
    }
}
