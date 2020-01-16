package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class RejectDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("reject_dial_block", RejectDialTileEntity::new, ModBlocks.REJECT_DIAL);
	
	public RejectDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
