package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

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

		this.setDamage(5);
		this.setMaxLife(20);
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				ResourceLocation particleToUse = this.ticksExisted % 2 == 0 ? ModResources.GORO2 : ModResources.GORO;
				
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(particleToUse);
				data.setLife(5);
				data.setSize(2);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
