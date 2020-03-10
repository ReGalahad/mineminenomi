package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.WyRegistry;

public class WantedPosterPackageTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("wanted_poster_package", WantedPosterPackageTileEntity::new, ModBlocks.WANTED_POSTER_PACKAGE);

	public WantedPosterPackageTileEntity()
	{
		super(TILE_ENTITY);
	} 

}
