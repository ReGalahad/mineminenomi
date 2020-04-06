package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GastilleProjectile extends AbilityProjectileEntity
{
	public GastilleProjectile(World world)
	{
		super(GasuProjectiles.GASTILLE, world);
	}

	public GastilleProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GastilleProjectile(World world, double x, double y, double z)
	{
		super(GasuProjectiles.GASTILLE, world, x, y, z);
	}

	public GastilleProjectile(World world, LivingEntity player)
	{
		super(GasuProjectiles.GASTILLE, world, player);
		
		this.setDamage(15);
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.POISON, 200, 0)
			};		
		};
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();		
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for(int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 5;
				double offsetY = WyHelper.randomDouble() / 5;
				double offsetZ = WyHelper.randomDouble() / 5;
				
				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.GASU2);
				data.setLife(5);
				data.setSize(0.8F);
				data.setColor(0.4F, 0.7F, 1.0F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
