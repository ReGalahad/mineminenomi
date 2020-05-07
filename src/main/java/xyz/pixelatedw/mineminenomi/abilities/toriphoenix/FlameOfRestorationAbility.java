package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueFlamesParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class FlameOfRestorationAbility extends PunchAbility
{
	public static final FlameOfRestorationAbility INSTANCE = new FlameOfRestorationAbility();

	private static final ParticleEffect PARTICLES = new BlueFlamesParticleEffect();

	public FlameOfRestorationAbility()
	{
		super("Flame of Restoration", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("Uses the blue flames to heal the target.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);		
		if (!props.getZoanPoint().equalsIgnoreCase(PhoenixAssaultZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), PhoenixAssaultPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		return true;
	}

	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		target.heal(10);
		target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);

		return 0;
	}
}