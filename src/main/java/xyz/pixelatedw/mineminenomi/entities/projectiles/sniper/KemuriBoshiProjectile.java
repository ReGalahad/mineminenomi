package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KemuriBoshiProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new KemuriBoshiParticleEffect();
	
	public KemuriBoshiProjectile(World world)
	{
		super(SniperProjectiles.KEMURI_BOSHI, world);
	}

	public KemuriBoshiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public KemuriBoshiProjectile(World world, double x, double y, double z)
	{
		super(SniperProjectiles.KEMURI_BOSHI, world, x, y, z);
	}

	public KemuriBoshiProjectile(World world, LivingEntity player)
	{
		super(SniperProjectiles.KEMURI_BOSHI, world, player);

		this.setDamage(3);
		this.setPhysical();

		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}

	private void onBlockImpactEvent(BlockPos hit)
	{
		KemuriBoshiCloudEntity smokeCloud = new KemuriBoshiCloudEntity(this.world);
		smokeCloud.setLife(100);
		smokeCloud.setLocationAndAngles(this.posX, (this.posY + 1), this.posZ, 0, 0);
		smokeCloud.setMotion(0, 0, 0);
		smokeCloud.setThrower((PlayerEntity) this.getThrower());
		this.world.addEntity(smokeCloud);		
	}
	
	public static class KemuriBoshiCloudEntity extends EntityCloud
	{
		public KemuriBoshiCloudEntity(World world)
		{
			super(world);
		}
		
		@Override
		public void tick()
		{
			super.tick();
			if(!this.world.isRemote)
			{				
				for(LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(this.getPosition(), this.world, 5))
					target.addPotionEffect(new EffectInstance(Effects.POISON, 100, 1));
				
				if(this.ticksExisted % 2 == 0)
					PARTICLES.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
			}
		}
	}
}