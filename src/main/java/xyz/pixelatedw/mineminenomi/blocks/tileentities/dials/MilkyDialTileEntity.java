package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class MilkyDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("milky_dial_block", MilkyDialTileEntity::new, ModBlocks.milkyDialBlock);
	
	public MilkyDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
