package xyz.pixelatedw.MineMineNoMi3.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityArlong;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityChew;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.arlongPirates.EntityKuroobi;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityDonKrieg;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityGin;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.kriegPirates.EntityPearl;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMorgan;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityBlueno;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityFukuro;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityJabra;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityJabraL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKaku;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKakuL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKalifa;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityKumadori;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityLucci;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntityLucciL;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.worldGovernment.EntitySpandam;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.world.TeleporterScenarioArena;

public class CommandFG extends CommandBase
{		
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length == 1)
		{
			EntityPlayer player = this.getCommandSenderAsPlayer(sender);
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			Entity toSpawn = null;
			
			if(str[0].equals("arlong"))
				toSpawn = new EntityArlong(player.worldObj);
			else if(str[0].equals("chew"))
				toSpawn = new EntityChew(player.worldObj);
			else if(str[0].equals("kuroobi"))
				toSpawn = new EntityKuroobi(player.worldObj);
			else if(str[0].equals("krieg"))
				toSpawn = new EntityDonKrieg(player.worldObj);
			else if(str[0].equals("gin"))
				toSpawn = new EntityGin(player.worldObj);
			else if(str[0].equals("pearl"))
				toSpawn = new EntityPearl(player.worldObj);
			else if(str[0].equals("lucci"))
				toSpawn = new EntityLucci(player.worldObj);
			else if(str[0].equals("luccil"))
				toSpawn = new EntityLucciL(player.worldObj);
			else if(str[0].equals("jabra"))
				toSpawn = new EntityJabra(player.worldObj);
			else if(str[0].equals("jabral"))
				toSpawn = new EntityJabraL(player.worldObj);
			else if(str[0].equals("kalifa"))
				toSpawn = new EntityKalifa(player.worldObj);
			else if(str[0].equals("kaku"))
				toSpawn = new EntityKaku(player.worldObj);
			else if(str[0].equals("kakul"))
				toSpawn = new EntityKakuL(player.worldObj);
			else if(str[0].equals("spandam"))
				toSpawn = new EntitySpandam(player.worldObj);
			else if(str[0].equals("morgan"))
				toSpawn = new EntityMorgan(player.worldObj);	
			else if(str[0].equals("fukuro"))
				toSpawn = new EntityFukuro(player.worldObj); //COMPLETED
			else if(str[0].equals("kumadori"))
				toSpawn = new EntityKumadori(player.worldObj); //COMPLETED
			else if(str[0].equals("blueno"))
				toSpawn = new EntityBlueno(player.worldObj); //COMPLETED
			else if(str[0].equals("scenario"))
			{
				if(!player.worldObj.isRemote)
					new TeleporterScenarioArena((WorldServer) player.worldObj).teleport(player, ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN);		
			}
			else if(str[0].equals("maxcola"))
				props.setMaxCola(250);
			else if(str[0].equals("fillcola"))
				props.setCola(props.getMaxCola());
			
			if(toSpawn != null)
			{
				toSpawn.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
				player.worldObj.spawnEntityInWorld(toSpawn);
			}
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender cmd)
	{
		return true;
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/fg <entity>";
	}

	public String getCommandName() 
	{
		return "fg";
	}

}
