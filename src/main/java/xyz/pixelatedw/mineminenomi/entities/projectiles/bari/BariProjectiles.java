package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class BariProjectiles 
{
	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType BARRIER_CRASH = WyRegistry.registerEntityType("barrier_crash", BarrierCrash::new);
	public static final EntityType BARRIERBILITY_STAIRS = WyRegistry.registerEntityType("barrierbility_stairs", BarrierbilityStairs::new);

	static
	{
		projectiles.put(ModAttributes.BARRIER_CRASH, new Data(BARRIER_CRASH, BarrierCrash.class));
		projectiles.put(ModAttributes.BARRIERBILITY_STAIRS, new Data(BARRIERBILITY_STAIRS, BarrierbilityStairs.class));
	}
	
	public static class BarrierbilityStairs extends AbilityProjectileEntity
	{
		public BarrierbilityStairs(World world)
		{super(BARRIERBILITY_STAIRS, world);}
		
		public BarrierbilityStairs(EntityType type, World world)
		{super(type, world);}
		
		public BarrierbilityStairs(World world, double x, double y, double z)
		{super(BARRIERBILITY_STAIRS, world, x, y, z);}
		
		public BarrierbilityStairs(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BARRIERBILITY_STAIRS, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(!this.world.isRemote)
			{
				if(this.getThrower() == null)
					return;
				
				IAbilityData abilityProps = AbilityDataCapability.get(this.getThrower());
				/*Ability ability = abilityProps.getHotbarAbilityFromName(ModAttributes.BARRIERBILITY_STAIRS.getAttributeName());            

				if(ability != null)
				{
					if(!ability.isPassiveActive())
					{
						this.remove();
						return;
					}
					
					((BariAbilities.BarrierbilityStairs)ability).fillBlocksList(WyHelper.createFilledCube(this.world, this.posX, this.posY - 2, this.posZ, new int[] {1, 1, 1}, ModBlocks.barrier, "air", "nogrief"));
				}*/

			}
			
			super.tick();
		}
	}	
	
	public static class BarrierCrash extends AbilityProjectileEntity
	{
		public BarrierCrash(World world)
		{super(BARRIER_CRASH, world);}
		
		public BarrierCrash(EntityType type, World world)
		{super(type, world);}
		
		public BarrierCrash(World world, double x, double y, double z)
		{super(BARRIER_CRASH, world, x, y, z);}
		
		public BarrierCrash(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BARRIER_CRASH, world, player, attr);		
		}
	}	
	
}
