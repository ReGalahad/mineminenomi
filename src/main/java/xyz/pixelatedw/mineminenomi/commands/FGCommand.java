package xyz.pixelatedw.mineminenomi.commands;

import java.util.Map;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.QuestArgument;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.Crew.Member;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenJollyRogerCreatorScreenPacket;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;

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
			.then(Commands.literal("check_crews")
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> checkCrews(context, EntityArgument.getPlayer(context, "target")))));
		
		builder
			.then(Commands.literal("turn_sword_clone")
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> turnSwordInClone(context, EntityArgument.getPlayer(context, "target")))));
		
		builder
			.then(Commands.literal("quest")
				.then(Commands.literal("finish")
					.then(Commands.argument("quest", QuestArgument.quest())
						.executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), context.getSource().asPlayer()))))
				.then(Commands.literal("give")
					.then(Commands.argument("quest", QuestArgument.quest())
					.then(Commands.argument("target", EntityArgument.player())
						.executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))))
				.then(Commands.literal("remove")
					.then(Commands.argument("quest", QuestArgument.quest())
					.then(Commands.argument("target", EntityArgument.player())
						.executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))));
		
		builder
			.then(Commands.literal("check_fruits_in_world")
				.executes(context -> checkFruitsInWorld(context, context.getSource().asPlayer())));
		
		dispatcher.register(builder);
	}

	private static int checkCrews(CommandContext<CommandSource> source, ServerPlayerEntity target)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(target.world); 

		StringBuilder builder = new StringBuilder();

		for(Crew crew : worldData.getCrews())
		{
			builder.append("\n=================\n" + crew.getName() + "\n");
			builder.append("Members: \n");
			for(Member member : crew.getMembers())
			{
				builder.append("> " + member.getUsername() + "\n");
			}
			builder.append("Details: \n");
			for(JollyRogerElement elem : crew.getJollyRoger().getDetails())
			{
				if(elem == null)
					continue;
				builder.append("> " + elem.getTexture() + "\n");
			}
		}
		WyHelper.sendMsgToPlayer(target, builder.toString());
		
		return 1;
	}
	
	private static int checkFruitsInWorld(CommandContext<CommandSource> context, ServerPlayerEntity target)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(target.world); 

		if(worldData.getDevilFruitsInWorld().size() <= 0)
		{
			WyHelper.sendMsgToPlayer(target, "None");
			return 1;
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Devil Fruits in World\n");
		builder.append(worldData.getDevilFruitsInWorld().size() + " ");
		for(String fruit : worldData.getDevilFruitsInWorld())
		{
			builder.append(fruit + " ");
		}
		builder.append("\n");
		
		builder.append("Devil Fruits Eaten by a Player\n");
		for(Map.Entry<String, String> entry : worldData.getAteFruits().entrySet())
		{
			builder.append(entry.getKey() + " ; " + entry.getValue() + "\n");
		}
		
		WyHelper.sendMsgToPlayer(target, builder.toString());

		return 1;
	}
	
	private static int finishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		
		if(props.hasInProgressQuest(quest))
		{
			props.addFinishedQuest(quest);
			props.removeInProgressQuest(quest);
			quest.triggerCompleteEvent(player);
			WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), player);
		}
		else
			WyHelper.sendMsgToPlayer(player, "You don't have this quest!");
		
		return 1;
	}
	
	private static int giveQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		
		if(!props.hasInProgressQuest(quest))
		{
			props.addInProgressQuest(quest.create());
			WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), player);
		}
		else
			WyHelper.sendMsgToPlayer(player, "You aleady have this quest!");
		
		return 1;
	}
	
	private static int removeQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		
		if(props.hasInProgressQuest(quest))
		{
			props.removeInProgressQuest(quest);
			props.removeFinishedQuest(quest);
			WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), player);
		}
		else
			WyHelper.sendMsgToPlayer(player, "You don't have this quest!");
		
		return 1;
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
