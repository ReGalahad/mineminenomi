package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class EisenDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("eisen_dial_block", EisenDialTileEntity::new, ModBlocks.EISEN_DIAL);
	
	public EisenDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
