package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
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
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.SLOWNESS, 200, 100),
					new EffectInstance(Effects.MINING_FATIGUE, 200, 100)
			};		
		};
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyMathHelper.randomDouble();
				double offsetY = WyMathHelper.randomDouble();
				double offsetZ = WyMathHelper.randomDouble();

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.HIE);
				data.setLife(8);
				data.setSize(2.0F);
				((ServerWorld) this.world).spawnParticle(data, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
