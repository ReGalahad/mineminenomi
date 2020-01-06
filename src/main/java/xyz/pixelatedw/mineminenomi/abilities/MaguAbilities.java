package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MaguProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class MaguAbilities 
{

	static
	{
		ModValues.abilityWebAppExtraParams.put("daifunka", new String[] {"desc", "The user covers their fist in lava and fires it at the opponent."});
		ModValues.abilityWebAppExtraParams.put("meigo", new String[] {"desc", "The user transforms their arm into magma and thrusts it at the opponent."});
		ModValues.abilityWebAppExtraParams.put("ryuseikazan", new String[] {"desc", "Functions like 'Dai Funka', but multiple fists are launched at the opponent."});
		ModValues.abilityWebAppExtraParams.put("bakuretsukazan", new String[] {"desc", "By spreading magma to the surroundings, the user turns everything into lava."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new BakuretsuKazan(), new RyuseiKazan(), new Meigo(), new DaiFunka()};

	public static class BakuretsuKazan extends Ability
	{
		public BakuretsuKazan() 
		{
			super(ModAttributes.BAKURETSU_KAZAN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			if(!this.isOnCooldown)
			{				
				final World world = player.world;
				if(CommonConfig.instance.isGriefingEnabled())
					WyHelper.createFilledSphere(player.world, (int)player.posX, (int)player.posY, (int)player.posZ, 10, Blocks.LAVA, "core");				
				
				super.use(player);
			}
		} 
	}
	
	public static class RyuseiKazan extends Ability
	{
		public RyuseiKazan() 
		{
			super(ModAttributes.RYUSEI_KAZAN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new MaguProjectiles.DaiFunka(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class Meigo extends Ability
	{
		public Meigo() 
		{
			super(ModAttributes.MEIGO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new MaguProjectiles.Meigo(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class DaiFunka extends Ability
	{
		public DaiFunka() 
		{
			super(ModAttributes.DAI_FUNKA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new MaguProjectiles.DaiFunka(player.world, player, attr);
			super.use(player);
		} 
	}
	
}
