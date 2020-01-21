package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectDoruLock;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class DoruProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType DORU_DORU_ARTS_MORI = WyRegistry.registerEntityType("doru_doru_arts_mori", DoruDoruArtsMori::new);
	public static final EntityType CANDLE_LOCK = WyRegistry.registerEntityType("candle_lock", CandleLock::new);

	static
	{
		projectiles.put(ModAttributes.DORU_DORU_ARTS_MORI, new Data(DORU_DORU_ARTS_MORI, DoruDoruArtsMori.class));
		projectiles.put(ModAttributes.CANDLE_LOCK, new Data(CANDLE_LOCK, CandleLock.class));
	}
	
	public static class DoruDoruArtsMori extends AbilityProjectileEntity
	{
		public DoruDoruArtsMori(World world)
		{super(DORU_DORU_ARTS_MORI, world);}
		
		public DoruDoruArtsMori(EntityType type, World world)
		{super(type, world);}
		
		public DoruDoruArtsMori(World world, double x, double y, double z)
		{super(DORU_DORU_ARTS_MORI, world, x, y, z);}
		
		public DoruDoruArtsMori(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(DORU_DORU_ARTS_MORI, world, player, attr);		
		}
	}
	
	public static class CandleLock extends AbilityProjectileEntity
	{
		public CandleLock(World world)
		{super(CANDLE_LOCK, world);}
		
		public CandleLock(EntityType type, World world)
		{super(type, world);}
		
		public CandleLock(World world, double x, double y, double z)
		{super(CANDLE_LOCK, world, x, y, z);}
		
		public CandleLock(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(CANDLE_LOCK, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{			
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				LivingEntity target = (LivingEntity) entityHit.getEntity();

				new DFEffectDoruLock(target, 400);
			}
		}
	}
	
}
