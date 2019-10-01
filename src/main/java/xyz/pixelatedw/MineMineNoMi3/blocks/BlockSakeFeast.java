package xyz.pixelatedw.MineMineNoMi3.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

		if (world.isRemote)
			return true;

		boolean isItem = player.getHeldItem() != null && player.getHeldItem().getItem() == ListMisc.SakeCup;
		boolean isFilledFlag = isItem && player.getHeldItem().getTagCompound() != null && player.getHeldItem().getTagCompound().getBoolean("IsFilled");
		if (isItem && !isFilledFlag)
		{
			if (sakeFeast.placeCup())
			{
				player.getHeldItem().stackSize--;
				return true;
			}
		}
		else if (player.getHeldItem() == null)
		{
			if (sakeFeast.countPlacedCups() > 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem ,new ItemStack(ListMisc.SakeCup, 1, 0));
				player.getHeldItem().setTagCompound(new NBTTagCompound());
				player.getHeldItem().getTagCompound().setBoolean("IsFilled", true);
				sakeFeast.removeCup();
				return true;
			}
		}

		return false;
	}

    @Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block block, int i1)
    {
		ItemStack stack = new ItemStack(ListMisc.SakeBottle);
    	world.spawnEntityInWorld(new EntityItem(world, posX, posY + 1, posZ, stack));
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