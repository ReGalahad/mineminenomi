package xyz.pixelatedw.mineminenomi.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SEntityStatsSyncPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class IssueBountyCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("issuebounty").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.argument("targets", EntityArgument.players())
				.executes(context ->
					{
						return issueBounty(context, EntityArgument.getPlayers(context, "targets")); 
					}
				)
			);
		
		dispatcher.register(builder);
	}

	private static int issueBounty(CommandContext<CommandSource> context, Collection<ServerPlayerEntity> players)
	{
		for (ServerPlayerEntity player : players)
		{
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
			
			worldData.issueBounty(player.getUniqueID().toString(), entityStatsProps.getBounty());
						
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] A new bounty was issued on your name!");

			WyNetwork.sendTo(new SEntityStatsSyncPacket(player.getEntityId(), entityStatsProps), player);
		}
		
		return 1;
	}
}
