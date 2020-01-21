package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class NikyuProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();

	public static final EntityType PAD_HO = WyRegistry.registerEntityType("pad_ho", PadHo::new);
	public static final EntityType URSUS_SHOCK = WyRegistry.registerEntityType("ursus_shock", UrsusShock::new);
	
	static
	{
		projectiles.put(ModAttributes.PAD_HO, new Data(PAD_HO, PadHo.class));
		projectiles.put(ModAttributes.URSUS_SHOCK, new Data(URSUS_SHOCK, UrsusShock.class));
	}
	
	public static class PadHo extends AbilityProjectileEntity
	{
		public PadHo(World world)
		{super(PAD_HO, world);}
		
		public PadHo(EntityType type, World world)
		{super(type, world);}
		
		public PadHo(World world, double x, double y, double z)
		{super(PAD_HO, world, x, y, z);}
		
		public PadHo(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(PAD_HO, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				LivingEntity entity = (LivingEntity) entityHit.getEntity();
				double newPosX = 0, newPosY = 0, newPosZ = 0;
				
				newPosY += 10;
				Direction dir = WyHelper.get4Directions(this.getThrower());
				if(dir == WyHelper.Direction.SOUTH)
					newPosX += WyMathHelper.randomWithRange(-200, 200);
				else if(dir == WyHelper.Direction.EAST)
					newPosX -= WyMathHelper.randomWithRange(-200, 200);
				else if(dir == WyHelper.Direction.NORTH)
					newPosZ += WyMathHelper.randomWithRange(-200, 200);
				else if(dir == WyHelper.Direction.WEST)  
					newPosZ -= WyMathHelper.randomWithRange(-200, 200);

				entity.setPositionAndUpdate(entity.posX + newPosX, entity.posY + newPosY, entity.posZ + newPosZ);
			}
		}
	}	
	
	public static class UrsusShock extends AbilityProjectileEntity
	{
		public UrsusShock(World world)
		{super(URSUS_SHOCK, world);}
		
		public UrsusShock(EntityType type, World world)
		{super(type, world);}
		
		public UrsusShock(World world, double x, double y, double z)
		{super(URSUS_SHOCK, world, x, y, z);}
		
		public UrsusShock(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(URSUS_SHOCK, world, player, attr);		
		}
	}	
	
	
}
