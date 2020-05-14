package xyz.pixelatedw.mineminenomi.api.jollyroger;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class JollyRogerElement
{
	private boolean canBeColored = false;
	private String color = "#FFFFFF";
	private ResourceLocation texture;
	private ICanUse canUseCheck = (player) -> { return true; };
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
	
	public JollyRogerElement setUseCheck(ICanUse check)
	{
		this.canUseCheck = check;
		return this;
	}
	
	public boolean canUse(PlayerEntity player)
	{
		return this.canUseCheck.canUse(player);
	}
	
	public LayerType getLayerType()
	{
		return this.type;
	}
	
	public interface ICanUse extends Serializable
	{
		boolean canUse(PlayerEntity player);
	}
	
	public enum LayerType
	{
		BASE,
		BACKGROUND,
		DETAIL
	}
}
