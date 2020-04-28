package xyz.pixelatedw.mineminenomi.screens.extra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.gui.ScrollPanel;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
import xyz.pixelatedw.wypi.WyHelper;

public class ItemListScreenPanel extends ScrollPanel {

	private TraderScreen parent;
	private List<Entry> entries = new ArrayList<Entry>();
	private static final int ENTRY_HEIGHT = 20;

	public ItemListScreenPanel(TraderScreen parent, List<ItemStack> stacks) {
		super(parent.getMinecraft(), 215, 130, parent.height / 2 - 98 + 24, parent.width / 2 - 109);

		this.parent = parent;

		for (int i = 0; i < stacks.size(); i++) {
			if (stacks.get(i) != null)
				this.entries.add(new Entry(stacks.get(i)));
		}

	}

	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
		return true;
	}

	@Override
	protected int getContentHeight() {
		return (this.entries.size()) * ENTRY_HEIGHT + 46;
	}

	@Override
	protected int getScrollAmount() {
		return 10;
	}

	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
		for (Entry entry : this.entries) {
			float y = relativeY;
			float x = (parent.width / 2 - 109) + 40;

			WyHelper.drawIcon(parent.getTexture(entry.stack.getItem().getRegistryName().getPath()),
					MathHelper.floor(x) - 30, MathHelper.floor(y) - 2, 16, 16);
			if(parent.selectedStack != null) {
				if(entry.stack.getItem() == parent.selectedStack.getItem()) {
					
		            WyHelper.drawColourOnScreen(Color.WHITE.getRGB(), 100, x - 40, y - 4, this.width, 24, 0);
		            parent.drawSizedString(entry.stack.getDisplayName().getFormattedText(),
							MathHelper.floor(x) + 64, MathHelper.floor(y), 0.8f, -1);
				} else {
					parent.drawSizedString( entry.stack.getDisplayName().getFormattedText(), MathHelper.floor(x) + 64,
							MathHelper.floor(y), 0.8f, -1);

				}
			} else {
				parent.drawSizedString(entry.stack.getDisplayName().getFormattedText(), MathHelper.floor(x) + 64,
						MathHelper.floor(y), 0.8f, -1);

			}
			parent.drawSizedString(Integer.toString(parent.entity.getPrice(entry.stack)), MathHelper.floor(x) + 122, MathHelper.floor(y), 0.8f, -1);
			relativeY += ENTRY_HEIGHT * 1.25;
		}
	}

	public Entry findStackEntry(final int mouseX, final int mouseY) {
		double offset = (mouseY - top) + scrollDistance;

		if (offset <= 0)
			return null;

		int lineIdx = (int) (offset / (ENTRY_HEIGHT * 1.25));
		if (lineIdx >= entries.size())
			return null;

		Entry entry = entries.get(lineIdx);
		if (entry != null && mouseX >= this.left && mouseX <= this.right && mouseY <= this.bottom) {
			return entry;
		}

		return null;
	}

	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
		Entry entry = this.findStackEntry((int) mouseX, (int) mouseY);

		if (entry != null) {
			parent.selectedStack = entry.stack;
			parent.wantedAmount = 1;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	public class Entry {

		public ItemStack stack;

		public Entry(ItemStack itemStack) {
			this.stack = itemStack;
		}

	}

	public void removeEntry(ItemStack stack) {
		for(int i = 0; i < this.entries.size(); i++) {
			if(this.entries.get(i).stack.getItem() == stack.getItem()) {
				this.entries.remove(i);
			}
		}

	}
}
