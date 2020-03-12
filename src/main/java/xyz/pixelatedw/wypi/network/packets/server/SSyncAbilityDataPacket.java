package xyz.pixelatedw.wypi.network.packets.server;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.abilities.mera.HikenAbility;
import xyz.pixelatedw.wypi.APIDefaults;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class SSyncAbilityDataPacket
{
	private INBT data;

	public SSyncAbilityDataPacket()
	{
	}

	public SSyncAbilityDataPacket(IAbilityData abiltiyDataProps)
	{
		this.data = new CompoundNBT();
		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, abiltiyDataProps, null);
		
		System.out.println(abiltiyDataProps.getEquippedAbility(HikenAbility.INSTANCE).getState());
		System.out.println(abiltiyDataProps.getEquippedAbility(0).getState());
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) this.data);
	}

	public static SSyncAbilityDataPacket decode(PacketBuffer buffer)
	{
		SSyncAbilityDataPacket msg = new SSyncAbilityDataPacket();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(SSyncAbilityDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = APIDefaults.PROXY.getPlayer();
				IAbilityData props = AbilityDataCapability.get(player);
				
				AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, props, null, message.data);
				
				System.out.println(props.getEquippedAbility(HikenAbility.INSTANCE).getState());
				System.out.println(props.getEquippedAbility(0).getState());
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
