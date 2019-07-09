package xyz.pixelatedw.MineMineNoMi3.entities.mobs.animals;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.INBTEntity;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.bandits.BanditData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.PirateData;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketEntityNBTSync;

public class EntityLapahn extends EntityMob implements INBTEntity
{

	public EntityLapahn(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, PirateData.class, 0, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, BanditData.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if (!this.worldObj.isRemote)
		{
			
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		//nbt.setBoolean("IsTamed", this.isTamed);
	}
	
	@Override
	public void readEntityFromExtraNBT(NBTTagCompound nbt)
	{
		this.readEntityFromNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		//this.isTamed = nbt.getBoolean("IsTamed");
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	public void updateNBT()
	{
		NBTTagCompound nbtClone = new NBTTagCompound();
		this.writeEntityToNBT(nbtClone);

		WyNetworkHelper.sendToAll(new PacketEntityNBTSync(this.getEntityId(), nbtClone));
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}
}
