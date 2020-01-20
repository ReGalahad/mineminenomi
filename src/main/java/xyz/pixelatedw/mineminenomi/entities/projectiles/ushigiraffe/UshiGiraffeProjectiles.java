package xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class UshiGiraffeProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType BIGAN = WyRegistry.registerEntityType("bigan", Bigan::new);
	
	static
	{
		projectiles.put(ModAttributes.BIGAN, new Data(BIGAN, Bigan.class));
	}
		
	public static class Bigan extends AbilityProjectileEntity
	{
		public Bigan(World world)
		{super(BIGAN, world);}
		
		public Bigan(EntityType type, World world)
		{super(type, world);}
		
		public Bigan(World world, double x, double y, double z)
		{super(BIGAN, world, x, y, z);}
		
		public Bigan(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BIGAN, world, player, attr);		
		}	
	}
}
