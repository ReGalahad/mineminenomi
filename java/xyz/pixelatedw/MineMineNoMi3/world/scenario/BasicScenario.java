package xyz.pixelatedw.MineMineNoMi3.world.scenario;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMorgan;

public class BasicScenario extends Scenario
{

	public void load(EntityPlayer player, World world)
	{		
        double dx = 0;
        double dy = 64;
        double dz = 0;
        
		player.motionX = player.motionY = player.motionZ = 0.0D;
		player.setPositionAndUpdate(dx, dy + 2, dz);
        
        for(int x = -20; x < 20; x++)
        {
        	for(int z = -20; z < 20; z++)
        	{
        		world.setBlock((int)dx + x, (int)dy, (int)dz + z, Blocks.stone);
        	}
        }
        
        EntityMorgan target = new EntityMorgan(player.worldObj);
        target.setPositionAndRotation(dx, dy + 1, dz + 8, 180, 0);
        world.spawnEntityInWorld(target);
	}

	public void unload(EntityPlayer player, World world)
	{
		
	}

}
