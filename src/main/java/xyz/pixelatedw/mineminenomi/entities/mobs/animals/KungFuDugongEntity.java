package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class KungFuDugongEntity extends AnimalEntity
{
	private static final AttributeModifier RAGE_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final Item[] FOOD = new Item[] { Items.COOKED_BEEF, Items.COOKED_CHICKEN, Items.COOKED_COD, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_RABBIT, Items.COOKED_SALMON };

	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> IS_ENRAGED = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_HAPPY = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_TRAINING = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);

	public KungFuDugongEntity(World world)
	{
		super(ModEntities.KUNG_FU_DUGONG, world);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, false));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
		
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(OWNER, Optional.empty());
		this.getDataManager().register(IS_ENRAGED, false);
		this.getDataManager().register(IS_SITTING, false);
		this.getDataManager().register(IS_HAPPY, false);
		this.getDataManager().register(IS_TRAINING, false);
	}

	@Override
	public void livingTick()
	{	
		super.livingTick();
		
		if (!this.world.isRemote)
		{
			boolean flagOwnerNearby = this.getOwner() != null && this.getDistance(this.getOwner()) < 10;
			boolean flagTamed = this.isTamed();
			boolean flagHasNoTarget = this.getAttackTarget() == null;
			boolean flagSitting = flagHasNoTarget && this.isSitting();
			boolean flagHealth = this.getHealth() > this.getMaxHealth() / 3;
			
			if (flagOwnerNearby && flagTamed && flagSitting && flagHealth)
				this.setHappy(true);
			else
				this.setHappy(false);
			
			if(!flagHasNoTarget)
			{
				this.setHappy(false);
				this.setTraining(false);
			}
			
			if (flagHasNoTarget && this.ticksExisted % (250 + WyHelper.randomWithRange(0, 250)) == 0)
				this.setTraining(!this.isTraining());
			
			if (flagSitting || this.isTraining())
				this.getNavigator().clearPath();
						
			if (this.getAttackTarget() == this.getOwner())
			{
				this.setAttackTarget(null);
				this.setRevengeTarget(null);
				this.getNavigator().clearPath();
			}

			if (flagTamed)
			{
				if (flagSitting)
				{
					List<PlayerEntity> players = WyHelper.getEntitiesNear(this.getPosition(), this.world, 3, PlayerEntity.class);
					for (PlayerEntity player : players)
					{
						if (player != this.getOwner())
							continue;
						
						this.lookAt(EntityAnchorArgument.Type.EYES, player.getEyePosition(0));
						this.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 40, 0, false, false));
					}
				}
				else
				{
					if (this.getDistance(this.getOwner()) > 10)
						this.getNavigator().tryMoveToEntityLiving(this.getOwner(), 1.5);
	
					if (this.getDistance(this.getOwner()) > 80)
						this.setPositionAndUpdate(this.getOwner().posX, this.getOwner().posY, this.getOwner().posZ);
				}
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float amount)
	{
		super.attackEntityFrom(damageSource, amount);

		Entity entity = damageSource.getTrueSource();
		
		if (this.isTamed() && entity instanceof LivingEntity && entity != this.getOwner())
			this.setAttackTarget((LivingEntity) entity);
		else if(!this.isTamed())
		{
			if(!this.world.isRemote)
			{
				if (entity instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity) entity;
					
					if(!player.getHeldItem(player.getActiveHand()).isEmpty())
					{
						this.setEnraged(true);
						for (int i = 0; i < 5; ++i)
						{
				            double offsetX = this.rand.nextGaussian() * 0.02D;
				            double offsetY = this.rand.nextGaussian() * 0.02D;
				            double offsetZ = this.rand.nextGaussian() * 0.02D;
				            
							((ServerWorld) world).spawnParticle(ParticleTypes.ANGRY_VILLAGER, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0);
						}
					}
					else
					{
						if(!this.isEnraged() && this.getHealth() < this.getMaxHealth() / 2)
						{
							for (int i = 0; i < 5; ++i)
							{
					            double offsetX = this.rand.nextGaussian() * 0.02D;
					            double offsetY = this.rand.nextGaussian() * 0.02D;
					            double offsetZ = this.rand.nextGaussian() * 0.02D;
		
								((ServerWorld) world).spawnParticle(ParticleTypes.HEART, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0);
							}
							this.setOwner(player.getUniqueID());
						}
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		if (this.isTamed() && player == this.getOwner() && !player.world.isRemote && hand == Hand.MAIN_HAND)
		{
			ItemStack stack = player.getHeldItem(player.getActiveHand() != null ? player.getActiveHand() : Hand.MAIN_HAND);
			
			if (!stack.isEmpty() && this.getHealth() < this.getMaxHealth())
			{
				Optional<Item> food = Arrays.stream(FOOD).filter(x -> stack.getItem() == x).findFirst();

				if (food.isPresent())
				{
					stack.shrink(1);
					this.heal(4);
					for (int i = 0; i < 5; ++i)
					{
			            double offsetX = this.rand.nextGaussian() * 0.02D;
			            double offsetY = this.rand.nextGaussian() * 0.02D;
			            double offsetZ = this.rand.nextGaussian() * 0.02D;

						((ServerWorld) world).spawnParticle(ParticleTypes.HEART, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0);
					}
					return true;
				}
			}
			else if(stack.isEmpty())
			{
				this.setSiting(!this.isSitting());
				this.setAttackTarget((LivingEntity)null);
				return true;
			}
		}

		return false;
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		if (this.dataManager.get(OWNER).isPresent())
			compound.putString("owner", this.dataManager.get(OWNER).get().toString());
		compound.putBoolean("isEnraged", this.dataManager.get(IS_ENRAGED));
		compound.putBoolean("isSitting", this.dataManager.get(IS_SITTING));
		compound.putBoolean("isHappy", this.dataManager.get(IS_HAPPY));
		compound.putBoolean("isTraining", this.dataManager.get(IS_TRAINING));
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		if(!WyHelper.isNullOrEmpty(compound.getString("owner")))
			this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("owner"))));
		this.dataManager.set(IS_ENRAGED, compound.getBoolean("isEnraged"));
		this.dataManager.set(IS_SITTING, compound.getBoolean("isSitting"));
		this.dataManager.set(IS_HAPPY, compound.getBoolean("isHappy"));
		this.dataManager.set(IS_TRAINING, compound.getBoolean("isTraining"));
	}

	public boolean isTraining()
	{
		return this.dataManager.get(IS_TRAINING);
	}

	public void setTraining(boolean value)
	{
		this.dataManager.set(IS_TRAINING, value);
	}
	
	public boolean isHappy()
	{
		return this.dataManager.get(IS_HAPPY);
	}

	public void setHappy(boolean value)
	{
		this.dataManager.set(IS_HAPPY, value);
	}
	
	public boolean isSitting()
	{
		return this.dataManager.get(IS_SITTING);
	}

	public void setSiting(boolean value)
	{
		this.dataManager.set(IS_SITTING, value);
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
		if(value)
			attr.applyModifier(RAGE_MODIFIER);
	}

	public void setOwner(UUID uuid)
	{
		this.dataManager.set(OWNER, Optional.of(uuid));
	}

	public PlayerEntity getOwner()
	{
		return this.getDataManager().get(OWNER).isPresent() ? this.world.getPlayerByUuid(this.getDataManager().get(OWNER).get()) : null;
	}

	public boolean isTamed()
	{
		return this.getOwner() != null;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}
}
