package xyz.pixelatedw.MineMineNoMi3.entities.mobs.animals;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;

public class EntityYagaraBull extends EntityNewMob implements IEntityOwnable
{
	private Item[] food = new Item[]
			{
					Items.cooked_fished, Items.fish
			};
	private boolean isTamed, isSaddled;
	private boolean isEmpty;
	private EntityPlayer owner;
	private UUID ownerUUID;
	private int timesFed = 0;
	
	public EntityYagaraBull(World world)
	{
		super(world);
		// this.tasks.addTask(0, new EntityAISwimming(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.26D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.ticksExisted < 20)
			this.updateNBT();
		
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, this.boundingBox.minY + 0.9, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ);

		if (this.worldObj.isAABBInMaterial(aabb, Material.water))
		{
			this.motionY += 0.03;
		}		
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack heldStack = player.getHeldItem();

		if(heldStack == null)
			return false;
		
		if (!this.isTamed())
		{
			Optional<Item> foodItem = Arrays.stream(this.food).filter(x -> heldStack.getItem() == x).findFirst();

			if (foodItem != null)
			{
				--heldStack.stackSize;
				for (int i = 0; i < 2; ++i)
				{
					double d0 = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					this.worldObj.spawnParticle(EnumParticleTypes.HEART.getParticleName(), this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
				}
				this.timesFed++;
			}
			
			
			if(this.timesFed >= 5 + WyMathHelper.randomWithRange(2, 5))
			{
				this.setTamed(true);
				for (int i = 0; i < 10; ++i)
				{
					double d0 = this.rand.nextGaussian() * 0.03D;
					double d1 = this.rand.nextGaussian() * 0.03D;
					double d2 = this.rand.nextGaussian() * 0.03D;
					this.worldObj.spawnParticle(EnumParticleTypes.HEART.getParticleName(), this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
				}
				this.updateNBT();
			}
			
			return true;
		}
		else
		{
			if(heldStack.getItem() == Items.saddle)
			{
				--heldStack.stackSize;
				this.setSaddled(true);
				this.updateNBT();
			}
			
			return true;
		}
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.0D - 0.30000001192092896D;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setBoolean("IsTamed", this.isTamed);
		nbt.setBoolean("IsSaddled", this.isSaddled);
		
		nbt.setString("OwnerUUID", this.ownerUUID != null ? this.ownerUUID.toString() : "");
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
		this.isSaddled = nbt.getBoolean("IsSaddled");
		
		String uuid = nbt.getString("OwnerUUID");

		if (!WyHelper.isNullOrEmpty(uuid))
		{
			this.ownerUUID = UUID.fromString(uuid);
			this.owner = this.worldObj.func_152378_a(this.ownerUUID);
			this.isTamed = true;
		}
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	public boolean isTamed()
	{
		return this.isTamed;
	}

	public void setTamed(boolean value)
	{
		this.isTamed = value;
	}
	
	public boolean isSaddled()
	{
		return this.isSaddled;
	}

	public void setSaddled(boolean value)
	{
		this.isSaddled = value;
	}

	@Override
	public EntityPlayer getOwner()
	{
		return this.owner;
	}

	@Override
	protected boolean canDespawn()
	{
		if (this.isTamed())
			return false;
		else
			return true;
	}

	@Override
	public String func_152113_b()
	{
		if(this.ownerUUID != null)
			return this.ownerUUID.toString();
		else
			return "";
	}
}
