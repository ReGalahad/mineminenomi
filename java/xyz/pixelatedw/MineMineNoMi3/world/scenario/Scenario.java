package xyz.pixelatedw.MineMineNoMi3.world.scenario;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;

public abstract class Scenario
{


	public abstract void load(EntityPlayer player, World world);
	
	public abstract void unload(EntityPlayer player, World world);
}
