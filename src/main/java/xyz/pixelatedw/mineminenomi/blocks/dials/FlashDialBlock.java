package xyz.pixelatedw.mineminenomi.blocks.dials;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.blocks.DialBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.FlashDialTileEntity;

public class FlashDialBlock extends DialBlock
{

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new FlashDialTileEntity();
	}
	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
	
}
