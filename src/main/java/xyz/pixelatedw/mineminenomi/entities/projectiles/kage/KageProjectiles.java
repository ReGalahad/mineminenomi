package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;

public class KageProjectiles 
{
	
	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType TSUNOTOKAGE_PILLAR = WyRegistry.registerEntityType("tsunotokage_pillar", TsunotokagePillar::new);
	public static final EntityType BLACK_BOX = WyRegistry.registerEntityType("black_box", BlackBox::new);
	public static final EntityType BRICK_BAT = WyRegistry.registerEntityType("brick_bat", BrickBat::new);

	static
	{
		projectiles.put(ModExtraAttributes.TSUNOTOKAGE_PILLAR, new Data(TSUNOTOKAGE_PILLAR, TsunotokagePillar.class));
		projectiles.put(ModAttributes.BLACK_BOX, new Data(BLACK_BOX, BlackBox.class));
		projectiles.put(ModAttributes.BRICK_BAT, new Data(BRICK_BAT, BrickBat.class));
	}
	
	public static class BrickBat extends AbilityProjectileEntity
	{
		public BrickBat(World world)
		{super(BRICK_BAT, world);}

		public BrickBat(EntityType type, World world)
		{super(type, world);}
		
		public BrickBat(World world, double x, double y, double z)
		{super(BRICK_BAT, world, x, y, z);}
		
		public BrickBat(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BRICK_BAT, world, player, attr);		
		}
	}	

	public static class BlackBox extends AbilityProjectileEntity
	{
		public BlackBox(World world)
		{super(BLACK_BOX, world);}

		public BlackBox(EntityType type, World world)
		{super(type, world);}
		
		public BlackBox(World world, double x, double y, double z)
		{super(BLACK_BOX, world, x, y, z);}
		
		public BlackBox(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BLACK_BOX, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				LivingEntity entity = (LivingEntity) entityHit.getEntity();
						
				WyHelper.createFilledCube(entity, new int[] {2, 2, 2}, ModBlocks.KAGE, "air", "foliage");
			}
		}
	}	
	
	public static class TsunotokagePillar extends AbilityProjectileEntity
	{
		public TsunotokagePillar(World world)
		{super(TSUNOTOKAGE_PILLAR, world);}

		public TsunotokagePillar(EntityType type, World world)
		{super(type, world);}
		
		public TsunotokagePillar(World world, double x, double y, double z)
		{super(TSUNOTOKAGE_PILLAR, world, x, y, z);}
		
		public TsunotokagePillar(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TSUNOTOKAGE_PILLAR, world, player, attr);		
			this.getSize(Pose.STANDING).scale(10, 2);
		}
		
		@Override
		public void tick()
		{
			for(LivingEntity e : WyHelper.<LivingEntity>getEntitiesNear(this.getPosition(), this.world, 2))
				e.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) this.getThrower()), 30);
			super.tick();
		}
	}

}
