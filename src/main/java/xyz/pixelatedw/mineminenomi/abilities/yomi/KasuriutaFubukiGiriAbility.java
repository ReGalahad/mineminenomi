package xyz.pixelatedw.mineminenomi.abilities.yomi;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yomi.KasuriutaFubukiGiriParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KasuriutaFubukiGiriAbility extends Ability
{
	public static final KasuriutaFubukiGiriAbility INSTANCE = new KasuriutaFubukiGiriAbility();
	
	private static final ParticleEffect PARTICLES = new KasuriutaFubukiGiriParticleEffect();
	
	public KasuriutaFubukiGiriAbility()
	{
		super("Kasuriuta: Fubuki Giri", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		if (!ItemsHelper.isSword(player.getHeldItemMainhand()))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD).getFormattedText());
			return false;
		}
		
		Vec3d speed = WyHelper.propulsion(player, 5, 5);
		player.setMotion(speed.x, player.getMotion().y, speed.z);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		
		return true;
	}

	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (this.canDealDamage())
		{
			if(cooldownTimer % 2 == 0)
				PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);
			for (LivingEntity target : list)
			{
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
				EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 100, 1);
				target.addPotionEffect(instance);
				((ServerPlayerEntity) player).connection.sendPacket(new SPlayEntityEffectPacket(target.getEntityId(), instance));
			}
		}
	}
	
	public boolean canDealDamage()
	{
		if(this.cooldown > 7 * 20) 
			return true;
		return false;
	}
}