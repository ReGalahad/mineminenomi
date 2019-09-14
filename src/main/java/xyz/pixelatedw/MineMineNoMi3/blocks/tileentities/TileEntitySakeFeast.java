package xyz.pixelatedw.MineMineNoMi3.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySakeFeast extends TileEntity
{
	private int[] cups = new int[] {0, 0, 0, 0};
	
	public void setCup(int pos, int state)
	{
		this.cups[pos] = state;
	}

	public int getCupState(int pos)
	{
		return this.cups[pos];
	}
	
	public boolean placeCup()
	{
		for(int i = 0; i < this.cups.length; i++)
		{
			if(this.cups[i] == 0)
			{
				this.setCup(i, 1);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean fillCup()
	{
		for(int i = 0; i < this.cups.length; i++)
		{
			if(this.cups[i] == 1)
			{
				this.setCup(i, 2);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean removeCup()
	{
		for(int i = 0; i < this.cups.length; i++)
		{
			if(this.cups[i] > 0)
			{
				this.setCup(i, 0);
				return true;
			}
		}
		
		return false;	
	}
	
	public int countPlacedCups()
	{
		int placedCups = 0;
		for(int i : this.cups)
		{
			if(i == 1)
			{
				placedCups++;
			}
		}
		
		return placedCups;
	}
	
	public int countFilledCups()
	{
		int filledCups = 0;
		for(int i : this.cups)
		{
			if(i == 2)
			{
				filledCups++;
			}
		}
		
		return filledCups;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setIntArray("Cups", this.cups);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.cups = nbt.getIntArray("Cups");
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
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
}
