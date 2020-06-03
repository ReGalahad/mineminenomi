package xyz.pixelatedw.mineminenomi.entities.projectiles.gura;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
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

public class KaishinProjectile extends AbilityProjectileEntity
{
	public KaishinProjectile(World world)
	{
		super(GuraProjectiles.KAISHIN, world);
	}

	public KaishinProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public KaishinProjectile(World world, double x, double y, double z)
	{
		super(GuraProjectiles.KAISHIN, world, x, y, z);
	}

	public KaishinProjectile(World world, LivingEntity player)
	{
		super(GuraProjectiles.KAISHIN, world, player);

		this.setDamage(50);
		this.setCanGetStuckInGround();
		this.setPassThroughEntities();

		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		for (int i = 0; i < 3; i++)
		{
			if(i % 2 == 0)
			{
				((ServerWorld) this.world).spawnParticle
				(
					ParticleTypes.EXPLOSION, 
					this.posX + (WyHelper.randomDouble() * 1.5),
					this.posY + (WyHelper.randomDouble() * 1.5),
					this.posZ + (WyHelper.randomDouble() * 1.5),
					1,
					0.0D, 0.0D, 0.0D,
					0
				);
			}
			else
			{
				double offsetX = WyHelper.randomDouble() * 2;
				double offsetY = WyHelper.randomDouble() * 2;
				double offsetZ = WyHelper.randomDouble() * 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.GURA2);
				data.setLife(10);
				data.setSize(7F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 4);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}
