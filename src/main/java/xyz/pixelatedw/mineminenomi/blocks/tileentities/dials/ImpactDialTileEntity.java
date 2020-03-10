package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.WyRegistry;

public class ImpactDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("impact_dial_block", ImpactDialTileEntity::new, ModBlocks.IMPACT_DIAL);
	
	public ImpactDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
