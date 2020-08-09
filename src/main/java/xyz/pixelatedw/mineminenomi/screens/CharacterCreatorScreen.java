package xyz.pixelatedw.mineminenomi.screens;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.client.CDeleteCCBookPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class CharacterCreatorScreen extends Screen
{
	private PlayerEntity player;
	private IEntityStats props;
	private int page = 0, selectedOpt = 0, maxOpt, lastFac = 0, lastRace = 0, lastFStyle = 0;
	
	public CharacterCreatorScreen()
	{
		super(new StringTextComponent(""));
		this.player = Minecraft.getInstance().player;
		this.props = EntityStatsCapability.get(player);
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
		GuiUtils.drawTexturedModalRect(posX - 110, posY - 80, 0, 0, 256, 256, 0);

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
				
		// Category selection	
		String msg = new TranslationTextComponent(ModI18n.FACTION_NAME).getFormattedText();
		TexturedIconButton factionButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY + 65, 90, 36, msg, (btn) -> 
		{
			if(this.page == 0) this.lastFac = this.selectedOpt;
			if(this.page == 1) this.lastRace = this.selectedOpt;
			if(this.page == 2) this.lastFStyle = this.selectedOpt;			
			
			this.page = 0;			
			this.selectedOpt = this.lastFac;
		});
		factionButton = factionButton.setTextureInfo(posX - 58, posY + 65, 90, 60).setTextInfo(posX + 50, posY + 173, 1.75);
		this.addButton(factionButton);

		msg = new TranslationTextComponent(ModI18n.RACE_NAME).getFormattedText();
		TexturedIconButton raceButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY + 110, 90, 36, msg, (btn) -> 
		{
			if(this.page == 0) this.lastFac = this.selectedOpt;
			if(this.page == 1) this.lastRace = this.selectedOpt;
			if(this.page == 2) this.lastFStyle = this.selectedOpt;		
			
			this.page = 1;
			this.selectedOpt = this.lastRace;
		});
		raceButton = raceButton.setTextureInfo(posX - 58, posY + 110, 90, 60).setTextInfo(posX + 50, posY + 218, 1.75);
		this.addButton(raceButton);

		msg = new TranslationTextComponent(ModI18n.STYLE_NAME).getFormattedText();
		TexturedIconButton styleButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY + 155, 90, 36, msg, (btn) -> 
		{
			if(this.page == 0) this.lastFac = this.selectedOpt;
			if(this.page == 1) this.lastRace = this.selectedOpt;
			if(this.page == 2) this.lastFStyle = this.selectedOpt;
			
			this.page = 2;			
			this.selectedOpt = this.lastFStyle;
		});
		styleButton = styleButton.setTextureInfo(posX - 58, posY + 155, 90, 60).setTextInfo(posX + 50, posY + 264, 1.75);
		this.addButton(styleButton);

		// Next / Previous buttons
		TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 215, posY + 80, 24, 100, "", (btn) -> 
		{
			if(this.selectedOpt + 1 < this.maxOpt)
				this.selectedOpt++;
			else
				this.selectedOpt = 0;
		});
		nextButton = nextButton.setTextureInfo(posX + 211, posY + 66, 32, 128);
		this.addButton(nextButton);
		
		TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX + 45, posY + 80, 24, 100, "", (btn) -> 
		{
			if(this.selectedOpt - 1 > -1)
				this.selectedOpt--;
			else
				this.selectedOpt = this.maxOpt - 1;
		});
		prevButton = prevButton.setTextureInfo(posX + 41, posY + 66, 32, 128);
		this.addButton(prevButton);

		// Finish button	
		TexturedIconButton finishButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 100, posY + 200, 90, 36, "Finish", (btn) -> this.completeCreation());
		finishButton = finishButton.setTextureInfo(posX + 100, posY + 200, 90, 60).setTextInfo(posX + 217, posY + 309, 1.75);
		this.addButton(finishButton);
	}
	
	private void completeCreation()
	{
		if(this.lastFac == 0) this.props.setFaction(ModValues.PIRATE);
		else if(this.lastFac == 1) this.props.setFaction(ModValues.MARINE);
		else if(this.lastFac == 2) this.props.setFaction(ModValues.BOUNTY_HUNTER);
		else if(this.lastFac == 3) this.props.setFaction(ModValues.REVOLUTIONARY);

		if(this.lastRace == 0) this.props.setRace(ModValues.HUMAN);
		else if(this.lastRace == 1) this.props.setRace(ModValues.FISHMAN);
		else if(this.lastRace == 2) this.props.setRace(ModValues.CYBORG);
		
		if(this.lastFStyle == 0) this.props.setFightingStyle(ModValues.SWORDSMAN);
		else if(this.lastFStyle == 1) this.props.setFightingStyle(ModValues.SNIPER);
		else if(this.lastFStyle == 2) this.props.setFightingStyle(ModValues.DOCTOR);
		else if(this.lastFStyle == 3) this.props.setFightingStyle(ModValues.ART_OF_WEATHER);

		switch(this.page)
		{
			case 0:
			{
				switch(this.selectedOpt)
				{
				case 0:
					this.props.setFaction(ModValues.PIRATE);
					break;
				case 1:
					this.props.setFaction(ModValues.MARINE);
					break;
				case 2:
					this.props.setFaction(ModValues.BOUNTY_HUNTER);
					break;
				case 3:
					this.props.setFaction(ModValues.REVOLUTIONARY);
					break;
				}
				break;
			}
			case 1:
			{
				switch(this.selectedOpt)
				{
				case 0:
					this.props.setRace(ModValues.HUMAN);
					break;
				case 1:
					this.props.setRace(ModValues.FISHMAN);
					break;
				case 2:
					this.props.setRace(ModValues.CYBORG);
					break;
				}
				break;					
			}
			case 2:
			{
				switch(this.selectedOpt)
				{
				case 0:
					this.props.setFightingStyle(ModValues.SWORDSMAN);
					break;
				case 1:
					this.props.setFightingStyle(ModValues.SNIPER);
					break;
				case 2:
					this.props.setFightingStyle(ModValues.DOCTOR);
					break;
				case 3:
					this.props.setFightingStyle(ModValues.ART_OF_WEATHER);
					break;
				}
				break;					
			}
		}
		
		if(!WyHelper.isNullOrEmpty(this.props.getRace()) && !WyHelper.isNullOrEmpty(this.props.getFaction()) && !WyHelper.isNullOrEmpty(this.props.getFightingStyle()))
		{
			Minecraft.getInstance().displayGuiScreen(null);
			WyNetwork.sendToServer(new CSyncEntityStatsPacket(this.props));
			WyNetwork.sendToServer(new CDeleteCCBookPacket());
		}
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
