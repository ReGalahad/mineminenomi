package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class ImpactDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("impact_dial_block", ImpactDialTileEntity::new, ModBlocks.impactDialBlock);
	
	public ImpactDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
