package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceAgeAbility;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class IceBlockAvalancheProjectile extends AbilityProjectileEntity{

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
		this.setDamage(30);
		this.setMaxLife(100);
		this.setPassThroughEntities();
		this.setCanGetStuckInGround();
		this.onTickEvent = this::onTickEvent;
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(ModEffects.FROZEN, 100, 1),
					new EffectInstance(Effects.MINING_FATIGUE, 100, 1)
			};		
		};
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	private void onTickEvent() {
		if (this.getFinalized() == false) {
			this.setCollisionSize(this.ticksExisted / 10);
		}
		if(this.isStuckInGround()) {
			this.setMotion(0, 0, 0);
		}
	}
	
	@Override
	public void registerData() {
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
	public void setFinalized(boolean val) {
		this.dataManager.set(FINALIZED, val);
	}
	public boolean getFinalized() {
		return this.dataManager.get(FINALIZED);
	}
	public void onEntityImpactEvent(LivingEntity e) {
		this.applySingleEntityCollision(e);
	}
	public void onBlockImpactEvent(BlockPos pos) {
		this.setCollisionSize(0);
		this.setMotion(0, 0, 0);
		if(!world.isRemote) {
			IceAgeAbility.PARTICLES.spawn(world, posX, posY, posZ, 0, 0, 0);
		}
	}
	
	//to make it so that only the entities hit by the avalanche move and not the avalanche itself
	   public void applySingleEntityCollision(Entity entityIn) {
		      if (!this.isRidingSameEntity(entityIn)) {
		         if (!entityIn.noClip && !this.noClip) {
		            double d0 = entityIn.posX - this.posX;
		            double d1 = entityIn.posZ - this.posZ;
		            double d2 = MathHelper.absMax(d0, d1);
		            if (d2 >= 0.01F) {
		               d2 = MathHelper.sqrt(d2);
		               d0 = d0 / d2;
		               d1 = d1 / d2;
		               double d3 = 1.0D / d2;
		               if (d3 > 1.0D) {
		                  d3 = 1.0D;
		               }

		               d0 = d0 * d3;
		               d1 = d1 * d3;
		               d0 = d0 * 0.05F;
		               d1 = d1 * 0.05F;
		               d0 = d0 * (1.0F - this.entityCollisionReduction);
		               d1 = d1 * (1.0F - this.entityCollisionReduction);
		               if (!entityIn.isBeingRidden()) {
		                  entityIn.addVelocity(d0, 0.0D, d1);
		               }
		            }

		         }
		      }
		   }
}
