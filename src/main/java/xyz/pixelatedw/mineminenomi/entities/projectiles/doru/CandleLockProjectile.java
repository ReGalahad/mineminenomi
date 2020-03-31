package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doru.CandleLockParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CandleLockProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new CandleLockParticleEffect();

	public CandleLockProjectile(World world)
	{
		super(DoruProjectiles.CANDLE_LOCK, world);
	}

	public CandleLockProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public CandleLockProjectile(World world, double x, double y, double z)
	{
		super(DoruProjectiles.CANDLE_LOCK, world, x, y, z);
	}

	public CandleLockProjectile(World world, LivingEntity player)
	{
		super(DoruProjectiles.CANDLE_LOCK, world, player);

		this.setDamage(8);
		this.setMaxLife(20);
		this.setPassThroughEntities();
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(ModEffects.CANDLE_LOCK, 200, 1)
			};		
		};
		
		this.onTickEvent = this::onTickEvent;
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

		PARTICLES.spawn(world, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
	}
}