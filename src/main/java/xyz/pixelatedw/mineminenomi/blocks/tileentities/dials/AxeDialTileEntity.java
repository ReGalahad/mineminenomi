package xyz.pixelatedw.mineminenomi.blocks.tileentities.dials;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.WyRegistry;

public class AxeDialTileEntity extends TileEntity
{
	public static final TileEntityType TILE_ENTITY = WyRegistry.registerTileEntity("axe_dial_block", AxeDialTileEntity::new, ModBlocks.AXE_DIAL);
	
	public AxeDialTileEntity()
	{
		super(TILE_ENTITY);
	}

}
