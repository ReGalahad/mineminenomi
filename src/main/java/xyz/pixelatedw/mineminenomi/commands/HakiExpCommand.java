package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.events.HakiGainEvents;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

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
		IEntityStats statsProps = EntityStatsCapability.get(player);
		
		if(hakiType == 0)
			props.alterBusoshokuImbuingHakiExp(amount);
		else if(hakiType == 1)
			props.alterBusoshokuHardeningHakiExp(amount);
		else if(hakiType == 2)
			props.alterKenbunshokuHakiExp(amount);
		
		if(statsProps.getDoriki() > 4000 && props.getBusoshokuImbuingHakiExp() > 40 + WyHelper.randomWithRange(-5, 20))
			HakiGainEvents.giveHakiAbility(player, BusoshokuHakiImbuingAbility.INSTANCE);

		if(statsProps.getDoriki() > 3000 && props.getBusoshokuHardeningHakiExp() > 50 + WyHelper.randomWithRange(-2, 25))
		{
			HakiGainEvents.giveHakiAbility(player, BusoshokuHakiHardeningAbility.INSTANCE);
			if(props.getBusoshokuHardeningHakiExp() > 80 + WyHelper.randomWithRange(0, 20))
			{
				HakiGainEvents.giveHakiAbility(player, BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
			}
		}
		
		if(statsProps.getDoriki() > 1500 && props.getKenbunshokuHakiExp() > 30 + WyHelper.randomWithRange(-5, 20))
			HakiGainEvents.giveHakiAbility(player, KenbunshokuHakiAuraAbility.INSTANCE);

		if(statsProps.getDoriki() > 5500 && props.getKenbunshokuHakiExp() > 60 + WyHelper.randomWithRange(0, 30))
			HakiGainEvents.giveHakiAbility(player, KenbunshokuHakiFutureSightAbility.INSTANCE);

		WyNetwork.sendToAllTracking(new SSyncHakiDataPacket(player.getEntityId(), props), player);
		
		return 1;
	}
}
