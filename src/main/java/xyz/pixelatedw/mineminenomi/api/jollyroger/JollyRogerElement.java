package xyz.pixelatedw.mineminenomi.api.jollyroger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class JollyRogerElement extends ForgeRegistryEntry<JollyRogerElement>
{
	private boolean canBeColored = false;
	private String color = "#FFFFFF";
	private ResourceLocation texture;
	private List<ICanUse> canUseChecks = new ArrayList<ICanUse>();
	private LayerType type;

	public JollyRogerElement(LayerType type)
	{
		this.type = type;
	}

	public boolean canBeColored()
	{
		return this.canBeColored;
	}

	public JollyRogerElement setCanBeColored()
	{
		this.canBeColored = true;
		return this;
	}

	public String getColor()
	{
		return this.color;
	}

	public JollyRogerElement setColor(String color)
	{
		this.color = color;
		return this;
	}

	public ResourceLocation getTexture()
	{
		return this.texture;
	}

	public JollyRogerElement setTexture(ResourceLocation texture)
	{
		this.texture = texture;
		return this;
	}

	public JollyRogerElement addUseCheck(ICanUse check)
	{
		this.canUseChecks.add(check);
		return this;
	}

	public boolean canUse(PlayerEntity player)
	{
		boolean flag = true;

		for (ICanUse check : this.canUseChecks)
		{
			if (check != null && !check.canUse(player))
			{
				flag = false;
				break;
			}
		}

		return flag;
	}

	public LayerType getLayerType()
	{
		return this.type;
	}
	
	@Override
	public boolean equals(Object element)
	{
		if(!(element instanceof JollyRogerElement) || this.getTexture() == null || ((JollyRogerElement) element).getTexture() == null)
			return false;
		
		return this.getTexture().toString().equalsIgnoreCase(((JollyRogerElement) element).getTexture().toString());
	}

	@Nullable
	public JollyRogerElement create()
	{
		try
		{
			return this.getClass().getConstructor().newInstance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public interface ICanUse extends Serializable
	{
		boolean canUse(PlayerEntity player);
	}

	public enum LayerType
	{
		BASE, BACKGROUND, DETAIL
	}
}
