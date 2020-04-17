package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.ClientBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;

public class SViewProtectionPacket
{

	private boolean state;
	private int[] midPoint;
	private int size;
	
	public SViewProtectionPacket() {}
	
	public SViewProtectionPacket(boolean state, int[] midPoint, int size) 
	{
		this.state = state;
		this.midPoint = midPoint;
		this.size = size;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.state);
		buffer.writeInt(this.midPoint[0]);
		buffer.writeInt(this.midPoint[1]);
		buffer.writeInt(this.midPoint[2]);
		buffer.writeInt(this.size);
	}
	
	public static SViewProtectionPacket decode(PacketBuffer buffer)
	{
		SViewProtectionPacket msg = new SViewProtectionPacket();
		msg.state = buffer.readBoolean();
		msg.midPoint = new int[] {buffer.readInt(), buffer.readInt(), buffer.readInt()};
		msg.size = buffer.readInt();
		return msg;
	}
	
	public static void handle(SViewProtectionPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
	
			});
		}

		ctx.get().setPacketHandled(true);
	}
	
	public static class ClientHandler
	{
		private static final BlockProtectionRule GRIEF_RULE1 = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE).setBypassGriefRule(); 
		private static final BlockProtectionRule GRIEF_RULE2 = new BlockProtectionRule(ClientBlockProtectionRule.INSTANCE).setBypassGriefRule(); 

		@OnlyIn(Dist.CLIENT)
		public static void handle(SViewProtectionPacket message)
		{
			PlayerEntity player = Minecraft.getInstance().player;

			if(message.state)
			{
				AbilityHelper.createEmptyCube(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], message.size, message.size, message.size, Blocks.BLUE_STAINED_GLASS, GRIEF_RULE1);
				AbilityHelper.createEmptySphere(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, Blocks.RED_STAINED_GLASS, GRIEF_RULE1);
			}
			else
			{
				AbilityHelper.createEmptyCube(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], message.size, message.size, message.size, Blocks.AIR, GRIEF_RULE2);
				AbilityHelper.createEmptySphere(player.world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, Blocks.AIR, GRIEF_RULE2);
			}
		}
	}
}
