package xyz.pixelatedw.mineminenomi.screens;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.containers.TraderContainer;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class TraderScreen extends ContainerScreen<TraderContainer>
{
	public int guiState = 0;
	public boolean shouldTrade = true;
	public int wantedAmount = 1;
	public ItemListScreenPanel listPanel;
	public TradeEntry selectedStack;
	public TradeEntry hoveredStack;
	public PlayerEntity player;
	public IEntityStats stats;
	public SequencedString startMessage;

	public TraderScreen(TraderContainer container, PlayerInventory inventory, ITextComponent text)
	{
		super(container, inventory, text);
		this.player = Minecraft.getInstance().player;
		this.stats = EntityStatsCapability.get(player);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		
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
		this.startMessage.render();
		if (this.startMessage.ticksExisted > this.startMessage.delayTicks)
		{
			if (this.shouldTrade)
			{
				this.guiState = 1;
				this.init(this.getMinecraft(), this.width, this.height);
			}
			else
				this.onClose();
		}

		int posX = this.width / 2;
		int posY = this.height / 2;
		
		// Trader model
		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			GlStateManager.enableBlend();
			WyHelper.drawEntityOnScreen(posX + 150, posY + 150, 100, 40, 5, this.container.getTrader());
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
		
		this.drawSizedString("Name", posX - 5, posY - 63, 0.9f, -1);
		this.drawSizedString("Price", posX + 50, posY - 63, 0.9f, -1);
		this.getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(posX + 53, posY - 76, 0, 32, 32, 64, 1);

		this.listPanel.render(mouseX, mouseY, partialTicks);

		this.hover(mouseX, mouseY);
		this.buttons.forEach((b) ->
		{
			b.render(mouseX, mouseY, partialTicks);
		});
	}

	public void renderUpperColumn()
	{
		int posX = this.width / 2;
		int posY = this.height / 2;
		String amount = "";
		
		if (this.hoveredStack != null)
		{
			WyHelper.drawIcon(this.getTexture(this.hoveredStack.getItemStack().getItem()), posX - 110, posY - 100, 16, 16);
			amount = this.wantedAmount + "/" + this.hoveredStack.getCount();
			if (this.hoveredStack.hasInfiniteStock())
				amount = this.wantedAmount + "/∞";
		}
		else if (this.selectedStack != null)
		{
			WyHelper.drawIcon(this.getTexture(this.selectedStack.getItemStack().getItem()), posX - 110, posY - 100, 16, 16);
			amount = this.wantedAmount + "/" + this.selectedStack.getCount();
			if (this.selectedStack.hasInfiniteStock())
				amount = this.wantedAmount + "/∞";
		}
		
		this.drawSizedString(amount, posX - 70, posY - 100, 0.9f, -1);
		this.drawSizedString(this.stats.getBelly() + "", posX + 85, posY - 100, 0.9f, -1);
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
			this.startMessage = new SequencedString(new TranslationTextComponent(ModI18n.TRADER_WELCOME_MESSAGE).getFormattedText(), this, posX - 200, posY - 50, 250, 14 * 20);
		
		if (this.guiState == 1)
		{
			this.listPanel = new ItemListScreenPanel(this, this.container.getTradingItems());
			this.children.add(this.listPanel);
			
			// Increase Quantity
			this.addButton(new TexturedIconButton(ModResources.WOOD_ARROW, posX - 85, posY - 90, 16, 16, "positive_arrow", this::onIncreaseQuantity));
			// Decrease Quantity
			this.addButton(new TexturedIconButton(ModResources.WOOD_ARROW, posX - 70, posY - 90, 16, 16, "negative_arrow", this::onDecreaseQuantity));
			/*
			this.addButton(new TexturedIconButton(ModResources.WOOD_ARROW, this.baseWidth + 200, this.baseHeight - 20, 16, 16, "buy", new Button.IPressable()
			{

				@Override
				public void onPress(Button p_onPress_1_)
				{

					TraderScreen scr = (TraderScreen) Minecraft.getInstance().currentScreen;
					if (scr.selectedStack != null)
					{
						if (scr.wantedAmount <= scr.selectedStack.getCount() || TraderEntity.DISPOSABLES.contains(scr.selectedStack.getItem()))
						{
							if (scr.getEmptySlots() >= scr.calculateSlotsFromCount(scr.wantedAmount))
							{
								List<Integer> list = scr.getStacks(scr.wantedAmount);
								if (scr.stats.getBelly() >= scr.entity.getPrice(scr.selectedStack) * scr.wantedAmount)
								{
									for (int i : list)
									{
										ItemStack stack = new ItemStack(scr.selectedStack.getItem());
										stack.setCount(i);
										WyNetwork.sendToServer(new CGiveItemStackPacket(stack));
										player.inventory.addItemStackToInventory(stack);

									}
									scr.stats.alterBelly(-(scr.entity.getPrice(scr.selectedStack) * scr.wantedAmount));
									if (!TraderEntity.DISPOSABLES.contains(scr.selectedStack.getItem()))
									{
										if (scr.selectedStack.getCount() - scr.wantedAmount <= 0)
										{
											scr.entity.removeStackFromList(scr.selectedStack);
										}

										scr.entity.changeStackCount(scr.selectedStack, scr.selectedStack.getCount() - scr.wantedAmount);
										scr.entity.updateNbtFromStacks(false);
										scr.listPanel.removeEntry(scr.selectedStack);
										WyNetwork.sendToServer(new CEntityStatsSyncPacket(scr.stats));
									}
								}
							}
						}
					}
				}
			}));*/

		}
	}

	public void onIncreaseQuantity(Button btn)
	{
		if (this.selectedStack != null && (this.wantedAmount < this.selectedStack.getCount() || this.selectedStack.hasInfiniteStock()))
			this.wantedAmount += 1;
	}
	
	public void onDecreaseQuantity(Button btn)
	{
		if (this.selectedStack != null && this.wantedAmount > 1)
			this.wantedAmount -= 1;
	}
	
	public ResourceLocation getTexture(Item item)
	{	
		return new ResourceLocation(item.getRegistryName().getNamespace(), "textures/items/" + item.getRegistryName().getPath() + ".png");
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
			this.wantedAmount = 1;
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
		this.drawString(this.font, txt, posX - font.getStringWidth(txt) / 2, posY, color);
	}

	@Override
	public void onClose()
	{
		//this.entity.setIsOpened(this.entity.getNBT(), false);
		//WyNetwork.sendToServer(new CUpdateTraderStacksPacket(this.entity));
		this.minecraft.displayGuiScreen((Screen) null);
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
				if (this.shouldTrade)
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
}
