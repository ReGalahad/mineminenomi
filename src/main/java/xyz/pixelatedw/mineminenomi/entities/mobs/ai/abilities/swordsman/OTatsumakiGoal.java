package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.swordsman;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.wypi.WyHelper;

public class OTatsumakiGoal extends CooldownGoal
{
	private GenericNewEntity entity;
	private double damage;
	
	public OTatsumakiGoal(GenericNewEntity entity)
	{
		super(entity, 80, entity.getRNG().nextInt(20));
		this.entity = entity;
		this.entity.addThreat(15);
		this.damage = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
	}

	@Override
	public boolean shouldExecute()
	{
		ItemStack itemStack = this.entity.getHeldItemMainhand();
			
		if(itemStack == null || itemStack.isEmpty() || this.entity.getAttackTarget() == null)
			return false;
			
		if(!this.entity.getEntitySenses().canSee(this.entity.getAttackTarget()))
			return false;
		
		if(this.entity.getDistance(this.entity.getAttackTarget()) > 3)
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
    	List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(this.entity.getPosition(), this.entity.world, 4);
    	targets.remove(this.entity);
		for(LivingEntity target : targets)
		{
			target.attackEntityFrom(DamageSource.causeMobDamage(this.entity), (float) this.damage);				
			target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 1, false, false));
		}	

		OTatsumakiAbility.PARTICLES.spawn(this.entity.world, this.entity.posX, this.entity.posY, this.entity.posZ, 0, 0, 0);
		
		this.entity.setCurrentGoal(this);
	    this.setOnCooldown(true);
    }
}
