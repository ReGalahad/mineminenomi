package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RaigoProjectile extends AbilityProjectileEntity
{
	public RaigoProjectile(World world)
	{
		super(GoroProjectiles.RAIGO, world);
	}

	public RaigoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public RaigoProjectile(World world, double x, double y, double z)
	{
		super(GoroProjectiles.RAIGO, world, x, y, z);
	}

	public RaigoProjectile(World world, LivingEntity player)
	{
		super(GoroProjectiles.RAIGO, world, player);

		this.setDamage(120);
		this.setMaxLife(256);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 30);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(30));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 25; i++)
			{
				ResourceLocation particleToUse = this.ticksExisted % 2 == 0 ? ModResources.GORO2 : ModResources.GORO;
				
				double offsetX = WyHelper.randomDouble() * 5;
				double offsetY = WyHelper.randomDouble();
				double offsetZ = WyHelper.randomDouble() * 5;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(particleToUse);
				data.setLife(20);
				data.setSize(7);
				
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
