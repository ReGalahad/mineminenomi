package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertSpadaParticleEffect;
import xyz.pixelatedw.wypi.abilities.events.AbilityProjectileEvents;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DesertSpadaProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new DesertSpadaParticleEffect();
	
	public DesertSpadaProjectile(World world)
	{
		super(SunaProjectiles.DESERT_SPADA, world);
	}

	public DesertSpadaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DesertSpadaProjectile(World world, double x, double y, double z)
	{
		super(SunaProjectiles.DESERT_SPADA, world, x, y, z);
	}

	public DesertSpadaProjectile(World world, LivingEntity player)
	{
		super(SunaProjectiles.DESERT_SPADA, world, player);

		this.setDamage(15);
		this.setMaxLife(30);
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.HUNGER, 200, 1)
			};		
		};
		
		this.onTickEvent = this::onTickEvent;
	}
	
	@Override
	protected void onImpact(RayTraceResult hit)
	{
		if(!this.world.isRemote)
		{
			if(hit.getType() == RayTraceResult.Type.ENTITY)
			{
				EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;

				if(entityHit.getEntity() instanceof LivingEntity && this.getThrower() != null)
				{
					LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();

					if(hitEntity == this.getThrower())
						return;
					
					AbilityProjectileEvents.Hit event = new AbilityProjectileEvents.Hit(this, hit);
					if(MinecraftForge.EVENT_BUS.post(event))
						return;

					hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), this.getDamage());
						
					if(this.withEffects.getEffects().length > 0)
					{
						for(EffectInstance instance : this.withEffects.getEffects())
						{
							hitEntity.addPotionEffect(instance);
						}
					}
				}
			}
		}
	}
	
	private void onTickEvent()
	{	
		BlockPos pos = null;
		int j = 1;
		
		while(pos == null)
		{
			BlockState state = this.world.getBlockState(this.getPosition().down(j));
			
			if(state.isSolid())
			{
				pos = this.getPosition().down(j);
				break;
			}
			
			if(j > 5)
				break;
			
			j++;
		}
		
		if(pos == null)
			return;
		
		DevilFruitsHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), 2, Blocks.SAND, "core");

		PARTICLES.spawn(world, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
	}
}
