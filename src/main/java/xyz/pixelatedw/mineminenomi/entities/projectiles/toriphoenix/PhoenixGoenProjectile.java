package xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PhoenixGoenProjectile extends AbilityProjectileEntity
{
	public PhoenixGoenProjectile(World world)
	{
		super(ToriPhoenixProjectiles.PHOENIX_GOEN, world);
	}

	public PhoenixGoenProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public PhoenixGoenProjectile(World world, double x, double y, double z)
	{
		super(ToriPhoenixProjectiles.PHOENIX_GOEN, world, x, y, z);
	}

	public PhoenixGoenProjectile(World world, LivingEntity player)
	{
		super(ToriPhoenixProjectiles.PHOENIX_GOEN, world, player);
		
		this.setDamage(1);
		this.setCanGetStuckInGround();
		this.setMaxLife(45);
		this.setChangeHurtTime(true);
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyHelper.randomDouble() / 2;
			double offsetY = WyHelper.randomDouble() / 2;
			double offsetZ = WyHelper.randomDouble() / 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.BLUE_FLAME);
			data.setLife(5);
			data.setSize(0.7F);
			WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
		}
	}
}