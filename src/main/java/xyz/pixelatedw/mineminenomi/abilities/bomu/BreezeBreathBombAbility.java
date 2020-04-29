package xyz.pixelatedw.mineminenomi.abilities.bomu;

import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class BreezeBreathBombAbility extends ContinuousAbility{

	public static final BreezeBreathBombAbility INSTANCE = new BreezeBreathBombAbility();
	
	public BreezeBreathBombAbility() {
		super("Breeze Breath Bomb", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
	}

}
