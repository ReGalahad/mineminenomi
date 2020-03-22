package xyz.pixelatedw.mineminenomi;

import net.minecraft.util.text.TextFormatting;

public enum EnumFruitType 
{

	PARAMECIA				(TextFormatting.RED			, "Paramecia"),
	LOGIA					(TextFormatting.YELLOW		, "Logia"),
	ZOAN					(TextFormatting.GREEN		, "Zoan"),
	MYTHICAL_ZOAN			(TextFormatting.AQUA		, "Mythical Zoan"),
	ANCIENT_ZOAN			(TextFormatting.AQUA		, "Ancient Zoan"),
	ARTIFICIAL_LOGIA		(TextFormatting.GOLD		, "Artificial Logia"),
	ARTIFICIAL_PARAMECIA	(TextFormatting.GOLD		, "Artificial Paramecia"),
	ARTIFICIAL_ZOAN			(TextFormatting.GOLD		, "Artificial Zoan");
	
	private TextFormatting color;
	private String name;
	
	private EnumFruitType(TextFormatting color, String name)
	{
		this.color = color;
		this.name = name;
	}
	
	public TextFormatting getColor()
	{
		return color;		
	}
	
	public String getName()
	{
		return name;
	}
}
