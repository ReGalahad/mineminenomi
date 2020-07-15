package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;

public class CrewHelper
{

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
