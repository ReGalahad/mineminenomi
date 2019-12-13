package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class AblProtectionTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("ability_protection", AblProtectionTileEntity::new, ModBlocks.abilityProtection);
	private int protectedSize = 100;
	
	public AblProtectionTileEntity()
	{
		super(TILE_ENTITY);
	}

	public void setupProtection(World world, BlockPos pos, int size)
	{
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		this.protectedSize = size;

		int minPosX = posX - size;
		int minPosY = posY - size;
		int minPosZ = posZ - size;
		int maxPosX = posX + size;
		int maxPosY = posY + size;
		int maxPosZ = posZ + size;

		System.out.println(minPosX + " " + maxPosX);
		System.out.println(minPosY + " " + maxPosY);
		System.out.println(minPosZ + " " + maxPosZ);

		worldData.addRestrictedArea(new int[] { minPosX, minPosY, minPosZ }, new int[] { maxPosX, maxPosY, maxPosZ });
	}
	
	public int getSize()
	{
		return this.protectedSize;
	}

	@Override
	public void read(CompoundNBT nbtTag)
	{
		super.read(nbtTag);
		
		this.protectedSize = nbtTag.getInt("Size");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbtTag)
	{
		super.write(nbtTag);
		
		nbtTag.putInt("Size", this.protectedSize);
		
		return nbtTag;
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
