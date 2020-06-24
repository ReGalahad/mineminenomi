package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.CooldownGoal;
import xyz.pixelatedw.wypi.WyHelper;

public class SoruGoal extends CooldownGoal
{
	private GenericNewEntity entity;
	private UUID soruSpeedUUID = UUID.fromString("4929edc3-45e7-4763-aecc-d478f5eadc3b");
	private AttributeModifier speedModifier;// = new AttributeModifier(soruSpeedUUID, "Soru Speed", 20, 0);
	
	public SoruGoal(GenericNewEntity entity)
	{
		super(entity, 40, (int) WyHelper.randomWithRange(5, 10));		
		this.entity = entity;
		this.entity.addThreat(5);
	}
	
	@Override
	public boolean shouldExecute()
	{
		if(this.isOnCooldown())
		{
			IAttributeInstance soruSpeed = this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			if(this.cooldown < this.maxCooldown / 2 && soruSpeed.getModifier(soruSpeedUUID) != null)
				soruSpeed.removeModifier(this.speedModifier);
			
			this.cooldownTick();
			return false;
		}
		
		if(this.isOnCooldown())
		{
			this.cooldownTick();
			return false;
		}
		
		if (this.entity.getAttackTarget() == null)
			return false;

		float distance = this.entity.getDistance(this.entity.getAttackTarget());
		if (distance > 15 && distance <= 25)
		{
			this.execute(0.3);
			return true;
		}
		else if(distance > 25)
		{
			this.execute(0.4);
			return true;	
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void endCooldown()	
	{
		super.endCooldown();
		this.entity.setCurrentGoal(null);
		this.entity.setPreviousGoal(this);
	}
	
	public void execute(double level)
	{
		IAttributeInstance soruSpeed = this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if(soruSpeed.getModifier(this.soruSpeedUUID) != null && this.speedModifier != null)
			soruSpeed.removeModifier(this.speedModifier);	
		
		this.speedModifier = new AttributeModifier(this.soruSpeedUUID, "Soru Speed", level, Operation.ADDITION);

		this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(this.speedModifier);
		this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(this.speedModifier);
		
		this.entity.setCurrentGoal(this);
	    this.setOnCooldown(true);
	}
}
