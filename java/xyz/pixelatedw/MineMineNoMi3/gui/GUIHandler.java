package xyz.pixelatedw.MineMineNoMi3.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class GUIHandler implements IGuiHandler
{
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(id)
		{
			case 0: return id == 0 && world.getBlock(x, y, z) == ListMisc.EnchantmentTable ? new GUIEnchantmentTable(player, world, x, y, z) : null;
			case 1: return id == 1 ? new GUIPlayer(player) : null;
			case 2: return id == 2 ? new GUICC(player) : null;
			case 4: return id == 4 ? new GUISelectHotbarAbilities(player) : null;
		}
		return null;
	}
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(id)
		{
			//case 0: return id == 0 && world.getBlock(x, y, z) == ListMisc.EnchantmentTable ? new ContainerEnchantmentTable(player, world) : null;
		}
		return null;
	}
}
