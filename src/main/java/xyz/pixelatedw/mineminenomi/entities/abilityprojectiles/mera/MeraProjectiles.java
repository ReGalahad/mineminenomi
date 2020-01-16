package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.RendererAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.CubeModel;
import xyz.pixelatedw.mineminenomi.api.abilities.SphereModel;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;

public class MeraProjectiles
{
	public static List<ProjectileAbility.Data> projectiles = new ArrayList<ProjectileAbility.Data>();
	
	public static final EntityType HIKEN = WyRegistry.registerEntityType("hiken", HikenProjectile::new);
	public static final EntityType HIGAN = WyRegistry.registerEntityType("higan", HiganProjectile::new);
	public static final EntityType DAI_ENKAI_ENTEI = WyRegistry.registerEntityType("dai_enkai_entei", DaiEnkaiEnteiProjectile::new);
	public static final EntityType HIDARUMA = WyRegistry.registerEntityType("hidaruma", Hidaruma::new);
	public static final EntityType JUJIKA = WyRegistry.registerEntityType("jujika", Jujika::new);
	
	private static final RendererAbility.Factory HIKEN_FACTORY = new RendererAbility.Factory(new FistModel()).setTexture("hiken").setScale(1.5);
	private static final RendererAbility.Factory HIGAN_FACTORY = new RendererAbility.Factory(new CubeModel()).setColor(255, 0, 0, 100).setScale(.5);
	private static final RendererAbility.Factory DAI_ENKAI_ENTEI_FACTORY = new RendererAbility.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(9);

	static
	{
		projectiles.add(new Data(HIKEN, HikenProjectile.class, HIKEN_FACTORY));
		projectiles.add(new Data(HIGAN, HiganProjectile.class, HIGAN_FACTORY));
		projectiles.add(new Data(DAI_ENKAI_ENTEI, DaiEnkaiEnteiProjectile.class, DAI_ENKAI_ENTEI_FACTORY));
		/*projectiles.put(ModAttributes.HIDARUMA, new Data(HIDARUMA, Hidaruma.class));
		projectiles.put(ModAttributes.JUJIKA, new Data(JUJIKA, Jujika.class));*/
	}
	
	public static class Hidaruma extends ProjectileAbility
	{
		public Hidaruma(World world)
		{super(HIDARUMA, world);}
		
		public Hidaruma(EntityType type, World world)
		{ super(type, world); }
		
		public Hidaruma(World world, double x, double y, double z)
		{super(HIDARUMA, world, x, y, z);}
		
		public Hidaruma(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(HIDARUMA, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;

				entityHit.getEntity().setFire(this.getMaxLife());
			}

			this.world.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), Blocks.FIRE.getDefaultState());
		};
		
		@Override
		public void tick()
		{	
			for (int i = 0; i < 10; i++)
			{
				double offsetX = (new Random().nextInt(10) + 1.0D - 5.0D) / 10.0D;
				double offsetY = (new Random().nextInt(10) + 1.0D - 5.0D) / 10.0D;
				double offsetZ = (new Random().nextInt(10) + 1.0D - 5.0D) / 10.0D;
		      
				//ModMain.proxy.spawnVanillaParticle(ParticleTypes.HAPPY_VILLAGER, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.05D, 0.0D);			
			}
			
			super.tick();
		}
	}
	
	public static class Jujika extends ProjectileAbility
	{
		public Jujika(World world)
		{super(JUJIKA, world);}
		
		public Jujika(EntityType type, World world)
		{ super(type, world); }
		
		public Jujika(World world, double x, double y, double z)
		{super(JUJIKA, world, x, y, z);}
		
		public Jujika(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(JUJIKA, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			int posX;
			int posY;
			int posZ;

			if(CommonConfig.instance.isGriefingEnabled())
			{
				for(int j = -2; j <= 2; j++)
				{
					for(int i = -5; i <= 5; i++)
						if(this.world.isAirBlock(new BlockPos(hit.getHitVec().x + i, hit.getHitVec().y + j, hit.getHitVec().z)))
							this.world.setBlockState(new BlockPos(hit.getHitVec().x + i, hit.getHitVec().y + j, hit.getHitVec().z), Blocks.FIRE.getDefaultState());
						
					for(int i = -5; i <= 5; i++)
						if(this.world.isAirBlock(new BlockPos(hit.getHitVec().x, hit.getHitVec().y + j, hit.getHitVec().z + i)))
							this.world.setBlockState(new BlockPos(hit.getHitVec().x, hit.getHitVec().y + j, hit.getHitVec().z + i), Blocks.FIRE.getDefaultState());
				}
			}
		};
	}
}

