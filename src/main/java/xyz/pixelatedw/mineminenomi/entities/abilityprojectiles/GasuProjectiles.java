package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public class GasuProjectiles 
{

	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();
	
	public static final EntityType GASTILLE = WyRegistry.registerEntityType("gastille", Gastille::new);
	public static final EntityType GAS_ROBE = WyRegistry.registerEntityType("gas_robe", GasRobe::new);
	
	static
	{
		projectiles.put(ModAttributes.GASTILLE, new Data(GASTILLE, Gastille.class));
		projectiles.put(ModAttributes.GAS_ROBE, new Data(GAS_ROBE, GasRobe.class));
	}
	
	public static class Gastille extends ProjectileAbility
	{
		public Gastille(World world)
		{super(GAS_ROBE, world);}
		
		public Gastille(EntityType type, World world)
		{super(type, world);}
		
		public Gastille(World world, double x, double y, double z)
		{super(GAS_ROBE, world, x, y, z);}
		
		public Gastille(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(GAS_ROBE, world, player, attr);		
		}	
	}
	
	public static class GasRobe extends ProjectileAbility
	{
		public GasRobe(World world)
		{super(GASTILLE, world);}
		
		public GasRobe(EntityType type, World world)
		{super(type, world);}
		
		public GasRobe(World world, double x, double y, double z)
		{super(GASTILLE, world, x, y, z);}
		
		public GasRobe(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(GASTILLE, world, player, attr);		
		}	
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				double posXOffset = this.world.rand.nextGaussian() * 0.42D;
				double posYOffset = this.world.rand.nextGaussian() * 0.22D;
				double posZOffset = this.world.rand.nextGaussian() * 0.42D;		
				
				CustomParticleData data = new CustomParticleData();
				data.setTexture(ModResources.GASU);
				data.setPosX(posX + posXOffset);
				data.setPosY(posY + posYOffset);
				data.setPosZ(posZ + posZOffset);
				
				data.setMaxAge(2);
				data.setScale(2);
				
				//ModMain.proxy.spawnParticles(world, data);

				posXOffset = this.world.rand.nextGaussian() * 0.12D;
				posYOffset = this.world.rand.nextGaussian() * 0.06D;
				posZOffset = this.world.rand.nextGaussian() * 0.12D;		
				
				data = new CustomParticleData();
				data.setTexture(ModResources.GASU2);
				data.setPosX(posX + posXOffset);
				data.setPosY(posY + posYOffset);
				data.setPosZ(posZ + posZOffset);
				
				data.setMaxAge(2);
				data.setScale(2);
				
				//ModMain.proxy.spawnParticles(world, data);
			}
			
			super.tick();
		}
	}
	
	
}
