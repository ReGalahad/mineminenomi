package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.WyRegistry;

public class FlashDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("flash_dial_block", FlashDialTileEntity::new, ModBlocks.FLASH_DIAL);
	
	public FlashDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
