package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.WyRegistry;

public class FlameDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("flame_dial_block", FlameDialTileEntity::new, ModBlocks.FLAME_DIAL);
	
	public FlameDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
