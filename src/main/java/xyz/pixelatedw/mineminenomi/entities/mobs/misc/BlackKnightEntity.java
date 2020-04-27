package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.GenericMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.GenericPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class BlackKnightEntity extends CreatureEntity
{
	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BlackKnightEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	public boolean isAggressive = true;
	public List<LivingEntity> forcedTargets = new ArrayList<LivingEntity>();
	
	public BlackKnightEntity(World world)
	{
		super(ModEntities.BLACK_KNIGHT, world);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(OWNER, null);
	}
	
    @Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageValue)
    {
    	if(damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == this.getOwner())
    		return false;
    	
    	return super.attackEntityFrom(damageSource, damageValue);
    }
    
    @Override
	public boolean attackEntityAsMob(Entity target)
    {
        float damage = (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
        int knockback = 0;

		if (target instanceof LivingEntity)
		{
			damage += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((LivingEntity) target).getCreatureAttribute());
			knockback += (float) EnchantmentHelper.getKnockbackModifier(this);
		}

        boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

        if (flag)
        {
            if (knockback > 0)
            {
                target.addVelocity(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * knockback * 0.5F);
                this.setMotion(this.getMotion().mul(0.6D, 1, 0.6D));
            }
        }

        return flag;
    }
    
	@Override
	public void tick()
	{
		if(!this.world.isRemote)
		{
			PlayerEntity owner = this.getOwner();
			
			if(owner == null)
			{
				this.remove();
				return;
			}
				
			if(this.getDistance(owner) > 10)
				this.getNavigator().tryMoveToEntityLiving(owner, 1.5);
			
			if(this.getDistance(owner) > 80)
				this.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
			
			IEntityStats ownerProps = EntityStatsCapability.get(owner);
			IDevilFruit ownerDFProps = DevilFruitCapability.get(owner);		
			List<LivingEntity> targetsList = this.isAggressive ? WyHelper.getEntitiesNear(this.getPosition(), this.world, 10, PlayerEntity.class, GenericMarineEntity.class, GenericPirateEntity.class, MonsterEntity.class) : !this.forcedTargets.isEmpty() ? this.forcedTargets : new ArrayList<LivingEntity>();
			LivingEntity target = null;

			if(!ownerDFProps.getDevilFruit().equalsIgnoreCase("ito_ito"))
				this.remove();
			
			if(!targetsList.isEmpty())
			{
				if(targetsList.contains(owner))
					targetsList.remove(owner);
				
				if(ownerProps.isMarine())
					targetsList = targetsList.stream().filter(x -> !(x instanceof GenericMarineEntity)).collect(Collectors.toList());
								
				target = targetsList.stream().findFirst().orElse(null);	
			}

			if(target != null)
				this.setAttackTarget(target);
			
			if(!this.forcedTargets.isEmpty())
			{
				Iterator it = this.forcedTargets.iterator();
				while(it.hasNext())
				{
					LivingEntity forcedTarget = (LivingEntity) it.next();
					if(forcedTarget == null || !forcedTarget.isAlive())
						it.remove();
				}
			}
		}
		
		super.tick();
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		if (this.dataManager.get(OWNER) != null)
		{
			compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
		}
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
	}

	public void setOwner(UUID uuid)
	{
		this.dataManager.set(OWNER, Optional.of(uuid));
	}

	public PlayerEntity getOwner()
	{
		return this.getDataManager().get(OWNER).isPresent() ? this.world.getPlayerByUuid(this.getDataManager().get(OWNER).get()) : null;
	}

}
