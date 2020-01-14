package xyz.pixelatedw.mineminenomi.packets.server;

import java.io.IOException;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;

public class SUpdateHotbarStatePacket
{
	private INBT data;
	
	public SUpdateHotbarStatePacket() {}

	public SUpdateHotbarStatePacket(IAbilityData props)
	{
		this.data = new CompoundNBT();
		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}
	
	public static SUpdateHotbarStatePacket decode(PacketBuffer buffer)
	{
		SUpdateHotbarStatePacket msg = new SUpdateHotbarStatePacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}
	
	public static void handle(SUpdateHotbarStatePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{	
				PlayerEntity player = Minecraft.getInstance().player;
				IAbilityData props = AbilityDataCapability.get(player);

				for(int i = 0; i < 8; i++)
				{
					if(props.getHotbarAbilities()[i] ==  null)
						continue;
					
					try
					{
						Ability sAbl = (Ability) WyHelper.deserialize(((CompoundNBT)message.data).getByteArray("ability_" + i));
						Ability cAbl = props.getAbility(props.getHotbarAbilities()[i]);
						
						if(cAbl == null)
							continue;
						
						cAbl.setState(sAbl.getState());
					}
					catch (ClassNotFoundException | IOException e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
