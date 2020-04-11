package xyz.pixelatedw.mineminenomi.events;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;

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
import xyz.pixelatedw.mineminenomi.api.helpers.ModRendererHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
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

		//if (event.getType() == ElementType.FOOD && devilFruitProps.getDevilFruit().equalsIgnoreCase("yomi_yomi") && devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
		//	event.setCanceled(true);

		if (event.getType() == ElementType.HEALTH)
		{
			event.setCanceled(true);
			double maxHealth = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
			double health = player.getHealth();

			WyHelper.drawCenteredString((int) health + "", posX / 2 - 20, posY - 39, Color.RED.getRGB());

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
			event.setCanceled(true);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			mc.getTextureManager().bindTexture(ModResources.WIDGETS);

			for (int i = 0; i < 8; i++)
			{
				Ability abl = abilityDataProps.getEquippedAbility(i);
				GL11.glEnable(GL11.GL_BLEND);
				if (abl != null && abl.isOnCooldown() && !abl.isDisabled())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 24, 0, 23, 23, 0);
				else if (abl != null && abl.isCharging())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 72, 0, 23, 23, 0);
				else if (abl != null && abl.isContinuous())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 48, 0, 23, 23, 0);
				else if (abl != null && abl.isDisabled())
				{
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 96, 0, 23, 23, 0);
					ModRendererHelper.drawAbilityIcon("disabled_status", (posX - 192 + (i * 50)) / 2, posY - 19, 3, 16, 16);
					mc.getTextureManager().bindTexture(ModResources.WIDGETS);
				}
				else
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 0, 0, 23, 23, 0);
			}

			for (int i = 0; i < 8; i++)
			{
				GLX.glBlendFuncSeparate(770, 771, 1, 0);
				Ability abl = abilityDataProps.getEquippedAbility(i);
				if (abl != null)
				{
					ModRendererHelper.drawAbilityIcon(WyHelper.getResourceName(abl.getName()), (posX - 192 + (i * 50)) / 2, posY - 19, 16, 16);
				}
			}

			GL11.glDisable(GL11.GL_BLEND);
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
