package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AllBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MokoProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AllBlockProtectionRule.INSTANCE, RestrictedBlockProtectionRule.INSTANCE, new BlockProtectionRule(LiquidBlockProtectionRule.INSTANCE).setBanListedBlocks()); 

	public MokoProjectile(World world)
	{
		super(ZushiProjectiles.MOKO, world);
	}

	public MokoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public MokoProjectile(World world, double x, double y, double z)
	{
		super(ZushiProjectiles.MOKO, world, x, y, z);
	}

	public MokoProjectile(World world, LivingEntity player)
	{
		super(ZushiProjectiles.MOKO, world, player);

		this.setDamage(10);
		this.setPassThroughEntities();
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity target)
	{
		for (int x = -1; x < 1; x++)
		{
			for (int z = -1; z < 1; z++)
			{
				int posX = (int) target.posX + x;
				int posY = (int) target.posY - 1;
				int posZ = (int) target.posZ + z;

				if (AbilityHelper.placeBlockIfAllowed(this.world, posX, posY, posZ, Blocks.AIR, GRIEF_RULE))
				{
					target.setMotion(0, -5, 0);
					target.velocityChanged = true;
					target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 10));
				}
			}
		}
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 25; i++)
			{
				double offsetX = WyHelper.randomDouble() / 2;
				double offsetY = WyHelper.randomDouble() / 2;
				double offsetZ = WyHelper.randomDouble() / 2;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.GASU);
				data.setLife(10);
				data.setSize(1);
				
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
