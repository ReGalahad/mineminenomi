package xyz.pixelatedw.MineMineNoMi3.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityPoneglyph;

public class BlockPoneglyph extends BlockContainer
{	
	public BlockPoneglyph()
	{
		super(Material.iron);
	}
	
	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int i1, float f1, float f2, float f3)
	{
		TileEntityPoneglyph tileEntity = (TileEntityPoneglyph) world.getTileEntity(posX, posY, posZ);
		
		//tileEntity.setEntryType(ID.HISTORY_ENTRY_TYPE_CHALLENGE);
		//tileEntity.setEntryName(ID.HISTORY_ENNTRY_NAME_CROCODILE);
		
		System.out.println(tileEntity.getEntryName());
		System.out.println(tileEntity.getEntryType());
		
		return true;	
	}

	/*@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		this.tileEntity = new TileEntityPoneglyph();
		return this.tileEntity;
	}
	
	@Override
	public boolean hasTileEntity()
	{
		return true;
	}*/

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityPoneglyph();
	}
	
}
