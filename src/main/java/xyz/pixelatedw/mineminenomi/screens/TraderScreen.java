package xyz.pixelatedw.mineminenomi.screens;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.CGiveItemStackPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUpdateTraderOffersPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.FlickeringString;
import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class TraderScreen extends Screen
{
	private int guiState = 0;
	private int wantedAmount = 1;
	private ItemListScreenPanel listPanel;
	private TradeEntry selectedStack;
	private TradeEntry hoveredStack;
	private PlayerEntity player;
	private TraderEntity trader;
	private IEntityStats props;
	private SequencedString startMessage;
	private FlickeringString skipMessage;

	public TraderScreen(TraderEntity entity)
	{
		super(new StringTextComponent(""));
		this.trader = entity;
		this.player = Minecraft.getInstance().player;
		this.props = EntityStatsCapability.get(player);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		switch (this.guiState)
		{
			case 0:
				this.renderEntryGui(mouseX, mouseY);
				break;
			case 1:
				this.renderMainGui(mouseX, mouseY, partialTicks);
				break;
		}
	}

	public void renderEntryGui(int x, int y)
	{
		this.renderBackground();
		
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		this.startMessage.render(posX - 200, posY - 50);
		this.skipMessage.render(posX - 100, posY + 60);
		
		if (this.startMessage.ticksExisted > this.startMessage.delayTicks)
		{
			this.guiState = 1;
			this.init(this.getMinecraft(), this.width, this.height);
			if (this.trader.canTrade(this.player))
			{
				this.guiState = 1;
				this.init(this.getMinecraft(), this.width, this.height);
			}
			else
				this.onClose();
		}
	
		// Trader model
		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			GlStateManager.enableBlend();
			WyHelper.drawEntityOnScreen(posX + 150, posY + 150, 100, 40, 5, this.trader);
		}
		GlStateManager.popMatrix();

	}

	public void renderMainGui(int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground();

		GlStateManager.pushMatrix();
		GlStateManager.translated(0, 0, 0);
		GlStateManager.scaled(8d, 8d, 1d);
		this.renderBackground();
		GlStateManager.popMatrix();
		this.getMinecraft().getTextureManager().bindTexture(ModResources.BLANK2);
		
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		GuiUtils.drawTexturedModalRect(posX - 128, posY - 110, 0, 0, 256, 256, 0);
		
		this.renderUpperColumn();
		
		this.drawSizedString(new TranslationTextComponent(ModI18n.GUI_NAME).getFormattedText(), posX - 20, posY - 63, 0.9f, -1);
		this.drawSizedString(new TranslationTextComponent(ModI18n.GUI_PRICE).getFormattedText(), posX + 50, posY - 63, 0.9f, -1);
		this.getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(posX + 53, posY - 76, 0, 32, 32, 64, 1);

		this.listPanel.render(mouseX, mouseY, partialTicks);

		this.hover(mouseX, mouseY);
		
		if(this.selectedStack != null)
		{
			this.buttons.forEach(button -> {
				button.render(mouseX, mouseY, partialTicks);
			});
		}
	}

	public void renderUpperColumn()
	{
		int posX = this.width / 2;
		int posY = this.height / 2;
		String amount = "";
		
		if (this.hoveredStack != null)
		{
			WyHelper.drawIcon(ModResources.BLANK, posX - 117, posY - 105, 32, 42);
			this.renderItem(this.hoveredStack.getItemStack(), posX - 110, posY - 100);
			amount = this.getWantedAmount() + "/" + this.hoveredStack.getCount();
			if (this.hoveredStack.hasInfiniteStock())
				amount = this.getWantedAmount() + "/∞";
		}
		else if (this.getSelectedStack() != null)
		{
			WyHelper.drawIcon(ModResources.BLANK, posX - 117, posY - 105, 32, 42);
			this.renderItem(this.getSelectedStack().getItemStack(), posX - 110, posY - 100);
			amount = this.getWantedAmount() + "/" + this.getSelectedStack().getCount();
			if (this.getSelectedStack().hasInfiniteStock())
				amount = this.getWantedAmount() + "/∞";
		}
		
		this.drawSizedString(amount, posX - 70, posY - 100, 0.9f, -1);
		this.drawSizedString(this.props.getBelly() + "", posX + 85, posY - 100, 0.9f, -1);
		this.getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(posX + 102, posY - 113, 0, 32, 32, 64, 1);
	}

	@Override
	public void init(Minecraft mc, int width, int height)
	{
		super.init(mc, width, height);
		
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		if (this.startMessage == null)
		{
			String message = "";
			if(this.trader.canTrade(this.player))
				message = new TranslationTextComponent(ModI18n.TRADER_WELCOME_MESSAGE).getFormattedText();
			else
				message = this.trader.getTradeFailMessage();
			
			this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
		}
		
		if(this.skipMessage == null)
			this.skipMessage = new FlickeringString("- " + new TranslationTextComponent(ModI18n.GUI_CLICK_TO_SKIP).getFormattedText() + " -", 20);
		
		if (this.guiState == 1)
		{
			this.listPanel = new ItemListScreenPanel(this, this.trader.getTradingItems());
			this.children.add(this.listPanel);
			
			// Increase Quantity
			TexturedIconButton incQtyBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW, posX - 50, posY - 105, 10, 32, "", this::onIncreaseQuantity);
			incQtyBtn = incQtyBtn.setTextureInfo(posX - 62, posY - 105, 32, 32);
			this.addButton(incQtyBtn);
			// Decrease Quantity
			TexturedIconButton decQtyBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_DOWN, posX - 30, posY - 109, 10, 32, "", this::onDecreaseQuantity);
			decQtyBtn = decQtyBtn.setTextureInfo(posX - 42, posY - 109, 32, 32);
			this.addButton(decQtyBtn);
			// Buy
			TexturedIconButton buyBtn = new TexturedIconButton(ModResources.BLANK, posX - 10, posY - 100, 64, 22, new TranslationTextComponent(ModI18n.GUI_BUY).getFormattedText(), this::onBuy);
			buyBtn = buyBtn.setTextureInfo(posX - 10, posY - 100, 64, 32).setTextInfo(posX + 10, posY - 95, 1);
			this.addButton(buyBtn);
		}
	}

	public void onBuy(Button btn)
	{
		if (this.getSelectedStack() == null)
			return;
		
		if(this.getWantedAmount() > this.getSelectedStack().getCount() && !this.getSelectedStack().hasInfiniteStock())
			return;
		
		if (this.getEmptySlots() < this.calculateSlotsFromCount(this.getWantedAmount()))
			return;
		
		int totalPrice = this.getSelectedStack().getPrice() * this.getWantedAmount();
		
		if(this.props.getBelly() < totalPrice)
			return;
		
		ItemStack stack = new ItemStack(this.getSelectedStack().getItemStack().getItem());
		stack.setCount(this.getWantedAmount());
		
		if(this.getSelectedStack().getItemStack().getOrCreateTag().getBoolean("isClone"))
			stack.getOrCreateTag().putBoolean("isClone", true);
		
		// Reduce the available stacks if its not infinite stock
		if(!this.getSelectedStack().hasInfiniteStock())
		{
			int count = this.getSelectedStack().getCount() - 1;
			
			if(count <= 0)
				this.trader.getTradingItems().remove(this.getSelectedStack());
			else		
				this.getSelectedStack().setCount(count);
						
			WyNetwork.sendToServer(new CUpdateTraderOffersPacket(this.trader.getEntityId(), this.trader.getTradingItems()));
		}
			
		// Give the item
		WyNetwork.sendToServer(new CGiveItemStackPacket(stack));
		this.player.inventory.addItemStackToInventory(stack);
		
		// Reduce belly from the user
		this.props.alterBelly(-totalPrice);
		WyNetwork.sendToServer(new CSyncEntityStatsPacket(this.props));
		
		this.setSelectedStack(null);
	}
	
	public void onIncreaseQuantity(Button btn)
	{
		if (this.getSelectedStack() != null && (this.getWantedAmount() < this.getSelectedStack().getCount() || this.getSelectedStack().hasInfiniteStock()))
			this.setWantedAmount(this.getWantedAmount() + 1);
	}
	
	public void onDecreaseQuantity(Button btn)
	{
		if (this.getSelectedStack() != null && this.getWantedAmount() > 1)
			this.setWantedAmount(this.getWantedAmount() - 1);
	}
	
	public void renderItem(ItemStack stack, int posX, int posY)
	{	
		Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(stack, posX, posY);
	}

	public void drawSizedString(String txt, int x, int y, float scale, int color)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y, 0);
		GlStateManager.scalef(scale, scale, scale);
			
		if (color == -1)
			color = WyHelper.hexToRGB("#FFFFFF").getRGB();
			
		this.drawCenteredString(txt, 0, 0, color);

		GlStateManager.popMatrix();
	}

	public void hover(int mouseX, int mouseY)
	{
		TradeEntry entry = this.listPanel.findStackEntry(mouseX, mouseY);
		if (entry != null)
		{
			this.hoveredStack = entry;
			this.setWantedAmount(1);
		}
		else
		{
			this.hoveredStack = null;
		}
	}

	public int getEmptySlots()
	{
		int i = 0;
		for (ItemStack stack : this.player.inventory.mainInventory)
		{
			if (stack.isEmpty())
			{
				i++;
			}
		}
		return i;
	}

	public int calculateSlotsFromCount(int count)
	{
		double val = count / 64.0;
		return MathHelper.ceil(val);
	}

	public List<Integer> getStacks(int count)
	{
		List<Integer> list = new ArrayList<>();
		int j = 0;
		for (int i = 0; i < count; i += 64)
		{
			if (count - 64 * j < 64)
			{
				list.add(count - 64 * j);
			}
			else
			{
				list.add(64);
			}
			j++;
		}
		return list;
	}

	public void drawCenteredString(String txt, int posX, int posY, int color)
	{
		WyHelper.drawStringWithBorder(this.font, txt, posX - font.getStringWidth(txt) / 2, posY, color);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int partialTicks)
	{	
		boolean flag = super.mouseClicked(mouseX, mouseY, partialTicks);
		
		if (this.guiState == 0)
		{
			if (this.startMessage.ticksExisted < this.startMessage.maxTicks)
				this.startMessage.ticksExisted = this.startMessage.maxTicks;
			else
			{
				if (this.trader.canTrade(this.player))
				{
					this.guiState = 1;
					this.init(this.getMinecraft(), this.width, this.height);
				}
				else
				{
					this.onClose();
				}
			}
		}

		return flag;
	}

	public TradeEntry getSelectedStack()
	{
		return selectedStack;
	}

	public void setSelectedStack(TradeEntry selectedStack)
	{
		this.selectedStack = selectedStack;
	}

	public int getWantedAmount()
	{
		return wantedAmount;
	}

	public void setWantedAmount(int wantedAmount)
	{
		this.wantedAmount = wantedAmount;
	}
	
    public static void open(TraderEntity entity) 
    {
        Minecraft.getInstance().displayGuiScreen(new TraderScreen(entity));
    }
}
