package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenJollyRogerCreatorScreenPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class FGCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("fg").requires(source -> source.hasPermissionLevel(0));

		builder
			.then(Commands.literal("jolly_roger_creator")
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> openJollyRogerCreator(context, EntityArgument.getPlayer(context, "target")))));

		builder
			.then(Commands.literal("check_haki")
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> checkHakiStats(context, EntityArgument.getPlayer(context, "target")))));
		
		builder
			.then(Commands.literal("turn_sword_clone")
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> turnSwordInClone(context, EntityArgument.getPlayer(context, "target")))));
		
		dispatcher.register(builder);
	}

	private static int turnSwordInClone(CommandContext<CommandSource> context, ServerPlayerEntity player)
	{
		ItemStack heldStack = player.getHeldItemMainhand();
		
		if(!heldStack.isEmpty())
			heldStack.getOrCreateTag().putBoolean("isClone", true);
		
		return 1;
	}

	private static int checkHakiStats(CommandContext<CommandSource> context, ServerPlayerEntity target)
	{
		IHakiData props = HakiDataCapability.get(target);

		WyHelper.sendMsgToPlayer(target, "Imbuing: " + props.getBusoshokuImbuingHakiExp());
		WyHelper.sendMsgToPlayer(target, "Hardening: " + props.getBusoshokuHardeningHakiExp());
		WyHelper.sendMsgToPlayer(target, "Observation: " + props.getKenbunshokuHakiExp());
		WyHelper.sendMsgToPlayer(target, "Total: " + HakiHelper.getTotalHakiExp(target));

		return 1;
	}
	
	private static int openJollyRogerCreator(CommandContext<CommandSource> context, ServerPlayerEntity target)
	{
		IEntityStats props = EntityStatsCapability.get(target);
		
		if(props.isPirate() || props.isBandit() || props.isBountyHunter())
			WyNetwork.sendTo(new SOpenJollyRogerCreatorScreenPacket(), target);
		
		return 1;
	}
}
