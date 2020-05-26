package xyz.pixelatedw.mineminenomi.screens;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.config.GuiSlider;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement.LayerType;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.IJollyRoger;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.JollyRogerCapability;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.CJollyRogerSyncPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class JollyRogerCreatorScreen extends Screen
{
	private PlayerEntity player;
	private Widget selectedButton;
	//private JollyRogerElement selectedElement;
	private LayerType layerType = LayerType.BASE;
	private IJollyRoger props;

	private int layerIndex;
	private int trueIndex;
	private GuiSlider redSlider;
	private GuiSlider greenSlider;
	private GuiSlider blueSlider;
	private Collection<RegistryObject<JollyRogerElement>> allElements;
	private List<RegistryObject<JollyRogerElement>> allBases;
	private List<RegistryObject<JollyRogerElement>> allBackgrounds;
	private List<RegistryObject<JollyRogerElement>> allDetails;

	public JollyRogerCreatorScreen()
	{
		super(new StringTextComponent(""));
		this.player = Minecraft.getInstance().player;
		this.props = JollyRogerCapability.get(this.player);

		this.allElements = ModJollyRogers.JOLLY_ROGER_ELEMENTS.getEntries();
		this.allBases = this.getTotalElementsForType(this.player, LayerType.BASE);
		this.allBackgrounds = this.getTotalElementsForType(this.player, LayerType.BACKGROUND);
		this.allDetails = this.getTotalElementsForType(this.player, LayerType.DETAIL);	
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = this.width / 2;
		int posY = this.height / 2;

		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

		GL11.glPushMatrix();
		{
			double scale = 0.5;
			GL11.glTranslated(posX - 115, posY - 130, 1);
			GL11.glTranslated(128, 128, 0);
			GL11.glScaled(scale, scale, scale);
			GL11.glTranslated(-128, -128, 0);

			// Draw the black flag background
			//this.fillGradient(-10, 0, 0 + 270, 0 + 260, WyHelper.hexToRGB("#111115").getRGB(), WyHelper.hexToRGB("#202025").getRGB());
			
			RendererHelper.drawPlayerJollyRoger(this.props);
		}
		GL11.glPopMatrix();

		String text = this.trueIndex >= 0 ? (this.trueIndex + 1) + " / " + this.allBases.size() : "Empty";
		if (this.layerType == LayerType.BACKGROUND)
			text = this.trueIndex >= 0 ? (this.trueIndex + 1) + " / " + this.allBackgrounds.size() : "Empty";
		else if (this.layerType == LayerType.DETAIL)
			text = this.trueIndex >= 0 ? (this.trueIndex + 1) + " / " + this.allDetails.size() : "Empty";
		WyHelper.drawStringWithBorder(this.font, text, posX - this.font.getStringWidth(text) / 2 + 12, posY + 80, WyHelper.hexToRGB("#FFFFFF").getRGB());

		GlStateManager.disableBlend();

		posX = this.width;
		
		int outlineSize = 2;
		int pX = posX - 95;
		int pY = posY - 85;
		int sW = posX + 100;
		int sH = posY + 45;

		//if(this.layerIndex <= 0)
		//	this.fillGradient(pX, pY, sW, sH, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
		
		this.fillGradient(pX - outlineSize, pY - outlineSize, sW + outlineSize, sH + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
		this.fillGradient(pX, pY, sW, sH, WyHelper.hexToRGB("#B3B3B3").getRGB(), WyHelper.hexToRGB("#505050").getRGB());
		
		posY = posY - 75;		
		WyHelper.drawStringWithBorder(this.font, "Red ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
		WyHelper.drawStringWithBorder(this.font, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
		WyHelper.drawStringWithBorder(this.font, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());

		posY += 40;	
		WyHelper.drawStringWithBorder(this.font, "Green ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
		WyHelper.drawStringWithBorder(this.font, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
		WyHelper.drawStringWithBorder(this.font, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());

		posY += 40;	
		WyHelper.drawStringWithBorder(this.font, "Blue ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
		WyHelper.drawStringWithBorder(this.font, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
		WyHelper.drawStringWithBorder(this.font, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
	
		super.render(x, y, f);
	}

	@Override
	public void init(Minecraft mc, int width, int height)
	{
		super.init(mc, width, height);

		int posX = 0;
		int posY = this.height / 2;

		int listPosY = posY - 85;

		NoTextureButton baseButton = new NoTextureButton(posX + 5, listPosY, 115, 16, "Base", this::selectButton);
		this.addButton(baseButton);

		for (int i = 0; i < this.props.getBackgrounds().length; i++)
		{
			NoTextureButton bgButton = new NoTextureButton(posX + 5, (listPosY + 20 + (i * 20)), 115, 16, "Background " + (i + 1), this::selectButton);
			this.addButton(bgButton);
		}

		for (int i = 0; i < this.props.getDetails().length; i++)
		{
			NoTextureButton detailButton = new NoTextureButton(posX + 5, (listPosY + 60 + (i * 20)), 115, 16, "Detail " + (i + 1), this::selectButton);
			this.addButton(detailButton);
		}

		posX = this.width / 2;

		TexturedIconButton nextBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 80, posY - 65, 32, 110, "", (btn) -> this.moveIndex(btn, true));
		nextBaseTexture = nextBaseTexture.setTextureInfo(posX + 80, posY - 75, 32, 128);
		this.addButton(nextBaseTexture);

		TexturedIconButton prevBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 85, posY - 65, 32, 110, "", (btn) -> this.moveIndex(btn, false));
		prevBaseTexture = prevBaseTexture.setTextureInfo(posX - 85, posY - 75, 32, 128);
		this.addButton(prevBaseTexture);
		
		posX = this.width;

		GuiSlider redColorPicker = new GuiSlider(posX - 76, posY - 65, 50, 16, "", "", 0, 255, 255, false, true, (btn) -> {}, (slider) -> this.changeColor(slider, "red"));
		this.redSlider = redColorPicker;
		this.addButton(redColorPicker);	
		
		GuiSlider greenColorPicker = new GuiSlider(posX - 76, posY - 25, 50, 16, "", "", 0, 255, 255, false, true, (btn) -> {}, (slider) -> this.changeColor(slider, "green"));
		this.greenSlider = greenColorPicker;
		this.addButton(greenColorPicker);	
		
		GuiSlider blueColorPicker = new GuiSlider(posX - 76, posY + 15, 50, 16, "", "", 0, 255, 255, false, true, (btn) -> {}, (slider) -> this.changeColor(slider, "blue"));
		this.blueSlider = blueColorPicker;
		this.addButton(blueColorPicker);	
	}

	public void changeColor(GuiSlider slider, String color)
	{
		if(!slider.isHovered())
			slider.dragging = false;
		
		if (this.layerType == LayerType.BASE)
		{
			if(this.props.getBase() == null)
				return;
			
			String currentColor = this.props.getBase().getColor();
			Color rgb = WyHelper.hexToRGB(currentColor);
			
			String hex = "#FFFFFF";
			
			if(color.equalsIgnoreCase("red"))
				hex = WyHelper.rgbToHex(slider.getValueInt(), rgb.getGreen(), rgb.getBlue());
			else if(color.equalsIgnoreCase("green"))
				hex = WyHelper.rgbToHex(rgb.getRed(), slider.getValueInt(), rgb.getBlue());	
			else if(color.equalsIgnoreCase("blue"))
				hex = WyHelper.rgbToHex(rgb.getRed(), rgb.getGreen(), slider.getValueInt());
			
			this.props.getBase().setColor(hex);
		}
		else if (this.layerType == LayerType.BACKGROUND)
		{
			if(this.props.getBackgrounds()[this.layerIndex] == null)
				return;
			
			String currentColor = this.props.getBackgrounds()[this.layerIndex].getColor();
			Color rgb = WyHelper.hexToRGB(currentColor);
			
			String hex = "#FFFFFF";
			
			if(color.equalsIgnoreCase("red"))
				hex = WyHelper.rgbToHex(slider.getValueInt(), rgb.getGreen(), rgb.getBlue());
			else if(color.equalsIgnoreCase("green"))
				hex = WyHelper.rgbToHex(rgb.getRed(), slider.getValueInt(), rgb.getBlue());	
			else if(color.equalsIgnoreCase("blue"))
				hex = WyHelper.rgbToHex(rgb.getRed(), rgb.getGreen(), slider.getValueInt());
			
			this.props.getBackgrounds()[this.layerIndex].setColor(hex);
		}
		else if (this.layerType == LayerType.DETAIL)
		{
			if(this.props.getDetails()[this.layerIndex] == null)
				return;
			
			String currentColor = this.props.getDetails()[this.layerIndex].getColor();
			Color rgb = WyHelper.hexToRGB(currentColor);
			
			String hex = "#FFFFFF";
			
			if(color.equalsIgnoreCase("red"))
				hex = WyHelper.rgbToHex(slider.getValueInt(), rgb.getGreen(), rgb.getBlue());
			else if(color.equalsIgnoreCase("green"))
				hex = WyHelper.rgbToHex(rgb.getRed(), slider.getValueInt(), rgb.getBlue());	
			else if(color.equalsIgnoreCase("blue"))
				hex = WyHelper.rgbToHex(rgb.getRed(), rgb.getGreen(), slider.getValueInt());
			
			this.props.getDetails()[this.layerIndex].setColor(hex);
		}
	}
	
	public void moveIndex(Button btn, boolean toRight)
	{
		try
		{
			if(toRight)
				this.trueIndex += 1;
			else
				this.trueIndex -= 1;
			
			if (this.layerType == LayerType.BASE)
			{
				if(this.trueIndex >= this.allBases.size())
					this.trueIndex = -1;
				if(this.trueIndex < 0 && this.props.getBase() == null)
					this.trueIndex = this.allBases.size() - 1;

				if(this.trueIndex >= 0 && this.trueIndex <= this.allBases.size())
					this.props.setBase(this.allBases.get(this.trueIndex).get());
				else if(this.trueIndex <= 0 && this.props.getBase().getTexture() != null)
					this.props.setBase(null);
				
				for(int i = 0; i < this.props.getBackgrounds().length; i++)
				{
					JollyRogerElement element = this.props.getBackgrounds()[i];
					boolean hasElement = this.allBackgrounds.stream().anyMatch(elem -> elem != null && elem.get() != null && elem.get().equals(element) && !elem.get().canUse(this.player));
					if(hasElement)
						this.props.getBackgrounds()[i] = null;
				}
				
				for(int i = 0; i < this.props.getDetails().length; i++)
				{
					JollyRogerElement element = this.props.getDetails()[i];
					boolean hasElement = this.allDetails.stream().anyMatch(elem -> elem != null && elem.get() != null && elem.get().equals(element) && !elem.get().canUse(this.player));
					if(hasElement)
						this.props.getDetails()[i] = null;
				}
			}
			else if (this.layerType == LayerType.BACKGROUND)
			{
				if(this.trueIndex >= this.allBackgrounds.size())
					this.trueIndex = -1;
				if(this.trueIndex < 0 && this.props.getBackgrounds()[this.layerIndex] == null)
					this.trueIndex = this.allBackgrounds.size() - 1;
				
				if(this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size())
				{
					JollyRogerElement ogElem = this.allBackgrounds.get(this.trueIndex).get();
					for(int i = 0; i < this.props.getBackgrounds().length; i++)
					{
						JollyRogerElement element = this.props.getBackgrounds()[i];
						if(element != null && ogElem != null && ogElem.equals(element))
						{
							this.moveIndex(btn, toRight);
							return;
						}
					}
				}
				
				if(this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size())
					this.props.getBackgrounds()[this.layerIndex] = this.allBackgrounds.get(this.trueIndex).get();
				else if(this.trueIndex <= 0 && this.props.getBackgrounds()[this.layerIndex].getTexture() != null)
					this.props.getBackgrounds()[this.layerIndex] = null;
			}
			else if (this.layerType == LayerType.DETAIL)
			{
				if(this.trueIndex >= this.allDetails.size())
					this.trueIndex = -1;
				if(this.trueIndex < 0 && this.trueIndex <= this.allDetails.size() && this.props.getDetails()[this.layerIndex] == null)
					this.trueIndex = this.allDetails.size() - 1;

				if(this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size())
				{
					JollyRogerElement ogElem = this.allDetails.get(this.trueIndex).get();
					for(int i = 0; i < this.props.getDetails().length; i++)
					{
						JollyRogerElement element = this.props.getDetails()[i];
						if(element != null && ogElem != null && ogElem.equals(element))
						{
							this.moveIndex(btn, toRight);
							return;
						}
					}
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size())
					this.props.getDetails()[this.layerIndex] = this.allDetails.get(this.trueIndex).get();
				else if(this.trueIndex <= 0 && this.props.getDetails()[this.layerIndex].getTexture() != null)
					this.props.getDetails()[this.layerIndex] = null;
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void selectButton(Button btn)
	{
		if (!(btn instanceof NoTextureButton))
			return;

		if (this.selectedButton != null)
			((NoTextureButton) this.selectedButton).select();
		this.selectedButton = btn;
		((NoTextureButton) btn).select();

		if (this.buttons.get(0) == btn)
		{
			this.trueIndex = this.findIndex(this.getListFromType(LayerType.BASE), this.props.getBase(), this.player);
			this.layerType = LayerType.BASE;
			
			this.resetColorSliders(this.props.getBase());

			return;
		}

		int j = 0;
		for (int i = 1; i < this.props.getBackgrounds().length + 1; i++)
		{
			if (this.buttons.get(i) == btn)
			{
				this.trueIndex = this.findIndex(this.getListFromType(LayerType.BACKGROUND), this.props.getBackgrounds()[j], this.player);
				this.layerType = LayerType.BACKGROUND;
				this.allBackgrounds = this.getTotalElementsForType(this.player, LayerType.BACKGROUND);
				this.layerIndex = j;
				
				this.resetColorSliders(this.props.getBackgrounds()[j]);

				return;
			}
			j++;
		}

		j = 0;
		for (int i = this.props.getBackgrounds().length + 1; i < this.props.getDetails().length + this.props.getBackgrounds().length + 1; i++)
		{
			if (this.buttons.get(i) == btn)
			{
				this.trueIndex = this.findIndex(this.getListFromType(LayerType.DETAIL), this.props.getDetails()[j], this.player);
				this.layerType = LayerType.DETAIL;
				this.allDetails = this.getTotalElementsForType(this.player, LayerType.DETAIL);	
				this.layerIndex = j;
				
				this.resetColorSliders(this.props.getDetails()[j]);
				
				return;
			}
			j++;
		}
	}

	private void resetColorSliders(JollyRogerElement element)
	{
		if(element != null)
		{
			Color rgb = WyHelper.hexToRGB(element.getColor());	
			this.redSlider.setValue(rgb.getRed());
			this.redSlider.updateSlider();
			this.greenSlider.setValue(rgb.getGreen());
			this.greenSlider.updateSlider();
			this.blueSlider.setValue(rgb.getBlue());
			this.blueSlider.updateSlider();
		}
		else
		{
			this.redSlider.setValue(255);
			this.redSlider.updateSlider();
			this.greenSlider.setValue(255);
			this.greenSlider.updateSlider();
			this.blueSlider.setValue(255);
			this.blueSlider.updateSlider();
		}
	}
	
	@Override
	public void onClose()
	{
		WyNetwork.sendToServer(new CJollyRogerSyncPacket(this.props));
		super.onClose();
	}

	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
	private int findIndex(List<RegistryObject<JollyRogerElement>> elements, JollyRogerElement element, PlayerEntity player)
	{
		for (int i = 0; i < elements.size(); i++)
		{
			if (elements.get(i).get().equals(element))
			{
				return i;
			}
		}

		return -1;
	}

	public List<RegistryObject<JollyRogerElement>> getListFromType(LayerType type)
	{
		if (type == LayerType.BASE)
			return this.allBases;
		else if (type == LayerType.BACKGROUND)
			return this.allBackgrounds;
		else if (type == LayerType.DETAIL)
			return this.allDetails;

		return this.allBases;
	}

	public List<RegistryObject<JollyRogerElement>> getTotalElementsForType(PlayerEntity player, LayerType type)
	{
		return this.allElements.stream().filter(reg -> reg.get().getLayerType() == type && reg.get().canUse(player)).collect(Collectors.toList());
	}

	public static void open()
	{
		Minecraft.getInstance().displayGuiScreen(new JollyRogerCreatorScreen());
	}
}
