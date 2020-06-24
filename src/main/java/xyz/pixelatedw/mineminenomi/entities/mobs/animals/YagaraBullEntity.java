package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class YagaraBullEntity extends AnimalEntity implements IDynamicRenderer
{
	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.VARINT);

	private static final Item[] SADDLES = new Item[] { Items.SADDLE };
	private static final Item[] TAMING_FOOD = new Item[] { Items.TROPICAL_FISH };
	private static final Item[] FOOD = new Item[] { Items.COOKED_COD, Items.COOKED_SALMON, Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH };

	private String[] textures = new String[] { "yagara_bull1", "yagara_bull2", "yagara_bull3" };
	private double waterLevel;

	public YagaraBullEntity(World world)
	{
		super(ModEntities.YAGARA_BULL, world);
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(8.0D);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(OWNER, Optional.empty());
		this.getDataManager().register(IS_SADDLED, false);
		this.getDataManager().register(TEXTURE_ID, 0);
	}

	@Override
	public void tick()
	{
		if (this.checkInWater())
		{
			double d2 = (this.waterLevel - this.getBoundingBox().minY) / this.getHeight();
			this.setAir(30);

			if (this.areEyesInFluid(FluidTags.WATER) || this.world.getBlockState(this.getPosition().up()).getBlock() == Blocks.WATER)
			{
				d2 = 0.5;
			}

			if (d2 > 0.0D)
			{
				Vec3d vec3d1 = this.getMotion();
				this.setMotion(vec3d1.x, (vec3d1.y + d2 * 0.02D) * 0.75D, vec3d1.z);
			}
		}

		super.tick();
	}

	private boolean checkInWater()
	{
		AxisAlignedBB axisalignedbb = this.getBoundingBox();
		int i = MathHelper.floor(axisalignedbb.minX);
		int j = MathHelper.ceil(axisalignedbb.maxX);
		int k = MathHelper.floor(axisalignedbb.minY);
		int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
		int i1 = MathHelper.floor(axisalignedbb.minZ);
		int j1 = MathHelper.ceil(axisalignedbb.maxZ);
		boolean flag = false;
		this.waterLevel = Double.MIN_VALUE;

		try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain())
		{
			for (int k1 = i; k1 < j; ++k1)
			{
				for (int l1 = k; l1 < l; ++l1)
				{
					for (int i2 = i1; i2 < j1; ++i2)
					{
						blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
						IFluidState ifluidstate = this.world.getFluidState(blockpos$pooledmutableblockpos);
						if (ifluidstate.isTagged(FluidTags.WATER))
						{
							float f = l1 + ifluidstate.getActualHeight(this.world, blockpos$pooledmutableblockpos);
							this.waterLevel = Math.max(f, this.waterLevel);
							flag |= axisalignedbb.minY < f;
						}
					}
				}
			}
		}

		return flag;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public double getMountedYOffset()
	{
		return 0.5D;
	}

	@Override
	public boolean canBeSteered()
	{
		return this.getControllingPassenger() instanceof LivingEntity;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected boolean canFitPassenger(Entity passenger)
	{
		return this.getPassengers().size() < 2 && !this.areEyesInFluid(FluidTags.WATER);
	}

	protected void mountTo(PlayerEntity player)
	{
		if (!this.world.isRemote)
		{
			player.rotationYaw = this.rotationYaw;
			player.rotationPitch = this.rotationPitch;
			player.startRiding(this);
		}
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);
		if (passenger instanceof MobEntity)
		{
			MobEntity mobentity = (MobEntity) passenger;
			this.renderYawOffset = mobentity.renderYawOffset;
		}
	}

	@Override
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		if (this.textures != null && this.textures.length > 0)
			this.setTexture(this.rand.nextInt(this.textures.length));

		return spawnData;
	}

	@Override
	public void travel(Vec3d vec)
	{
		if (this.isAlive())
		{		
			if (this.isBeingRidden() && this.canBeSteered() && this.isSaddled())
			{
				LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
				this.rotationYaw = livingentity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = livingentity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.renderYawOffset = this.rotationYaw;
				this.rotationYawHead = this.renderYawOffset;
				float f = livingentity.moveStrafing * 0.5F;
				float f1 = livingentity.moveForward;

				if (this.canPassengerSteer())
				{
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					super.travel(new Vec3d(f, vec.y, f1));
				}
			}
			else
				super.travel(vec);
		}

	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		if (player.world.isRemote)
			return false;

		// If the entity is tamed we can't tame it anymore thus we're checking for healing items
		if (this.isTamed() && player == this.getOwner() && hand == Hand.MAIN_HAND)
		{
			ItemStack stack = player.getHeldItemMainhand();

			if (!stack.isEmpty())
			{
				Optional<Item> food = Arrays.stream(FOOD).filter(x -> stack.getItem() == x).findFirst();
				Optional<Item> saddle = Arrays.stream(SADDLES).filter(x -> stack.getItem() == x).findFirst();

				if (food.isPresent() && this.getHealth() < this.getMaxHealth())
				{
					stack.shrink(1);
					this.heal(4);
					this.spawnHeartParticles();
					return true;
				}
				else if (saddle.isPresent() && !this.isSaddled())
				{
					this.setSaddled(true);
				}
			}
			else
			{
				this.mountTo(player);
			}
		}
		else if (!this.isTamed() && hand == Hand.MAIN_HAND)
		{
			// If its not tamed, try and tame it with tropical fish
			ItemStack stack = player.getHeldItemMainhand();

			if (!stack.isEmpty())
			{
				Optional<Item> tropicalFish = Arrays.stream(TAMING_FOOD).filter(x -> stack.getItem() == x).findFirst();

				if (tropicalFish.isPresent())
				{
					stack.shrink(1);
					double chance = this.rand.nextDouble();
					System.out.println(chance);
					if (chance < 0.25)
					{
						this.spawnHeartParticles();
						this.setOwner(player.getUniqueID());
					}
				}
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
		compound.putBoolean("isSaddled", this.dataManager.get(IS_SADDLED));
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		if (!WyHelper.isNullOrEmpty(compound.getString("owner")))
			this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("owner"))));
		this.dataManager.set(IS_SADDLED, compound.getBoolean("isSaddled"));
	}

	@Override
	public boolean canDespawn(double distance)
	{
		return !this.isTamed();
	}

	public void spawnHeartParticles()
	{
		for (int i = 0; i < 5; ++i)
		{
			double offsetX = WyHelper.randomDouble() / 2;
			double offsetY = WyHelper.randomDouble() / 2;
			double offsetZ = WyHelper.randomDouble() / 2;

			((ServerWorld) this.world).spawnParticle(ParticleTypes.HEART, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0);
		}
	}

	public void setSaddled(boolean flag)
	{
		this.dataManager.set(IS_SADDLED, flag);
	}

	public boolean isSaddled()
	{
		return this.dataManager.get(IS_SADDLED);
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

	public int getTextureId()
	{
		return this.getDataManager().get(TEXTURE_ID);
	}

	protected void setTexture(int texture)
	{
		this.getDataManager().set(TEXTURE_ID, texture);
	}

	@Override
	public String getMobTexture()
	{
		return this.textures[this.getTextureId()];
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}
}
