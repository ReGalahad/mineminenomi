package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.wypi.WyHelper;

public class MirageCloneEntity extends CreatureEntity
{
	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(DoppelmanEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private int tick = 0;

	public MirageCloneEntity(World world)
	{
		super(ModEntities.MIRAGE_CLONE, world);
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(OWNER, null);
	}

	public void remove()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 10; i++)
			{
				double offsetX = WyHelper.randomDouble();
				double offsetY = WyHelper.randomDouble();
				double offsetZ = WyHelper.randomDouble();

				if (i % 2 == 0)
					((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, posX + offsetX + WyHelper.randomWithRange(-7, 7), (posY + 0.5) + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 1, 0.0D, 0, 0.0D, 0.05);
				else
					((ServerWorld) world).spawnParticle(ParticleTypes.POOF, posX + offsetX + WyHelper.randomWithRange(-7, 7), (posY + 0.5) + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 1, 0.0D, 0, 0.0D, 0.05);
			}
		}
		super.remove();
	}

	@Override
	public void tick()
	{
		if (!this.world.isRemote && this.getOwner() == null)
		{
			this.remove();
			return;
		}

		this.setRevengeTarget(this.getOwner());

		if (this.tick > 200)
			this.remove();

		this.tick++;
		super.tick();
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		if (this.dataManager.get(OWNER) != null)
			compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
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
