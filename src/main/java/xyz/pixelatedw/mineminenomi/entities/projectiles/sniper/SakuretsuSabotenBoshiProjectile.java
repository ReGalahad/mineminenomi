package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SakuretsuSabotenBoshiProjectile extends AbilityProjectileEntity
{
	private static final ParticleEffect PARTICLES = new KemuriBoshiParticleEffect();
	
	public SakuretsuSabotenBoshiProjectile(World world)
	{
		super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world);
	}

	public SakuretsuSabotenBoshiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SakuretsuSabotenBoshiProjectile(World world, double x, double y, double z)
	{
		super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world, x, y, z);
	}

	public SakuretsuSabotenBoshiProjectile(World world, LivingEntity player)
	{
		super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world, player);

		this.setDamage(10);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}

	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 5);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(6));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
		
		int flag = 2 | 16 | 32;
		String[] rules = new String[] {"air", "foliage"};
		
		for (int i = 0; i < 8; i++)
		{
			int a1 = (int) WyHelper.randomWithRange(-5, 5);
			int a2 = (int) WyHelper.randomWithRange(-5, 5);

			AbilityHelper.placeBlockIfAllowed(world, (int) posX + a1, (int) posY - 3, (int) posZ + a2, Blocks.CACTUS, flag, rules);
			AbilityHelper.placeBlockIfAllowed(world, (int) posX + a1, (int) posY - 2, (int) posZ + a2, Blocks.CACTUS, flag, rules);
			AbilityHelper.placeBlockIfAllowed(world, (int) posX + a1, (int) posY - 1, (int) posZ + a2, Blocks.CACTUS, flag, rules);
			AbilityHelper.placeBlockIfAllowed(world, (int) posX + a1, (int) posY, (int) posZ + a2, Blocks.CACTUS, flag, rules);
			AbilityHelper.placeBlockIfAllowed(world, (int) posX + a1, (int) posY + 1, (int) posZ + a2, Blocks.CACTUS, flag, rules);
			AbilityHelper.placeBlockIfAllowed(world, (int) posX + a1, (int) posY + 2, (int) posZ + a2, Blocks.CACTUS, flag, rules);
		}	
	}
}