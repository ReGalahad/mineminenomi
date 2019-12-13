package xyz.pixelatedw.mineminenomi.api.json.models.item;

import xyz.pixelatedw.mineminenomi.api.json.models.JSONPredicateObject;

public class JSONModelRod extends JSONModelPredicates
{
	public JSONModelRod(String itemName)
	{
		super(itemName, "rod");
	}

	public JSONModelRod(String itemName, JSONPredicateObject... predicates)
	{
		super(itemName, "rod", predicates);
	}
	
	@Override
	public String[] getModel()
	{		
		return super.getModel();
	}
}
