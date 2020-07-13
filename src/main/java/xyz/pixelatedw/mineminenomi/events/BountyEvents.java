package xyz.pixelatedw.mineminenomi.events;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class BountyEvents
{
	private static HashMap<PlayerEntity, double[]> cachedPositions = new HashMap<PlayerEntity, double[]>();

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase != Phase.END || event.side != LogicalSide.SERVER)
			return;
		
		PlayerEntity player = event.player;
					
		// Bounty poster drops
		if(CommonConfig.instance.isWantedPosterPackagesEnabled())
		{
			double currentPosX = player.posX;
			double currentPosZ = player.posZ;

			// Every ~15 minutes, by default
			if(player.ticksExisted % CommonConfig.instance.getTimeBetweenPackages() == 0)
			{
				if(!cachedPositions.containsKey(player))
					cachedPositions.put(player, new double[] {currentPosX, currentPosZ});
				else
				{
					double[] positions = cachedPositions.get(player);
					double cachedPosX = positions[0];
					double cachedPosZ = positions[1];
					
					boolean flagPosX = Math.abs(currentPosX - cachedPosX) > 100;
					boolean flagPosZ = Math.abs(currentPosZ - cachedPosZ) > 100;						
					
					if(flagPosX || flagPosZ)
					{						
						if(BountyHelper.issueBountyForPlayer(event.player))
						{				
							WantedPosterPackageEntity pkg = new WantedPosterPackageEntity(player.world);
							pkg.setLocationAndAngles(player.posX + WyHelper.randomWithRange(-10, 10), player.posY + 30, player.posZ + WyHelper.randomWithRange(-10, 10), 0, 0);
							player.world.addEntity(pkg);
						}
						
						cachedPositions.remove(player);					
						cachedPositions.put(player, new double[] {currentPosX, currentPosZ});
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event)
	{
		if(event.getState().getBlock() == ModBlocks.WANTED_POSTER)
		{
    		ItemStack stack = new ItemStack(ModBlocks.WANTED_POSTER.asItem());
    		
			WantedPosterTileEntity tileEntity = (WantedPosterTileEntity) event.getWorld().getTileEntity(event.getPos());
			
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putString("UUID", tileEntity.getUUID());
			nbt.putString("Name", tileEntity.getEntityName());
			nbt.putLong("Bounty", Long.parseLong(tileEntity.getPosterBounty()));
			nbt.putString("Background", tileEntity.getBackground());
			nbt.putString("Date", tileEntity.getIssuedDate());
			CompoundNBT compoundnbt = new CompoundNBT();
			NBTUtil.writeGameProfile(compoundnbt, event.getPlayer().getGameProfile());
			nbt.put("Owner", compoundnbt);
			
	    	event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack));
		}
	}
	
	@SubscribeEvent
	public static void onBountyKilled(LivingDeathEvent event)
	{
		if(event.getSource().getTrueSource() instanceof PlayerEntity && !event.getEntityLiving().world.isRemote)
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			LivingEntity target = event.getEntityLiving();
			ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
			IEntityStats props = EntityStatsCapability.get(player);
			IEntityStats targetProps = EntityStatsCapability.get(target);
			
			if(!props.isBountyHunter())
				return;
			
			WyNetwork.sendTo(new SSyncEntityStatsPacket(target.getEntityId(), targetProps), player);			
			for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				ItemStack itemStack = player.inventory.getStackInSlot(i);
				if(itemStack.hasTag())
				{
					String uuid = itemStack.getTag().getString("UUID");
					Long bounty = itemStack.getTag().getLong("Bounty");
					
					if(WyHelper.isNullOrEmpty(uuid))
						continue;
										
					boolean isTarget = UUID.fromString(uuid).equals(target.getUniqueID());
					boolean hasBounty = worldData.getBounty(target.getUniqueID().toString()) == bounty;
					
					if(isTarget && hasBounty)
					{
						worldData.issueBounty(uuid, 0);
						
						long bellyGain = 0;
						
						if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO)
							bellyGain = targetProps.getBounty() / 3;
						else if(CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.NONE)
							bellyGain = targetProps.getBounty();

						props.alterBelly(bellyGain);
						WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
						WyNetwork.sendToAll(new SSyncWorldDataPacket(worldData));
					}
				}
			}
		}
	}
}
