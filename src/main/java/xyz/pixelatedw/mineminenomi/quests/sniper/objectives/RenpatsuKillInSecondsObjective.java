package xyz.pixelatedw.mineminenomi.quests.sniper.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.quests.objectives.arrow.ArrowKillInSecondsObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class RenpatsuKillInSecondsObjective extends ArrowKillInSecondsObjective
{
	public RenpatsuKillInSecondsObjective(String title, int count, int seconds)
	{
		super(title, count, seconds);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		RenpatsuNamariBoshiAbility ability = props.getEquippedAbility(RenpatsuNamariBoshiAbility.INSTANCE);
		boolean hasAbility = ability != null && ability.isOnCooldown();
		
		return super.checkKill(player, target, source) && hasAbility;
	}

}
