package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class SukeProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();

	public static final EntityType SHISA_NO_TE = WyRegistry.registerEntityType("shisha_no_te", ShishaNoTe::new);
	
	static
	{
		projectiles.put(ModAttributes.SHISHA_NO_TE, new Data(SHISA_NO_TE, ShishaNoTe.class));
	}
	
	public static class ShishaNoTe extends AbilityProjectileEntity
	{
		public ShishaNoTe(World world)
		{super(SHISA_NO_TE, world);}
		
		public ShishaNoTe(EntityType type, World world)
		{super(type, world);}
		
		public ShishaNoTe(World world, double x, double y, double z)
		{super(SHISA_NO_TE, world, x, y, z);}
		
		public ShishaNoTe(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SHISA_NO_TE, world, player, attr);		
		}
	}	
	
	
}
