package xyz.pixelatedw.mineminenomi.abilities.supa;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class SparClawAbility extends PunchAbility 
{
	public static final SparClawAbility INSTANCE = new SparClawAbility();

	public SparClawAbility()
	{
		super("Spar Claw", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("Turns the undersides of the user's fingers into blades before slashing at his opponent.");

		this.onHitEntityEvent = this::onHitEntityEvent;
	}
	
	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		return 10;
	}
}
