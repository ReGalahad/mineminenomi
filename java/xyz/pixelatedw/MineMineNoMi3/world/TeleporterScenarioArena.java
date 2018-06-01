package xyz.pixelatedw.MineMineNoMi3.world;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.EntityMorgan;

public class TeleporterScenarioArena extends Teleporter
{

	private final WorldServer worldServerInstance;
	private Random random;
	
	public TeleporterScenarioArena(WorldServer ws) 
	{
		super(ws);
		worldServerInstance = ws;
		random = new Random(ws.getSeed());
	}
	
	public void teleport(Entity entity, String scenarioName)
	{
		EntityPlayerMP playerMP = (EntityPlayerMP) entity;
		
        double dx = 0;
        double dy = 64;
        double dz = 0;
		
		entity.setPosition(dx, dy + 1, dz);

        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        entity.setPosition(dx, dy + 1, dz);

        playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, ID.DIMENSION_ID_SCENARIOARENA, this);

        entity.setPositionAndRotation(dx, dy + 1, dz, 180, 0);
        
        for(int x = -20; x < 20; x++)
        {
        	for(int z = -20; z < 20; z++)
        	{
        		playerMP.worldObj.setBlock((int)dx + x, (int)dy, (int)dz + z, Blocks.stone);
        	}
        }
        
        EntityMorgan target = new EntityMorgan(playerMP.worldObj);
        target.setPositionAndRotation(dx, dy + 1, dz + 8, 180, 0);
        playerMP.worldObj.spawnEntityInWorld(target);
	}

	public void placeInPortal(Entity entity, double x, double y, double z, double f)
	{
        if (worldServerInstance.provider.getDimensionName().equals(ID.DIMENSION_NAME_SCENARIOARENA)) 
        {
            entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, 0.0F);

            entity.motionX = 0.0D;
            entity.motionY = 0.0D;
            entity.motionZ = 0.0D;
            
            if(entity instanceof EntityPlayer) 
            {
            	EntityPlayer player = (EntityPlayer) entity;
            	if (player.worldObj.isRemote) 
            	{
                    if (player.dimension == ID.DIMENSION_ID_SCENARIOARENA) 
                    {
                    	WyHelper.sendMsgToPlayer(player, "TEST MESSAGE");
                    }
                }
            }

        }		
	}
	
    @Override
    public boolean placeInExistingPortal(Entity entityIn, double x, double y, double z, float f) 
    {
        return false;
    }

    @Override
    public boolean makePortal(Entity entityIn) 
    {
        return false;
    }


}
