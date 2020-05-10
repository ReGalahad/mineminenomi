package xyz.pixelatedw.mineminenomi.entities.projectiles.yuki;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class YukiRabiProjectile extends AbilityProjectileEntity
{
	public YukiRabiProjectile(World world)
	{
		super(YukiProjectiles.YUKI_RABI, world);
	}

	public YukiRabiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public YukiRabiProjectile(World world, double x, double y, double z)
	{
		super(YukiProjectiles.YUKI_RABI, world, x, y, z);
	}

	public YukiRabiProjectile(World world, LivingEntity player)
	{
		super(YukiProjectiles.YUKI_RABI, world, player);

		this.setDamage(5);
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 5;
				double offsetY = WyHelper.randomDouble() / 5;
				double offsetZ = WyHelper.randomDouble() / 5;

				GenericParticleData data = new GenericParticleData();
				if(i % 2 == 0)
					data.setTexture(ModResources.YUKI2);
				else
					data.setTexture(ModResources.YUKI);				
				data.setLife(20);
				data.setSize(1.3F);
				data.setMotion(0, -0.02, 0);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + 0.25 + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
