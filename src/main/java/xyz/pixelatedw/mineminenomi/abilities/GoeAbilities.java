package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoeProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class GoeAbilities 
{
	static
	{
		ModValues.abilityWebAppExtraParams.put("todoroki", new String[] {"desc", "The user shouts and creates a powerful sound blast."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Todoroki()};	
	
	public static class Todoroki extends Ability
	{
		public Todoroki() 
		{
			super(ModAttributes.TODOROKI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new GoeProjectiles.Todoroki(player.world, player, attr);
			super.use(player);
		} 
	}
}
