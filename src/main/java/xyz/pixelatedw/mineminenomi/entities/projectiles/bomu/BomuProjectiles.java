package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class BomuProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType NOSE_FANCY_CANNON = WyRegistry.registerEntityType("nose_fancy_cannon", NoseFancyCannon::new);
	
	static
	{
		projectiles.put(ModAttributes.NOSE_FANCY_CANNON, new Data(NOSE_FANCY_CANNON, NoseFancyCannon.class));
	}
	
	public static class NoseFancyCannon extends AbilityProjectileEntity
	{
		public NoseFancyCannon(World world)
		{super(NOSE_FANCY_CANNON, world);}
		
		public NoseFancyCannon(EntityType type, World world)
		{super(type, world);}
		
		public NoseFancyCannon(World world, double x, double y, double z)
		{super(NOSE_FANCY_CANNON, world, x, y, z);}
		
		public NoseFancyCannon(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(NOSE_FANCY_CANNON, world, player, attr);		
		}
	}	
	
	
}
