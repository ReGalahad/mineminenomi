package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class PunchAbility extends ContinuousAbility
{

	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnHitEntity onHitEntity = (player, target) -> { };
	
	public PunchAbility(String name, Category category)
	{
		super(name, category);
	}
	
	
	
	/*
	 *  Methods 
	 */
	public void hitEntity(PlayerEntity player, LivingEntity target)
	{
		this.onHitEntity.onHitEntity(player, target);
		this.stopContinuity(player);
	}
	
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnHitEntity extends Serializable
	{
		void onHitEntity(PlayerEntity player, LivingEntity target);
	}
}
