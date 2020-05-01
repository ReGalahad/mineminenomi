package xyz.pixelatedw.mineminenomi.screens.extra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.gui.ScrollPanel;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
import xyz.pixelatedw.wypi.WyHelper;

public class ItemListScreenPanel extends ScrollPanel
{

	private TraderScreen parent;
	private List<TradeEntry> entries = new ArrayList<TradeEntry>();
	private static final int ENTRY_HEIGHT = 20;

	public ItemListScreenPanel(TraderScreen parent, List<TradeEntry> list)
	{
		super(parent.getMinecraft(), 215, 140, parent.height / 2 - 50, parent.width / 2 - 109);

		this.parent = parent;
		this.entries = list;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int partialTicks)
	{
		return true;
	}

	@Override
	protected int getContentHeight()
	{
		return (this.entries.size()) * ENTRY_HEIGHT + 46;
	}

	@Override
	protected int getScrollAmount()
	{
		return 10;
	}

	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY)
	{
		for (TradeEntry entry : this.entries)
		{
			int y = relativeY;
			int x = (this.parent.width / 2 - 109) + 40;

			this.parent.renderItem(entry.getItemStack(), x - 30, y - 1);
			if (this.parent.getSelectedStack() != null && entry.getItemStack().getItem() == this.parent.getSelectedStack().getItemStack().getItem())
				WyHelper.drawColourOnScreen(Color.WHITE.getRGB(), 100, x - 40, y - 4, this.width, 24, 0);
			
			this.parent.drawSizedString(entry.getItemStack().getDisplayName().getFormattedText(), x + 50, y + 4, 0.8f, -1);
			this.parent.drawSizedString(entry.getPrice() + "", x + 122, y + 4, 0.8f, -1);
			relativeY += ENTRY_HEIGHT * 1.25;
		}
	}

	public TradeEntry findStackEntry(final int mouseX, final int mouseY)
	{
		double offset = (mouseY - this.top) + this.scrollDistance;

		if (offset <= 0)
			return null;

		int lineIdx = (int) (offset / (ENTRY_HEIGHT * 1.25));
		if (lineIdx >= this.entries.size())
			return null;

		TradeEntry entry = this.entries.get(lineIdx);
		if (entry != null && mouseX >= this.left && mouseX <= this.right && mouseY <= this.bottom)
			return entry;

		return null;
	}

	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button)
	{
		TradeEntry entry = this.findStackEntry((int) mouseX, (int) mouseY);

		if (entry != null)
		{
			this.parent.setSelectedStack(entry);
			this.parent.setWantedAmount(1);
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	public void removeEntry(ItemStack stack)
	{
		for (int i = 0; i < this.entries.size(); i++)
		{
			if (this.entries.get(i).getItemStack().getItem() == stack.getItem())
			{
				this.entries.remove(i);
			}
		}
	}
}
