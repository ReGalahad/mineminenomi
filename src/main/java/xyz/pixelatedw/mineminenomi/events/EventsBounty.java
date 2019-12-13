package xyz.pixelatedw.mineminenomi.events;

import java.util.HashMap;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.helpers.BountyHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID)
public class EventsBounty
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
							pkg.setLocationAndAngles(player.posX + WyMathHelper.randomWithRange(-10, 10), player.posY + 30, player.posZ + WyMathHelper.randomWithRange(-10, 10), 0, 0);
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
		if(event.getState().getBlock() == ModBlocks.wantedPoster)
		{
    		ItemStack stack = new ItemStack(ModBlocks.wantedPoster.asItem());
    		
			WantedPosterTileEntity tileEntity = (WantedPosterTileEntity) event.getWorld().getTileEntity(event.getPos());
			
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putString("UUID", tileEntity.getUUID());
			nbt.putString("Name", tileEntity.getEntityName());
			nbt.putLong("Bounty", Long.parseLong(tileEntity.getPosterBounty()));
			nbt.putString("Background", tileEntity.getBackground());
			nbt.putString("Date", tileEntity.getIssuedDate());
    		
	    	event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack));
		}
	}
	
}
