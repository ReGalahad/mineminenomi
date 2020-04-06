package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;

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

public class GasRobeProjectile extends AbilityProjectileEntity
{
	public GasRobeProjectile(World world)
	{
		super(GasuProjectiles.GAS_ROBE, world);
	}

	public GasRobeProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GasRobeProjectile(World world, double x, double y, double z)
	{
		super(GasuProjectiles.GAS_ROBE, world, x, y, z);
	}

	public GasRobeProjectile(World world, LivingEntity player)
	{
		super(GasuProjectiles.GAS_ROBE, world, player);
		
		this.setDamage(10);
		this.setPassThroughEntities();
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.WEAKNESS, 100, 0)
			};		
		};
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for(int i = 0; i < 5; i++)
			{
				double offsetX = WyHelper.randomDouble() / 3;
				double offsetY = WyHelper.randomDouble() / 3;
				double offsetZ = WyHelper.randomDouble() / 3;
				
				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.GASU);
				data.setLife(5);
				data.setSize(2.0F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
