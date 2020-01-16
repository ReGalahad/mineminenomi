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

public class HoroProjectiles 
{
	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();
	
	public static final EntityType NEGATIVE_HOLLOW = WyRegistry.registerEntityType("negative_hollow", NegativeHollow::new);
	public static final EntityType MINI_HOLLOW = WyRegistry.registerEntityType("mini_hollow", MiniHollow::new);
	public static final EntityType TOKU_HOLLOW = WyRegistry.registerEntityType("toku_hollow", TokuHollow::new);

	
	static
	{
		projectiles.put(ModAttributes.NEGATIVE_HOLLOW, new Data(NEGATIVE_HOLLOW, NegativeHollow.class));
		projectiles.put(ModAttributes.MINI_HOLLOW, new Data(MINI_HOLLOW, MiniHollow.class));
		projectiles.put(ModAttributes.TOKU_HOLLOW, new Data(TOKU_HOLLOW, TokuHollow.class));
	}
	
	public static class TokuHollow extends ProjectileAbility
	{
		public TokuHollow(World world)
		{super(TOKU_HOLLOW, world);}
		
		public TokuHollow(EntityType type, World world)
		{super(type, world);}
		
		public TokuHollow(World world, double x, double y, double z)
		{super(TOKU_HOLLOW, world, x, y, z);}
		
		public TokuHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TOKU_HOLLOW, world, player, attr);		
		}
	}	
	
	public static class MiniHollow extends ProjectileAbility
	{
		public MiniHollow(World world)
		{super(MINI_HOLLOW, world);}
		
		public MiniHollow(EntityType type, World world)
		{super(type, world);}
		
		public MiniHollow(World world, double x, double y, double z)
		{super(MINI_HOLLOW, world, x, y, z);}
		
		public MiniHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(MINI_HOLLOW, world, player, attr);		
		}
	}	
	
	public static class NegativeHollow extends ProjectileAbility
	{
		public NegativeHollow(World world)
		{super(NEGATIVE_HOLLOW, world);}
		
		public NegativeHollow(EntityType type, World world)
		{super(type, world);}
		
		public NegativeHollow(World world, double x, double y, double z)
		{super(NEGATIVE_HOLLOW, world, x, y, z);}
		
		public NegativeHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(NEGATIVE_HOLLOW, world, player, attr);		
		}
	}	

}
