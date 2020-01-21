package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class BaneProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType SPRING_DEATH_KNOCK = WyRegistry.registerEntityType("spring_death_knock", SpringDeathKnock::new);
	
	static
	{
		projectiles.put(ModAttributes.SPRING_DEATH_KNOCK, new Data(SPRING_DEATH_KNOCK, SpringDeathKnock.class));
	}
	
	public static class SpringDeathKnock extends AbilityProjectileEntity
	{
		public SpringDeathKnock(World world)
		{super(SPRING_DEATH_KNOCK, world);}
		
		public SpringDeathKnock(EntityType type, World world)
		{super(type, world);}
		
		public SpringDeathKnock(World world, double x, double y, double z)
		{super(SPRING_DEATH_KNOCK, world, x, y, z);}
		
		public SpringDeathKnock(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SPRING_DEATH_KNOCK, world, player, attr);		
		}
	}	
	
	
}
