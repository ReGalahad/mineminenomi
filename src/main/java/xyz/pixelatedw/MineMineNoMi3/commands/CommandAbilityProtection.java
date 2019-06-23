package xyz.pixelatedw.MineMineNoMi3.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityAbilityProtection;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class CommandAbilityProtection extends CommandBase
{
	@Override
	public void processCommand(ICommandSender sender, String[] str)
	{
		//if (str.length > 0)
		//{
			EntityPlayer player = CommandBase.getCommandSenderAsPlayer(sender);
			ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);
			int areaSize = 100;//Integer.parseInt(str[0]);
			
			TileEntityAbilityProtection center = new TileEntityAbilityProtection().setRadius(areaSize);
			player.worldObj.setTileEntity((int)player.posX, (int)player.posY, (int)player.posZ, center);

			player.worldObj.setBlock((int)player.posX, (int)player.posY, (int)player.posZ, ListMisc.AbilityProtectionBlock);		
		//}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		EntityPlayer senderEntity = CommandBase.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());

		if (flag)
			return true;

		return false;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/abilityprotection <size>";
	}

	@Override
	public String getCommandName()
	{
		return "abilityprotection";
	}
}
