package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

public class WantedPosterTileEntity extends TileEntity
{

	private String uuid = "";
	private String entityName = "";
	private String bounty = "";
	private String date = "";
	private String background = "";
	private GameProfile gameProfile;

	public WantedPosterTileEntity()
	{
		super(ModTileEntities.WANTED_POSTER);
	}

	public void setGameProfile(GameProfile gp)
	{
		this.gameProfile = gp;
	}

	public GameProfile getGameProfile()
	{
		return this.gameProfile;
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
		if (this.gameProfile != null)
		{
			CompoundNBT compoundnbt = new CompoundNBT();
			NBTUtil.writeGameProfile(compoundnbt, this.gameProfile);
			nbt.put("Owner", compoundnbt);
		}

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
		if (nbt.contains("Owner", 10))
			this.setGameProfile(NBTUtil.readGameProfile(nbt.getCompound("Owner")));
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

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
	{
		this.read(pkt.getNbtCompound());
	}

	/*
	 * @Override
	 * public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
	 * {
	 * this.read(pkt.getNbtCompound());
	 * // this.world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags);
	 * }
	 */
}
