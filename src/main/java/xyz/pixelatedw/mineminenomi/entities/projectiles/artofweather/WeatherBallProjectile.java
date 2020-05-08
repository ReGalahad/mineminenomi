package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class WeatherBallProjectile extends AbilityProjectileEntity
{
	protected Item weaponUsed;
	
	public WeatherBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}
	
	public WeatherBallProjectile(EntityType type, World world, double x, double y, double z)
	{
		super(type, world, x, y, z);
	}
	
	public WeatherBallProjectile(EntityType type, World world, LivingEntity player)
	{
		super(type, world, player);
	}
	
	public void tick()
	{
		super.tick();

		this.setMotion(this.getMotion().x / 1.5, this.getMotion().y, this.getMotion().z / 1.5);
		if(this.ticksExisted < 100)
			this.getMotion().add(0, 0.25, 0);
		else
			this.setMotion(0, 0, 0);
	}
	
	public Item getWeaponUsed()
	{
		return this.weaponUsed;
	}
}
