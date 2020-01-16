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

public class ZouProjectiles
{
	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();
	
	public static final EntityType TRUNK_SHOT = WyRegistry.registerEntityType("trunk_shot", TrunkShot::new);
	
	static
	{
		projectiles.put(ModAttributes.TRUNK_SHOT, new Data(TRUNK_SHOT, TrunkShot.class));
	}
	
	public static class TrunkShot extends ProjectileAbility
	{
		public TrunkShot(World world)
		{super(TRUNK_SHOT, world);}
		
		public TrunkShot(EntityType type, World world)
		{super(type, world);}
		
		public TrunkShot(World world, double x, double y, double z)
		{super(TRUNK_SHOT, world, x, y, z);}
		
		public TrunkShot(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TRUNK_SHOT, world, player, attr);		
		}		
	}
}
