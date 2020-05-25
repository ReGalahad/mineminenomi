package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class VoltVari60MillionProjectile extends AbilityProjectileEntity
{
	public VoltVari60MillionProjectile(World world)
	{
		super(GoroProjectiles.VOLT_VARI_60_MILLION, world);
	}

	public VoltVari60MillionProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public VoltVari60MillionProjectile(World world, double x, double y, double z)
	{
		super(GoroProjectiles.VOLT_VARI_60_MILLION, world, x, y, z);
	}

	public VoltVari60MillionProjectile(World world, LivingEntity player)
	{
		super(GoroProjectiles.VOLT_VARI_60_MILLION, world, player);

		this.setDamage(20);
		this.setMaxLife(25);
		
		this.onTickEvent = this::onTickEvent;
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
