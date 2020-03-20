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

		this.onHitEntity = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		double[] speed = WyHelper.propulsion(player, 20, 20);

		double xPower = WyHelper.randomWithRange((int)-speed[0], (int)speed[0]);
		if(xPower >= 0) xPower += 30;
		else xPower -= 30;
		
		double zPower = WyHelper.randomWithRange((int)-speed[2], (int)speed[2]);
		if(zPower >= 0) zPower += 30;
		else zPower -= 30;
		
		target.setPosition(target.posX, target.posY + 20, target.posZ);
		target.setMotion(xPower, 2.5, zPower);
		target.velocityChanged = true;
		target.fallDistance = 0;

		return 0;
	}
}
