package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

public class IceBlockPheasantProjectile  extends AbilityProjectileEntity
{
	public IceBlockPheasantProjectile(World world)
	{
		super(HieProjectiles.ICE_BLOCK_PHEASANT, world);
	}

	public IceBlockPheasantProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public IceBlockPheasantProjectile(World world, double x, double y, double z)
	{
		super(HieProjectiles.ICE_BLOCK_PHEASANT, world, x, y, z);
	}

	public IceBlockPheasantProjectile(World world, LivingEntity player)
	{
		super(HieProjectiles.ICE_BLOCK_PHEASANT, world, player);

		this.setDamage(45);
		
		this.onTickEvent = this::onTickEvent;
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 15; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 1.75;
				double offsetY = WyMathHelper.randomDouble() / 1.75;
				double offsetZ = WyMathHelper.randomDouble() / 1.75;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.HIE);
				data.setLife(5);
				data.setSize(2.0F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
