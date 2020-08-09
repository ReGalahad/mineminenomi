package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class IssueBountyCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("issuebounty").requires(source -> source.hasPermissionLevel(0));

		builder
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> issueBounty(context, EntityArgument.getPlayer(context, "target"))));
		
		dispatcher.register(builder);
	}

	private static int issueBounty(CommandContext<CommandSource> context, ServerPlayerEntity player)
	{
		try
		{
			PlayerEntity sender = context.getSource().asPlayer();
	
			IEntityStats propz = EntityStatsCapability.get(sender);
			IEntityStats props = EntityStatsCapability.get(player);
			
			if(propz.isMarine() && propz.getDoriki() > 4000)
			{		
				if(props.isPirate() || props.isBandit() || props.isRevolutionary())
				{
					ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
					
					worldData.issueBounty(player.getUniqueID().toString(), props.getBounty());
		
					sender.sendMessage(new StringTextComponent("A bounty of " + props.getBounty() + " has been issued for " + player.getDisplayName().getFormattedText()));
					
					WyNetwork.sendToAllTracking(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
					WyNetwork.sendToAllTracking(new SSyncWorldDataPacket(worldData), player);			
				}
				else
				{
					sender.sendMessage(new StringTextComponent("Bounties can only be issued for Pirates, Bandits or Revolutionaries!"));
				}
			}
			else
			{
				sender.sendMessage(new StringTextComponent("Only Marines with above 4000 doriki can use this command!"));
			}
		}
		catch (CommandSyntaxException e)
		{
			e.printStackTrace();
		}

		return 1;
	}
}
