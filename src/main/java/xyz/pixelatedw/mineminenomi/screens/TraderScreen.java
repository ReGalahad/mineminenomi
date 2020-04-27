package xyz.pixelatedw.mineminenomi.screens;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;
import xyz.pixelatedw.mineminenomi.packets.client.CEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CGiveItemStackPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CUpdateTraderStacksPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel.Entry;
import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class TraderScreen extends Screen {

	public static final ResourceLocation BASE = new ResourceLocation(APIConfig.PROJECT_ID, "textures/gui/blank.png");
	public static final ResourceLocation BELI = new ResourceLocation(APIConfig.PROJECT_ID,
			"textures/gui/currencies.png");
	public static final ResourceLocation ARROW = new ResourceLocation(APIConfig.PROJECT_ID,
			"textures/gui/icons/arrow.png");
	public int guiState = 0;
	public int height;
	public int width;
	public int baseHeight;
	public int baseWidth;
	public int wantedAmount = 1;
	public ItemListScreenPanel listPanel;
	public ItemStack selectedStack;
	public ItemStack hoveredStack;
	public TraderEntity entity;
	public PlayerEntity p;
	public IEntityStats stats;
	public SequencedString startMessage;

	public TraderScreen(TraderEntity e, PlayerEntity p) {
		super(new StringTextComponent(""));
		this.entity = e;
		this.p = p;
		this.stats = EntityStatsCapability.get(p);
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {

		switch(this.guiState) {
		case 0:
			this.renderBackground();
		this.startMessage.render();
		if(this.startMessage.ticksExisted > this.startMessage.delayTicks) {
			this.guiState = 1;
			this.init(this.getMinecraft(), p_render_1_, p_render_2_);
		}
		break;
		case 1:
			this.renderMainGui(p_render_1_, p_render_2_, p_render_3_);
			break;
		}
	}

	public void renderMainGui(int arg1, int arg2, float arg3) {
		GlStateManager.pushMatrix();
		GlStateManager.translated(0, 0, -2);
		GlStateManager.scaled(4d, 4d, 1d);
		this.renderBackground();
		GlStateManager.popMatrix();
		this.getMinecraft().getTextureManager().bindTexture(BASE);
		height = this.getMinecraft().mainWindow.getScaledHeight();
		width = this.getMinecraft().mainWindow.getScaledWidth();
		baseHeight = height / 2 - 128 + 40;
		baseWidth = width / 2 - 128 + 8;

		float x = (this.width / 2 - 109) + 40;

		GuiUtils.drawTexturedModalRect(baseWidth, baseHeight, 0, 0, 256, 256, 0);
		GuiUtils.drawTexturedModalRect(baseWidth, baseHeight - 40, 0, 0, 256, 38, 0);
		this.renderUpperColumn();
		this.drawSizedString("Name", MathHelper.floor(x) + 58, baseHeight + 5, 0.8f, -1);
		this.drawSizedString("Price", MathHelper.floor(x) + 112, baseHeight + 5, 0.8f, -1);
		this.getMinecraft().getTextureManager().bindTexture(BELI);
		GuiUtils.drawTexturedModalRect(MathHelper.floor(x) + 116, baseHeight - 10, 0, 32, 32, 64, 1);
		this.listPanel.render(arg1, arg2, arg3);

		this.hover(arg1, arg2);
		this.buttons.forEach((b) -> {
			b.render(arg1, arg2, arg2);
		});
	
	}
	public void renderUpperColumn() {
		if (this.hoveredStack != null) {
			WyHelper.drawIcon(this.getTexture(this.hoveredStack.getItem().getRegistryName().getPath()), baseWidth + 15,
					baseHeight - 30, 16, 16);
			if (TraderEntity.DISPOSABLES.contains(this.hoveredStack.getItem())) {
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/" + "∞" + " x", baseWidth + 65,
						baseHeight - 25, 0.9f, -1);
			} else {
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/"
						+ Integer.toString(this.hoveredStack.getCount()) + " x", baseWidth + 65, baseHeight - 25, 0.9f,
						-1);

			}
		} else if (this.selectedStack != null) {
			WyHelper.drawIcon(this.getTexture(this.selectedStack.getItem().getRegistryName().getPath()), baseWidth + 15,
					baseHeight - 30, 16, 16);
			if (TraderEntity.DISPOSABLES.contains(this.selectedStack.getItem())) {
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/" + "∞" + " x", baseWidth + 65,
						baseHeight - 25, 0.9f, -1);
			} else {
				this.drawSizedString(Integer.toString(this.wantedAmount) + "/"
						+ Integer.toString(this.selectedStack.getCount()) + " x", baseWidth + 65, baseHeight - 25, 0.9f,
						-1);

			}
		}
		this.drawSizedString(Integer.toString(this.stats.getBelly()), baseWidth + 200, baseHeight - 25, 0.9f, -1);
		this.getMinecraft().getTextureManager().bindTexture(BELI);
		GuiUtils.drawTexturedModalRect(baseWidth + 215, baseHeight - 40, 0, 32, 32, 64, 1);

	}

	@Override
	public void init(Minecraft mc, int p_init_2_, int p_init_3_) {

		super.init(mc, p_init_2_, p_init_3_);
		height = this.getMinecraft().mainWindow.getScaledHeight();
		width = this.getMinecraft().mainWindow.getScaledWidth();
		baseHeight = height / 2 - 128 + 40;
		baseWidth = width / 2 - 128 + 8;
		if(startMessage == null) {
		startMessage = new SequencedString("Welcome to my humble Shop!", this, baseWidth, baseHeight, 40, 3 * 20);
		}
		if(guiState == 1) {
		entity.setStacksFromNBT(entity.getNBT());

		this.listPanel = new ItemListScreenPanel(this, entity.STACKS);
		this.children.add(this.listPanel);
		this.addButton(new TexturedIconButton(ARROW, baseWidth + 75, baseHeight - 20, 16, 16, "positive_arrow",
				new Button.IPressable() {

					@Override
					public void onPress(Button p_onPress_1_) {
						if (((TraderScreen) mc.currentScreen).selectedStack != null) {
							if (((TraderScreen) mc.currentScreen).wantedAmount < ((TraderScreen) mc.currentScreen).selectedStack
									.getCount() || TraderEntity.DISPOSABLES.contains(((TraderScreen) mc.currentScreen).selectedStack.getItem())) {
								((TraderScreen) mc.currentScreen).wantedAmount += 1;
							}
						}
					}
				}));

		this.addButton(new TexturedIconButton(ARROW, baseWidth + 65, baseHeight - 20, 16, 16, "negative_arrow",
				new Button.IPressable() {

					@Override
					public void onPress(Button p_onPress_1_) {
						if (((TraderScreen) mc.currentScreen).selectedStack != null) {
							if (((TraderScreen) mc.currentScreen).wantedAmount > 1) {
								((TraderScreen) mc.currentScreen).wantedAmount -= 1;
							}
						}
					}
				}));
		this.addButton(new TexturedIconButton(ARROW, baseWidth + 200, baseHeight - 20, 16, 16, "buy",
				new Button.IPressable() {

					@Override
					public void onPress(Button p_onPress_1_) {

						TraderScreen scr = (TraderScreen) Minecraft.getInstance().currentScreen;
						if (scr.selectedStack != null) {
							if (scr.wantedAmount <= scr.selectedStack.getCount()
									|| TraderEntity.DISPOSABLES.contains(scr.selectedStack.getItem())) {
								if (scr.getEmptySlots() >= scr.calculateSlotsFromCount(scr.wantedAmount)) {
									List<Integer> list = scr.getStacks(scr.wantedAmount);
									if (scr.stats.getBelly() >= scr.entity.getPrice(scr.selectedStack)
											* scr.wantedAmount) {
										for (int i : list) {
											ItemStack stack = new ItemStack(scr.selectedStack.getItem());
											stack.setCount(i);
											WyNetwork.sendToServer(new CGiveItemStackPacket(stack));
											p.inventory.addItemStackToInventory(stack);
										

										}
										scr.stats.alterBelly(-(scr.entity.getPrice(scr.selectedStack) * scr.wantedAmount));
										if(!TraderEntity.DISPOSABLES.contains(scr.selectedStack.getItem())) {
											if(scr.selectedStack.getCount() - scr.wantedAmount <= 0) {
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
				}));

		}
	}

	public ResourceLocation getTexture(String str) {
		return new ResourceLocation(APIConfig.PROJECT_ID, "textures/items/" + str + ".png");
	}

	public void drawSizedString(String txt, int x, int y, float scale, int color) {
		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y, 0);
		GlStateManager.scalef(scale, scale, scale);
		if (color == -1) {
			WyHelper.drawCenteredString(txt, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
		} else {
			WyHelper.drawCenteredString(txt, 0, 0, color);
		}

		GlStateManager.popMatrix();
	}

	public void drawStringWithoutShadow(String txt, int x, int y, float scale, int color) {
		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y, 0);
		GlStateManager.scalef(scale, scale, scale);
		if (color == -1) {
			this.font.drawString(txt, 0, 0, color);
		} else {
			this.font.drawString(txt, 0, 0, color);
		}

		GlStateManager.popMatrix();

	}

	public void hover(int mouseX, int mouseY) {
		Entry entry = this.listPanel.findStackEntry(mouseX, mouseY);
		if (entry != null) {
			this.hoveredStack = entry.stack;
			this.wantedAmount = 1;
		} else {
			this.hoveredStack = null;
		}
	}

	public int getEmptySlots() {
		int i = 0;
		for (ItemStack stack : this.p.inventory.mainInventory) {
			if (stack.isEmpty()) {
				i++;
			}
		}
		return i;
	}

	public int calculateSlotsFromCount(int count) {
		double val = count / 64.0;
		return MathHelper.ceil(val);
	}

	public List<Integer> getStacks(int count) {
		List<Integer> list = new ArrayList<>();
		int j = 0;
		for (int i = 0; i < count; i += 64) {
			if (count - 64 * j < 64) {
				list.add(count - 64 * j);
			} else {
				list.add(64);
			}
			j++;
		}
		return list;
	}
	@Override
	public void onClose() {
		this.entity.setIsOpened(this.entity.getNBT(), false);
		WyNetwork.sendToServer(new CUpdateTraderStacksPacket(this.entity));
		      this.minecraft.displayGuiScreen((Screen)null);
		   }

}
