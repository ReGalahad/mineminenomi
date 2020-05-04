package xyz.pixelatedw.mineminenomi.abilities.supa;

import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class AtomicSpurtAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final AtomicSpurtAbility INSTANCE = new AtomicSpurtAbility();

	public AtomicSpurtAbility()
	{
		super("Atomic Spurt", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setThreshold(30);
		this.setDescription("Allows the user to skate around using blades in their feet");
	}
}
