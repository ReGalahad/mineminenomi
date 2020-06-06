package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
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

public class VoltVari20MillionProjectile extends AbilityProjectileEntity
{
	public VoltVari20MillionProjectile(World world)
	{
		super(GoroProjectiles.VOLT_VARI_20_MILLION, world);
	}

	public VoltVari20MillionProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public VoltVari20MillionProjectile(World world, double x, double y, double z)
	{
		super(GoroProjectiles.VOLT_VARI_20_MILLION, world, x, y, z);
	}

	public VoltVari20MillionProjectile(World world, LivingEntity player)
	{
		super(GoroProjectiles.VOLT_VARI_20_MILLION, world, player);

		this.setDamage(20);
		this.setMaxLife(20);
		
		this.onTickEvent = this::onTickEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;

	}

	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				ResourceLocation particleToUse = this.ticksExisted % 2 == 0 ? ModResources.GORO2 : ModResources.GORO;
				
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(particleToUse);
				data.setLife(5);
				data.setSize(2);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
