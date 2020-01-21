package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class OpeProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType GAMMA_KNIFE = WyRegistry.registerEntityType("gamma_knife", GammaKnife::new);
	
	static
	{
		projectiles.put(ModAttributes.GAMMA_KNIFE, new Data(GAMMA_KNIFE, GammaKnife.class));
	}
	
	
	public static class GammaKnife extends AbilityProjectileEntity
	{
		public GammaKnife(World world)
		{super(GAMMA_KNIFE, world);}
		
		public GammaKnife(EntityType type, World world)
		{super(type, world);}
		
		public GammaKnife(World world, double x, double y, double z)
		{super(GAMMA_KNIFE, world, x, y, z);}
		
		public GammaKnife(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(GAMMA_KNIFE, world, player, attr);		
		}
	}	
	
	
}
