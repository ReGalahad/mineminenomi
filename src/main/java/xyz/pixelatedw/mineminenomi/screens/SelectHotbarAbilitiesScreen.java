package xyz.pixelatedw.mineminenomi.screens;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.AbilitiesListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@OnlyIn(Dist.CLIENT)
public class SelectHotbarAbilitiesScreen extends Screen
{
	protected PlayerEntity player;
	private AbilitiesListScreenPanel devilFruitsAbilitiesList, racialAbilitiesList, hakiAbilitiesList;
	public int slotSelected = -1;
	private int menuSelected = 0;
	public int relativePosX, relativePosY;
	public int abilitySelected = -1;

	private IEntityStats entityStatsProps;
	private IAbilityData abilityDataProps;
	private IDevilFruit devilFruitProps;

	public SelectHotbarAbilitiesScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.minecraft = Minecraft.getInstance();

		this.entityStatsProps = EntityStatsCapability.get(player);
		this.abilityDataProps = AbilityDataCapability.get(player);
		this.devilFruitProps = DevilFruitCapability.get(player);
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();

		Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);

		int posX = this.width;
		int posY = this.height;
		GuiUtils.drawTexturedModalRect((posX - 250) / 2, (posY - 230) / 2, 0, 0, 256, 256, 0);
		GuiUtils.drawTexturedModalRect((posX - 250) / 2, posY - 60, 0, 0, 256, 256, 0);

		this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);

		GL11.glEnable(GL11.GL_BLEND);

		for (int i = 0; i < 8; i++)
		{
			if (this.slotSelected == i)
				GlStateManager.color4f(0, 0, 1, 1);
			
			GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 33, 0, 0, 23, 23, 1);
			GlStateManager.color4f(1, 1, 1, 1);
		}

		for (int i = 0; i < 8; i++)
		{
			GLX.glBlendFuncSeparate(770, 771, 1, 0);
			if (this.abilityDataProps.getEquippedAbility(i) != null)
				RendererHelper.drawAbilityIcon(WyHelper.getResourceName(this.abilityDataProps.getEquippedAbility(i).getName()), (posX - 192 + (i * 50)) / 2, posY - 29, 16, 16);
		}

		this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		if (!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
		{
			GuiUtils.drawTexturedModalRect((posX - 280) / 2, (posY - 200) / 2, 0, 23, 27, 26, 0);
			if (devilFruitProps.hasYamiPower())
				RendererHelper.drawDevilFruitIcon("yamiyaminomi", (posX - 268) / 2, (posY - 187) / 2, 16, 16);
			else
			{
				ItemStack df = AbilityHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());

				RendererHelper.drawDevilFruitIcon(df.getTranslationKey().replace("item." + APIConfig.PROJECT_ID + ".", ""), (posX - 268) / 2, (posY - 187) / 2, 16, 16);
			}
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		}
		Ability abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.RACIAL).parallelStream().findFirst().orElse(null);
		if (abl != null)
		{
			GuiUtils.drawTexturedModalRect((posX - 280) / 2, (posY - 140) / 2, 0, 23, 27, 26, 0);
			RendererHelper.drawAbilityIcon(abl.getName(), (posX - 268) / 2, (posY - 127) / 2, 16, 16);
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		}
		abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.HAKI).parallelStream().findFirst().orElse(null);
		if (abl != null)
		{
			GuiUtils.drawTexturedModalRect((posX - 280) / 2, (posY - 80) / 2, 0, 23, 27, 26, 0);
			RendererHelper.drawAbilityIcon(abl.getName(), (posX - 268) / 2, (posY - 67) / 2, 16, 16);
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		}

		GL11.glDisable(GL11.GL_BLEND);

		// WyHelper.startGlScissor((posX - 220) / 2, (posY - 200) / 2, 215, 130);

		if (this.menuSelected == 0 && this.devilFruitsAbilitiesList != null)
			this.devilFruitsAbilitiesList.render(x, y, f);
		else if (this.menuSelected == 1 && this.racialAbilitiesList != null)
			this.racialAbilitiesList.render(x, y, f);
		else if (this.menuSelected == 2 && this.hakiAbilitiesList != null)
			this.hakiAbilitiesList.render(x, y, f);

		// WyHelper.endGlScissor();

		super.render(x, y, f);
	}

	@Override
	public void init()
	{
		int posX = this.width;
		int posY = this.height;
		this.relativePosX = posX;
		this.relativePosY = posY;
		
		if (!WyHelper.isNullOrEmpty(this.devilFruitProps.getDevilFruit()))
		{
			this.addButton(new NoTextureButton((posX - 280) / 2, (posY - 200) / 2, 27, 25, "", b ->
			{
				this.menuSelected = 0;
				this.updateScreen();
			}));
		}
		Ability abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.RACIAL).stream().findFirst().orElse(null);
		if (abl != null)
		{
			this.addButton(new NoTextureButton((posX - 280) / 2, (posY - 140) / 2, 27, 25, "", b ->
			{
				this.menuSelected = 1;
				this.updateScreen();
			}));
		}
		abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.HAKI).stream().findFirst().orElse(null);
		if (abl != null)
		{
			this.addButton(new NoTextureButton((posX - 280) / 2, (posY - 80) / 2, 27, 25, "", b ->
			{
				this.menuSelected = 2;
				this.updateScreen();
			}));
		}
		for (int i = 0; i < 8; i++)
		{
			GL11.glEnable(GL11.GL_BLEND);
			final int id = i;
			this.addButton(new NoTextureButton((posX - 196 + (i * 50)) / 2, posY - 31, 21, 21, "", b ->
			{
				if (this.slotSelected != id)
					this.slotSelected = id;
				else
				{
					this.abilityDataProps.setEquippedAbility(this.slotSelected, null);
					WyNetwork.sendToServer(new SSyncAbilityDataPacket(this.abilityDataProps));
				}
			}));
		}
		
		this.updateScreen();
	}

	public void updateScreen()
	{
		this.children.remove(this.devilFruitsAbilitiesList);
		this.children.remove(this.racialAbilitiesList);
		this.children.remove(this.hakiAbilitiesList);

		if (this.menuSelected == 0)
		{
			List<Ability> list = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.DEVIL_FRUIT);
			Ability[] arr = new Ability[list.size()];
			arr = list.toArray(arr);
			this.devilFruitsAbilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
			
			this.children.add(this.devilFruitsAbilitiesList);
			this.setFocused(this.devilFruitsAbilitiesList);		
		}
		else if (this.menuSelected == 1)
		{			
			List<Ability> list = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.RACIAL);
			Ability[] arr = new Ability[list.size()];
			arr = list.toArray(arr);
			this.racialAbilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
			
			this.children.add(this.racialAbilitiesList);
			this.setFocused(this.racialAbilitiesList);
		}
		else if (this.menuSelected == 2)
		{			
			List<Ability> list = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.HAKI);
			Ability[] arr = new Ability[list.size()];
			arr = list.toArray(arr);
			this.hakiAbilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
			
			this.children.add(this.hakiAbilitiesList);
			this.setFocused(this.hakiAbilitiesList);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (mouseButton == 1 && this.slotSelected > -1 && this.abilityDataProps.getEquippedAbility(this.slotSelected) != null)
		{
			this.abilityDataProps.setEquippedAbility(this.slotSelected, null);
			WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.abilityDataProps));
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void onClose()
	{
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.abilityDataProps));
		super.onClose();
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
