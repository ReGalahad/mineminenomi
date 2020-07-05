package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class FiddleBanffAbility extends Ability
{
	public static final FiddleBanffAbility INSTANCE = new FiddleBanffAbility();

	public FiddleBanffAbility()
	{
		super("Fiddle Banff", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("The transformed user dashes towards the opponent to crash against them with great power.");
		
		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(BisonHeavyZoanInfo.FORM) && !props.getZoanPoint().equalsIgnoreCase(BisonWalkZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE, this.getName(), BisonHeavyPointAbility.INSTANCE.getName(), BisonWalkPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		Vec3d speed = WyHelper.propulsion(player, 5, 5);
		player.setMotion(speed.x, player.getMotion().getY(), speed.z);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (this.canDealDamage())
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);
			for (LivingEntity target : list)
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
		}
	}
	
	// Cooldown = 7*20 = 140 
	// Damage Frame = 5*20 = 100
	// 140-100=40 ticks or 2 seconds of damage frames
	public boolean canDealDamage()
	{
		if(this.cooldown > 5 * 20) 
			return true;
		return false;
	}
}
