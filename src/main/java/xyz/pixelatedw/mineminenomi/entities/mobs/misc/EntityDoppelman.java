package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityGenericBandit;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityGenericMarine;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityGenericPirate;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;

public class EntityDoppelman extends CreatureEntity
{

	private PlayerEntity owner;
	public boolean isAggressive = true;
	public List<LivingEntity> forcedTargets = new ArrayList<LivingEntity>();
	
	public EntityDoppelman(World world)
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
		this.goalSelector.addGoal(5, new LookAtGoal(this, EntityGenericMarine.class, 8.0F));
		this.goalSelector.addGoal(5, new LookAtGoal(this, EntityGenericPirate.class, 8.0F));
		this.goalSelector.addGoal(5, new LookAtGoal(this, EntityGenericBandit.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
	}
	
    @Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageValue)
    {
    	if(damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == this.owner)
    		return false;
    	
    	return super.attackEntityFrom(damageSource, damageValue);
    }
    
    @Override
	public boolean attackEntityAsMob(Entity target)
    {
        float f = (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() + (EntityStatsCapability.get(this).getDoriki() * 4);
        int i = 0;

       /* if (target instanceof LivingEntity)
        {
            f += EnchantmentHelper.getEnchantmentModifierDamage(this, (LivingEntity)target);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)target);
        }*/

        boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0)
            {
                target.addVelocity(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * i * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * i * 0.5F);
                this.setMotion(this.getMotion().mul(0.6D, 1, 0.6D));
            }
        }

        return flag;
    }
    
	@Override
	public void tick()
	{
		//System.out.println(EntityStatsCapability.get(this).getDoriki());
		if(!this.world.isRemote)
		{
			if(this.owner == null)
			{
				this.remove();
				return;
			}
				
			if(this.getDistance(this.owner) > 10)
				this.getNavigator().tryMoveToEntityLiving(owner, 1.5);
			
			if(this.getDistance(this.owner) > 80)
				this.setPositionAndUpdate(this.owner.posX, this.owner.posY, this.owner.posZ);
			
			IEntityStats ownerProps = EntityStatsCapability.get(this.owner);
			IDevilFruit ownerDFProps = DevilFruitCapability.get(this.owner);		
			List<LivingEntity> doppelmanAttackList = this.isAggressive ? WyHelper.getEntitiesNear(this.getPosition(), this.world, 10, PlayerEntity.class, EntityGenericMarine.class, EntityGenericPirate.class) : !forcedTargets.isEmpty() ? forcedTargets : new ArrayList<LivingEntity>();
			LivingEntity target = null;

			if(!ownerDFProps.getDevilFruit().equalsIgnoreCase("kagekage"))
				this.remove();
			
			if(!doppelmanAttackList.isEmpty())
			{
				if(doppelmanAttackList.contains(this.owner))
					doppelmanAttackList.remove(this.owner);
				
				if(ownerProps.isMarine())
					doppelmanAttackList = doppelmanAttackList.stream().filter(x -> !(x instanceof EntityGenericMarine)).collect(Collectors.toList());
								
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
    	if(player == this.owner)
    	{
        	ItemStack itemStack = player.getHeldItem(hand);
    		IEntityStats props = EntityStatsCapability.get(this);

    		if(itemStack != null && itemStack.getItem() == ModItems.SHADOW && itemStack.getCount() >= 10 && props.getDoriki() < 6)
    		{
    			itemStack.setCount(itemStack.getCount() - 10);
    			props.alterDoriki(1);
    			ModNetwork.sendToAll(new SEntityStatsSyncPacket(player.getEntityId(), props));
    		}
    	}
    	
    	return false;
    }

	public void setOwner(PlayerEntity player) {this.owner = player;}
	public PlayerEntity getOwner() {return this.owner;}
}
