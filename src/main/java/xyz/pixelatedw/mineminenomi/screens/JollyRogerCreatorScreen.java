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
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRoger;
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
	private JollyRoger jollyRoger;
	private Widget selectedButton;
	private JollyRogerElement selectedElement;
	private LayerType layerType = LayerType.BASE;
	private IJollyRoger props;

	private int currentIndex;
	private int trueIndex;
	private Collection<RegistryObject<JollyRogerElement>> allElements;
	private List<RegistryObject<JollyRogerElement>> allBases;
	private List<RegistryObject<JollyRogerElement>> allBackgrounds;
	private List<RegistryObject<JollyRogerElement>> allDetails;

	public JollyRogerCreatorScreen()
	{
		super(new StringTextComponent(""));
		this.player = Minecraft.getInstance().player;
		this.props = JollyRogerCapability.get(this.player);
		this.jollyRoger = new JollyRoger();

		if(this.props.getBase() != null)
			this.jollyRoger.getBase().setTexture(this.props.getBase().getTexture());
	
		for(int i = 0; i < this.jollyRoger.getBackgrounds().length; i++)
		{
			if(this.props.getBackgrounds()[i] != null)
				this.jollyRoger.getBackgrounds()[i].setTexture(this.props.getBackgrounds()[i].getTexture());
		}
		
		for(int i = 0; i < this.jollyRoger.getDetails().length; i++)
		{
			if(this.props.getDetails()[i] != null)
				this.jollyRoger.getDetails()[i].setTexture(this.props.getDetails()[i].getTexture());
		}
		
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
			GL11.glTranslated(posX - 100, posY - 130, 1);
			GL11.glTranslated(128, 128, 0);
			GL11.glScaled(scale, scale, scale);
			GL11.glTranslated(-128, -128, 0);

			// Jolly Roger draw with all the backgrounds and layers
			// Drawing the base
			if (this.jollyRoger.getBase() != null && this.jollyRoger.getBase().getTexture() != null)
			{
				if (this.jollyRoger.getBase().canBeColored())
				{
					Color color = WyHelper.hexToRGB(this.jollyRoger.getBase().getColor());
					GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
				}
				else
				{
					GlStateManager.color3f(1.0F, 1.0F, 1.0F);
				}
				Minecraft.getInstance().getTextureManager().bindTexture(this.jollyRoger.getBase().getTexture());
				GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 2);
			}

			// Drawing the backgrounds
			for (JollyRogerElement element : this.jollyRoger.getBackgrounds())
			{
				int i = 0;
				if (element != null && element.getTexture() != null)
				{
					if (element.canBeColored())
					{
						Color color = WyHelper.hexToRGB(element.getColor());
						GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
					}
					else
					{
						GlStateManager.color3f(1.0F, 1.0F, 1.0F);
					}
					Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
					GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
				}
				i++;
			}

			// Drawing the details
			for (JollyRogerElement element : this.jollyRoger.getDetails())
			{
				int i = 8;
				if (element != null && element.getTexture() != null)
				{
					if (element.canBeColored())
					{
						Color color = WyHelper.hexToRGB(element.getColor());
						GlStateManager.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
					}
					else
					{
						GlStateManager.color3f(1.0F, 1.0F, 1.0F);
					}
					Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
					GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
				}
				i--;
			}

		}
		GL11.glPopMatrix();

		if(this.selectedElement != null)
		{
			String text = this.trueIndex >= 0 ? (this.trueIndex + 1) + " / " + this.allBases.size() : "Empty";
			if (this.layerType == LayerType.BACKGROUND)
				text = this.trueIndex >= 0 ? (this.trueIndex + 1) + " / " + this.allBackgrounds.size() : "Empty";
			else if (this.layerType == LayerType.DETAIL)
				text = this.trueIndex >= 0 ? (this.trueIndex + 1) + " / " + this.allDetails.size() : "Empty";
			WyHelper.drawStringWithBorder(this.font, text, posX - font.getStringWidth(text) / 2 + 28, posY + 80, WyHelper.hexToRGB("#FFFFFF").getRGB());
		}
		
		GlStateManager.disableBlend();

		super.render(x, y, f);
	}

	@Override
	public void init(Minecraft mc, int width, int height)
	{
		super.init(mc, width, height);

		int posX = 0;
		int posY = this.height / 2;

		int listPosY = posY - 85;

		NoTextureButton baseButton = new NoTextureButton(posX + 5, listPosY, 128, 16, "Base", this::selectButton);
		this.addButton(baseButton);

		for (int i = 0; i < this.jollyRoger.getBackgrounds().length; i++)
		{
			NoTextureButton bgButton = new NoTextureButton(posX + 5, (listPosY + 20 + (i * 20)), 128, 16, "Background " + (i + 1), this::selectButton);
			this.addButton(bgButton);
		}

		for (int i = 0; i < this.jollyRoger.getDetails().length; i++)
		{
			NoTextureButton detailButton = new NoTextureButton(posX + 5, (listPosY + 60 + (i * 20)), 128, 16, "Detail " + (i + 1), this::selectButton);
			this.addButton(detailButton);
		}

		posX = this.width / 2;

		TexturedIconButton nextBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 100, posY - 65, 32, 110, "", this::nextElement);
		nextBaseTexture = nextBaseTexture.setTextureInfo(posX + 100, posY - 75, 32, 128);
		this.addButton(nextBaseTexture);

		TexturedIconButton prevBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 75, posY - 65, 32, 110, "", this::previousElement);
		prevBaseTexture = prevBaseTexture.setTextureInfo(posX - 75, posY - 75, 32, 128);
		this.addButton(prevBaseTexture);
	}

	public void previousElement(Button btn)
	{
		if (this.selectedElement == null)
			return;
		
		try
		{
			if (this.layerType == LayerType.BASE)
			{
				this.currentIndex = this.findIndex(this.allBases, this.player);
				this.trueIndex = this.currentIndex - 1;

				if(this.trueIndex < 0 && this.selectedElement.getTexture() == null)
				{
					this.currentIndex = this.allBases.size();
					this.trueIndex = this.currentIndex - 1;
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allBases.size())
					this.selectedElement.setTexture(this.allBases.get(this.trueIndex).get().getTexture());
				else if(this.currentIndex == 0 && this.selectedElement.getTexture() != null)
					this.selectedElement.setTexture(null);
				
				this.props.setBase(this.jollyRoger.getBase());
				
				for(int i = 0; i < this.props.getBackgrounds().length; i++)
				{
					JollyRogerElement element = this.props.getBackgrounds()[i];
					boolean hasElement = this.allBackgrounds.stream().anyMatch(elem -> elem != null && elem.get() != null && elem.get().equals(element) && !elem.get().canUse(this.player));
					if(hasElement)
						this.props.getBackgrounds()[i].setTexture(null);
				}
				
				for(int i = 0; i < this.props.getDetails().length; i++)
				{
					JollyRogerElement element = this.props.getDetails()[i];
					boolean hasElement = this.allDetails.stream().anyMatch(elem -> elem != null && elem.get() != null && elem.get().equals(element) && !elem.get().canUse(this.player));
					if(hasElement)
						this.props.getDetails()[i].setTexture(null);
				}
			}
			else if (this.layerType == LayerType.BACKGROUND)
			{
				this.currentIndex = this.findIndex(this.allBackgrounds, this.player);
				this.trueIndex = this.currentIndex - 1;

				if(this.trueIndex < 0 && this.selectedElement.getTexture() == null)
				{
					this.currentIndex = this.allBackgrounds.size();
					this.trueIndex = this.currentIndex - 1;
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size())
					this.selectedElement.setTexture(this.allBackgrounds.get(this.trueIndex).get().getTexture());
				else if(this.currentIndex == 0 && this.selectedElement.getTexture() != null)
					this.selectedElement.setTexture(null);
				
				this.props.setBackgrounds(this.jollyRoger.getBackgrounds());
			}
			else if (this.layerType == LayerType.DETAIL)
			{
				this.currentIndex = this.findIndex(this.allDetails, this.player);
				this.trueIndex = this.currentIndex - 1;

				if(this.trueIndex < 0 && this.selectedElement.getTexture() == null)
				{
					this.currentIndex = this.allDetails.size();
					this.trueIndex = this.currentIndex - 1;
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size())
					this.selectedElement.setTexture(this.allDetails.get(this.trueIndex).get().getTexture());
				else if(this.currentIndex == 0 && this.selectedElement.getTexture() != null)
					this.selectedElement.setTexture(null);
				
				this.props.setDetails(this.jollyRoger.getDetails());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void nextElement(Button btn)
	{
		if (this.selectedElement == null)
			return;

		try
		{
			if (this.layerType == LayerType.BASE)
			{
				this.currentIndex = this.findIndex(this.allBases, this.player);
				this.trueIndex = this.currentIndex + 1;

				if(this.trueIndex >= this.allBases.size())
				{
					this.currentIndex = 0;
					this.trueIndex = this.currentIndex - 1;
				}
				else if(this.currentIndex == 0 && this.selectedElement.getTexture() == null)
				{
					this.currentIndex = 0;
					this.trueIndex = 0;
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allBases.size())
					this.selectedElement.setTexture(this.allBases.get(this.trueIndex).get().getTexture());
				else if(this.currentIndex <= 0 && this.selectedElement.getTexture() != null)
					this.selectedElement.setTexture(null);
				
				this.props.setBase(this.jollyRoger.getBase());
				
				for(int i = 0; i < this.props.getBackgrounds().length; i++)
				{
					JollyRogerElement element = this.props.getBackgrounds()[i];
					boolean hasElement = this.allBackgrounds.stream().anyMatch(elem -> elem != null && elem.get() != null && elem.get().equals(element) && !elem.get().canUse(this.player));
					if(hasElement)
						this.props.getBackgrounds()[i].setTexture(null);
				}
				
				for(int i = 0; i < this.props.getDetails().length; i++)
				{
					JollyRogerElement element = this.props.getDetails()[i];
					boolean hasElement = this.allDetails.stream().anyMatch(elem -> elem != null && elem.get() != null && elem.get().equals(element) && !elem.get().canUse(this.player));
					if(hasElement)
						this.props.getDetails()[i].setTexture(null);
				}
			}
			else if (this.layerType == LayerType.BACKGROUND)
			{
				this.currentIndex = this.findIndex(this.allBackgrounds, this.player);
				this.trueIndex = this.currentIndex + 1;

				if(this.trueIndex >= this.allBackgrounds.size())
				{
					this.currentIndex = 0;
					this.trueIndex = this.currentIndex - 1;
				}
				else if(this.currentIndex == 0 && this.selectedElement.getTexture() == null)
				{
					this.currentIndex = 0;
					this.trueIndex = 0;
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size())
					this.selectedElement.setTexture(this.allBackgrounds.get(this.trueIndex).get().getTexture());
				else if(this.currentIndex <= 0 && this.selectedElement.getTexture() != null)
					this.selectedElement.setTexture(null);
				
				this.props.setBackgrounds(this.jollyRoger.getBackgrounds());
			}
			else if (this.layerType == LayerType.DETAIL)
			{
				this.currentIndex = this.findIndex(this.allDetails, this.player);
				this.trueIndex = this.currentIndex + 1;

				if(this.trueIndex >= this.allDetails.size())
				{
					this.currentIndex = 0;
					this.trueIndex = this.currentIndex - 1;
				}
				else if(this.currentIndex == 0 && this.selectedElement.getTexture() == null)
				{
					this.currentIndex = 0;
					this.trueIndex = 0;
				}

				if(this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size())
					this.selectedElement.setTexture(this.allDetails.get(this.trueIndex).get().getTexture());
				else if(this.currentIndex <= 0 && this.selectedElement.getTexture() != null)
					this.selectedElement.setTexture(null);
				
				this.props.setDetails(this.jollyRoger.getDetails());
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
			this.selectedElement = this.jollyRoger.getBase();
			this.trueIndex = this.findIndex(this.getListFromType(LayerType.BASE), this.player);
			this.layerType = LayerType.BASE;
			return;
		}

		int j = 0;
		for (int i = 1; i < this.jollyRoger.getBackgrounds().length + 1; i++)
		{
			if (this.buttons.get(i) == btn)
			{
				this.selectedElement = this.jollyRoger.getBackgrounds()[j];
				this.trueIndex = this.findIndex(this.getListFromType(LayerType.BACKGROUND), this.player);
				this.layerType = LayerType.BACKGROUND;
				this.allBackgrounds = this.getTotalElementsForType(this.player, LayerType.BACKGROUND);
				return;
			}
			j++;
		}

		j = 0;
		for (int i = this.jollyRoger.getBackgrounds().length + 1; i < this.jollyRoger.getDetails().length + this.jollyRoger.getBackgrounds().length + 1; i++)
		{
			if (this.buttons.get(i) == btn)
			{
				this.selectedElement = this.jollyRoger.getDetails()[j];
				this.trueIndex = this.findIndex(this.getListFromType(LayerType.DETAIL), this.player);
				this.layerType = LayerType.DETAIL;
				this.allDetails = this.getTotalElementsForType(this.player, LayerType.DETAIL);	
				return;
			}
			j++;
		}
	}

	@Override
	public void onClose()
	{
		//this.props.setBase(this.jollyRoger.getBase());
		//this.props.setBackgrounds(this.jollyRoger.getBackgrounds());
		//this.props.setDetails(this.jollyRoger.getDetails());
		WyNetwork.sendToServer(new CJollyRogerSyncPacket(this.props));
		super.onClose();
	}

	public boolean doesGuiPauseGame()
	{
		return true;
	}

	private int findIndex(List<RegistryObject<JollyRogerElement>> elements, PlayerEntity player)
	{
		if (this.selectedElement == null || this.selectedElement.getTexture() == null)
			return 0;

		for (int i = 0; i < elements.size(); i++)
		{
			if (elements.get(i).get().equals(this.selectedElement))
			{
				return i;
			}
		}

		return 0;
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
