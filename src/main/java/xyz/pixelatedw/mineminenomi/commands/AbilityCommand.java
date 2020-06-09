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
import xyz.pixelatedw.mineminenomi.api.AbilityArgument;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.debug.WyDebug;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class AbilityCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("ability").requires(source -> source.hasPermissionLevel(3));

		builder
			.then(Commands.literal("give")
				.then(Commands.argument("ability", AbilityArgument.ability())
					.then(Commands.argument("targets", EntityArgument.players())
						.executes(context -> 
							{ 
								return addAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.getPlayers(context, "targets")); 
							}
						)
					)	
				)
			)
			.then(Commands.literal("remove")
				.then(Commands.argument("ability", new AbilityArgument())
					.then(Commands.argument("targets", EntityArgument.players())
						.executes(context -> 
							{ 
								return removeAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.getPlayers(context, "targets")); 
							}
						)
					)	
				)
			);
		
		dispatcher.register(builder);
	}

	private static int addAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets)
	{
		for (ServerPlayerEntity player : targets)
		{
			IAbilityData props = AbilityDataCapability.get(player);
			
			props.addUnlockedAbility(ability);
			
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " unlocked for " + player.getName().getFormattedText()); 

			WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
		}
		
		return 1;
	}
	
	private static int removeAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets)
	{		
		for (ServerPlayerEntity player : targets)
		{
			IAbilityData props = AbilityDataCapability.get(player);
			
			props.removeUnlockedAbility(ability);
			
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " removed for " + player.getName().getFormattedText()); 

			WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
		}
		
		return 1;
	}
}
