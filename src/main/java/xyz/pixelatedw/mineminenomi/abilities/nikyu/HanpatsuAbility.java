package xyz.pixelatedw.mineminenomi.abilities.nikyu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class HanpatsuAbility extends PunchAbility
{
	public static final Ability INSTANCE = new HanpatsuAbility();

	public HanpatsuAbility()
	{
		super("Hanpatsu", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Anyone the user punches gets sent flying far into the air.");

		this.onHitEntityEvent = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		double xPower = WyHelper.randomWithRange(-20, 20);
		if(xPower >= 0) xPower += 30;
		else xPower -= 30;
		
		double zPower = WyHelper.randomWithRange(-20, 20);
		if(zPower >= 0) zPower += 30;
		else zPower -= 30;
		
		target.setPosition(target.posX, target.posY + 20, target.posZ);
		target.setMotion(xPower, 2.5, zPower);
		target.velocityChanged = true;
		target.fallDistance = 0;

		return 0;
	}
}
