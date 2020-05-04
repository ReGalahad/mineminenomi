package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class VenomRoadProjectile extends AbilityProjectileEntity
{
	public VenomRoadProjectile(World world)
	{
		super(DokuProjectiles.VENOM_ROAD, world);
	}

	public VenomRoadProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public VenomRoadProjectile(World world, double x, double y, double z)
	{
		super(DokuProjectiles.VENOM_ROAD, world, x, y, z);
	}

	public VenomRoadProjectile(World world, LivingEntity player)
	{
		super(DokuProjectiles.VENOM_ROAD, world, player);

		this.setDamage(10);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 0));
		this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
	}
	
	private void onBlockImpactEvent(BlockPos pos)
	{
		if (this.getThrower().getRidingEntity() != null)
			this.getThrower().stopRiding();
		EnderTeleportEvent event = new EnderTeleportEvent(this.getThrower(), pos.getX(), pos.getY(), pos.getZ(), 0.0F);
		this.getThrower().setPositionAndUpdate(event.getTargetX(), event.getTargetY() + 1, event.getTargetZ());
		this.getThrower().fallDistance = 0.0F;
	}
}