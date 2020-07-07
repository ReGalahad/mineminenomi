package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class WhiteOutProjectile extends AbilityProjectileEntity
{
	public WhiteOutProjectile(World world)
	{
		super(MokuProjectiles.WHITE_OUT, world);
	}

	public WhiteOutProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public WhiteOutProjectile(World world, double x, double y, double z)
	{
		super(MokuProjectiles.WHITE_OUT, world, x, y, z);
	}

	public WhiteOutProjectile(World world, LivingEntity player)
	{
		super(MokuProjectiles.WHITE_OUT, world, player);

		this.setDamage(7);
		this.setMaxLife(128);
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.SLOWNESS, 300, 1),
					new EffectInstance(Effects.MINING_FATIGUE, 300, 1),
					new EffectInstance(Effects.JUMP_BOOST, 300, -10)
			};		
		};
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity target)
	{
		target.setPosition(this.getThrower().posX, this.getThrower().posY, this.getThrower().posZ);
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 10; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MOKU);
				data.setLife(10);
				data.setSize(1.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}