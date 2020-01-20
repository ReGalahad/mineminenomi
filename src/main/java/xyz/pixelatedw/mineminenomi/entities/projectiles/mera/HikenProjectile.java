package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class HikenProjectile extends AbilityProjectileEntity
{
	public HikenProjectile(World world)
	{
		super(MeraProjectiles.HIKEN, world);
	}

	public HikenProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public HikenProjectile(World world, double x, double y, double z)
	{
		super(MeraProjectiles.HIKEN, world, x, y, z);
	}

	public HikenProjectile(World world, LivingEntity player)
	{
		super(MeraProjectiles.HIKEN, world, player);

		this.setDamage(20);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{		
		ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 15; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(10);
				data.setSize(1.3F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}

			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MOKU);
				data.setLife(7);
				data.setSize(1.2F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
