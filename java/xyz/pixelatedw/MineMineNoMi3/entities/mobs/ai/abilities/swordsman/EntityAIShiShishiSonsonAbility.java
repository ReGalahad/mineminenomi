package xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.swordsman;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SwordsmanProjectiles.Yakkodori;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.EntityAICooldown;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class EntityAIShiShishiSonsonAbility extends EntityAICooldown
{
	private EntityNewMob entity;
	private EntityLivingBase attackTarget;
	
	public EntityAIShiShishiSonsonAbility(EntityNewMob entity)
	{
		super(entity, 120, entity.getRNG().nextInt(20));
		this.entity = entity;
	}

	public boolean shouldExecute()
	{
		ItemStack itemStack = this.entity.getHeldItem();
		
		//if(this.entity.getPreviousAI() != null && this.entity.getPreviousAI() == this.entity.getCurrentAI())
		//	return false;
		
		if(itemStack == null || this.entity.getAttackTarget() == null)
			return false;

		if(this.entity.getDistanceToEntity(this.entity.getAttackTarget()) < 10)
			return false;
		
		if(this.isOnCooldown())
		{
			this.countDownCooldown();
			return false;
		}
		
		this.execute();
		return true;
	} 
	
	public void startExecuting()
	{
		this.attackTarget = this.entity.getAttackTarget();
	}
	
	public void endCooldown()	
	{
		super.endCooldown();
		this.entity.setCurrentAI(null);
		this.entity.setPreviousAI(this);
	}

    public void execute()
    {
    	WyDebug.info("Shi Shishi Sonson");
    	
    	double d0 = entity.getDistanceSqToEntity(entity.getAttackTarget());
		float f = MathHelper.sqrt_float(MathHelper.sqrt_double(d0));
		double d1 = entity.getAttackTarget().posX - entity.posX;
		double d2 = entity.getAttackTarget().boundingBox.minY + (double)(entity.getAttackTarget().height / 2.0F) - (entity.posY + (double)(entity.height / 2.0F));
		double d3 = entity.getAttackTarget().posZ - entity.posZ;
    	
    	Yakkodori projectile = new Yakkodori(this.entity.worldObj, this.entity, ListAttributes.YAKKODORI);
    	
    	projectile.posY = entity.posY + (double)(entity.height / 2.0F) + 0.5D;
		projectile.setThrowableHeading(d1 + entity.getRNG().nextGaussian(), d2, d3 + entity.getRNG().nextGaussian(), 1, 0);
		entity.worldObj.spawnEntityInWorld(projectile);
    	
    	this.entity.setCurrentAI(this);
	    this.setOnCooldown(true); 	
    }
}
