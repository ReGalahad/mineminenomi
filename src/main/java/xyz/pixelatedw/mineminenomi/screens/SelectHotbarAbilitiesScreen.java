package xyz.pixelatedw.mineminenomi.screens;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRenderHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.client.CAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.extra.AbilitiesListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;

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
				GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 33, 48, 0, 23, 23, 1);
			else
				GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 33, 0, 0, 23, 23, 1);
		}

		for (int i = 0; i < 8; i++)
		{
			GLX.glBlendFuncSeparate(770, 771, 1, 0);
			if (this.abilityDataProps.getAbilityInSlot(i) != null)
				WyRenderHelper.drawAbilityIcon(WyHelper.getResourceName(this.abilityDataProps.getAbilityInSlot(i).getName()), (posX - 192 + (i * 50)) / 2, posY - 29, 16, 16);
		}

		this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		if (!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
		{
			GuiUtils.drawTexturedModalRect((posX - 280) / 2, (posY - 200) / 2, 0, 23, 27, 26, 0);
			if (devilFruitProps.hasYamiPower())
				WyRenderHelper.drawDevilFruitIcon("yamiyaminomi", (posX - 268) / 2, (posY - 187) / 2, 16, 16);
			else
			{
				ItemStack df = DevilFruitsHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());

				WyRenderHelper.drawDevilFruitIcon(df.getTranslationKey().replace("item." + Env.PROJECT_ID + ".", ""), (posX - 268) / 2, (posY - 187) / 2, 16, 16);
			}
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		}
		Ability abl = this.abilityDataProps.getAbilities(Category.RACIAL).parallelStream().findFirst().orElse(null);
		if (abl != null)
		{
			GuiUtils.drawTexturedModalRect((posX - 280) / 2, (posY - 140) / 2, 0, 23, 27, 26, 0);
			WyRenderHelper.drawAbilityIcon(abl.getName(), (posX - 268) / 2, (posY - 127) / 2, 16, 16);
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		}
		abl = this.abilityDataProps.getAbilities(Category.HAKI).parallelStream().findFirst().orElse(null);
		if (abl != null)
		{
			GuiUtils.drawTexturedModalRect((posX - 280) / 2, (posY - 80) / 2, 0, 23, 27, 26, 0);
			WyRenderHelper.drawAbilityIcon(abl.getName(), (posX - 268) / 2, (posY - 67) / 2, 16, 16);
			this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
		}

		GL11.glDisable(GL11.GL_BLEND);

		//WyRenderHelper.startGlScissor((posX - 220) / 2, (posY - 200) / 2, 215, 130);

		if (this.menuSelected == 0)
			this.devilFruitsAbilitiesList.render(x, y, f);
		else if (this.menuSelected == 1)
			this.racialAbilitiesList.render(x, y, f);
		else if (this.menuSelected == 2)
			this.hakiAbilitiesList.render(x, y, f);

		//WyRenderHelper.endGlScissor();

		super.render(x, y, f);
	}

	@Override
	public void init()
	{
		int posX = this.width;
		int posY = this.height;
		relativePosX = posX;
		relativePosY = posY;

		if (!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
		{
			this.addButton(new NoTextureButton((posX - 280) / 2, (posY - 200) / 2, 27, 25, "", b ->
			{
				this.menuSelected = 0;
				updateScreen();
			}));
		}
		Ability abl = this.abilityDataProps.getAbilities(Category.RACIAL).parallelStream().findFirst().orElse(null);
		if (abl != null)
		{
			this.addButton(new NoTextureButton((posX - 280) / 2, (posY - 140) / 2, 27, 25, "", b -> 
			{
				this.menuSelected = 1;
				updateScreen();
			}));
		}
		abl = this.abilityDataProps.getAbilities(Category.HAKI).parallelStream().findFirst().orElse(null);
		if (abl != null)
		{
			this.addButton(new NoTextureButton((posX - 280) / 2, (posY - 80) / 2, 27, 25, "", b ->
			{
				this.menuSelected = 2;
				updateScreen();
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
					this.abilityDataProps.setAbilityInHotbar(this.slotSelected, null);
					ModNetwork.sendToServer(new SAbilityDataSyncPacket(this.player.getEntityId(), this.abilityDataProps));
				}
			}));
		}
		
		if (this.menuSelected == 0)
		{
			List<Ability> list = this.abilityDataProps.getAbilities(Category.DEVIL_FRUIT);
			Ability[] arr = new Ability[list.size()];
			arr = list.toArray(arr);
			this.devilFruitsAbilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
		}
		else if (this.menuSelected == 1)
		{
			List<Ability> list = this.abilityDataProps.getAbilities(Category.RACIAL);
			Ability[] arr = new Ability[list.size()];
			arr = list.toArray(arr);
			this.racialAbilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
		}
		else if (this.menuSelected == 2)
		{
			List<Ability> list = this.abilityDataProps.getAbilities(Category.HAKI);
			Ability[] arr = new Ability[list.size()];
			arr = list.toArray(arr);
			this.hakiAbilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
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
			this.children.add(this.devilFruitsAbilitiesList);
			this.setFocused(this.devilFruitsAbilitiesList);
		}
		else if (this.menuSelected == 1)
		{
			this.children.add(this.racialAbilitiesList);
			this.setFocused(this.racialAbilitiesList);
		}
		else if (this.menuSelected == 2)
		{
			this.children.add(this.hakiAbilitiesList);
			this.setFocused(this.hakiAbilitiesList);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (mouseButton == 1 && this.slotSelected > -1 && this.abilityDataProps.getAbilityInSlot(this.slotSelected) != null)
		{
			this.abilityDataProps.setAbilityInHotbar(this.slotSelected, null);
			ModNetwork.sendToServer(new CAbilityDataSyncPacket(this.abilityDataProps));
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void onClose()
	{
		ModNetwork.sendToServer(new CAbilityDataSyncPacket(this.abilityDataProps));
		super.onClose();
		// WyNetworkHelper.sendToServer(new PacketAbilityReset(true));
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	@SuppressWarnings("resource")
	private void drawItemStack(ItemStack itemStack, int x, int y, String string)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.itemRenderer.zLevel = 200.0F;
		FontRenderer font = null;
		if (itemStack != null)
			font = itemStack.getItem().getFontRenderer(itemStack);
		if (font == null)
			font = this.font;
		this.itemRenderer.renderItemAndEffectIntoGUI(itemStack, x, y);
		this.itemRenderer.zLevel = 0.0F;
	}

}
