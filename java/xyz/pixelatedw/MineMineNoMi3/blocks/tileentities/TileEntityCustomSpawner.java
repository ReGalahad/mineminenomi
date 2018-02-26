package xyz.pixelatedw.MineMineNoMi3.blocks.tileentities;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;

public class TileEntityCustomSpawner extends TileEntity
{
	
	private String entityToSpawn;
	private int spawnLimit = 5;
	private ArrayList<EntityLivingBase> spawnedEntities = new ArrayList<EntityLivingBase>();
	
	public TileEntityCustomSpawner(String entityToSpawn, int spawnLimit)
	{
		this.entityToSpawn = entityToSpawn;
		this.spawnLimit = spawnLimit;
	}
	
    public void updateEntity()
    {
    	if(!this.worldObj.isRemote)
    	{
	    	if(!WyHelper.getEntitiesNear(this, 30, EntityPlayer.class).isEmpty())
	    	{
				EntityLivingBase elb = WyHelper.getEntitiesNear(this, 30, EntityPlayer.class).get(0);
				
				if(elb instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) elb;
					
					if((this.spawnedEntities.size() < this.spawnLimit))
					{
						EntityLivingBase newSpawn = (EntityLivingBase) EntityList.createEntityByName(this.entityToSpawn, this.worldObj);//new EntityPig(this.worldObj);
						newSpawn.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord, 0, 0);	
						this.worldObj.spawnEntityInWorld(newSpawn);
						this.spawnedEntities.add(newSpawn);
					}
				}
			} 
			else
			{ 	
				if(this.spawnedEntities.size() == this.spawnLimit)
				{
					for(int i = 0; i <= this.spawnedEntities.size(); i++)
					{
						this.spawnedEntities.get(i).setDead();
						this.spawnedEntities.clear();
					}
				}
			}
    	}
    }
	
}

