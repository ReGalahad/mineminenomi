package xyz.pixelatedw.MineMineNoMi3.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSkyblock extends ItemBlock
{

	public ItemSkyblock(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
		
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

    @Override
	public int getMetadata(int meta)
    {
        return meta;
    }
}
