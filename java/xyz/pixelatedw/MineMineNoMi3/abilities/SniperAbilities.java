package xyz.pixelatedw.MineMineNoMi3.abilities;

import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities.ShiShishiSonson;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class SniperAbilities
{

	public static Ability KAENBOSHI = new ShiShishiSonson();
	
	public static Ability[] abilitiesArray = new Ability[] {KAENBOSHI};
	
	public static class KaenBoshi extends Ability
	{
		public KaenBoshi() 
		{
			super(ListAttributes.KAENBOSHI); 
		}
	}
	
}
