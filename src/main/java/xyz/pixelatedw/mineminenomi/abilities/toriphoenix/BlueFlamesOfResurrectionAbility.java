package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueFlamesParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BlueFlamesOfResurrectionAbility extends Ability
{
	public static final BlueFlamesOfResurrectionAbility INSTANCE = new BlueFlamesOfResurrectionAbility();

	private static final ParticleEffect PARTICLES = new BlueFlamesParticleEffect();

	public BlueFlamesOfResurrectionAbility()
	{
		super("Blue Flames of Resurrection", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(20);
		this.setDescription("Blue phoenix flames grant the user regeneration.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);		
		if (!props.getZoanPoint().equalsIgnoreCase(PhoenixFlyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), PhoenixFlyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 600, 2));
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		return true;
	}
}
