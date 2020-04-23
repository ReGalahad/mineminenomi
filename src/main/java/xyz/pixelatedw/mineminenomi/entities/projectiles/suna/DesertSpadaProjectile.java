package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertSpadaParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DesertSpadaProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new DesertSpadaParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE); 

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
		this.setPassThroughEntities();
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.HUNGER, 200, 1)
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
		
		AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), 2, Blocks.SAND, GRIEF_RULE);

		PARTICLES.spawn(world, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
	}
}
