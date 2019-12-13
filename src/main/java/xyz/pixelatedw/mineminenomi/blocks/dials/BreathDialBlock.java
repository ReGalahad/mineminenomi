package xyz.pixelatedw.mineminenomi.blocks.dials;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.blocks.DialBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.BreathDialTileEntity;

public class BreathDialBlock extends DialBlock
{

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new BreathDialTileEntity();
	}
	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
	
}
