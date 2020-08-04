package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.GenericMarineEntity;

public class FactionHelper
{
	public static final Predicate<Entity> IN_CREW_PREDICATE = (entity) -> {
		boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
		boolean isInCrew = false;

		if(entity instanceof LivingEntity)
		{
			ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);		
			Crew crew = worldData.getCrewWithMember(entity.getUniqueID());
			if(crew != null)
				isInCrew = true;
		}
				
		return !isNotSpectating && isInCrew;
	};
	
	public static final Predicate<Entity> IN_MARINES_PREDICATE = (entity) -> {
		boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
		boolean isInMarines = false;

		if(entity instanceof LivingEntity)
		{
			IEntityStats props = EntityStatsCapability.get((LivingEntity) entity);
			return props.isMarine() || entity instanceof GenericMarineEntity; 
		}
				
		return !isNotSpectating && isInMarines;
	};
	
	public static final Predicate<Entity> IN_REVO_ARMY_PREDICATE = (entity) -> {
		boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
		boolean isInRevoArmy = false;

		if(entity instanceof LivingEntity)
		{
			IEntityStats props = EntityStatsCapability.get((LivingEntity) entity);
			return props.isRevolutionary();
		}
				
		return !isNotSpectating && isInRevoArmy;
	};
	
	public static Predicate<Entity> getOutsideGroupPredicate(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		Predicate<Entity> predicate = IN_CREW_PREDICATE.negate();
		
		if(props.isMarine())
			predicate = IN_MARINES_PREDICATE.negate();
		else if(props.isRevolutionary())
			predicate = IN_REVO_ARMY_PREDICATE.negate();
		
		return predicate;
	}
	
	public static Predicate<Entity> getSameGroupPredicate(PlayerEntity player)
	{
		return getOutsideGroupPredicate(player).negate();
	}
}
