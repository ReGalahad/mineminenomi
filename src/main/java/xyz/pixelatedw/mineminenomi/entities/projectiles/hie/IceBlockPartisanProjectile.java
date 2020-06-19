package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class IceBlockPartisanProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public IceBlockPartisanProjectile(World world)
	{
		super(HieProjectiles.ICE_BLOCK_PARTISAN, world);
	}

	public IceBlockPartisanProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public IceBlockPartisanProjectile(World world, double x, double y, double z)
	{
		super(HieProjectiles.ICE_BLOCK_PARTISAN, world, x, y, z);
	}

	public IceBlockPartisanProjectile(World world, LivingEntity player)
	{
		super(HieProjectiles.ICE_BLOCK_PARTISAN, world, player);

		this.setDamage(4);
		this.setChangeHurtTime(true);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
		
		this.withEffects = () -> {
			return new EffectInstance[] {
					new EffectInstance(Effects.SLOWNESS, 100, 0),
					new EffectInstance(Effects.MINING_FATIGUE, 100, 0)
			};		
		};
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY(), hit.getZ(), Blocks.PACKED_ICE, GRIEF_RULE);
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

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.HIE);
				data.setLife(2);
				data.setSize(1.5F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}
	}
}
