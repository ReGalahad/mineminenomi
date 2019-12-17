package xyz.pixelatedw.mineminenomi.api.abilities;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class AbilityProjectile extends ThrowableEntity
{
	public int ticks, maxticks;
	private AbilityAttribute attr;
	private LivingEntity user;

	public AbilityProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public AbilityProjectile(EntityType type, World world, double x, double y, double z)
	{
		super(type, x, y, z, world);
	}

	public AbilityProjectile(EntityType type, World world, LivingEntity player, AbilityAttribute attr)
	{
		super(type, player, world);
		this.attr = attr;
		this.ticks = attr.getProjectileTicks();
		this.maxticks = ticks;
		this.user = player;
		
		if (this.attr != null)
		{
			this.setLocationAndAngles(this.user.posX, this.user.posY + this.user.getEyeHeight(), this.user.posZ, this.user.rotationYaw, this.user.rotationPitch);
			double motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
			double motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
			double motionY = -MathHelper.sin((this.rotationPitch) / 180.0F * (float) Math.PI) * 0.4;
			this.setMotion(new Vec3d(motionX, motionY, motionZ));
			this.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		}

	}

	public AbilityAttribute getAttribute()
	{
		return this.attr;
	}

	public void tasksImapct(RayTraceResult hit) {};

	@Override
	public void tick()
	{
		super.tick();
		if (this.attr != null)
		{
			if (ticks <= 0)
			{
				ticks = maxticks;
				this.remove();
			}
			else
				ticks--;
		}
		
		if(this.attr != null)
		{			
			Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
			Vec3d vec3 = new Vec3d(this.posX + this.getMotion().x, this.posY + this.getMotion().y, this.posZ + this.getMotion().z);
			RayTraceResult hit = this.world.rayTraceBlocks(new RayTraceContext(vec3, vec31, BlockMode.OUTLINE, FluidMode.ANY, this));

			double sizeX = this.attr.getProjectileCollisionSizes()[0];
			double sizeY = this.attr.getProjectileCollisionSizes()[1];
			double sizeZ = this.attr.getProjectileCollisionSizes()[2];
						
			AxisAlignedBB aabb = new AxisAlignedBB(this.posX, this.posY, this.posZ, this.posX, this.posY, this.posZ).grow(sizeX, sizeY, sizeZ);
			List list = world.getEntitiesWithinAABB(LivingEntity.class, aabb);
			
			Entity entity = null;
			
			for (int i = 0; i < list.size(); ++i)
			{
				Entity target = (Entity) list.get(i);
				
				if (target.canBeCollidedWith() && (target != this.getThrower() || this.ticksExisted >= 5))
				{					
					entity = target;
				}
			}
			
			if (entity != null)
				hit = new EntityRayTraceResult(entity);
			
			if (hit.getType() == Type.ENTITY && ((EntityRayTraceResult) hit).getEntity() instanceof LivingEntity)
				this.onImpact(hit);
		}
	}

	@Override
	protected void onImpact(RayTraceResult hit)
	{
		if(!this.world.isRemote && this.attr != null)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
	
				if(entityHit.getEntity() instanceof LivingEntity)
				{
					LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
					IDevilFruit hitDevilFruitProps = DevilFruitCapability.get(hitEntity);
					IHakiData throwerHakiDataProps = HakiDataCapability.get(this.getThrower());
					
					if(hitDevilFruitProps.isLogia() && this.getAttribute().isProjectilePhysical() && !throwerHakiDataProps.hasBusoHakiActive())
						return;
						
					if (this.attr.getPotionEffectsForProjectile() != null)
						for (EffectInstance p : this.attr.getPotionEffectsForProjectile())
							hitEntity.addPotionEffect(new EffectInstance(p));
					
					if (this.attr.getProjectileDamage() > 0)
						hitEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.attr.getProjectileDamage());
					
					if (this.attr.getProjectileExplosionPower() > 0)
					{
						float power = this.attr.getProjectileExplosionPower();
						
						AbilityExplosion explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 2);
						explosion.setExplosionSound(true);
						explosion.setDamageOwner(false);
						explosion.setDestroyBlocks(this.attr.canProjectileExplosionDestroyBlocks());
						explosion.setFireAfterExplosion(this.attr.canProjectileExplosionSetFire());
						explosion.setSmokeParticles(new CommonExplosionParticleEffect(this.attr.getProjectileExplosionPower()));
						explosion.setDamageEntities(true);
						explosion.doExplosion();						
					}
					
					this.tasksImapct(hit);		
					this.remove();
				}
			}
			else if(hit.getType() == Type.BLOCK)
			{
				BlockRayTraceResult blockHit = (BlockRayTraceResult) hit;
	
				Material hitMat = this.world.getBlockState(new BlockPos(blockHit.getHitVec().getX(), blockHit.getHitVec().getY(), blockHit.getHitVec().getZ())).getMaterial();
				
				if (this.attr.getProjectileExplosionPower() > 0)
				{
					float power = this.attr.getProjectileExplosionPower();
					
					AbilityExplosion explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, this.attr.getProjectileExplosionPower());
					explosion.setExplosionSound(true);
					explosion.setDamageOwner(false);
					explosion.setDestroyBlocks(this.attr.canProjectileExplosionDestroyBlocks());
					explosion.setFireAfterExplosion(this.attr.canProjectileExplosionSetFire());
					explosion.setSmokeParticles(new CommonExplosionParticleEffect(this.attr.getProjectileExplosionPower()));
					explosion.setDamageEntities(true);
					explosion.doExplosion();
					
					//this.world.createExplosion(this.getThrower(), this.posX, this.posY, this.posZ, power, Mode.DESTROY);
				}
				
				if (!this.attr.canProjectileMoveThroughBlocks())
				{										
					this.tasksImapct(hit);
					this.remove();
				}
				else
				{
					this.tasksImapct(hit);
				}
			}
		}
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.001F;
	}

	@Override
	public boolean isImmuneToExplosions()
	{
		return true;
	}

	@Override
	protected void registerData()
	{
		
	}
	
	@Override
    public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
    }
	
	public static class Data
	{
		private EntityType type;
		private Class entityClass;
		
		public Data(EntityType type, Class<? extends Entity> clz)
		{
			this.type = type;
			this.entityClass = clz;
		}
		
		public EntityType getEntityType()
		{
			return type;
		}
		
		public Class getEntityClass()
		{
			return this.entityClass;
		}
	}

}