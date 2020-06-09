package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class HakiExpCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("hakiexp").requires(source -> source.hasPermissionLevel(3));

		builder
			.then(Commands.literal("imbuing")
			.then(Commands.argument("amount", FloatArgumentType.floatArg(0, ModValues.BUSOSHOKU_IMBUING_MAX_EXP))
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> alterHakiExp(context, 0, FloatArgumentType.getFloat(context, "amount"), EntityArgument.getPlayer(context, "target"))))));

		builder
			.then(Commands.literal("hardening")
			.then(Commands.argument("amount", FloatArgumentType.floatArg(0, ModValues.BUSOSHOKU_HARDENING_MAX_EXP))
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> alterHakiExp(context, 1, FloatArgumentType.getFloat(context, "amount"), EntityArgument.getPlayer(context, "target"))))));

		builder
			.then(Commands.literal("observation")
			.then(Commands.argument("amount", FloatArgumentType.floatArg(0, ModValues.KENBUNSHOKU_MAX_EXP))
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> alterHakiExp(context, 2, FloatArgumentType.getFloat(context, "amount"), EntityArgument.getPlayer(context, "target"))))));
	
		dispatcher.register(builder);
	}

	private static int alterHakiExp(CommandContext<CommandSource> context, int hakiType, float amount, ServerPlayerEntity player)
	{
		IHakiData props = HakiDataCapability.get(player);

		if(hakiType == 0)
			props.alterBusoshokuImbuingHakiExp(amount);
		else if(hakiType == 1)
			props.alterBusoshokuHardeningHakiExp(amount);
		else if(hakiType == 2)
			props.alterKenbunshokuHakiExp(amount);
		
		return 1;
	}
}
