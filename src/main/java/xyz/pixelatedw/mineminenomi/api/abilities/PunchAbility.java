package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class PunchAbility extends ContinuousAbility
{

	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnHitEntity onHitEntity = (player, target) -> { return 0; };
	
	public PunchAbility(String name, Category category)
	{
		super(name, category);
	}
	
	
	
	/*
	 *  Methods 
	 */
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{
		this.stopContinuity(player);
		return this.onHitEntity.onHitEntity(player, target);
	}
	
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnHitEntity extends Serializable
	{
		float onHitEntity(PlayerEntity player, LivingEntity target);
	}
}
