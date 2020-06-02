package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

public class CannonTileEntity extends TileEntity
{
	private int gunpowderLoaded = 0;
	private boolean hasCannonBall = false;
	
	public CannonTileEntity()
	{
		super(ModTileEntities.CANNON);
	}

	public int getGunpowederLoaded()
	{
		return this.gunpowderLoaded;
	}

	public void addGunpoweder()
	{
		this.gunpowderLoaded++;
	}

	public void emptyGunpoweder()
	{
		this.gunpowderLoaded = 0;
	}
	
	public boolean hasCannonBall()
	{
		return this.hasCannonBall;
	}

	public void setHasCannonBall(boolean hasCannonBall)
	{
		this.hasCannonBall = hasCannonBall;
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		super.write(nbt);
		nbt.putInt("loadedGunpowder", this.gunpowderLoaded);
		nbt.putBoolean("hasCannonBall", this.hasCannonBall);
		
		return nbt;
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);
		this.gunpowderLoaded = nbt.getInt("loadedGunpowder");
		this.hasCannonBall = nbt.getBoolean("hasCannonBall");
	}
	
	@Override
	public CompoundNBT getUpdateTag()
	{
		return this.write(new CompoundNBT());
	}

	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		CompoundNBT nbttagcompound = new CompoundNBT();
		this.write(nbttagcompound);
		return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
	}
}
