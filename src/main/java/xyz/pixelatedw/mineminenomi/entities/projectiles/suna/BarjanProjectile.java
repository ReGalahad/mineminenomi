package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

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

public class BarjanProjectile extends AbilityProjectileEntity
{
	public BarjanProjectile(World world)
	{
		super(SunaProjectiles.BARJAN, world);
	}

	public BarjanProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public BarjanProjectile(World world, double x, double y, double z)
	{
		super(SunaProjectiles.BARJAN, world, x, y, z);
	}

	public BarjanProjectile(World world, LivingEntity player)
	{
		super(SunaProjectiles.BARJAN, world, player);

		this.setDamage(15);
		this.setCanGetStuckInGround();
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.HUNGER, 200, 1)
			};		
		};
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		for (int i = 0; i < 5; i++)
		{
			double offsetX = WyHelper.randomDouble();
			double offsetY = WyHelper.randomDouble() / 4;
			double offsetZ = WyHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.SUNA2);
			data.setLife(4);
			data.setSize(1.4F);
			WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
		}
	}

}