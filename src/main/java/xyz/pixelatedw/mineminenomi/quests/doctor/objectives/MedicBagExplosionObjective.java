package xyz.pixelatedw.mineminenomi.quests.doctor.objectives;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.quests.objectives.IAbilityUseObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class MedicBagExplosionObjective extends Objective implements IAbilityUseObjective
{
	public MedicBagExplosionObjective(String title)
	{
		super(title);
	}

	@Override
	public boolean checkAbility(PlayerEntity player, Ability ability)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10);
		targets.remove(player);
		boolean hasTargets = targets.size() >= 5;
		boolean isAbility = ability instanceof MedicBagExplosionAbility;
		
		return hasTargets && isAbility;
	}
}
