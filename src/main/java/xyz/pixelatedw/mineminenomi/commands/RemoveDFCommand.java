package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class RemoveDFCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("removedf");

		builder
			.requires(source -> source.hasPermissionLevel(2))
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context -> removesDF(context, EntityArgument.getPlayer(context, "target"))));

		builder
			.requires(source -> source.hasPermissionLevel(0))
				.executes(context -> removesDF(context, context.getSource().asPlayer()));
	
		dispatcher.register(builder);
	}

	private static int removesDF(CommandContext<CommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		devilFruitProps.setDevilFruit("");
		devilFruitProps.setLogia(false);
		devilFruitProps.setZoanPoint("");
		devilFruitProps.setYamiPower(false);

		for (Ability ability : abilityDataProps.getEquippedAbilities(AbilityCategory.ALL))
		{
			if (ability != null)
			{
				if (ability instanceof ContinuousAbility)
					((ContinuousAbility) ability).stopContinuity(player);
				else
					ability.stopCooldown(player);
			}
		}

		if (CommonConfig.instance.isSpecialFlyingEnabled() && abilityDataProps.hasUnlockedAbility(SpecialFlyAbility.INSTANCE) && !player.isCreative() && !player.isSpectator())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
			player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
		}

		abilityDataProps.clearUnlockedAbilities(AbilityCategory.DEVIL_FRUIT);
		abilityDataProps.clearEquippedAbilities(AbilityCategory.DEVIL_FRUIT);

		player.clearActivePotions();

		WyNetwork.sendToAllTracking(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
		WyNetwork.sendToAllTracking(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);

		return 1;
	}
}
