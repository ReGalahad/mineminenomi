package xyz.pixelatedw.mineminenomi.events;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeIngameGui;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.config.ClientConfig;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;


@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class CombatModeEvents
{
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);

		ForgeIngameGui.left_height += 1;

		int posX = mc.mainWindow.getScaledWidth();
		int posY = mc.mainWindow.getScaledHeight();

		if (abilityDataProps == null)
			return;

		if (event.getType() == ElementType.HEALTH)
		{
			event.setCanceled(true);
			double maxHealth = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
			double health = player.getHealth();

			WyHelper.drawStringWithBorder(Minecraft.getInstance().fontRenderer, (int) health + "", posX / 2 - 27, posY - 39, Color.RED.getRGB());

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			mc.getTextureManager().bindTexture(Widget.GUI_ICONS_LOCATION);

			for (int i = MathHelper.ceil((maxHealth) / 2.0F) - 1; i >= 0; i--)
			{
				int k = (posX / 2 - 91) + i % 10 * 6;

				GuiUtils.drawTexturedModalRect(k, posY - 39, 16, 0, 9, 9, 0);
			}

			for (int i = 0; i < (100 - (((maxHealth - health) / maxHealth)) * 100) / 10; i++)
			{
				int k = (posX / 2 - 91) + i % 10 * 6;

				GuiUtils.drawTexturedModalRect(k, posY - 39, 16 + 36, 9 * 0, 9, 9, 0);
			}
		}

		if(!entityStatsProps.isInCombatMode())
			return;
		
		if (event.getType() == ElementType.HOTBAR)
		{
			boolean[] visuals = ClientConfig.instance.getCooldownVisuals();

			boolean hasNumberVisual = visuals[0]; // For Text
			boolean hasColorVisual = visuals[1]; // For Color
			//System.out.println(Arrays.toString(visuals.toArray()));
			
			event.setCanceled(true);
			GlStateManager.pushMatrix();	
			{
				GlStateManager.color4f(1, 1, 1, 1);
				GlStateManager.disableLighting();
				//GlStateManager.enableBlend();
				mc.getTextureManager().bindTexture(ModResources.WIDGETS);			
				
				for (int i = 0; i < 8; i++)
				{
					Ability abl = abilityDataProps.getEquippedAbility(i);
					
					if(abl == null)
					{
						GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 0, 0, 23, 23, 0);
						continue;
					}
					
					String number = "";
										
					float cooldown = 23 - (float) (((abl.getCooldown() - 10) / abl.getMaxCooldown()) * 23);
					float threshold = 23;
					float charge = 23;
					
					if(abl.isOnCooldown() && abl.getCooldown() - 10 > 0)
						number = (int) abl.getCooldown() - 10 + " ";
					
					if(abl instanceof ContinuousAbility)
					{
						ContinuousAbility cAbility = (ContinuousAbility)abl;
						threshold = cAbility.getContinueTime() / (float) cAbility.getThreshold() * 23;
						if(cAbility.getThreshold() > 0 && abl.isContinuous() && cAbility.getContinueTime() > 0)
							number = cAbility.getThreshold() - cAbility.getContinueTime() + " ";
					}
				
					if(abl instanceof ChargeableAbility)
					{
						ChargeableAbility cAbility = (ChargeableAbility)abl;
						charge = cAbility.getChargeTime() / (float) cAbility.getMaxChargeTime() * 23;
						if(abl.isCharging() && cAbility.getChargeTime() > 0)
							number = cAbility.getChargeTime() + " ";
					}

					// Setting their color based on their state
					if (abl.isOnCooldown() && !abl.isDisabled() && abl.getCooldown() > 10)
						GlStateManager.color4f(1, 0, 0, 1);
					else if (abl.isCharging())
						GlStateManager.color4f(1, 1, 0, 1);
					else if (abl.isContinuous())
						GlStateManager.color4f(0, 0, 1, 1);
					else if (abl.isDisabled())
						GlStateManager.color4f(0, 0, 0, 1);

					// Drawing the slot
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 0, 0, 23, 23, 0);
					// Reverting the color back to avoid future slots being wrongly colored
					GlStateManager.color4f(1, 1, 1, 1);

					// Setting up addition effects based on the ability's state
					if(hasColorVisual)
					{
						if (abl.isDisabled())
						{
							RendererHelper.drawAbilityIcon("disabled_status", (posX - 192 + (i * 50)) / 2, posY - 19, 3, 16, 16);
							mc.getTextureManager().bindTexture(ModResources.WIDGETS);
						}
						else if(abl.isContinuous())
						{
							GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 24, 0, 23, (int) threshold, 0);
						}
						else if(abl.isCharging())
						{
							GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 24, 0, 23, (int) charge, 0);
						}
						else if(abl.isOnCooldown() && !abl.isDisabled())
						{
							GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 24, 0, 23, (int) cooldown, 0);
							
							if(abl.getCooldown() < 10)
							{
								// Ready animation
								GlStateManager.pushMatrix();
								{
									double scale = 2.5 - (abl.getCooldown() / 10.0);
									GlStateManager.color4f(0.2f, 0.8f, 0.4f, (float)(abl.getCooldown() / 10));
									GlStateManager.translated((posX - 200 + (i * 50)) / 2, posY - 23, 1);
									GlStateManager.translated(12, 12, 0);
									GlStateManager.scaled(scale, scale, 1);
									GlStateManager.translated(-12, -12, 0);
									GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 23, 23, -1);	
								}
								GlStateManager.popMatrix();
							}
						}
					}
					
					// Reverting the color back to avoid future slots being wrongly colored
					GlStateManager.color4f(1, 1, 1, 1);
										
					// Drawing the ability icons
					if(!abl.isDisabled())
						RendererHelper.drawAbilityIcon(WyHelper.getResourceName(abl.getName()), (posX - 192 + (i * 50)) / 2, posY - 19, 16, 16);	
					GlStateManager.translated(0, 0, 2);
					if(hasNumberVisual)
						WyHelper.drawStringWithBorder(mc.fontRenderer, number, (posX - 172 + (i * 50)) / 2 - mc.fontRenderer.getStringWidth(number) / 2, posY - 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
					mc.getTextureManager().bindTexture(ModResources.WIDGETS);
				}		
				//GlStateManager.disableBlend();
			}
			GlStateManager.popMatrix();
		}
	}

	@SubscribeEvent
	public static void updateFOV(FOVUpdateEvent event)
	{
		if (CommonConfig.instance.isFOVRemoved())
		{
			if (event.getEntity().isPotionActive(Effects.SLOWNESS))
				event.setNewfov(1.0F);

			if (event.getEntity().isPotionActive(Effects.SPEED))
				event.setNewfov(1.0F);

			if ((event.getEntity().isPotionActive(Effects.SPEED)) && (event.getEntity().isSprinting()))
				event.setNewfov(1.1F);			
		}
	}
}
