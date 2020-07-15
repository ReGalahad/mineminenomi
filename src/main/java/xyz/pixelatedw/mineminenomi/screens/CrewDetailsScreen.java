package xyz.pixelatedw.mineminenomi.screens;

import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.Crew.Member;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncWorldDataPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class CrewDetailsScreen extends Screen
{
	private PlayerEntity player;
	private ExtendedWorldData worldProps;
	private JollyRoger jollyRoger;
	private Crew crew;
	
	public CrewDetailsScreen()
	{
		super(new StringTextComponent(""));
		this.minecraft = Minecraft.getInstance();
		this.player = this.minecraft.player;
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		String nameString = I18n.format(ModI18n.GUI_NAME);
		String jollyRogerString = I18n.format(ModI18n.GUI_CREW_JOLLY_ROGER);
		String membersString = I18n.format(ModI18n.GUI_CREW_MEMBERS);

		String crewActual = "";
		if(this.crew == null)
			return;
		
		crewActual = this.crew.getName();
		
		WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + nameString + ": " + TextFormatting.RESET + crewActual, posX - 50, posY + 50, -1);
		WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + jollyRogerString + ": ", posX - 50, posY + 70, -1);
		WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + membersString + ": ", posX + 150, posY + 50, -1);

		int memPosY = posY + 70;
		for(Member member : this.crew.getMembers())
		{		
			PlayerEntity player = this.minecraft.world.getPlayerByUuid(member.getUUID());
			String memberName = player.getDisplayName().getFormattedText() + (member.isCaptain() ? " (" + I18n.format(ModI18n.CREW_CAPTAIN) + ")" : "");
			WyHelper.drawStringWithBorder(this.font, memberName, posX + 150, memPosY, -1);
			memPosY += 20;
		}
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
	
			double scale = 0.4;
			GlStateManager.translated(posX - 110, posY + 15, 1);
			GlStateManager.translated(128, 128, 0);
			GlStateManager.scaled(scale, scale, scale);
			GlStateManager.translated(-128, -128, 0);
	
			if(this.jollyRoger != null)
				RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
			
			GlStateManager.disableBlend();
		}
		GlStateManager.popMatrix();
		
		super.render(x, y, f);
	}
	
	@Override
	public void init()
	{	
		this.worldProps = ExtendedWorldData.get(this.player.world);

		this.crew = this.worldProps.getCrewWithMember(this.player.getUniqueID());
		if(this.crew == null)
			return;
		UUID captainUUID = this.crew.getCaptain().getUUID();
		PlayerEntity crewCaptain = this.minecraft.world.getPlayerByUuid(captainUUID);
		
		this.jollyRoger = this.crew.getJollyRoger();
		
		int posX = ((this.width - 256) / 2) - 50;
		int posY = (this.height - 256) / 2;
		
		posX += 80;
		this.addButton(new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_LEAVE), b -> 
		{
			this.crew.removeMember(this.player.getUniqueID());
			if(this.crew.getMembers().size() <= 0)
				this.worldProps.removeCrew(this.crew);
			WyNetwork.sendToServer(new CSyncWorldDataPacket(this.worldProps));
			Minecraft.getInstance().displayGuiScreen(null);
		}));
		
		if(CommonConfig.instance.canChangeJollyRoger() && this.player == crewCaptain)
		{
			posX += 80;
			this.addButton(new Button(posX, posY + 210, 120, 20, I18n.format(ModI18n.GUI_CHANGE_JOLLY_ROGER), b -> 
			{
				Minecraft.getInstance().displayGuiScreen(new JollyRogerCreatorScreen(false));
			}));
		}
	}
}
