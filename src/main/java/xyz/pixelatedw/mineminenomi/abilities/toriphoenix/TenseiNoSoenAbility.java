package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoen2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoenParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class TenseiNoSoenAbility extends ChargeableAbility
{
	public static final TenseiNoSoenAbility INSTANCE = new TenseiNoSoenAbility();

	private static final ParticleEffect PARTICLES1 = new TenseiNoSoenParticleEffect();
	private static final ParticleEffect PARTICLES2 = new TenseiNoSoen2ParticleEffect();
	
	public TenseiNoSoenAbility()
	{
		super("Tensei no Soen", AbilityCategory.DEVIL_FRUIT);
		this.setMaxChargeTime(5);
		this.setMaxCooldown(30);
		this.setDescription("While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave.");

		this.onStartChargingEvent = this::onStartChargingEvent;
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private boolean onStartChargingEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);		
		if (!props.getZoanPoint().equalsIgnoreCase(PhoenixFlyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), PhoenixFlyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		if(player.onGround)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, this.getName()).getFormattedText());
			return false;
		}
				
		return true;
	}
	
	private void duringChargingEvent(PlayerEntity player, int time)
	{
		PARTICLES1.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		if(!player.onGround && time == 1)
		{
			this.setChargeTime(1);
			player.abilities.isFlying = false;
			((ServerPlayerEntity)player).connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
			player.setMotion(player.getMotion().x, -100, player.getMotion().z);
			((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
			player.fallDistance = 0;
		}
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		PARTICLES2.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 24, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		list.remove(player);
		
		for (LivingEntity target : list)
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), 30);
		
		return true;
	}
}
