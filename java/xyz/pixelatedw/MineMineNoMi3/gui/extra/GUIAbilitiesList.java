package xyz.pixelatedw.MineMineNoMi3.gui.extra;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.GuiScrollingList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyRenderHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.gui.GUISelectHotbarAbilities;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class GUIAbilitiesList extends GuiScrollingList
{

	private GUISelectHotbarAbilities parent;
	private ExtendedEntityStats props;
	
	private List<AbilityAttribute> availableAbilities = new ArrayList<AbilityAttribute>();
	
	public GUIAbilitiesList(GUISelectHotbarAbilities parent, ExtendedEntityStats props, String[] abilitiesList) 
	{
		super(parent.mc, 220, 300, (parent.relativePosY - 200) / 2, (parent.relativePosY + 60) / 2, (parent.relativePosX - 220) / 2, 26);
        this.parent = parent;
        this.props = props;
        availableAbilities.clear();
        
		for(int i = 0; i < abilitiesList.length - 1; i++)
		{		
			if(AbilityManager.instance().getAbilityByName(abilitiesList[i]) != null)
			{
				this.availableAbilities.add( AbilityManager.instance().getAbilityByName(abilitiesList[i]).getAttribute() );
			}
		}	 
	}
	
	
	
    protected int getContentHeight()
    {
        return (this.getSize()) * 25 + 1;
    }

	
	protected int getSize() 
	{
		return this.availableAbilities.size();
	}

	protected void elementClicked(int index, boolean doubleClick) 
	{	
		if(parent.slotSelected > -1)
		{
			boolean flag = true;
			
			for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
			{								
				if( props.getAbilityFromSlot(i) != null && props.getAbilityFromSlot(i) == AbilityManager.instance().getAbilityByName(WyHelper.getFancyName(availableAbilities.get(index).getAttributeName())) )
				{
					flag = false;
				}
			}
			
			if(flag)
			{
				props.setAbilityInSlot(parent.slotSelected, AbilityManager.instance().getAbilityByName( WyHelper.getFancyName(availableAbilities.get(index).getAttributeName()) ));
			}
		}
	}

	@Override
	protected boolean isSelected(int index) 
	{
        return false;
	}

	protected void drawBackground() 
	{
		
	}

	protected void drawSlot(int slotIndex, int entryRight, int slotTop, int slotBuffer, Tessellator tess) 
	{
    	boolean flag = false;
		for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
		{	
			if(props.getAbilityFromSlot(i) != null && props.getAbilityFromSlot(i).getAttribute() == availableAbilities.get(slotIndex))
			{
				flag = true;
			}
    		
			if(flag)
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(availableAbilities.get(slotIndex).getAttributeName(), this.left + 40, slotTop + 7, 0xFF0000);
			else
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(availableAbilities.get(slotIndex).getAttributeName(), this.left + 40, slotTop + 7, 0xFFFFFF);
		}
		
    	WyRenderHelper.drawAbilityIcon(WyHelper.getFancyName(availableAbilities.get(slotIndex).getAttributeName()), this.left + 10, slotTop + 2, 16, 16);
	}
	
}
