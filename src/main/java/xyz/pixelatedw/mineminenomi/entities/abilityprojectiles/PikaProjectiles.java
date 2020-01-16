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

public class PikaProjectiles 
{
	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();

	public static final EntityType AMATERASU = WyRegistry.registerEntityType("amaterasu", Amaterasu::new);
	public static final EntityType YASAKANI_NO_MAGATAMA = WyRegistry.registerEntityType("yasakani_no_magatama", YasakaniNoMagatama::new);
	
	static
	{
		projectiles.put(ModAttributes.AMATERASU, new Data(AMATERASU, Amaterasu.class));
		projectiles.put(ModAttributes.YASAKANI_NO_MAGATAMA, new Data(YASAKANI_NO_MAGATAMA, YasakaniNoMagatama.class));
	}
	
	public static class YasakaniNoMagatama extends ProjectileAbility
	{
		public YasakaniNoMagatama(World world)
		{super(YASAKANI_NO_MAGATAMA, world);}
		
		public YasakaniNoMagatama(EntityType type, World world)
		{super(type, world);}
		
		public YasakaniNoMagatama(World world, double x, double y, double z)
		{super(YASAKANI_NO_MAGATAMA, world, x, y, z);}
		
		public YasakaniNoMagatama(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(YASAKANI_NO_MAGATAMA, world, player, attr);		
		}
	}
	
	public static class Amaterasu extends ProjectileAbility
	{
		public Amaterasu(World world)
		{super(AMATERASU, world);}
		
		public Amaterasu(EntityType type, World world)
		{super(type, world);}
		
		public Amaterasu(World world, double x, double y, double z)
		{super(AMATERASU, world, x, y, z);}
		
		public Amaterasu(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(AMATERASU, world, player, attr);		
		}
	}
}
