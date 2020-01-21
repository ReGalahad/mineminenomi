package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectNoroSlowness;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class NoroProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType NORO_NORO_BEAM = WyRegistry.registerEntityType("noro_noro_beam", NoroNoroBeam::new);
	
	static
	{
		projectiles.put(ModAttributes.NORO_NORO_BEAM, new Data(NORO_NORO_BEAM, NoroNoroBeam.class));
	}
	
	public static class NoroNoroBeam extends AbilityProjectileEntity
	{
		public NoroNoroBeam(World world)
		{super(NORO_NORO_BEAM, world);}
		
		public NoroNoroBeam(EntityType type, World world)
		{super(type, world);}
		
		public NoroNoroBeam(World world, double x, double y, double z)
		{super(NORO_NORO_BEAM, world, x, y, z);}
		
		public NoroNoroBeam(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(NORO_NORO_BEAM, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.getType() == Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;
				
				if(entityHit.getEntity() instanceof LivingEntity)
				{
					LivingEntity target = (LivingEntity) entityHit.getEntity();
				
					if( target.isPotionActive(Effects.SLOWNESS) && target.isPotionActive(Effects.MINING_FATIGUE) )
					{
						int newTimer = 0;
						int newAmplifier = 0;
						
						newTimer = target.getActivePotionEffect(Effects.SLOWNESS).getDuration() + 240;
						if(target.getActivePotionEffect(Effects.SLOWNESS).getAmplifier() + 10 < 200)
							newAmplifier = target.getActivePotionEffect(Effects.SLOWNESS).getAmplifier() + 10;
						else
							newAmplifier = 200;
						target.removePotionEffect(Effects.SLOWNESS);
						target.removePotionEffect(Effects.MINING_FATIGUE);
						target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, newTimer, newAmplifier));
						target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, newTimer, newAmplifier));
						
						IExtraEffect props = ExtraEffectCapability.get(target);
						//if(!props.hasExtraEffect(ID.EXTRAEFFECT_NORO))
						//	new DFEffectNoroSlowness(target, newTimer);
						
					}
					else
					{
						target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 240, 10));
						target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 240, 10));
						new DFEffectNoroSlowness(target, 240);					
					}
				}
			}
		}
	}	
	
	
}
