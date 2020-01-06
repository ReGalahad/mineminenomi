package xyz.pixelatedw.mineminenomi.screens;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.mineminenomi.values.ModValuesGUI;

@OnlyIn(Dist.CLIENT)
public class CharacterCreatorScreen extends Screen
{
	
	private PlayerEntity player;
	private int page = 0, selectedOpt = 0, maxOpt, lastFac = 0, lastRace = 0, lastFStyle = 0;
	
	public CharacterCreatorScreen()
	{
		super(new StringTextComponent(""));
		this.player = Minecraft.getInstance().player;
	}
	
	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glTranslated(20, -10, 0);
		
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_BLANK);
		GuiUtils.drawTexturedModalRect(posX, posY + 50, 0, 0, 256, 256, 1);
		
		Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_COMBATMODE);
		GuiUtils.drawTexturedModalRect(posX + 15, posY + 75, 0, 92, 25, 100, 1);	
		GuiUtils.drawTexturedModalRect(posX + 200, posY + 73, 26, 92, 30, 100, 1);
		
		GuiUtils.drawTexturedModalRect(posX - 80, posY + 70, 0, 196, 96, 49, 1);
		GuiUtils.drawTexturedModalRect(posX - 80, posY + (int)(70 * 1.6), 0, 196, 96, 49, 1);
		GuiUtils.drawTexturedModalRect(posX - 80, posY + (int)(70 * 2.2), 0, 196, 96, 49, 1);	
		GuiUtils.drawTexturedModalRect(posX + 75, posY + 200, 0, 196, 96, 49, 1);
		Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
		GuiUtils.drawTexturedModalRect(posX - 78, posY + 80, 0, 232, 86, 22, 1);
		GuiUtils.drawTexturedModalRect(posX - 70, posY + 121, 94, 230, 74, 22, 1);
		GuiUtils.drawTexturedModalRect(posX - 75, posY + 148, 172, 210, 85, 52, 1);		
		GuiUtils.drawTexturedModalRect(posX + 76, posY + 207, 110, 0, 85, 30, 1);

		if(this.page == 0) 
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_PIRATE);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 69, posY + 65, 0, 0, 118, 30, 1);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_MARINE);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 70, posY + 75, 0, 30, 129, 23, 1);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_BOUNTYHUNTER);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 46, posY + 65, 0, 52, 170, 50, 1);
			}
		}
		if(this.page == 1)
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_HUMAN);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 75, posY + 65, 0, 102, 129, 30, 1);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_FISHMAN);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 65, posY + 70, 0, 130, 129, 26, 1);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_CYBORG);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 75, posY + 72, 0, 160, 110, 24, 1);
			}	
		}
		if(this.page == 2) 
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_SWORDSMAN);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 38, posY + 72, 0, 185, 170, 24, 1);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_SNIPER);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 80, posY + 72, 0, 209, 110, 23, 1);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.ICON_MEDIC);
				GuiUtils.drawTexturedModalRect(posX - 2, posY + 10, 0, 0, 256, 256, 1);
				Minecraft.getInstance().getTextureManager().bindTexture(ModValuesGUI.TEXTURE_STRINGS1);
				GuiUtils.drawTexturedModalRect(posX + 75, posY + 72, 109, 159, 86, 27, 1);
			}	
		}
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		IEntityStats props = EntityStatsCapability.get(player);
		
		this.addButton(new NoTextureButton(posX - 58, posY + 63, 90, 36, "", b -> 
		{
			if(page == 0) lastFac = selectedOpt;
			if(page == 1) lastRace = selectedOpt;
			if(page == 2) lastFStyle = selectedOpt;			
			
			page = 0;			
			selectedOpt = lastFac;
		}));
		
		this.addButton(new NoTextureButton(posX - 58, (int)(posY + 63 * 1.6), 90, 36, "", b -> 
		{
			if(page == 0) lastFac = selectedOpt;
			if(page == 1) lastRace = selectedOpt;
			if(page == 2) lastFStyle = selectedOpt;		
			
			page = 1;
			selectedOpt = lastRace;
		}));
		
		this.addButton(new NoTextureButton(posX - 58, (int)(posY + 62 * 2.2), 90, 36, "", b -> 
		{
			if(page == 0) lastFac = selectedOpt;
			if(page == 1) lastRace = selectedOpt;
			if(page == 2) lastFStyle = selectedOpt;
			
			page = 2;			
			selectedOpt = lastFStyle;
		}));
		
		// Next / Previous buttons
		this.addButton(new NoTextureButton(posX + 35, posY + 75, 24, 100, "", b -> 
		{
			if(selectedOpt - 1 > -1)
				selectedOpt--;
			else
				selectedOpt = maxOpt - 1;
		}));

		this.addButton(new NoTextureButton(posX + 230, posY + 73, 24, 100, "", b -> 
		{
			if(selectedOpt + 1 < maxOpt)
				selectedOpt++;
			else
				selectedOpt = 0;
		}));
		
		// Finish button
		this.addButton(new NoTextureButton(posX + 97, posY + 195, 90, 35, "", b -> 
		{
			if(lastFac == 0) props.setFaction(ModValues.PIRATE);
			else if(lastFac == 1) props.setFaction(ModValues.MARINE);
			else if(lastFac == 2) props.setFaction(ModValues.BOUNTY_HUNTER);
			
			if(lastRace == 0) props.setRace(ModValues.HUMAN);
			else if(lastRace == 1) props.setRace(ModValues.FISHMAN);
			else if(lastRace == 2) props.setRace(ModValues.CYBORG);
			
			if(lastFStyle == 0) props.setFightingStyle(ModValues.SWORDSMAN);
			else if(lastFStyle == 1) props.setFightingStyle(ModValues.SNIPER);
			else if(lastFStyle == 2) props.setFightingStyle(ModValues.DOCTOR);
			
			switch(page)
			{
				case 0:
				{
					switch(selectedOpt)
					{
					case 0:
						props.setFaction(ModValues.PIRATE);
						break;
					case 1:
						props.setFaction(ModValues.MARINE);
						break;
					case 2:
						props.setFaction(ModValues.BOUNTY_HUNTER);
						break;
					}
					break;
				}
				case 1:
				{
					switch(selectedOpt)
					{
					case 0:
						props.setRace(ModValues.HUMAN);
						break;
					case 1:
						props.setRace(ModValues.FISHMAN);
						break;
					case 2:
						props.setRace(ModValues.CYBORG);
						break;
					}
					break;					
				}
				case 2:
				{
					switch(selectedOpt)
					{
					case 0:
						props.setFightingStyle(ModValues.SWORDSMAN);
						break;
					case 1:
						props.setFightingStyle(ModValues.SNIPER);
						break;
					case 2:
						props.setFightingStyle(ModValues.DOCTOR);
						break;
					}
					break;					
				}
			}
			
			if(!props.getRace().equals("N/A") && !props.getFaction().equals("N/A") && !props.getFightingStyle().equals("N/A"))
			{
				Minecraft.getInstance().displayGuiScreen(null);
				ModNetwork.sendToServer(new CEntityStatsSyncPacket(props));
				ModNetwork.sendToServer(new CDeleteCCBookPacket());
			}
		}));
	}
	
	@Override
	public void tick()
	{
		if(this.page == 0)
			maxOpt = 3;
		if(this.page == 2)
			maxOpt = 3;
		if(this.page != 2)
			maxOpt = 3;
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
    public static void open() 
    {
        Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen());
    }
}
