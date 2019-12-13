package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class SSpawnLightningPacket
{
	
	private int radius;
	
	public SSpawnLightningPacket() {}

	public SSpawnLightningPacket(int radius) 
	{
		this.radius = radius;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.radius);
	}
	
	public static SSpawnLightningPacket decode(PacketBuffer buffer)
	{
		SSpawnLightningPacket msg = new SSpawnLightningPacket();
		msg.radius = buffer.readInt();
		return msg;
	}
	
	public static void handle(SSpawnLightningPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				ClientWorld world = ((ClientWorld) player.world);
				RayTraceResult mop = WyHelper.rayTraceBlocks(player);
				
				double x = mop.getHitVec().x;
				double y = mop.getHitVec().y;
				double z = mop.getHitVec().z;
				
				world.addLightning(new LightningBoltEntity(player.world, x, y, z, false));
				for(int i = 0; i < message.radius; i++)
				{
					world.addLightning(new LightningBoltEntity(player.world, x + i, y, z + i, false));
					world.addLightning(new LightningBoltEntity(player.world, x + i, y, z - i, false));
					world.addLightning(new LightningBoltEntity(player.world, x - i, y, z - i, false));
					world.addLightning(new LightningBoltEntity(player.world, x - i, y, z + i, false));				
				}
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
