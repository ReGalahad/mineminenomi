package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenJollyRogerCreatorScreenPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class FGCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("fg").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.literal("jolly_roger_creator")
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> openJollyRogerCreator(context, EntityArgument.getPlayer(context, "target")))));

		dispatcher.register(builder);
	}

	private static int openJollyRogerCreator(CommandContext<CommandSource> context, ServerPlayerEntity target)
	{
		WyNetwork.sendTo(new SOpenJollyRogerCreatorScreenPacket(), target);
		
		return 1;
	}
}
