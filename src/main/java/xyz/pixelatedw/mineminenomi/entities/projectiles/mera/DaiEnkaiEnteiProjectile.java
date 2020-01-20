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

public class DaiEnkaiEnteiProjectile extends AbilityProjectileEntity
{
	public DaiEnkaiEnteiProjectile(World world)
	{
		super(MeraProjectiles.DAI_ENKAI_ENTEI, world);
	}

	public DaiEnkaiEnteiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DaiEnkaiEnteiProjectile(World world, double x, double y, double z)
	{
		super(MeraProjectiles.DAI_ENKAI_ENTEI, world, x, y, z);
	}

	public DaiEnkaiEnteiProjectile(World world, LivingEntity player)
	{
		super(MeraProjectiles.DAI_ENKAI_ENTEI, world, player);

		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{
		ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 7);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(7));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 20; i++)
			{
				double offsetX = WyMathHelper.randomDouble();
				double offsetY = WyMathHelper.randomDouble();
				double offsetZ = WyMathHelper.randomDouble();

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(6);
				data.setSize(1.3F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}

			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyMathHelper.randomDouble();
				double offsetY = WyMathHelper.randomDouble();
				double offsetZ = WyMathHelper.randomDouble();

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MOKU);
				data.setLife(4);
				data.setSize(1.2F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
