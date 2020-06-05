package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

public class DenDenMushiTileEntity extends TileEntity
{

	public DenDenMushiTileEntity()
	{
		super(ModTileEntities.DEN_DEN_MUSHI);
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
