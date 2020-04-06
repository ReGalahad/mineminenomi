package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;

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

public class SagariNoRyuseiProjectile extends AbilityProjectileEntity
{
	public SagariNoRyuseiProjectile(World world)
	{
		super(ZushiProjectiles.SAGARI_NO_RYUSEI, world);
	}

	public SagariNoRyuseiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SagariNoRyuseiProjectile(World world, double x, double y, double z)
	{
		super(ZushiProjectiles.SAGARI_NO_RYUSEI, world, x, y, z);
	}

	public SagariNoRyuseiProjectile(World world, LivingEntity player)
	{
		super(ZushiProjectiles.SAGARI_NO_RYUSEI, world, player);

		this.setDamage(70);
		this.setMaxLife(256);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 20);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(20));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 25; i++)
			{
				ResourceLocation particleToUse = this.ticksExisted % 2 == 0 ? ModResources.MOKU : ModResources.MERA;
				
				double offsetX = WyHelper.randomDouble() * 5;
				double offsetY = WyHelper.randomDouble();
				double offsetZ = WyHelper.randomDouble() * 5;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(particleToUse);
				data.setLife(20);
				data.setSize(7);
				
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
