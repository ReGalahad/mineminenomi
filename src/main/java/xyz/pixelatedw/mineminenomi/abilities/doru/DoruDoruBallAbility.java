package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class DoruDoruBallAbility extends ContinuousAbility {

	public static final DoruDoruBallAbility INSTANCE = new DoruDoruBallAbility();
	public double rotateAngleX = 0;
	public double rotateAngleZ = 0;
	public DoruDoruBallAbility() {
		super("Doru Doru Ball", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(15);
		this.setCooldown(5);
		this.setDescription("Puts the user into a hardened wax ball for max defense.");
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	private boolean onStartContinuityEvent(PlayerEntity p) {
		
		this.rotateAngleX = 0;
		this.rotateAngleZ = 0;
		return true;
		}
	private void duringContinuity(PlayerEntity p, int passiveTime) {
		
		p.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 5, false, false));
		p.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity p) {
		this.rotateAngleX = 0;
		this.rotateAngleZ = 0;
		p.removePotionEffect(Effects.SLOWNESS);
		p.removePotionEffect(Effects.MINING_FATIGUE);
		return true;
	}
}
