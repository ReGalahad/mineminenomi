package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KaenBoshiProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE); 

	public KaenBoshiProjectile(World world)
	{
		super(SniperProjectiles.KAEN_BOSHI, world);
	}

	public KaenBoshiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public KaenBoshiProjectile(World world, double x, double y, double z)
	{
		super(SniperProjectiles.KAEN_BOSHI, world, x, y, z);
	}

	public KaenBoshiProjectile(World world, LivingEntity player)
	{
		super(SniperProjectiles.KAEN_BOSHI, world, player);

		this.setDamage(8);
		this.setPhysical();
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		SetOnFireEvent event = new SetOnFireEvent((PlayerEntity) this.getThrower(), hitEntity, 10);
		if (!MinecraftForge.EVENT_BUS.post(event))
			hitEntity.setFire(10);
	}

	private void onBlockImpactEvent(BlockPos hit)
	{
		AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY() + 1, hit.getZ(), Blocks.FIRE, GRIEF_RULE);
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
				data.setTexture(ModResources.MERA);
				data.setLife(10);
				data.setSize(0.5F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + 0.2 + offsetY, this.posZ + offsetZ);
			}
		}
	}
}