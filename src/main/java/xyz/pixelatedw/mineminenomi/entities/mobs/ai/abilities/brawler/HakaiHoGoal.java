package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.brawler;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class HakaiHoGoal extends CooldownGoal
{
	private GenericNewEntity entity;
	private double damage;
	
	public HakaiHoGoal(GenericNewEntity entity)
	{
		super(entity, 90, entity.getRNG().nextInt(20));
		this.entity = entity;
		this.damage = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
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
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.entity, this.entity.posX, this.entity.posY, this.entity.posZ, 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		this.entity.setCurrentGoal(this);
	    this.setOnCooldown(true);
    }
}
