package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.GapCloserGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.lapahn.LapahnJumpGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.lapahn.LapahnRageGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.GenericBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.GenericMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.GenericPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class LapahnEntity extends GenericNewEntity
{
	private static final AttributeModifier RAGE_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final DataParameter<Boolean> IS_ENRAGED = EntityDataManager.createKey(LapahnEntity.class, DataSerializers.BOOLEAN);
	private static final ParticleEffect PARTICLES = new GreatStompParticleEffect();
	
	public LapahnEntity(World worldIn)
	{
		super(ModEntities.LAPAHN, worldIn, null);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(1, new LapahnJumpGoal(this));
		this.goalSelector.addGoal(1, new LapahnRageGoal(this));
		this.goalSelector.addGoal(2, new GapCloserGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GenericPirateEntity.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GenericBanditEntity.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GenericMarineEntity.class, true));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(55.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		
		this.setDoriki(10);
		this.setBelly(0);
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(IS_ENRAGED, false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putBoolean("isEnraged", this.dataManager.get(IS_ENRAGED));
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.dataManager.set(IS_ENRAGED, compound.getBoolean("isEnraged"));
	}
	
	public boolean isEnraged()
	{
		return this.dataManager.get(IS_ENRAGED);
	}

	public void setEnraged(boolean value)
	{
		this.dataManager.set(IS_ENRAGED, value);
		IAttributeInstance attr = this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		attr.removeModifier(RAGE_MODIFIER);
		attr.applyModifier(RAGE_MODIFIER);
	}
	
	@Override
	public void fall(float distance, float damageMultiplier)
	{
		if (distance > 5 && !this.world.isRemote)
		{
			PARTICLES.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
			List<LivingEntity> targets = WyHelper.getEntitiesNear(this.getPosition(), this.world, 5);
			targets.remove(this);
			
			float damage = 2 + (float)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
			for (LivingEntity entity : targets)
			{
				if (!(entity instanceof LapahnEntity))
				{
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
					entity.setMotion(0, 0.5, 0);
					entity.velocityChanged = true;
				}
			}
		}
	}
}
