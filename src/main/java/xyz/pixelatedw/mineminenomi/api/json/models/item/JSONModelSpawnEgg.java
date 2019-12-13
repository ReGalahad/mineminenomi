package xyz.pixelatedw.mineminenomi.api.json.models.item;

import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;

public class JSONModelSpawnEgg extends JSONModelItem
{
	public JSONModelSpawnEgg(String itemName)
	{
		super(itemName, "spawn_egg");
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
