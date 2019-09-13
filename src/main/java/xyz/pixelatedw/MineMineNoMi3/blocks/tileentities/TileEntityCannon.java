package xyz.pixelatedw.MineMineNoMi3.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCannon extends TileEntity
{
	private boolean hasGunpoweder = false;
	private boolean hasCannonBall = false;

	public boolean isHasGunpoweder()
	{
		return hasGunpoweder;
	}

	public void setHasGunpoweder(boolean hasGunpoweder)
	{
		this.hasGunpoweder = hasGunpoweder;
	}

	public boolean isHasCannonBall()
	{
		return hasCannonBall;
	}

	public void setHasCannonBall(boolean hasCannonBall)
	{
		this.hasCannonBall = hasCannonBall;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setBoolean("HasGunpowder", this.hasGunpoweder);
		nbt.setBoolean("HasCannonBall", this.hasCannonBall);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.hasGunpoweder = nbt.getBoolean("HasGunpowder");
		this.hasCannonBall = nbt.getBoolean("HasCannonBall");
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}
