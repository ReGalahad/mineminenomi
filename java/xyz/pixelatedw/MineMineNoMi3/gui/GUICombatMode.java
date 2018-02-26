package xyz.pixelatedw.MineMineNoMi3.gui;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.WyRenderHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

@SideOnly(Side.CLIENT)
public class GUICombatMode extends Gui
{
	private Minecraft mc;
	protected static final RenderItem itemRenderer = new RenderItem();
	private int trackDistance = 15;
	private EntityLivingBase trackMob = null;
	
	public GUICombatMode(Minecraft mc)
	{	
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderUI(RenderGameOverlayEvent event)
	{		
		EntityPlayer player = mc.thePlayer;
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		
		int posX = event.resolution.getScaledWidth();
		int posY = event.resolution.getScaledHeight();
		
		if (props.isInCombatMode() && event.type == ElementType.HOTBAR)
		{ 
			event.setCanceled(true);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.mc.getTextureManager().bindTexture(ID.TEXTURE_COMBATMODE);           
			
			for(int i = 0; i < 8; i++)
			{
	            GL11.glEnable(GL11.GL_BLEND);
	            if(props.getAbilityFromSlot(i) != null && props.getAbilityFromSlot(i).isOnCooldown())
	            	this.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 24, 0, 23, 23);
	            else if(props.getAbilityFromSlot(i) != null && props.getAbilityFromSlot(i).isCharging())
	            	this.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 72, 0, 23, 23);
	            else if(props.getAbilityFromSlot(i) != null && props.getAbilityFromSlot(i).isPassiveActive())
	            	this.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 48, 0, 23, 23);
	            else
	            	this.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 0, 0, 23, 23);
			}
			
			if(props.getRace().equals(ID.RACE_CYBORG))
			{
				this.drawTexturedModalRect((posX - 260) / 2, posY - 42, 0, 52, 23, 56);
				int barHeight = (int) ( ((float)props.getCola() / props.getMaxCola()) * 30) + 23; // 139
				
				if(barHeight > 0 && barHeight < 24) barHeight = 24;
				else if(barHeight > 52) barHeight = 52;		
				
				this.drawTexturedModalRect((posX - 252) / 2, posY - 42, 32, barHeight, 16, 32); //23 - 52
			
				this.drawCenteredString(this.mc.fontRenderer, props.getCola() + "",(posX - 237) / 2, posY - 12, Color.WHITE.getRGB());
			}
			
			for(int i = 0; i < 8; i++)
			{
	            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	            if(props.getAbilityFromSlot(i) != null)
            		WyRenderHelper.drawAbilityIcon(WyHelper.getFancyName(props.getAbilityFromSlot(i).getAttribute().getAttributeName()), (posX - 192 + (i * 50)) / 2, posY - 19, 16, 16);	           
            }	
			
			int trackDistance = 15;
			if(props.hasKenHakiActive())
			{
				List<EntityLivingBase> nearbyEnemies = WyHelper.getEntitiesNear(player, 15);
				for(EntityLivingBase elb : nearbyEnemies)
				{
					if(trackMob == null)
					{
						trackMob = elb;
					}
					else
					{
						if(player.getDistanceToEntity(elb) <= player.getDistanceToEntity(trackMob)) trackMob = elb;
						else if(trackMob.getHealth() <= 0 || !trackMob.isEntityAlive()) trackMob = null;
						if(trackMob != null && player.getDistanceToEntity(trackMob) < trackDistance)
						{
							trackDistance = (int) player.getDistanceToEntity(trackMob);							
							float angle = (float) Math.toDegrees(Math.atan2(trackMob.posZ - player.posZ, trackMob.posX - player.posX));
							String text = "";
							
							text += trackDistance + " blocks";						
							Direction playerDir = WyHelper.get4Directions(player);						
							switch(playerDir)
							{
							case NORTH:
								if(angle > 75 && angle < 115)
									text += " [↓]";
								else if(angle > 115 && angle < 165)
									text += " [↙]";
								else if((angle > 165 && angle < 180) || (angle > -180 && angle < -165))
									text += " [←]";
								else if(angle < -104 && angle > -165)
									text += " [↖]";
								else if(angle < -60 && angle > -104)
									text += " [↑]";
								else if(angle < -20 && angle > -60)
									text += " [↗]";
								else if((angle < 0 && angle > -20) || (angle > 0 && angle < 30))
									text += " [→]";
								else if(angle > 30 && angle < 115)
									text += " [↘]";
								break;
							case SOUTH:
								if(angle > 75 && angle < 115)
									text += " [↑]";
								else if(angle > 115 && angle < 165)
									text += " [↗]";
								else if((angle > 165 && angle < 180) || (angle > -180 && angle < -165))
									text += " [→]";
								else if(angle < -104 && angle > -165)
									text += " [↘]";
								else if(angle < -60 && angle > -104)
									text += " [↓]";
								else if(angle < -20 && angle > -60)
									text += " [↙]";
								else if((angle < 0 && angle > -20) || (angle > 0 && angle < 30))
									text += " [←]";
								else if(angle > 30 && angle < 115)
									text += " [↖]";
								break;
							case EAST:
								if(angle > 75 && angle < 115)
									text += " [→]";
								else if(angle > 115 && angle < 165)
									text += " [↘]";
								else if((angle > 165 && angle < 180) || (angle > -180 && angle < -165))
									text += " [↓]";
								else if(angle < -104 && angle > -165)
									text += " [↙]";
								else if(angle < -60 && angle > -104)
									text += " [←]";
								else if(angle < -20 && angle > -60)
									text += " [↖]";
								else if((angle < 0 && angle > -20) || (angle > 0 && angle < 30))
									text += " [↑]";
								else if(angle > 30 && angle < 115)
									text += " [↗]";
								break;
							case WEST:
								if(angle > 75 && angle < 115)
									text += " [←]";
								else if(angle > 115 && angle < 165)
									text += " [↖]";
								else if((angle > 165 && angle < 180) || (angle > -180 && angle < -165))
									text += " [↑]";
								else if(angle < -104 && angle > -165)
									text += " [↗]";
								else if(angle < -60 && angle > -104)
									text += " [→]";
								else if(angle < -20 && angle > -60)
									text += " [↘]";
								else if((angle < 0 && angle > -20) || (angle > 0 && angle < 30))
									text += " [↓]";
								else if(angle > 30 && angle < 115)
									text += " [↙]";
								break;
							default:
								break;
							}
							
							WyRenderHelper.drawEntityOnScreen((posX + 320) / 2, posY - 42, 40, 40, 0, trackMob);
							this.drawCenteredString(this.mc.fontRenderer, text, (posX + 320) / 2, posY - 32, Color.WHITE.getRGB());
						}
					}
				}
			}
			
			GL11.glDisable(GL11.GL_BLEND);				
		}
	}
	
}
