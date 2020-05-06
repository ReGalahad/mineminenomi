package xyz.pixelatedw.mineminenomi.abilities.doa;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class AirDoorAbility extends ContinuousAbility
{
	public static final AirDoorAbility INSTANCE = new AirDoorAbility();

	public AirDoorAbility()
	{
		super("Air Door", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(60);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1200, 0, false, false));
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		player.removePotionEffect(Effects.INVISIBILITY);
		
		double cooldown = this.continueTime / 30;	
		this.setMaxCooldown(cooldown);
		
		return true;
	}
}
