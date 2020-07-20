package xyz.pixelatedw.mineminenomi.abilities.zou;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class IvoryDartAbility extends Ability
{
	public static final IvoryDartAbility INSTANCE = new IvoryDartAbility();

	private static final ParticleEffect PARTICLES = new BakuMunchParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	private int initialY;
	
	public IvoryDartAbility()
	{
		super("Ivory Dart", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		
		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(ZouGuardZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), ZouGuardPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		this.initialY = (int) player.posY;
		Vec3d speed = WyHelper.propulsion(player, 4, 4);
		player.setMotion(speed.x, player.getMotion().getY(), speed.z);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (this.canDealDamage() && player.posY >= this.initialY)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
			list.remove(player);
			for (LivingEntity target : list)
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 12);
			for (BlockPos location : WyHelper.getNearbyBlocks(player, 4)) 
			{
				if (location.getY() >= player.posY) 
				{
					if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, GRIEF_RULE)) 
					{
						PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0, 0, 0);
					}
				}
			}
		}
	}
	
	public boolean canDealDamage()
	{
		if(this.cooldown > 5 * 20) 
			return true;
		return false;
	}
}
