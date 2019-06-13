package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.PirateData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class EntityMirageClone extends EntityMob
{	
	private EntityPlayer owner;
	
	public EntityMirageClone(World worldIn, EntityPlayer owner) 
	{ 
		this(worldIn);
		this.setOwner(owner);
	    this.tasks.addTask(1, new EntityAISwimming(this));
	    this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
	    this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
	    this.tasks.addTask(4, new EntityAIPanic(this, 1.5));	    
	}

	public EntityMirageClone(World worldIn) 
	{
		super(worldIn); 
	}

	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(10);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	}
    
	public boolean isAIEnabled()
	{return true;}
    
	public void onEntityUpdate()
	{
		if(!this.worldObj.isRemote && this.owner == null)
			this.setDead();

	    this.setRevengeTarget(this.owner);
		
		if(this.ticksExisted > 200)
			this.setDead();
		
		super.onEntityUpdate();
	}

	private void setOwner(EntityPlayer player) {this.owner = player;}
	public EntityPlayer getOwner() {return this.owner;}

}
