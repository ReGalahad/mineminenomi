package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectHieSlowness;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class HieProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType ICE_BALL = WyRegistry.registerEntityType("ice_ball", IceBall::new);
	public static final EntityType ICE_BLOCK_PARTISAN = WyRegistry.registerEntityType("ice_block_partisan", IceBlockPartisan::new);
	public static final EntityType ICE_BLOCK_PHEASANT = WyRegistry.registerEntityType("ice_block_pheasant", IceBlockPheasant::new);
	
	static
	{
		projectiles.put(ModAttributes.ICE_BALL, new Data(ICE_BALL, IceBall.class));
		projectiles.put(ModAttributes.ICE_BLOCK_PARTISAN, new Data(ICE_BLOCK_PARTISAN, IceBlockPartisan.class));
		projectiles.put(ModAttributes.ICE_BLOCK_PHEASANT, new Data(ICE_BLOCK_PHEASANT, IceBlockPheasant.class));
	}
	
	public static class IceBlockPheasant extends AbilityProjectile
	{
		public IceBlockPheasant(World world)
		{super(ICE_BLOCK_PHEASANT, world);}
		
		public IceBlockPheasant(EntityType type, World world)
		{super(type, world);}
		
		public IceBlockPheasant(World world, double x, double y, double z)
		{super(ICE_BLOCK_PHEASANT, world, x, y, z);}
		
		public IceBlockPheasant(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(ICE_BLOCK_PHEASANT, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				LivingEntity entity = (LivingEntity) entityHit.getEntity();
				
				new DFEffectHieSlowness(entity, 200);
			}
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for(int i = 0; i < 5; i++)
				{
					double offsetX = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
					double offsetY = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
					double offsetZ = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
				    
					CustomParticleData data = new CustomParticleData();
					data.setTexture(ModValuesParticles.PARTICLE_ICON_HIE);
					data.setPosX(posX + offsetX);
					data.setPosY(posY + offsetY);
					data.setPosZ(posZ + offsetZ);
					
					data.setMaxAge(5);
					data.setScale(3F);
					
					ModMain.proxy.spawnParticles(world, data);
				}
			}
			
			super.tick();
		}
	}	
	
	public static class IceBlockPartisan extends AbilityProjectile
	{
		public IceBlockPartisan(World world)
		{super(ICE_BLOCK_PARTISAN, world);}
		
		public IceBlockPartisan(EntityType type, World world)
		{super(type, world);}
		
		public IceBlockPartisan(World world, double x, double y, double z)
		{super(ICE_BLOCK_PARTISAN, world, x, y, z);}
		
		public IceBlockPartisan(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(ICE_BLOCK_PARTISAN, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				LivingEntity entity = (LivingEntity) entityHit.getEntity();
				
				new DFEffectHieSlowness(entity, 100);
			}
			
			WyHelper.placeBlockIfAllowed(this.world, (int)posX, (int)posY, (int)posZ, Blocks.PACKED_ICE, "core", "foliage");
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				double offsetX = WyMathHelper.randomDouble();
				double offsetY = WyMathHelper.randomDouble();
				double offsetZ = WyMathHelper.randomDouble();
			    
				CustomParticleData data = new CustomParticleData();
				data.setTexture(ModValuesParticles.PARTICLE_ICON_HIE);
				data.setPosX(posX + offsetX);
				data.setPosY(posY + offsetY);
				data.setPosZ(posZ + offsetZ);
				
				data.setMaxAge(2);
				data.setScale(1.5F);
				
				ModMain.proxy.spawnParticles(world, data);
			}
			
			super.tick();
		}
	}
	
	public static class IceBall extends AbilityProjectile
	{
		public IceBall(World world)
		{super(ICE_BALL, world);}
		
		public IceBall(EntityType type, World world)
		{super(type, world);}
		
		public IceBall(World world, double x, double y, double z)
		{super(ICE_BALL, world, x, y, z);}
		
		public IceBall(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(ICE_BALL, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				LivingEntity entity = (LivingEntity) entityHit.getEntity();
				
				new DFEffectHieSlowness(entity, 100);
			}
			
			if(CommonConfig.instance.isGriefingEnabled())
			{
				WyHelper.createEmptySphere(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 6, Blocks.PACKED_ICE, "air", "foliage");
			}
		}
	}
}
