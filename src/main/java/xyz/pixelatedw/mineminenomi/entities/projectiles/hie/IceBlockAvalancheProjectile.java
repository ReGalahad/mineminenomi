package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceAgeAbility;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class IceBlockAvalancheProjectile extends AbilityProjectileEntity
{
	private static final DataParameter<Boolean> FINALIZED = EntityDataManager.createKey(IceBlockAvalancheProjectile.class, DataSerializers.BOOLEAN);
	
	public IceBlockAvalancheProjectile(World world)
	{
		super(HieProjectiles.ICE_BLOCK_AVALANCHE, world);
	}

	public IceBlockAvalancheProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public IceBlockAvalancheProjectile(World world, double x, double y, double z)
	{
		super(HieProjectiles.ICE_BLOCK_AVALANCHE, world, x, y, z);
	}

	public IceBlockAvalancheProjectile(World world, LivingEntity player)
	{
		super(HieProjectiles.ICE_BLOCK_AVALANCHE, world, player);
		this.setDamage(40);
		this.setMaxLife(130);
		this.setPassThroughEntities();
		this.setCanGetStuckInGround();
		this.onTickEvent = this::onTickEvent;
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(ModEffects.FROZEN, 200, 1),
					new EffectInstance(Effects.MINING_FATIGUE, 100, 1)
			};
		};
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}

	private void onTickEvent()
	{
		if (this.getFinalized() == false)
		{
			this.setCollisionSize(this.ticksExisted / 10);
			if(this.ticksExisted > 3 * 20) {
				this.setFinalized(true);
			}

		}
		if (this.isStuckInGround())
		{
			this.setMotion(0, 0, 0);
		}
	}

	@Override
	public void registerData()
	{
		super.registerData();
		this.dataManager.register(FINALIZED, false);
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putBoolean("finalized", this.getFinalized());
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(FINALIZED, compound.getBoolean("finalized"));
	}

	public void setFinalized(boolean val)
	{
		this.dataManager.set(FINALIZED, val);
	}

	public boolean getFinalized()
	{
		return this.dataManager.get(FINALIZED);
	}

	public void onEntityImpactEvent(LivingEntity entity)
	{
		List<LivingEntity> targets = WyHelper.getEntitiesNear(this.getPosition(), this.world, this.getCollisionSize());
		
		for(LivingEntity target : targets)
		{
			Vec3d speed = WyHelper.propulsion(target, 1, 1);
			target.setMotion(-speed.x, 0.5, -speed.z);
			target.attackEntityFrom(this.source, this.getDamage() / 2);
			this.triggerEffects(target);
		}
		
		this.onBlockImpactEvent.onImpact(entity.getPosition());
	}

	public void onBlockImpactEvent(BlockPos pos)
	{
		System.out.println(this.getCollisionSize());
		this.setCollisionSize(0);
		this.setMotion(0, 0, 0);
		if (!this.world.isRemote)
		{
			IceAgeAbility.PARTICLES.spawn(world, posX, posY, posZ, 0, 0, 0);
		}
	}
}
