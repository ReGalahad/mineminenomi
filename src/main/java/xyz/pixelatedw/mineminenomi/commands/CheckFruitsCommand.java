package xyz.pixelatedw.mineminenomi.commands;

import java.util.Map;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.wypi.WyHelper;

public class CheckFruitsCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("check_fruits").requires(source -> source.hasPermissionLevel(3));

		builder.executes(context -> checkFruitsInWorld(context, context.getSource().asPlayer()));
		
		dispatcher.register(builder);
	}

	private static int checkFruitsInWorld(CommandContext<CommandSource> context, ServerPlayerEntity target)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(target.world);

		if (worldData.getDevilFruitsInWorld().size() <= 0)
		{
			WyHelper.sendMsgToPlayer(target, "None");
			return 1;
		}

		StringBuilder builder = new StringBuilder();

		builder.append("Devil Fruits in World\n");
		builder.append(worldData.getDevilFruitsInWorld().size() + " ");
		for (String fruit : worldData.getDevilFruitsInWorld())
		{
			builder.append(fruit + " ");
		}
		builder.append("\n");

		builder.append("Devil Fruits Eaten by a Player\n");
		for (Map.Entry<String, String> entry : worldData.getAteFruits().entrySet())
		{
			builder.append(entry.getKey() + " ; " + entry.getValue() + "\n");
		}

		WyHelper.sendMsgToPlayer(target, builder.toString());

		return 1;
	}
}
