package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

public class SangoProjectile extends AbilityProjectileEntity
{
	public SangoProjectile(World world)
	{
		super(GoroProjectiles.SANGO, world);
	}

	public SangoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SangoProjectile(World world, double x, double y, double z)
	{
		super(GoroProjectiles.SANGO, world, x, y, z);
	}

	public SangoProjectile(World world, LivingEntity player)
	{
		super(GoroProjectiles.SANGO, world, player);

		this.setDamage(15);
		this.setMaxLife(128);
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				ResourceLocation particleToUse = this.ticksExisted % 2 == 0 ? ModResources.GORO2 : ModResources.GORO;
				
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(particleToUse);
				data.setLife(5);
				data.setSize(2);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
