package xyz.pixelatedw.MineMineNoMi3.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntitySakeFeast;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class BlockSakeFeast extends BlockContainer
{

	public BlockSakeFeast()
	{
		super(Material.clay);
		this.setHardness(1);
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntitySakeFeast sakeFeast = (TileEntitySakeFeast) world.getTileEntity(posX, posY, posZ);
		
		if(!world.isRemote)
		{
			if (player.getHeldItem() != null && player.getHeldItem().getItem() == ListMisc.SakeCup)
			{
				if(sakeFeast.placeCup())
				{
					player.getHeldItem().stackSize--;
					return true;
				}
			}
			else if(player.getHeldItem() == null || player.getHeldItem().getItem() != ListMisc.SakeCup)
			{
				if(sakeFeast.countPlacedCups() > 0)
				{
					sakeFeast.fillCup();
					return true;
				}
				
				if(sakeFeast.countFilledCups() > 0 && sakeFeast.removeCup())
				{
					player.inventory.addItemStackToInventory(new ItemStack(ListMisc.SakeCup));
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
    
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntitySakeFeast();
	}
}