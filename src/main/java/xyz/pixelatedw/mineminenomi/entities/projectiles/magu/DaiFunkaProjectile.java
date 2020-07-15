package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DaiFunkaProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE); 

	public DaiFunkaProjectile(World world)
	{
		super(MaguProjectiles.DAI_FUNKA, world);
	}

	public DaiFunkaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DaiFunkaProjectile(World world, double x, double y, double z)
	{
		super(MaguProjectiles.DAI_FUNKA, world, x, y, z);
	}

	public DaiFunkaProjectile(World world, LivingEntity player)
	{
		super(MaguProjectiles.DAI_FUNKA, world, player);

		this.setDamage(20);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		SetOnFireEvent event = new SetOnFireEvent((PlayerEntity) this.getThrower(), hitEntity, 25);
		if (!MinecraftForge.EVENT_BUS.post(event))
			hitEntity.setFire(25);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY(), hit.getZ(), Blocks.LAVA, GRIEF_RULE);
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(3);
				data.setSize(2.3F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}

			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				((ServerWorld)this.world).spawnParticle(ParticleTypes.LAVA, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.5);
			}
		}
	}
}
