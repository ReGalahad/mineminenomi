package xyz.pixelatedw.mineminenomi.screens;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.CStopAbilityPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.AbilitiesListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;

@OnlyIn(Dist.CLIENT)
public class SelectHotbarAbilitiesScreen extends Screen
{
	protected PlayerEntity player;
	private AbilitiesListScreenPanel devilFruitsAbilitiesList, racialAbilitiesList, hakiAbilitiesList;
	public int slotSelected = -1;
	private int menuSelected = 0;
	public int relativePosX, relativePosY;
	public int abilitySelected = -1;

	private IAbilityData abilityDataProps;
	private IDevilFruit devilFruitProps;

	public SelectHotbarAbilitiesScreen(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
		this.minecraft = Minecraft.getInstance();

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
			
			GuiUtils.drawTexturedModalRect((posX / 2) - 102 + (i * 25), posY - 33, 0, 0, 23, 23, 1);
			GlStateManager.color4f(1, 1, 1, 1);
		}

		for (int i = 0; i < 8; i++)
		{
			GLX.glBlendFuncSeparate(770, 771, 1, 0);
			if (this.abilityDataProps.getEquippedAbility(i) != null)
				RendererHelper.drawAbilityIcon(WyHelper.getResourceName(this.abilityDataProps.getEquippedAbility(i).getName()), (posX / 2) - 98 + (i * 25), posY - 29, 16, 16);
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
		
		// Side menu buttons
		Ability abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.DEVIL_FRUIT).stream().findFirst().orElse(null);
		if (abl != null || this.devilFruitProps.hasDevilFruit())
		{
			String iconName = abl != null ? abl.getName() : "";
			ResourceLocation dfIcon = new ResourceLocation(APIConfig.PROJECT_ID, "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png");
			
			if(this.devilFruitProps.hasDevilFruit())
			{
				if (this.devilFruitProps.hasYamiPower())
					iconName = "yami_yami_no_mi";
				else
				{
					ItemStack df = DevilFruitHelper.getDevilFruitItem(this.devilFruitProps.getDevilFruit());
					iconName = df.getTranslationKey().replace("item." + APIConfig.PROJECT_ID + ".", "");
				}
				dfIcon = new ResourceLocation(APIConfig.PROJECT_ID, "textures/items/" + WyHelper.getResourceName(iconName) + ".png");
			}

			TexturedIconButton devilFruitsButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, (posX - 290) / 2, (posY - 200) / 2, 30, 30, "", (btn) -> this.updateListScreen(0));
			devilFruitsButton = devilFruitsButton.setTextureInfo((posX - 290) / 2, (posY - 200) / 2, 30, 40).setIconInfo(dfIcon, (posX - 268) / 2, (posY - 183) / 2, 1.5);
			this.addButton(devilFruitsButton);
		}
		abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.RACIAL).stream().findFirst().orElse(null);
		if (abl != null)
		{
			String iconName = abl.getName();
			ResourceLocation raceIcon = new ResourceLocation(APIConfig.PROJECT_ID, "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png");
			TexturedIconButton racialButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, (posX - 290) / 2, (posY - 130) / 2, 30, 30, "", (btn) -> this.updateListScreen(1));
			racialButton = racialButton.setTextureInfo((posX - 290) / 2, (posY - 130) / 2, 30, 40).setIconInfo(raceIcon, (posX - 268) / 2, (posY - 112) / 2, 1.5);
			this.addButton(racialButton);
		}
		abl = this.abilityDataProps.getUnlockedAbilities(AbilityCategory.HAKI).stream().findFirst().orElse(null);
		if (abl != null)
		{
			String iconName = abl.getName();
			ResourceLocation hakiIcon = new ResourceLocation(APIConfig.PROJECT_ID, "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png");
			TexturedIconButton hakiButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, (posX - 290) / 2, (posY - 60) / 2, 30, 30, "", (btn) -> this.updateListScreen(2));
			hakiButton = hakiButton.setTextureInfo((posX - 290) / 2, (posY - 60) / 2, 30, 40).setIconInfo(hakiIcon, (posX - 268) / 2, (posY - 42) / 2, 1.5);
			this.addButton(hakiButton);
		}
		
		// Ability slots
		for (int i = 0; i < 8; i++)
		{
			GL11.glEnable(GL11.GL_BLEND);
			final int id = i;
			NoTextureButton slotButton = new NoTextureButton((posX / 2) - 101 + (i * 25), posY - 31, 22, 21, "", b ->
			{
				if (this.slotSelected != id)
					this.slotSelected = id;
				else
				{
					Ability ability = this.abilityDataProps.getEquippedAbility(this.slotSelected);
					if(ability != null && ability.isOnStandby())
					{
						WyNetwork.sendToServer(new CStopAbilityPacket(this.slotSelected));
						this.abilityDataProps.setEquippedAbility(this.slotSelected, null);
					}
				}
			});
			slotButton.setFake();
			this.addButton(slotButton);
		}
		
		this.updateListScreen(0);
	}

	public void updateListScreen(int listNo)
	{		
		this.children.remove(this.devilFruitsAbilitiesList);
		this.children.remove(this.racialAbilitiesList);
		this.children.remove(this.hakiAbilitiesList);

		this.menuSelected = listNo;
		
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
