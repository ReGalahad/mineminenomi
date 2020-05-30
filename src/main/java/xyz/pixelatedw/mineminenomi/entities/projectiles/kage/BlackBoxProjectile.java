package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BlackBoxProjectile extends AbilityProjectileEntity
{
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public BlackBoxProjectile(World world)
	{
		super(KageProjectiles.BLACK_BOX, world);
	}

	public BlackBoxProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public BlackBoxProjectile(World world, double x, double y, double z)
	{
		super(KageProjectiles.BLACK_BOX, world, x, y, z);
	}

	public BlackBoxProjectile(World world, LivingEntity player)
	{
		super(KageProjectiles.BLACK_BOX, world, player);

		this.setDamage(5);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity target)
	{
		EffectInstance instance = new EffectInstance(ModEffects.BLACK_BOX, 200, 0);
		target.addPotionEffect(instance);	
		if(this.owner instanceof ServerPlayerEntity)
			((ServerPlayerEntity) this.owner).connection.sendPacket(new SPlayEntityEffectPacket(target.getEntityId(), instance));		
	}
}