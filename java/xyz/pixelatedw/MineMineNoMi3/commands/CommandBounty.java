package xyz.pixelatedw.MineMineNoMi3.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class CommandBounty extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length >= 2)
		{
			EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
			EntityPlayer target = null;
			ExtendedEntityStats props = null;
			int plusBounty = 0;		
			
			if(!str[1].equals("INF"))
				plusBounty = Integer.decode(str[1]);
			
			if(str.length == 2)
			{
				try{target = this.getCommandSenderAsPlayer(sender);}
				catch(PlayerNotFoundException e){e.printStackTrace();}
				props = ExtendedEntityStats.get(target);
			}
			if(str.length == 3)
			{
				target = MinecraftServer.getServer().getConfigurationManager().func_152612_a(str[2]);	
				props = ExtendedEntityStats.get(target);
			}

			if(str[0].equals("+"))
			{
				if(plusBounty + props.getBounty() <= Values.MAX_GENERAL)
				{
					props.alterBounty(plusBounty);
					if(props.getBountyFromCommand() + plusBounty <= Values.MAX_GENERAL)
						props.alterBountyFromCommand(plusBounty);
				}
				else
				{
					props.setBounty(Values.MAX_GENERAL);
					props.alterBountyFromCommand( Values.MAX_GENERAL - plusBounty );
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Added " + plusBounty + " doriki to " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("-"))
			{
				if(props.getBounty() - plusBounty <= 0)
				{			
					props.setBounty(0);
					if(props.getBountyFromCommand() - plusBounty > 0)
						props.alterBountyFromCommand( -(props.getBountyFromCommand() - plusBounty) );
					else
						props.setBountyFromCommand(0);
				}
				else
				{
					props.alterBounty(-plusBounty);	
					if(props.getBountyFromCommand() - plusBounty > 0)
						props.alterBountyFromCommand( -(props.getBountyFromCommand() - plusBounty) );
					else
						props.setBountyFromCommand(0);
				}
				
				WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] Subtracted " + plusBounty + " doriki from " + target.getCommandSenderName()); 
			}
			else if(str[0].equals("="))
			{
				if(str[1].equals("INF"))
				{
					props.setBountyFromCommand( Values.MAX_GENERAL );
					props.setBounty( Values.MAX_GENERAL );
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + Values.MAX_GENERAL + " doriki"); 
				}
				else if(plusBounty <= Values.MAX_GENERAL)
				{
					props.setBountyFromCommand(plusBounty);
					props.setBounty(plusBounty);
					
					WyHelper.sendMsgToPlayer(senderEntity, EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "[DEBUG] " + target.getCommandSenderName() + " now has " + plusBounty + " doriki"); 
				}				
			}
			 
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP)target);
		}		
	}

	public boolean canCommandSenderUseCommand(ICommandSender cmd)
	{
		return true;
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/bounty <+/-/=> <amount> [player]";
	}

	public String getCommandName() 
	{
		return "bounty";
	}

}
