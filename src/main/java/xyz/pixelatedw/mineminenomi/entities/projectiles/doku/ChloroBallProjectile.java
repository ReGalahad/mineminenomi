package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallCloudParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ChloroBallProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES1 = new ChloroBallParticleEffect();
	private static final ParticleEffect PARTICLES2 = new ChloroBallCloudParticleEffect();

	public ChloroBallProjectile(World world)
	{
		super(DokuProjectiles.CHLORO_BALL, world);
	}

	public ChloroBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public ChloroBallProjectile(World world, double x, double y, double z)
	{
		super(DokuProjectiles.CHLORO_BALL, world, x, y, z);
	}

	public ChloroBallProjectile(World world, LivingEntity player)
	{
		super(DokuProjectiles.CHLORO_BALL, world, player);

		this.setDamage(10);

		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 300, 0));
		this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
	}

	private void onBlockImpactEvent(BlockPos pos)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyHelper.randomWithRange(-3, 3);
			double offsetZ = WyHelper.randomWithRange(-3, 3);

			BlockPos location = new BlockPos(this.posX + offsetX, this.posY, this.posZ + offsetZ);

			if (this.world.getBlockState(location.down()).isSolid())
				AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.POISON, "air", "foliage");
		}

		PARTICLES1.spawn(this.world, this.posX, this.posY + 1, this.posZ, 0, 0, 0);
		
		ChloroBallCloudEntity smokeCloud = new ChloroBallCloudEntity(this.world);
		smokeCloud.setLife(30);
		smokeCloud.setLocationAndAngles(this.posX, (this.posY + 1), this.posZ, 0, 0);
		smokeCloud.setMotion(0, 0, 0);
		smokeCloud.setThrower((PlayerEntity) this.getThrower());
		this.world.addEntity(smokeCloud);		
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.DOKU);
				data.setLife(5);
				data.setSize(1.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
	
	public static class ChloroBallCloudEntity extends EntityCloud
	{
		public ChloroBallCloudEntity(World world)
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
					target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 2));
				
				if(this.ticksExisted % 2 == 0)
					PARTICLES2.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
			}
		}
	}
}
