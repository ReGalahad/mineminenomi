package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class WantedPosterTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("wanted_poster", WantedPosterTileEntity::new, ModBlocks.wantedPoster);

	private String uuid = "";
	private String entityName = "";
	private String bounty = "";
	private String date = "";
	private String background = "";

	public WantedPosterTileEntity()
	{
		super(TILE_ENTITY);
	}

	public String getUUID()
	{
		return this.uuid;
	}

	public void setUUID(String uuid)
	{
		this.uuid = uuid;
	}

	public String getEntityName()
	{
		return this.entityName;
	}

	public void setEntityName(String name)
	{
		this.entityName = name;
	}

	public String getBackground()
	{
		return this.background;
	}

	public void setBackground(String background)
	{
		this.background = background;
	}

	public void setPosterBounty(String bounty)
	{
		this.bounty = bounty;
	}

	public String getPosterBounty()
	{
		return this.bounty;
	}

	public void setIssuedDate(String date)
	{
		this.date = date;
	}

	public String getIssuedDate()
	{
		return this.date;
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		super.write(nbt);
		if (this.entityName == null || this.bounty == null || this.date == null || this.background == null)
			return nbt;

		nbt.putString("UUID", this.uuid);
		nbt.putString("Name", this.entityName);
		nbt.putString("Bounty", this.bounty);
		nbt.putString("Date", this.date);
		nbt.putString("Background", this.background);

		return nbt;
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);
		this.uuid = nbt.getString("UUID");
		this.entityName = nbt.getString("Name");
		this.bounty = nbt.getString("Bounty");
		this.date = nbt.getString("Date");
		this.background = nbt.getString("Background");
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

	/*@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
	{
		this.read(pkt.getNbtCompound());
		// this.world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags);
	}*/
}
