package xyz.pixelatedw.mineminenomi.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoBisonHeavy;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoBisonWalk;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoGiraffeHeavy;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoGiraffeWalk;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoMoguMole;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoPhoenixFull;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoPhoenixHybrid;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoVenomDemon;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoYomi;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoZouGuard;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoZouHeavy;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;

public class MorphsHelper
{
	
	private static List<ZoanInfo> zoanInfoList = new ArrayList<ZoanInfo>();

	public static void updateEyeView(PlayerEntity player)
	{
		MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight(player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
		ModNetwork.sendTo(new SRecalculateEyeHeightPacket(), (ServerPlayerEntity) player);
	}

	public static List<ZoanInfo> getZoanInfoList()
	{
		return zoanInfoList;
	}
	
	static
	{
		// Bison Zoan Points
		zoanInfoList.add(new ZoanInfoBisonHeavy());
		zoanInfoList.add(new ZoanInfoBisonWalk());
		
		// Phoenix Zoan Points
		zoanInfoList.add(new ZoanInfoPhoenixFull());
		zoanInfoList.add(new ZoanInfoPhoenixHybrid());
		
		// Zou Zoan Points
		zoanInfoList.add(new ZoanInfoZouGuard());
		zoanInfoList.add(new ZoanInfoZouHeavy());
		
		// Mogu Zoan Points
		zoanInfoList.add(new ZoanInfoMoguMole());
	
		// Giraffe Zoan Points
		zoanInfoList.add(new ZoanInfoGiraffeHeavy());
		zoanInfoList.add(new ZoanInfoGiraffeWalk());
		
		// Non-zoan Morphs
		zoanInfoList.add(new ZoanInfoVenomDemon());
		zoanInfoList.add(new ZoanInfoYomi());
	}
	
}
