package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.UUID;
import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;

public class CrewHelper
{
	public static final Predicate<Entity> NOT_IN_CREW_PREDICATE = (entity) -> {
		boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
		boolean isInCrew = false;

		if(entity instanceof LivingEntity)
		{
			ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);		
			Crew crew = worldData.getCrewWithMember(entity.getUniqueID());
			if(crew != null)
				isInCrew = true;
		}
				
		return isNotSpectating && !isInCrew;
	};
	
	public static final Predicate<Entity> IN_CREW_PREDICATE = NOT_IN_CREW_PREDICATE.negate();

	public static boolean isInCrew(PlayerEntity player, LivingEntity target)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
		UUID targetId = target.getUniqueID();
		Crew crew = worldData.getCrewWithMember(player.getUniqueID());
		
		if(crew != null)
		{
			if(crew.hasMember(targetId))
			{
				return true;
			}
		}
		
		return false;
	}
	
}
