package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class PouchCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("pouch").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.argument("amount", IntegerArgumentType.integer(1, ModValues.MAX_GENERAL))
				.executes(context -> createBellyPouch(context, IntegerArgumentType.getInteger(context, "amount"))));
		
		builder
			.then(Commands.literal("ALL")
				.executes(context -> createBellyPouch(context, ModValues.MAX_GENERAL)));
		
		dispatcher.register(builder);
	}

	private static int createBellyPouch(CommandContext<CommandSource> context, int amount)
	{
		if(amount <= 0)
			return 1;
		
		try
		{
			ServerPlayerEntity player = context.getSource().asPlayer();
			IEntityStats props = EntityStatsCapability.get(player);
			
			if(props.getBelly() <= 0)
				return 1;
			
			if(props.getBelly() - amount >= 0)
				props.alterBelly(-amount);
			else
			{
				amount = props.getBelly();		
				props.alterBelly(-amount);
			}
			
			ItemStack pouch = new ItemStack(ModItems.BELLY_POUCH);
			pouch.getOrCreateTag().putInt("belly", amount);
			
			player.inventory.addItemStackToInventory(pouch);
		}
		catch (CommandSyntaxException e)
		{
			e.printStackTrace();
		}
		
		return 1;
	}
}
