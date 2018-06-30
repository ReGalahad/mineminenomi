package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityZoanMorph extends EntityLiving
{
	private EntityPlayer owner;
	
	public EntityZoanMorph(World worldObj)
	{
		super(worldObj);
		this.motionX *= 0;
		this.motionY *= 0;
		this.motionZ *= 0;
		
		this.ignoreFrustumCheck = true;
		this.setHealth(Float.MAX_VALUE);
	}
	
	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Float.MAX_VALUE);
	}
	
    public boolean attackEntityFrom(DamageSource ds, float f)
    {
    	if(this.owner != null && this.owner.isEntityAlive())
    		this.owner.attackEntityFrom(ds, f);
    	return true;
    }
	
	public void onUpdate()
	{	
	    this.lastTickPosX = this.posX;
	    this.lastTickPosY = this.posY;
	    this.lastTickPosZ = this.posZ;
	    
	    super.onUpdate();
		if(!this.worldObj.isRemote)
		{			
			if(owner == null)
				this.setDead();			
			
			if(owner != null)
			{
				if(owner.isDead)
					this.setDead();
				
				this.posX = owner.posX;
				this.posY = owner.posY;
				this.posZ = owner.posZ;
				this.rotationYaw = owner.rotationYaw;
				setPosition(this.posX, this.posY, this.posZ);
				setRotation(this.rotationYaw, this.rotationPitch);
			}
			
			this.clearActivePotions();
		}
			
	}
	
	public void moveEntityWithHeading(float par1, float par2)
	{
		if (this.owner != null) 
		{
			moveEntity(this.owner.motionX, this.owner.motionY, this.owner.motionZ);
		}
	}
	
	public boolean canBeCollidedWith()
	{
		return false;
	}
	
	public boolean canBePushed()
	{
		return false;
	}
	
	public void setOwner(EntityPlayer owner)
	{
		this.owner = owner;
	}
	
	public EntityPlayer getOwner()
	{
		return this.owner;
	}
}
