package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class WantedPosterPackageTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("wanted_poster_package", WantedPosterPackageTileEntity::new, ModBlocks.wantedPosterPackage);

	public WantedPosterPackageTileEntity()
	{
		super(TILE_ENTITY);
	} 

}
