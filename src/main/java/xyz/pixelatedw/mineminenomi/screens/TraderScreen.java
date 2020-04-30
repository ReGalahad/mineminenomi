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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.containers.TraderContainer;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel.Entry;
import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class TraderScreen extends ContainerScreen<TraderContainer>
{
	public int guiState = 0;
	public boolean shouldTrade = true;
	public int height;
	public int width;
	public int baseHeight;
	public int baseWidth;
	public int wantedAmount = 1;
	public ItemListScreenPanel listPanel;
	public ItemStack selectedStack;
	public ItemStack hoveredStack;
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
		//this.renderBackground();
		//super.render(mouseX, mouseY, partialTicks);

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
		//this.startMessage.render();
		/*if (this.startMessage.ticksExisted > this.startMessage.delayTicks)
		{
			if (this.shouldTrade)
			{
				this.guiState = 1;
				this.init(this.getMinecraft(), x, y);
			}
			else
				this.onClose();
		}*/

		int posX = this.width / 2;
		int posY = this.height / 2;

		// Quest Giver model
		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			GlStateManager.enableBlend();
		//	WyHelper.drawEntityOnScreen(posX + 150, posY + 150, 100, 40, 5, );
		}
		GlStateManager.popMatrix();

	}

	public void renderMainGui(int arg1, int arg2, float arg3)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translated(0, 0, 0);
		GlStateManager.scaled(8d, 8d, 1d);
		this.renderBackground();
		GlStateManager.popMatrix();
		this.getMinecraft().getTextureManager().bindTexture(ModResources.BLANK2);
		this.height = this.getMinecraft().mainWindow.getScaledHeight();
		this.width = this.getMinecraft().mainWindow.getScaledWidth();
		this.baseHeight = this.height / 2 - 138 + 40;
		this.baseWidth = this.width / 2 - 128 + 8;

		float x = (this.width / 2 - 109) + 40;

		GuiUtils.drawTexturedModalRect(this.baseWidth, this.baseHeight, 0, 0, 256, 256, 0);
		// GuiUtils.drawTexturedModalRect(baseWidth, this.baseHeight - 40, 0, 0, 256, 38, 0);
		this.renderUpperColumn();
		this.drawSizedString("Name", MathHelper.floor(x) + 58, this.baseHeight + 5, 0.8f, -1);
		this.drawSizedString("Price", MathHelper.floor(x) + 112, this.baseHeight + 5, 0.8f, -1);
		this.getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(MathHelper.floor(x) + 116, this.baseHeight - 10, 0, 32, 32, 64, 1);
		this.listPanel.render(arg1, arg2, arg3);

		this.hover(arg1, arg2);
		this.buttons.forEach((b) ->
		{
			b.render(arg1, arg2, arg2);
		});

	}

	public void renderUpperColumn()
	{
		/*
		if (this.hoveredStack != null)
		{
			WyHelper.drawIcon(this.getTexture(this.hoveredStack.getItem().getRegistryName().getPath()), this.baseWidth + 15, baseHeight - 30, 16, 16);
			if (TraderEntity.DISPOSABLES.contains(this.hoveredStack.getItem()))
			{
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/" + "∞" + " x", this.baseWidth + 65, baseHeight - 25, 0.9f, -1);
			}
			else
			{
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/" + Integer.toString(this.hoveredStack.getCount()) + " x", this.baseWidth + 65, this.baseHeight - 25, 0.9f, -1);

			}
		}
		else if (this.selectedStack != null)
		{
			WyHelper.drawIcon(this.getTexture(this.selectedStack.getItem().getRegistryName().getPath()), this.baseWidth + 15, baseHeight - 30, 16, 16);
			if (TraderEntity.DISPOSABLES.contains(this.selectedStack.getItem()))
			{
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/" + "∞" + " x", this.baseWidth + 65, baseHeight - 25, 0.9f, -1);
			}
			else
			{
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/" + Integer.toString(this.selectedStack.getCount()) + " x", this.baseWidth + 65, this.baseHeight - 25, 0.9f, -1);

			}
		}*/
		this.drawSizedString(Integer.toString(this.stats.getBelly()), this.baseWidth + 200, this.baseHeight - 25, 0.9f, -1);
		this.getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
		GuiUtils.drawTexturedModalRect(baseWidth + 215, this.baseHeight - 40, 0, 32, 32, 64, 1);

	}

	@Override
	public void init(Minecraft mc, int width, int height)
	{
		super.init(mc, width, height);
		this.height = this.getMinecraft().mainWindow.getScaledHeight();
		this.width = this.getMinecraft().mainWindow.getScaledWidth();
		this.baseHeight = this.height / 2 - 128 + 40;
		this.baseWidth = this.width / 2 - 128 + 8;
		
		/*if (this.entity.getFaction() == "")
		{
			if (startMessage == null)
			{
				startMessage = new SequencedString("Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.", this, this.baseWidth, this.baseHeight, 250, 16 * 20);
			}

		}
		else
		{
			if (startMessage == null)
			{
				if (!this.stats.getFaction().contains(this.entity.getFaction()))
				{
					if (this.stats.isPirate())
					{
						startMessage = new SequencedString("I don't trade to Pirate Scum", this, baseWidth, baseHeight, 250, 16 * 20);
						this.shouldTrade = false;

					}
					else if (this.stats.isMarine())
					{
						if (this.entity.getFaction().equalsIgnoreCase("bountyhunter"))
						{
							startMessage = new SequencedString("Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.", this, baseWidth, baseHeight, 250, 16 * 20);

						}
						else
						{
							startMessage = new SequencedString("I don't support the Marines.", this, baseWidth, baseHeight, 250, 16 * 20);
							this.shouldTrade = false;
						}
					}
					else if (this.stats.isBountyHunter())
					{
						startMessage = new SequencedString("Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.", this, baseWidth, baseHeight, 250, 16 * 20);

					}
					else
					{
						startMessage = new SequencedString("Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.", this, baseWidth, baseHeight, 250, 16 * 20);

					}
				}
				else
				{
					startMessage = new SequencedString("Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.", this, baseWidth, baseHeight, 250, 16 * 20);

				}
			}
		}*/
		if (guiState == 1)
		{
		//	entity.setStacksFromNBT(entity.getNBT());

		//	this.listPanel = new ItemListScreenPanel(this, entity.STACKS);
			this.children.add(this.listPanel);
			// Increase Quantity
			this.addButton(new TexturedIconButton(ModResources.WOOD_ARROW, this.baseWidth + 75, this.baseHeight - 20, 16, 16, "positive_arrow", this::onIncreaseQuantity));
			// Decrease Quantity
			this.addButton(new TexturedIconButton(ModResources.WOOD_ARROW, this.baseWidth + 65, this.baseHeight - 20, 16, 16, "negative_arrow", this::onDecreaseQuantity));
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
		//if (this.selectedStack != null && (this.wantedAmount < this.selectedStack.getCount() || TraderEntity.DISPOSABLES.contains(this.selectedStack.getItem())))
		//	this.wantedAmount += 1;
	}
	
	public void onDecreaseQuantity(Button btn)
	{
		if (this.selectedStack != null && this.wantedAmount > 1)
			this.wantedAmount -= 1;
	}
	
	public ResourceLocation getTexture(String str)
	{
		return new ResourceLocation(APIConfig.PROJECT_ID, "textures/items/" + str + ".png");
	}

	public void drawSizedString(String txt, int x, int y, float scale, int color)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y, 0);
		GlStateManager.scalef(scale, scale, scale);
		if (color == -1)
		{
			this.drawCenteredString(txt, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
		}
		else
		{
			this.drawCenteredString(txt, 0, 0, color);
		}

		GlStateManager.popMatrix();
	}

	public void hover(int mouseX, int mouseY)
	{
		Entry entry = this.listPanel.findStackEntry(mouseX, mouseY);
		if (entry != null)
		{
			this.hoveredStack = entry.stack;
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
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_)
	{	
		boolean flag = super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
		/*
		if (guiState == 0)
		{

			if (this.startMessage.ticksExisted < this.startMessage.maxTicks)
			{
				this.startMessage.ticksExisted = this.startMessage.maxTicks;
			}
			else
			{
				if (this.shouldTrade)
				{
					guiState = 1;
					this.init(this.getMinecraft(), (int) (p_mouseClicked_1_), (int) (p_mouseClicked_3_));
				}
				else
				{
					this.onClose();
				}
			}
		}*/

		return flag;
	}
}
