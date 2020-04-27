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
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CEntityStatsSyncPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

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

		int posX = this.width / 2;
		int posY = this.height / 2;
		
		// Background
		Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
		GuiUtils.drawTexturedModalRect(posX - 110, posY - 80, 0, 0, 256, 256, 1);
		
		// Next and previous buttons
		Minecraft.getInstance().getTextureManager().bindTexture(ModResources.WIDGETS);
		GuiUtils.drawTexturedModalRect(posX - 90, posY - 45, 0, 92, 25, 100, 1);	
		GuiUtils.drawTexturedModalRect(posX + 85, posY - 45, 26, 92, 30, 100, 1);
		
		// Side buttons and the confirm button rendering
		int distance = 75;
		GuiUtils.drawTexturedModalRect(posX - 190, (posY - 140) + distance, 0, 196, 96, 49, 1);
		GuiUtils.drawTexturedModalRect(posX - 190, (posY - 140) + (int)(distance * 1.6), 0, 196, 96, 49, 1);
		GuiUtils.drawTexturedModalRect(posX - 190, (posY - 140) + (int)(distance * 2.2), 0, 196, 96, 49, 1);	
		GuiUtils.drawTexturedModalRect(posX - 30, posY + 65, 0, 196, 96, 49, 1);
		this.drawCategory("Faction", posX - 47, posY + 45, 1.75);
		this.drawCategory("Race", posX - 47, posY + 90, 1.75);
		this.drawCategory("Fighting", posX - 80, posY + 98, 1.5);
		this.drawCategory("Style", posX - 67, posY + 110, 1.5);
		this.drawCategory("Create", posX + 145, posY + 205, 2);
		
		if(this.page == 0) 
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.PIRATE);
				GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Pirate", posX + 268, posY + 190, 3);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.MARINE);
				GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Marine", posX + 269, posY + 190, 3);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BOUNTY_HUNTER);
				GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Bounty", posX + 215, posY + 150, 2.7);
				this.drawCategory("Hunter", posX + 248, posY + 170, 2.7);
			}
			else if(this.selectedOpt == 3)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.REVOLUTIONARY_ARMY);
				GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Revolutionary", posX + 165, posY + 85, 2.2);
				this.drawCategory("Army", posX + 190, posY + 100, 2.2);
			}
		}
		if(this.page == 1)
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.HUMAN);
				GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Human", posX + 268, posY + 190, 3);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.FISHMAN);
				GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Fishman", posX + 268, posY + 190, 3);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CYBORG);
				GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Cyborg", posX + 268, posY + 190, 3);
			}	
		}
		if(this.page == 2) 
		{
			if(this.selectedOpt == 0)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SWORDSMAN);
				GuiUtils.drawTexturedModalRect(posX - 113, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Swordsman", posX + 268, posY + 190, 3);
			}
			else if(this.selectedOpt == 1)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SNIPER);
				GuiUtils.drawTexturedModalRect(posX - 113, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Sniper", posX + 268, posY + 190, 3);
			}
			else if(this.selectedOpt == 2)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.DOCTOR);
				GuiUtils.drawTexturedModalRect(posX - 113, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Doctor", posX + 268, posY + 190, 3);
			}
			else if(this.selectedOpt == 3)
			{
				Minecraft.getInstance().getTextureManager().bindTexture(ModResources.ART_OF_WEATHER);
				GuiUtils.drawTexturedModalRect(posX - 113, posY - 110, 0, 0, 256, 256, 1);
				this.drawCategory("Art of Weather", posX + 180, posY + 100, 2.3);
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
		
		this.addButton(new NoTextureButton(posX - 58, posY + 65, 90, 36, "", b -> 
		{
			if(this.page == 0) this.lastFac = this.selectedOpt;
			if(this.page == 1) this.lastRace = this.selectedOpt;
			if(this.page == 2) this.lastFStyle = this.selectedOpt;			
			
			this.page = 0;			
			this.selectedOpt = this.lastFac;
		}));
		
		this.addButton(new NoTextureButton(posX - 58, (int)(posY + 70 * 1.6), 90, 36, "", b -> 
		{
			if(this.page == 0) this.lastFac = this.selectedOpt;
			if(this.page == 1) this.lastRace = this.selectedOpt;
			if(this.page == 2) this.lastFStyle = this.selectedOpt;		
			
			this.page = 1;
			this.selectedOpt = this.lastRace;
		}));
		
		this.addButton(new NoTextureButton(posX - 58, (int)(posY + 71 * 2.2), 90, 36, "", b -> 
		{
			if(this.page == 0) this.lastFac = this.selectedOpt;
			if(this.page == 1) this.lastRace = this.selectedOpt;
			if(this.page == 2) this.lastFStyle = this.selectedOpt;
			
			this.page = 2;			
			this.selectedOpt = this.lastFStyle;
		}));
		
		// Next / Previous buttons
		this.addButton(new NoTextureButton(posX + 35, posY + 75, 24, 100, "", b -> 
		{
			if(this.selectedOpt - 1 > -1)
				this.selectedOpt--;
			else
				this.selectedOpt = this.maxOpt - 1;
		}));

		this.addButton(new NoTextureButton(posX + 220, posY + 73, 24, 100, "", b -> 
		{
			if(this.selectedOpt + 1 < this.maxOpt)
				this.selectedOpt++;
			else
				this.selectedOpt = 0;
		}));
		
		// Finish button
		this.addButton(new NoTextureButton(posX + 100, posY + 195, 90, 35, "", b -> 
		{
			if(this.lastFac == 0) props.setFaction(ModValues.PIRATE);
			else if(this.lastFac == 1) props.setFaction(ModValues.MARINE);
			else if(this.lastFac == 2) props.setFaction(ModValues.BOUNTY_HUNTER);
			
			if(this.lastRace == 0) props.setRace(ModValues.HUMAN);
			else if(this.lastRace == 1) props.setRace(ModValues.FISHMAN);
			else if(this.lastRace == 2) props.setRace(ModValues.CYBORG);
			
			if(this.lastFStyle == 0) props.setFightingStyle(ModValues.SWORDSMAN);
			else if(this.lastFStyle == 1) props.setFightingStyle(ModValues.SNIPER);
			else if(this.lastFStyle == 2) props.setFightingStyle(ModValues.DOCTOR);
			
			switch(this.page)
			{
				case 0:
				{
					switch(this.selectedOpt)
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
					case 3:
						props.setFaction(ModValues.REVOLUTIONARY);
						break;
					}
					break;
				}
				case 1:
				{
					switch(this.selectedOpt)
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
					switch(this.selectedOpt)
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
					case 3:
						props.setFightingStyle(ModValues.ART_OF_WEATHER);
						break;
					}
					break;					
				}
			}
			
			if(!WyHelper.isNullOrEmpty(props.getRace()) && !WyHelper.isNullOrEmpty(props.getFaction()) && !WyHelper.isNullOrEmpty(props.getFightingStyle()))
			{
				Minecraft.getInstance().displayGuiScreen(null);
				WyNetwork.sendToServer(new CEntityStatsSyncPacket(props));
				WyNetwork.sendToServer(new CDeleteCCBookPacket());
			}
		}));
	}
	
	@Override
	public void tick()
	{
		if(this.page == 0)
			this.maxOpt = 4;
		if(this.page == 1)
			this.maxOpt = 3;
		if(this.page == 2)
			this.maxOpt = 4;
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
    public static void open() 
    {
        Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen());
    }
    
	private void drawCategory(String text, int posX, int posY, double scale)
	{
		GL11.glPushMatrix();
		
			GL11.glTranslated(posX, posY, 2);
			GL11.glTranslated(128, 128, 0);
			GL11.glScaled(scale, scale, scale);
			GL11.glTranslated(-128, -128, 0);

			WyHelper.drawStringWithBorder(this.font, text, -font.getStringWidth(text) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
			
		GL11.glPopMatrix();
	}
}
