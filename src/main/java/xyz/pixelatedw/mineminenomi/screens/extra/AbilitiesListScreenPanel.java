package xyz.pixelatedw.mineminenomi.screens.extra;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.gui.ScrollPanel;
import xyz.pixelatedw.mineminenomi.api.helpers.ModRendererHelper;
import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;

public class AbilitiesListScreenPanel extends ScrollPanel
{

	private SelectHotbarAbilitiesScreen parent;
	private IAbilityData props;
	private List<Entry> entries = new ArrayList<Entry>();
	private static final int ENTRY_HEIGHT = 20;

	public AbilitiesListScreenPanel(SelectHotbarAbilitiesScreen parent, IAbilityData abilityProps, Ability[] abilities)
	{
		super(parent.getMinecraft(), 215, 130, parent.height / 2 - 98, parent.width / 2 - 109);

		this.parent = parent;
		this.props = abilityProps;

		for (int i = 0; i <= abilities.length - 1; i++)
		{
			if (abilities[i] != null)
				this.entries.add(new Entry(abilities[i]));
		}
		
	}

	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_)
	{
		return true;
	}
	
	@Override
	protected int getContentHeight()
	{
		return (this.entries.size()) * ENTRY_HEIGHT + 16;
	}

	@Override
	protected int getScrollAmount()
	{
		return 12;
	}

	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY)
	{
		for (Entry entry : this.entries)
		{
			float y = relativeY;
			float x = (parent.width / 2 - 109) + 40;
			boolean flag = false;

			if (entry != null)
			{
				if(this.props.hasEquippedAbility(entry.ability))
					flag = true;

				Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format("ability." + APIConfig.PROJECT_ID + "." + WyHelper.getResourceName(entry.ability.getName())), x, y + 4, flag ? 0xFF0000 : 0xFFFFFF);
				ModRendererHelper.drawAbilityIcon(WyHelper.getResourceName(entry.ability.getName()), MathHelper.floor(x) - 30, MathHelper.floor(y), 16, 16);
			}

			relativeY += ENTRY_HEIGHT * 1.25;
		}
	}

	private Entry findAbilityEntry(final int mouseX, final int mouseY)
	{
		double offset = (mouseY - top) + scrollDistance;

		if (offset <= 0)
			return null;

		int lineIdx = (int) (offset / (ENTRY_HEIGHT * 1.25));
		if (lineIdx >= entries.size())
			return null;

		Entry entry = entries.get(lineIdx);
		if (entry != null)
		{
			return entry;
		}

		return null;
	}

	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button)
	{
		Entry entry = this.findAbilityEntry((int) mouseX, (int) mouseY);

		if (this.parent.slotSelected < 0 || entry == null)
			return false;

		boolean flag = true;
		
		for (int i = 0; i < this.props.getEquippedAbilities().length; i++)
		{
			if (this.props.getEquippedAbility(i) != null && this.props.getEquippedAbility(i).equals(entry.ability))
			{
				flag = false;
			}
		}
		
		if (flag)
		{
			this.props.setEquippedAbility(this.parent.slotSelected, entry.ability);
			WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.props));
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	class Entry
	{

		private Ability ability;

		public Entry(Ability ability)
		{
			this.ability = ability;
		}

	}
}
