package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class FlameDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("flame_dial_block", FlameDialTileEntity::new, ModBlocks.flameDialBlock);
	
	public FlameDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
