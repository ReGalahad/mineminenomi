package xyz.pixelatedw.MineMineNoMi3.entities.mobs.animals;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.INBTEntity;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketEntityNBTSync;

public class EntityKungFuDugong extends EntityMob implements INBTEntity
{
	private boolean isHappy, isTamed, isWaiting;
	private EntityPlayer owner;
	private UUID ownerUUID;

	public EntityKungFuDugong(World world)
	{
		super(world);
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
	}

	@Override
	public void onEntityUpdate()
	{
		System.out.println("" + this.isHappy);
		if(!this.worldObj.isRemote)
		{
			if(this.getOwner() != null && this.getDistanceToEntity(this.getOwner()) < 10 && this.isTamed() && this.getAttackTarget() == null && this.isWaiting())
			{
				this.isHappy = true;
				if(this.ticksExisted % 100 == 0)
					this.updateNBT();
			}
			else
				this.isHappy = false;
			
		}
		super.onEntityUpdate();
	}

	public void updateNBT()
	{
		NBTTagCompound nbtClone = new NBTTagCompound();
		this.writeEntityToNBT(nbtClone);
		
		WyNetworkHelper.sendToAll(new PacketEntityNBTSync(this.getEntityId(), nbtClone));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setBoolean("IsTamed", this.isTamed);
		nbt.setBoolean("IsWaiting", this.isWaiting);
		nbt.setBoolean("IsHappy", this.isHappy);
		nbt.setString("OwnerUUID", this.ownerUUID != null ? this.ownerUUID.toString() : "" );
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

		this.isTamed = nbt.getBoolean("IsTamed");
		this.isWaiting = nbt.getBoolean("IsWaiting");
		this.isHappy = nbt.getBoolean("IsHappy");
		String uuid = nbt.getString("OwnerUUID");
		
		if(!WyHelper.isNullOrEmpty(uuid))
		{
			this.ownerUUID = UUID.fromString(uuid);
			this.owner = this.worldObj.func_152378_a(this.ownerUUID);
			this.isTamed = true;
		}
					
		//System.out.println(this.isTamed);	
		//System.out.println(uuid);		
	}

    @Override
	public boolean interact(EntityPlayer player)
    {
    	this.isWaiting = !this.isWaiting;
    	this.updateNBT();
    	
		return false;
    }
	
	public boolean isHappy()
	{
		return this.isHappy;
	}

	public boolean isTamed()
	{
		return this.isTamed;
	}
	
	public boolean isWaiting()
	{
		return this.isWaiting;
	}
	
	@Override
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);
		return this.getBlockPathWeight(i, j, k) >= 0.0F;
	}
	
	private void setOwner(EntityPlayer player) 
	{
		this.owner = player;
		this.ownerUUID = player.getPersistentID();
		this.isTamed = true;
	}
	public EntityPlayer getOwner() {return this.owner;}
}
