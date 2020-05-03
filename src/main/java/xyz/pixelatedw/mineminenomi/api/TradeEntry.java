package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.item.ItemStack;

public class TradeEntry
{
	private ItemStack itemStack;
	
	public TradeEntry(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}
	
	public ItemStack getItemStack()
	{
		return this.itemStack;
	}
	
	public int getCount()
	{
		return this.itemStack.getCount();
	}
	
	public TradeEntry setCount(int count)
	{
		this.itemStack.setCount(count);
		return this;
	}
	
	public boolean hasInfiniteStock()
	{
		return this.itemStack.getOrCreateTag().getBoolean("isInfinite");
	}
	
	public int getPrice()
	{
		return this.itemStack.getOrCreateTag().getInt("price");
	}
}
