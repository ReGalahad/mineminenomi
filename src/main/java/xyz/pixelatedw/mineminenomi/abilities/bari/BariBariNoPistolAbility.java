package xyz.pixelatedw.mineminenomi.abilities.bari;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class BariBariNoPistolAbility extends PunchAbility
{
	public static final BariBariNoPistolAbility INSTANCE = new BariBariNoPistolAbility();

	public BariBariNoPistolAbility()
	{
		super("Bari Bari no Pistol", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user shapes a barrier aroud their fist, which greatly increases the power of a punch.");

		this.setMaxCooldown(5);
		this.onHitEntityEvent = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{		
		return 12;
	}
}
