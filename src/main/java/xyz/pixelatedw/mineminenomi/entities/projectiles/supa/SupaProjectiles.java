package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class SupaProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType SPIRAL_HOLLOW = WyRegistry.registerEntityType("spiral_hollow", SpiralHollow::new);
	
	static
	{
		projectiles.put(ModAttributes.SPIRAL_HOLLOW, new Data(SPIRAL_HOLLOW, SpiralHollow.class));
	}
	
	public static class SpiralHollow extends AbilityProjectileEntity
	{
		public SpiralHollow(World world)
		{super(SPIRAL_HOLLOW, world);}
		
		public SpiralHollow(EntityType type, World world)
		{super(type, world);}
		
		public SpiralHollow(World world, double x, double y, double z)
		{super(SPIRAL_HOLLOW, world, x, y, z);}
		
		public SpiralHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SPIRAL_HOLLOW, world, player, attr);		
		}
	}	
}
