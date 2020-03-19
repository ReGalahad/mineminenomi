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

public class WhiteSnakeProjectile extends AbilityProjectileEntity
{
	public WhiteSnakeProjectile(World world)
	{
		super(MokuProjectiles.WHITE_SNAKE, world);
	}

	public WhiteSnakeProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public WhiteSnakeProjectile(World world, double x, double y, double z)
	{
		super(MokuProjectiles.WHITE_SNAKE, world, x, y, z);
	}

	public WhiteSnakeProjectile(World world, LivingEntity player)
	{
		super(MokuProjectiles.WHITE_SNAKE, world, player);

		this.setDamage(30);
		this.setMaxLife(128);
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.POISON, 300, 0)
			};		
		};
		
		this.onTickEvent = this::onTickEvent;
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MOKU);
				data.setLife(10);
				data.setSize(1.3F);
				data.setColor(0.3F, 0.3F, 0.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}