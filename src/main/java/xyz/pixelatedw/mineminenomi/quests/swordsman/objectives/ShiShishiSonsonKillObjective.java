package xyz.pixelatedw.mineminenomi.quests.swordsman.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.quests.objectives.SwordKillObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class ShiShishiSonsonKillObjective extends SwordKillObjective implements IKillEntityObjective
{

	public ShiShishiSonsonKillObjective(String title, int count)
	{
		super(title, count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		ShiShishiSonsonAbility ability = props.getEquippedAbility(ShiShishiSonsonAbility.INSTANCE);
		
		boolean hasAbility = ability != null && ability.isOnCooldown();
		
		boolean hasDamageFrame = hasAbility && ability.canDealDamage();
				
		return super.checkKill(player, target, source) && hasDamageFrame;
	}
}
