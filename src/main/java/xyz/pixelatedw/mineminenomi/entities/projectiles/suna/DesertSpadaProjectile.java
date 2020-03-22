package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DesertSpadaProjectile extends AbilityProjectileEntity
{
	public DesertSpadaProjectile(World world)
	{
		super(SunaProjectiles.DESERT_SPADA, world);
	}

	public DesertSpadaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DesertSpadaProjectile(World world, double x, double y, double z)
	{
		super(SunaProjectiles.DESERT_SPADA, world, x, y, z);
	}

	public DesertSpadaProjectile(World world, LivingEntity player)
	{
		super(SunaProjectiles.DESERT_SPADA, world, player);

		this.setDamage(10);
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.HUNGER, 200, 1)
			};		
		};
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		
		BlockState state = this.world.getBlockState(this.getPosition().down());
		
		if(state.isSolid())
		{
			this.world.setBlockState(this.getPosition().down(), ModBlocks.SUNA_SAND.getDefaultState());
		}
		
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyHelper.randomDouble() / 2;
			double offsetZ = WyHelper.randomDouble() / 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.SUNA2);
			data.setLife(4);
			data.setSize(1.4F);
			data.setMotion(0, 0.2 + (WyHelper.randomDouble() / 2), 0);
			WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY, this.posZ + offsetZ);
		}
	}
}
