package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class BakuProjectiles
{
	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();
	
	public static final EntityType BERO_CANNON = WyRegistry.registerEntityType("bero_cannon", BeroCannon::new);
	public static final EntityType BERO_TSUIHO = WyRegistry.registerEntityType("baku_tsuiho", BakuTsuiho::new);
	
	static
	{
		projectiles.put(ModAttributes.BERO_CANNON, new Data(BERO_CANNON, BeroCannon.class));
		projectiles.put(ModAttributes.BAKU_TSUIHO, new Data(BERO_TSUIHO, BakuTsuiho.class));
	}
	
	public static class BakuTsuiho extends ProjectileAbility
	{
		public BakuTsuiho(World world)
		{super(BERO_TSUIHO, world);}
		
		public BakuTsuiho(EntityType type, World world)
		{super(type, world);}
		
		public BakuTsuiho(World world, double x, double y, double z)
		{super(BERO_TSUIHO, world, x, y, z);}
		
		public BakuTsuiho(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BERO_TSUIHO, world, player, attr);		
		}
	}	
	
	public static class BeroCannon extends ProjectileAbility
	{
		public BeroCannon(World world)
		{super(BERO_CANNON, world);}
		
		public BeroCannon(EntityType type, World world)
		{super(type, world);}
		
		public BeroCannon(World world, double x, double y, double z)
		{super(BERO_CANNON, world, x, y, z);}
		
		public BeroCannon(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BERO_CANNON, world, player, attr);		
		}
	}	
}
