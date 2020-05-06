package xyz.pixelatedw.mineminenomi.abilities.doa;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class KaitenDoorAbility extends PunchAbility
{
	public static final KaitenDoorAbility INSTANCE = new KaitenDoorAbility();

	public KaitenDoorAbility()
	{
		super("Kaiten Door", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);

		this.onHitEntityEvent = this::onHitEntityEvent;
	}
	
	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 1, false, false));
		EffectInstance instance = new EffectInstance(ModEffects.DOOR_HEAD, 100, 0, false, false);
		target.addPotionEffect(instance);
		((ServerPlayerEntity) player).connection.sendPacket(new SPlayEntityEffectPacket(target.getEntityId(), instance));
		
		return 4;
	}
}
