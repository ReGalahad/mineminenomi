package xyz.pixelatedw.mineminenomi.abilities.yomi;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yomi.SoulParadeParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

import java.util.UUID;

public class SoulParadeAbility extends ContinuousAbility
{
	public static final SoulParadeAbility INSTANCE = new SoulParadeAbility();
	public static final ParticleEffect PARTICLES = new SoulParadeParticleEffect();
	private static final AttributeModifier JUMP_MULTIPLIER = new AttributeModifier(UUID.fromString("efa21cbd-57e5-478f-b15c-6295eb1b375e"), "Jump Multiplier",  -50, AttributeModifier.Operation.ADDITION).setSaved(false);

	public SoulParadeAbility()
	{
		super("Soul Parade", AbilityCategory.DEVIL_FRUIT);

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (!ItemsHelper.isSword(player.getHeldItemMainhand()))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD).getFormattedText());
			return false;
		}
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MULTIPLIER);
		return true;
	}

	
	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 6, false, false));
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 100, false, false));
		player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MULTIPLIER))
			player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(JUMP_MULTIPLIER);
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		player.removePotionEffect(Effects.RESISTANCE);
		player.removePotionEffect(Effects.SLOWNESS);
		player.removePotionEffect(Effects.MINING_FATIGUE);
		return true;
	}
}