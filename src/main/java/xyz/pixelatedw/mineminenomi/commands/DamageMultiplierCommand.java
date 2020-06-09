package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class DamageMultiplierCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("damagem").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.argument("multiplier", DoubleArgumentType.doubleArg(1, 10))
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> applyMultiplier(context, DoubleArgumentType.getDouble(context, "amount"), EntityArgument.getPlayer(context, "target")))));
		
		
		dispatcher.register(builder);
	}

	private static int applyMultiplier(CommandContext<CommandSource> context, double multiplier, ServerPlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		
		props.setDamageMultiplier(multiplier);
		
		return 1;
	}
}
