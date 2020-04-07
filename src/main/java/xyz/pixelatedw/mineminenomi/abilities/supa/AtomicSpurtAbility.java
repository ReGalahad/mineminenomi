package xyz.pixelatedw.mineminenomi.abilities.supa;

import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class AtomicSpurtAbility extends ContinuousAbility
{
	public static final AtomicSpurtAbility INSTANCE = new AtomicSpurtAbility();

	public AtomicSpurtAbility()
	{
		super("Atomic Spurt", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setThreshold(10);
		this.setDescription("Allows the user to skate around using blades in their feet");
	}
}
