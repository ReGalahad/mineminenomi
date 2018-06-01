package xyz.pixelatedw.MineMineNoMi3.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class CommandRemoveDF extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		EntityPlayer target = null;
		
		if(str.length >= 1)
		{
			try{target = this.getCommandSenderAsPlayer(sender);}
			catch(PlayerNotFoundException e){e.printStackTrace();}		
		}
		else
		{
			target = senderEntity;
		}
		
		ExtendedEntityStats props = ExtendedEntityStats.get(target);
		
		props.setUsedFruit("N/A");
		props.setYamiPower(false);
		
		props.clearHotbar();
		props.clearDevilFruitAbilities();
		
		for(EntityLivingBase zm : WyHelper.getEntitiesNear(target, 1.5, EntityZoanMorph.class))
			zm.setDead();
		target.removePotionEffect(Potion.invisibility.id);
		props.setZoanPoint("n/a");
		
		WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP)target);	
	}

	public boolean canCommandSenderUseCommand(ICommandSender cmd)
	{
		return true;
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/removedf [player]";
	}

	public String getCommandName() 
	{
		return "removedf";
	}

}

