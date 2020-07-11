package xyz.pixelatedw.mineminenomi.quests.objectives.artofweather;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherCloudEntity;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ThunderTempoKillObjective extends Objective implements IKillEntityObjective
{
	public ThunderTempoKillObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		boolean isPreviousTempo = false;
		boolean isUnderThunderTempo = false;
		boolean isLightingSource = false;
		
		if(props.getPreviouslyUsedAbility() != null && (props.getPreviouslyUsedAbility() instanceof TempoAbility || props.getPreviouslyUsedAbility() instanceof ThunderBallAbility))
			isPreviousTempo = true;
		
		List<WeatherCloudEntity> clouds = WyHelper.getEntitiesNear(target.getPosition(), player.world, 50, WeatherCloudEntity.class);
		if(clouds.size() > 0)
			isUnderThunderTempo = clouds.stream().anyMatch(cloud -> cloud.isCharged() || cloud.isSuperCharged());

		if(source.getDamageType().equalsIgnoreCase("explosion.player"))
			isLightingSource = true;
		
		return isPreviousTempo && isUnderThunderTempo && isLightingSource;
	}
}
