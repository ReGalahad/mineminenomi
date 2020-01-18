package xyz.pixelatedw.mineminenomi.entities;

import java.util.UUID;

import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.VivreCardItem;

public class VivreCardEntity extends Entity
{
	
	private UUID ownerUUID;
	private final double speedLimit = 0.001;
	
	public VivreCardEntity(World worldIn)
	{
		super(ModEntities.VIVRE_CARD, worldIn);
	}

	public void setOwner(UUID uuid)
	{
		this.ownerUUID = uuid;
	}

	@Override
	public void tick()
	{
		super.tick();
				
		if(world.isRemote)
			return;
				
		if(!this.onGround)
			this.move(MoverType.SELF, new Vec3d(0, -0.1, 0));
		
		if(this.ownerUUID == null)
		{
			this.remove();
			return;	
		}

		LivingEntity owner = (LivingEntity) ((ServerWorld) world).getEntityByUuid(this.ownerUUID);

		if(owner == null)
			return;
		
		double posX = this.posX - owner.posX;
		double posZ = this.posZ - owner.posZ;

		if(posX > 0 && posX > this.speedLimit)
			posX = this.speedLimit;
		else if(posX < 0 && posX < -this.speedLimit)
			posX = -this.speedLimit;
		
		if(posZ > 0 && posZ > this.speedLimit)
			posZ = this.speedLimit;
		else if(posZ < 0 && posZ < -this.speedLimit)
			posZ = -this.speedLimit;
		
		this.move(MoverType.SELF, new Vec3d(-posX, 0, -posZ));

		this.lookAt(Type.EYES, owner.getPositionVec());
		
		if(this.ticksExisted > 40)
		{
			for(LivingEntity player : WyHelper.getEntitiesNear(this, 0.2))
			{
				if(player instanceof PlayerEntity)
				{
					ItemStack stack = new ItemStack(ModItems.vivreCard);
					
					((VivreCardItem) stack.getItem()).setOwner(stack, owner);
					
					((PlayerEntity) player).addItemStackToInventory(stack);
					this.remove();
					break;
				}
			}
		}
	}
	
	@Override
	protected void registerData() {}

	@Override
	protected void writeAdditional(CompoundNBT compound)
	{
		compound.putString("OwnerUUID", this.ownerUUID.toString());
	}

	@Override
	protected void readAdditional(CompoundNBT compound)
	{
		if (compound.contains("OwnerUUID", 8))
			this.setOwner(UUID.fromString(compound.getString("OwnerUUID")));
	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
