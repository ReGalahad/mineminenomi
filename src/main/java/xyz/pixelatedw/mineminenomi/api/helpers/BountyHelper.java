package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class BountyHelper
{

	public static boolean issueBountyForPlayer(PlayerEntity player)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
		IEntityStats props = EntityStatsCapability.get(player);
		
		if(props.getFaction().equalsIgnoreCase(ModValues.PIRATE) && props.getBounty() > 1000)
		{
			worldData.issueBounty(player.getUniqueID().toString(), props.getBounty());	
			WyNetwork.sendToAllTracking(new SSyncWorldDataPacket(worldData), player);
			return true;
		}
		
		return false;
	}
	
	public static void issueBountyForAllPlayers(World world)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		
		//if(!(world instanceof ServerWorld))
		//	return;
		
		for(Object entity : world.getPlayers())
		{
			if(entity instanceof PlayerEntity)
				issueBountyForPlayer((PlayerEntity) entity);
		}
	}
	
}
