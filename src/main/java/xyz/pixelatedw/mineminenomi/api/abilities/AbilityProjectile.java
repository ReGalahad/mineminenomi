package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;

public class AbilityProjectile extends ThrowableEntity
{
	private int life = 60;
	private int maxLife = 60;
	private double collisionSize = 1;
	private boolean isPhysical = false;
	private boolean canPassThroughBlocks = false;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnImpact onImpactEvent = (hit) -> {};
	protected IOnTick onTickEvent = () -> {};

	public AbilityProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public AbilityProjectile(EntityType type, World world, double x, double y, double z)
	{
		super(type, x, y, z, world);
	}

	public AbilityProjectile(EntityType type, World world, LivingEntity player)
	{
		super(type, player, world);
		this.maxLife = life;
		
		this.setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
		double motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
		double motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
		double motionY = -MathHelper.sin((this.rotationPitch) / 180.0F * (float) Math.PI) * 0.4;
		this.setMotion(new Vec3d(motionX, motionY, motionZ));
		this.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
	}
	
	public AbilityProjectile(EntityType type, World world, LivingEntity player, AbilityAttribute attr) 
	{
		super(type, player, world);
		WyDebug.error("Wrong constructor when spawning " + type.getName());
	}

	@Deprecated
	public void tasksImapct(RayTraceResult hit) {};

	@Override
	public void tick()
	{
		super.tick();
		if (this.life <= 0)
		{
			this.life = maxLife;
			this.remove();
		}
		else
			this.life--;

		Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
		Vec3d vec3 = new Vec3d(this.posX + this.getMotion().x, this.posY + this.getMotion().y, this.posZ + this.getMotion().z);
		RayTraceResult hit = this.world.rayTraceBlocks(new RayTraceContext(vec3, vec31, BlockMode.OUTLINE, FluidMode.ANY, this));

		double sizeX = this.collisionSize;
		double sizeY = this.collisionSize;
		double sizeZ = this.collisionSize;

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

		if (hit.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult) hit).getEntity() instanceof LivingEntity)
			this.onImpact(hit);
		
		this.onTickEvent.onTick();
	}

	@Override
	protected void onImpact(RayTraceResult hit)
	{
		if(!this.world.isRemote)
		{
			if(hit.getType() == RayTraceResult.Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
	
				if(entityHit.getEntity() instanceof LivingEntity)
				{
					LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
					IDevilFruit hitDevilFruitProps = DevilFruitCapability.get(hitEntity);
					IHakiData throwerHakiDataProps = HakiDataCapability.get(this.getThrower());
					
					if(hitDevilFruitProps.isLogia() && this.isPhysical && !throwerHakiDataProps.hasBusoHakiActive())
						return;
											
					this.onImpactEvent.onImpact(entityHit);
					this.remove();
				}
			}
			else if(hit.getType() == RayTraceResult.Type.BLOCK)
			{
				BlockRayTraceResult blockHit = (BlockRayTraceResult) hit;
	
				if (!this.canPassThroughBlocks)
				{										
					this.onImpactEvent.onImpact(blockHit);
					this.remove();
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
		private AbilityRenderer.Factory factory;
		
		public Data(EntityType type, Class<? extends Entity> clz)
		{
			this.type = type;
			this.entityClass = clz;
		}
		
		public Data(EntityType type, Class<? extends Entity> clz, AbilityRenderer.Factory factory)
		{
			this.type = type;
			this.entityClass = clz;
			this.factory = factory;
		}
		
		public EntityType getEntityType()
		{
			return this.type;
		}
		
		public Class getEntityClass()
		{
			return this.entityClass;
		}
		
		public AbilityRenderer.Factory getFactory()
		{
			return this.factory;
		}
	}

	
	/*
	 * 	Setters/Getters
	 */
	public int getLife()
	{
		return this.life;
	}
	
	public int getMaxLife()
	{
		return this.maxLife;
	}
	
	public void setMaxCooldown(int cooldown)
	{
		this.maxLife = cooldown * 20;
		this.life = this.maxLife;
	}
	
	public void setPhysical()
	{
		this.isPhysical = true;
	}
	
	public void setPassThroughBlocks()
	{
		this.canPassThroughBlocks = true;
	}
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnImpact extends Serializable
	{
		void onImpact(RayTraceResult hit);
	}
	
	public interface IOnTick extends Serializable
	{
		void onTick();
	}
	
}