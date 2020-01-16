package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class FishKarateProjectiles 
{

	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();
	
	public static final EntityType UCHIMIZU = WyRegistry.registerEntityType("uchimizu", Uchimizu::new);
	public static final EntityType MURASAME = WyRegistry.registerEntityType("murasame", Murasame::new);

	static
	{
		projectiles.put(ModAttributes.UCHIMIZU, new Data(UCHIMIZU, Uchimizu.class));
		projectiles.put(ModAttributes.MURASAME, new Data(MURASAME, Murasame.class));
	}
	
	public static class Uchimizu extends ProjectileAbility
	{
		public Uchimizu(World world)
		{super(UCHIMIZU, world);}
		
		public Uchimizu(EntityType type, World world)
		{super(type, world);}
		
		public Uchimizu(World world, double x, double y, double z)
		{super(UCHIMIZU, world, x, y, z);}
		
		public Uchimizu(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(UCHIMIZU, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 1.2F);
			explosion.setExplosionSound(false);
			//explosion.setSmokeParticles(ID.PARTICLEFX_WATEREXPLOSION);
			explosion.setDestroyBlocks(false);
			explosion.setDamageOwner(false);
			explosion.doExplosion();
		}
	}

	public static class Murasame extends ProjectileAbility
	{
		public Murasame(World world)
		{super(MURASAME, world);}
		
		public Murasame(EntityType type, World world)
		{super(type, world);}
		
		public Murasame(World world, double x, double y, double z)
		{super(MURASAME, world, x, y, z);}
		
		public Murasame(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(MURASAME, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 2.2F);
			explosion.setExplosionSound(false);
			//explosion.setSmokeParticles(ID.PARTICLEFX_WATEREXPLOSION);
			explosion.setDamageOwner(false);
			explosion.doExplosion();
		}
	}
}
