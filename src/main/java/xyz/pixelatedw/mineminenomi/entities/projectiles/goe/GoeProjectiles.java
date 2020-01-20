package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class GoeProjectiles 
{
	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
		
	public static final EntityType TODOROKI = WyRegistry.registerEntityType("todoroki", Todoroki::new);
	
	static
	{
		projectiles.put(ModAttributes.TODOROKI, new Data(TODOROKI, Todoroki.class));
	}
	
	public static class Todoroki extends AbilityProjectileEntity
	{
		public Todoroki(World world)
		{super(TODOROKI, world);}
		
		public Todoroki(EntityType type, World world)
		{super(type, world);}
		
		public Todoroki(World world, double x, double y, double z)
		{super(TODOROKI, world, x, y, z);}
		
		public Todoroki(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TODOROKI, world, player, attr);		
		}	
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 3);
			explosion.setDamageOwner(false);
			//explosion.setSmokeParticles("");
			explosion.doExplosion();
		}	
	}
}
