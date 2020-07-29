package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.brawler;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class HakaiHoGoal extends CooldownGoal
{
	private GenericNewEntity entity;
	private double damage;
	
	public HakaiHoGoal(GenericNewEntity entity)
	{
		super(entity, 90, entity.getRNG().nextInt(20));
		this.entity = entity;
		this.damage = 2 + this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
		this.entity.addThreat(4);
	}

	@Override
	public boolean shouldExecute()
	{
		ItemStack itemStack = this.entity.getHeldItemMainhand();
		
		//if(this.entity.getPreviousAI() != null && this.entity.getPreviousAI() == this.entity.getCurrentAI())
		//	return false;
		
		if((itemStack != null && !itemStack.isEmpty()) || this.entity.getAttackTarget() == null)
			return false;

		if(this.entity.getDistance(this.entity.getAttackTarget()) > 2)
			return false;
		
		if(this.isOnCooldown())
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
    	List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 2);
    	targets.remove(this.entity);
    	
    	for(LivingEntity target : targets)
    	{
    		target.attackEntityFrom(DamageSource.causeMobDamage(this.entity), (float) this.damage);
    	}
    	
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.entity, this.entity.posX, this.entity.posY, this.entity.posZ, 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(false);
		explosion.doExplosion();
		
		this.entity.setCurrentGoal(this);
	    this.setOnCooldown(true);
    }
}
