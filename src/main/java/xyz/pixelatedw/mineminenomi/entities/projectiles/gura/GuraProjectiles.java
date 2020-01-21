package xyz.pixelatedw.mineminenomi.entities.projectiles.gura;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public class GuraProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType SHIMA_YURASHI = WyRegistry.registerEntityType("shima_yurashi", ShimaYurashi::new);
	public static final EntityType KAISHIN = WyRegistry.registerEntityType("kaishin", Kaishin::new);

	static
	{
		projectiles.put(ModAttributes.SHIMA_YURASHI, new Data(SHIMA_YURASHI, ShimaYurashi.class));
		projectiles.put(ModAttributes.KAISHIN, new Data(KAISHIN, Kaishin.class));
	}
	
	public static class ShimaYurashi extends AbilityProjectileEntity
	{
		public ShimaYurashi(World world)
		{super(SHIMA_YURASHI, world);}
	
		public ShimaYurashi(EntityType type, World world)
		{super(type, world);}
		
		public ShimaYurashi(World world, double x, double y, double z)
		{super(SHIMA_YURASHI, world, x, y, z);}
		
		public ShimaYurashi(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SHIMA_YURASHI, world, player, attr);		
		}
		
		@Override
		public void tick()
		{		
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					if(i % 2 == 0)
						this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
					else
					{
						CustomParticleData data = new CustomParticleData();
						data.setTexture(ModResources.GURA2);
						data.setPosX(posX + (WyMathHelper.randomDouble() * 2));
						data.setPosY(posY + (WyMathHelper.randomDouble() * 2));
						data.setPosZ(posZ + (WyMathHelper.randomDouble() * 2));
						data.setMotionX(this.getMotion().x);
						data.setMotionY(this.getMotion().y);
						data.setMotionZ(this.getMotion().z);
						
						data.setMaxAge(10);
						data.setScale(3F);
						
						//ModMain.proxy.spawnParticles(world, data);
					}
				}
			}
				
			super.tick();
		}
	}	
	
	public static class Kaishin extends AbilityProjectileEntity
	{
		public Kaishin(World world)
		{super(KAISHIN, world);}
		
		public Kaishin(EntityType type, World world)
		{super(type, world);}
		
		public Kaishin(World world, double x, double y, double z)
		{super(KAISHIN, world, x, y, z);}
		
		public Kaishin(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(KAISHIN, world, player, attr);		
		}
		
		@Override
		public void tick()
		{		
			if(this.world.isRemote)
			{
				for (int i = 0; i < 3; i++)
				{
					if(i % 2 == 0)
						this.world.addParticle
						(
							ParticleTypes.EXPLOSION, 
							this.posX + (WyMathHelper.randomDouble() * 1.5), 
							this.posY + (WyMathHelper.randomDouble() * 1.5), 
							this.posZ + (WyMathHelper.randomDouble() * 1.5), 
							0.0D, 0.0D, 0.0D
						);
					else
					{
						CustomParticleData data = new CustomParticleData();
						data.setTexture(ModResources.GURA2);
						data.setPosX(posX + (WyMathHelper.randomDouble() * 2));
						data.setPosY(posY + (WyMathHelper.randomDouble() * 2));
						data.setPosZ(posZ + (WyMathHelper.randomDouble() * 2));
						
						data.setMaxAge(10);
						data.setScale(3F);
						
						//ModMain.proxy.spawnParticles(world, data);
					}
				}
			}
			
			super.tick();
		}
	}	
}
