package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DarkMatterProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE); 
	private static final ParticleEffect PARTICLES = new DarkMatterParticleEffect();

	public DarkMatterProjectile(World world)
	{
		super(YamiProjectiles.DARK_MATTER, world);
	}

	public DarkMatterProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DarkMatterProjectile(World world, double x, double y, double z)
	{
		super(YamiProjectiles.DARK_MATTER, world, x, y, z);
	}

	public DarkMatterProjectile(World world, LivingEntity player)
	{
		super(YamiProjectiles.DARK_MATTER, world, player);
		
		this.setDamage(5);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		AbilityHelper.createFilledSphere(this.world, hit.getX(), hit.getY(), hit.getZ(), 4, ModBlocks.DARKNESS, GRIEF_RULE);
		PARTICLES.spawn(this.world, hit.getX(), hit.getY(), hit.getZ(), 0, 0, 0);
	}
}