package xyz.pixelatedw.MineMineNoMi3.packets;

import java.util.Random;
import java.util.Timer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.CharacterCreator;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;

public class PacketPlayer implements IMessage
{
	private String cmd;
	private boolean ablState = false;
	private double mX, mY, mZ;
	
	public PacketPlayer() {}
	
	public PacketPlayer(String cmd) 
	{
		this.cmd = cmd;
	}
	
	public PacketPlayer(String cmd, boolean bool) 
	{
		this.cmd = cmd;
		this.ablState = bool;
	}
	
	public PacketPlayer(String cmd, double mX, double mY, double mZ) 
	{
		this.cmd = cmd;		
		this.mX = mX;
		this.mY = mY;
		this.mZ = mZ;
	}
	
	public void fromBytes(ByteBuf buf) 
	{
		this.cmd = ByteBufUtils.readUTF8String(buf);
		this.ablState = buf.readBoolean();
		this.mX = buf.readDouble();
		this.mY = buf.readDouble();
		this.mZ = buf.readDouble();
	}

	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, this.cmd);
		buf.writeBoolean(this.ablState);
		buf.writeDouble(this.mX);
		buf.writeDouble(this.mY);
		buf.writeDouble(this.mZ);
	}

	public static class ClientHandler implements IMessageHandler<PacketPlayer, IMessage>
	{
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketPlayer message, MessageContext ctx) 
		{
			final EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

		    boolean canAnimate = true;
			double frame = 0;
			
			if(message.cmd.equals("ChangeRotation"))
			{
				float initialRotation = player.rotationYaw;
				
				player.rotationYaw = initialRotation + 10;
			}
			
			if(message.cmd.equals("ElThorThunder"))
			{
				int i = (int) message.mX;
				int j = (int) message.mY;
				int k = (int) message.mZ;
				
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i+1, j, k));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i, j, k+1));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i-1, j, k));
				player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, i, j, k-1));
			}
			
			for(int i = 0; i < 8; i++)
			{
				if(message.cmd.contains("clientUpdateIsPassive"))
				{
					String ablName = message.cmd.substring("clientUpdateIsPassive".length(), message.cmd.length());
					
					if(props.getAbilityFromSlot(i) != null && ablName.toLowerCase().equals(props.getAbilityFromSlot(i).getAttribute().getAttributeName().toLowerCase())) 
					{
						props.getAbilityFromSlot(i).setPassiveActive(message.ablState);						
					}
				}				
				if(message.cmd.contains("clientUpdateIsCooldown"))
				{
					String ablName = message.cmd.substring("clientUpdateIsCooldown".length(), message.cmd.length());
					
					if(props.getAbilityFromSlot(i) != null && ablName.toLowerCase().equals(props.getAbilityFromSlot(i).getAttribute().getAttributeName().toLowerCase())) 
					{
						props.getAbilityFromSlot(i).setCooldownActive(message.ablState);						
					}
				}
				if(message.cmd.contains("clientUpdateIsCharging"))
				{
					String ablName = message.cmd.substring("clientUpdateIsCharging".length(), message.cmd.length());
					
					if(props.getAbilityFromSlot(i) != null && ablName.toLowerCase().equals(props.getAbilityFromSlot(i).getAttribute().getAttributeName().toLowerCase())) 
					{
						props.getAbilityFromSlot(i).setChargeActive(message.ablState);						
					}
				}			
			}
			
			if(message.cmd.contains("motion+"))
			{
				player.motionX += message.mX;
				player.motionY += message.mY;
				player.motionZ += message.mZ;			
			}
			if(message.cmd.contains("motion-"))
			{
				player.motionX -= message.mX;
				player.motionY -= message.mY;
				player.motionZ -= message.mZ;			
			}
			if(message.cmd.contains("motion="))
			{
				player.motionX = message.mX;
				player.motionY = message.mY;
				player.motionZ = message.mZ;						
			}
			if(message.cmd.contains("motion*"))
			{
				player.motionX *= message.mX;
				player.motionY *= message.mY;
				player.motionZ *= message.mZ;						
			}	
			if(message.cmd.contains("pos"))
			{
				player.setPositionAndRotation(message.mX, message.mY, message.mZ, player.rotationYaw, player.rotationPitch);
			}
			
			return null;		
		}
	}
	
	public static class ServerHandler implements IMessageHandler<PacketPlayer, IMessage>
	{
		public IMessage onMessage(PacketPlayer message, MessageContext ctx) 
		{
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(message.cmd.equals("delete_book"))
			{
				props.clearRacialAbilities();
				
				if(props.getRace().equals(ID.RACE_CYBORG))
				{										
					props.addRacialAbility(CyborgAbilities.FRESHFIRE);
					props.addRacialAbility(CyborgAbilities.COLAOVERDRIVE);
					props.addRacialAbility(CyborgAbilities.RADICALBEAM);
					props.addRacialAbility(CyborgAbilities.STRONGRIGHT);
					props.addRacialAbility(CyborgAbilities.COUPDEVENT);
					
					props.setMaxCola(100);
					props.setCola(props.getMaxCola());
				}
				
				if(props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN))						
					props.addRacialAbility(SwordsmanAbilities.SHISHISHISONSON);
				
				for(ItemStack is : player.inventory.mainInventory)
					if(is != null && is.getItem() instanceof CharacterCreator)
						WyHelper.removeStackFromInventory(player, is);	
				
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			}
			if(message.cmd.equals("forcesync"))
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			if(message.cmd.contains("msg"))
				WyHelper.sendMsgToPlayer(player, message.cmd.replace("msg", ""));
			if(message.cmd.equals("enterCombatMode"))
			{
				props.setCombatMode(!props.isInCombatMode());
				//WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			}
			for(int i = 0; i < 8; i++)
			{
				if(message.cmd.equals("useAbility" + i))
				{
					if(props.getAbilityFromSlot(i) != null) 
					{
						for(int j = 0; j < 8; j++)
						{
							if(props.getAbilityFromSlot(j) != null) 
							{
								if(props.getAbilityFromSlot(j).isCharging())
									return null;
								if(props.getAbilityFromSlot(i) != props.getAbilityFromSlot(j) && props.getAbilityFromSlot(j).isPassiveActive() && props.getAbilityFromSlot(i).getAttribute().isPassive())
									return null;
							}							
						}
						
						if(props.getAbilityFromSlot(i).getAttribute().isPassive())
							props.getAbilityFromSlot(i).passive(player);
						else if(props.getAbilityFromSlot(i).getAttribute().getAbilityCharges() > 0)
							props.getAbilityFromSlot(i).startCharging(player);
						else
							props.getAbilityFromSlot(i).use(player);
					}
				}
			}

			return null;
		}
	}
	
}
