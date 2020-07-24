package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HidarumaProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE); 

	public HidarumaProjectile(World world)
	{
		super(MeraProjectiles.HIDARUMA, world);
	}

	public HidarumaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public HidarumaProjectile(World world, double x, double y, double z)
	{
		super(MeraProjectiles.HIDARUMA, world, x, y, z);
	}

	public HidarumaProjectile(World world, LivingEntity player)
	{
		super(MeraProjectiles.HIDARUMA, world, player);

		this.setDamage(2);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		SetOnFireEvent event = new SetOnFireEvent(this.getThrower(), hitEntity, 15);
		if (!MinecraftForge.EVENT_BUS.post(event))
			hitEntity.setFire(15);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{	
		AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY() + 1, hit.getZ(), Blocks.FIRE, GRIEF_RULE);
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
				WyHelper.spawnParticles(ParticleTypes.HAPPY_VILLAGER, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
