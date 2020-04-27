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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.GenericBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.GenericMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.GenericPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.WyHelper;

public class DoppelmanEntity extends CreatureEntity
{

	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(DoppelmanEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Integer> SHADOWS = EntityDataManager.createKey(DoppelmanEntity.class, DataSerializers.VARINT);
	public boolean isAggressive = true;
	public List<LivingEntity> forcedTargets = new ArrayList<LivingEntity>();
	
	public DoppelmanEntity(World world)
	{
		super(ModEntities.DOPPELMAN, world);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookAtGoal(this, GenericMarineEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookAtGoal(this, GenericPirateEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookAtGoal(this, GenericBanditEntity.class, 8.0F));
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
		this.dataManager.register(SHADOWS, 0);
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
        float damage = (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() + (this.getShadows() * 4);
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
			if(this.getOwner() == null)
			{
				this.remove();
				return;
			}
				
			if(this.getDistance(this.getOwner()) > 10)
				this.getNavigator().tryMoveToEntityLiving(this.getOwner(), 1.5);
			
			if(this.getDistance(this.getOwner()) > 80)
				this.setPositionAndUpdate(this.getOwner().posX, this.getOwner().posY, this.getOwner().posZ);
			
			IEntityStats ownerProps = EntityStatsCapability.get(this.getOwner());
			IDevilFruit ownerDFProps = DevilFruitCapability.get(this.getOwner());		
			List<LivingEntity> doppelmanAttackList = this.isAggressive ? WyHelper.getEntitiesNear(this.getPosition(), this.world, 10, PlayerEntity.class, GenericMarineEntity.class, GenericPirateEntity.class, MonsterEntity.class) : !forcedTargets.isEmpty() ? forcedTargets : new ArrayList<LivingEntity>();
			LivingEntity target = null;

			if(!ownerDFProps.getDevilFruit().equalsIgnoreCase("kage_kage"))
				this.remove();
			
			if(!doppelmanAttackList.isEmpty())
			{
				if(doppelmanAttackList.contains(this.getOwner()))
					doppelmanAttackList.remove(this.getOwner());
				
				if(ownerProps.isMarine())
					doppelmanAttackList = doppelmanAttackList.stream().filter(x -> !(x instanceof GenericMarineEntity)).collect(Collectors.toList());
								
				target = doppelmanAttackList.stream().findFirst().orElse(null);	
			}

			if(target != null)
				this.setAttackTarget(target);
			
			if(!forcedTargets.isEmpty())
			{
				Iterator it = forcedTargets.iterator();
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
	public boolean processInteract(PlayerEntity player, Hand hand)
    {
    	if(player == this.getOwner())
    	{
        	ItemStack itemStack = player.getHeldItem(hand);

    		if(itemStack != null && itemStack.getItem() == ModItems.SHADOW && itemStack.getCount() >= 10 && this.getShadows() < 6)
    		{
    			itemStack.setCount(itemStack.getCount() - 10);
    			this.addShadow();
    		}
    	}
    	
    	return false;
    }

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		if (this.dataManager.get(OWNER) != null)
			compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
		compound.putInt("ShadowsAte", this.dataManager.get(SHADOWS));
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
		this.dataManager.set(SHADOWS, compound.getInt("ShadowsAte"));
	}

	public void setOwner(UUID uuid)
	{
		this.dataManager.set(OWNER, Optional.of(uuid));
	}

	public PlayerEntity getOwner()
	{
		return this.getDataManager().get(OWNER).isPresent() ? this.world.getPlayerByUuid(this.getDataManager().get(OWNER).get()) : null;
	}
	
	public void addShadow()
	{
		this.dataManager.set(SHADOWS, this.dataManager.get(SHADOWS) + 1);
	}
	
	public void setShadow(int value)
	{
		this.dataManager.set(SHADOWS, value);
	}
	
	public int getShadows()
	{
		return this.dataManager.get(SHADOWS);
	}
}
