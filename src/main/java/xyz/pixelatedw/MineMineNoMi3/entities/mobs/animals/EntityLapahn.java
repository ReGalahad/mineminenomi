package xyz.pixelatedw.MineMineNoMi3.entities.mobs.animals;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.INBTEntity;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.lapahn.EntityAILapahnJump;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.lapahn.EntityAILapahnRage;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.bandits.BanditData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.PirateData;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketEntityNBTSync;

public class EntityLapahn extends EntityNewMob implements INBTEntity
{

	private AttributeModifier rangeModeModifier = new AttributeModifier(UUID.randomUUID(), "Rage Mode", 10, 0);
	private boolean isEnraged;
	
	public EntityLapahn(World world)
	{
		super(world);
		
		this.setSize(0.8F, 2.5F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(0, new EntityAILapahnJump(this));
		this.tasks.addTask(0, new EntityAILapahnRage(this));
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
			//System.out.println(this.isEnraged);
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setBoolean("IsEnraged", this.isEnraged);
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

		this.isEnraged = nbt.getBoolean("IsEnraged");
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void updateNBT()
	{
		NBTTagCompound nbtClone = new NBTTagCompound();
		this.writeEntityToNBT(nbtClone);

		WyNetworkHelper.sendToAll(new PacketEntityNBTSync(this.getEntityId(), nbtClone));
	}
	
	public boolean isEnraged()
	{
		return this.isEnraged;
	}

	public void setEnraged(boolean value)
	{
		this.isEnraged = value;
		if(value)
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(rangeModeModifier);
		else
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(rangeModeModifier);
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	
	@Override
	public void fall(float distance) 
	{
		if(distance > 5)
		{
			if(this.worldObj.isRemote)
			{
				for (int i = 0; i < 256; i++)
				{
					double posX = this.posX + WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
					double posZ = this.posZ + WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
				
					this.worldObj.spawnParticle("explode", posX, this.posY + 0.5, posZ, 0, 0.1, 0);
					this.worldObj.spawnParticle("smoke", posX, this.posY + 0.5, posZ, 0, 0.1, 0);		            
				}
			}
			
			for(EntityLivingBase entity : WyHelper.getEntitiesNear(this, 5))
			{
				if(!(entity instanceof EntityLapahn))
				{
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6);			
					entity.motionY = 0.5;
				}
			}
		}
	}
}
