package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SViewProtectionPacket;

public class AbilityProtectionCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("abilityprotection").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.literal("new")
				.then(Commands.argument("size", IntegerArgumentType.integer(1, 100))
					.executes(context ->
					{
						return createProtection(context, IntegerArgumentType.getInteger(context, "size"));
					})
				)
			)
			.then(Commands.literal("view")
				.then(Commands.argument("state", BoolArgumentType.bool())
					.executes(context -> 
					{
						return viewProtection(context, BoolArgumentType.getBool(context, "state"));
					})
				)
			);
		
		dispatcher.register(builder);
	}
	
	@SuppressWarnings("resource")
	private static int viewProtection(CommandContext<CommandSource> context, boolean state) throws CommandSyntaxException
	{
		World world = context.getSource().getWorld();	
		ServerPlayerEntity player = context.getSource().asPlayer();
		
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		
		for (int[][] area : worldData.getAllRestrictions())
		{
			int[] minPos = area[0];
			int[] maxPos = area[1];

			if (minPos.length <= 0 || maxPos.length <= 0)
				continue;

			int midX = (minPos[0] + maxPos[0]) / 2;
			int midY = (minPos[1] + maxPos[1]) / 2;
			int midZ = (minPos[2] + maxPos[2]) / 2;
			int[] midPoint = new int[] { midX, midY, midZ };

			AblProtectionTileEntity te = (AblProtectionTileEntity) world.getTileEntity(new BlockPos(midPoint[0], midPoint[1], midPoint[2]));

			if (te == null || !(te instanceof AblProtectionTileEntity))
				continue;

			ModNetwork.sendTo(new SViewProtectionPacket(state, midPoint, te.getSize()), player);
		}
		return 1;
	}
	
	@SuppressWarnings("resource")
	private static int createProtection(CommandContext<CommandSource> context, int size) throws CommandSyntaxException
	{
		World world = context.getSource().getWorld();
		Vec3d vec = context.getSource().getPos();
		BlockPos pos = new BlockPos(vec);
		
		//world.setBlockState(pos, Blocks.BLUE_STAINED_GLASS.getDefaultState());
		world.setBlockState(pos, ModBlocks.ABILITY_PROTECTION.getDefaultState());
		((AblProtectionTileEntity)world.getTileEntity(pos)).setupProtection(world, pos, size);
		
		return 1;
	}
}
