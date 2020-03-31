package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityCloud extends Entity
{
	private int life = 100;
	private PlayerEntity thrower;
	
	public EntityCloud(World world)
	{
		super(ExtraProjectiles.CLOUD, world);
	}
	
	public EntityCloud(EntityType type, World world)
	{super(type, world);}
	
	@Override
	public void tick()
	{
		super.tick();
		if(!this.world.isRemote)
		{
			if(life <= 0)
				this.remove();

			life--;
		}		
	}
	
	public PlayerEntity getThrower()
	{
		return this.thrower;
	}
	
	public void setThrower(PlayerEntity player)
	{
		this.thrower = player;
	}
	
	public int getLife()
	{
		return this.life;
	}
	
	public void setLife(int life)
	{
		this.life = life;
	}

	@Override
	protected void registerData() {}
	@Override
	protected void readAdditional(CompoundNBT compound) {}
	@Override
	protected void writeAdditional(CompoundNBT compound) {}
	@Override
	public IPacket<?> createSpawnPacket() { return NetworkHooks.getEntitySpawningPacket(this); }
}