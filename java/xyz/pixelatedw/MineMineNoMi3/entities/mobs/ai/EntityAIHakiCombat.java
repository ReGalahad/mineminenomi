package xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedNPCData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class EntityAIHakiCombat extends EntityAIBase
{

    private EntityNewMob entity;
    private EntityNewMob target;
    private static final String __OBFID = "CL_00001618";

    public EntityAIHakiCombat(EntityNewMob entity)
    {
        this.entity = entity;
        this.setMutexBits(0);
    }

	public boolean shouldExecute()
	{
		ExtendedEntityData props = ExtendedEntityData.get(this.entity);
		ExtendedNPCData npcProps = ExtendedNPCData.get(this.entity);
		
		if(!npcProps.getBusoHaki())
			return false;
		
		if(!props.hasBusoHakiActive() && this.entity.getAttackTarget() != null)
		{
			System.out.println("target set");
			props.triggerBusoHaki(true);
			WyNetworkHelper.sendToAllAround(new PacketSyncInfo(this.entity.getEntityId(), props), this.entity.dimension, this.entity.posX, this.entity.posY, this.entity.posZ, 256);
			
			ItemStack itemStack = this.entity.getHeldItem();
			
			if(itemStack != null)
			{
				if(itemStack.getTagCompound() == null)
					itemStack.setTagCompound(new NBTTagCompound());
				
				itemStack.getTagCompound().setInteger("metadata", 2);
			}
			
			return true;
		}
		else if(props.hasBusoHakiActive() && this.entity.getAttackTarget() == null)
		{
			System.out.println("target unset");
			props.triggerBusoHaki(false);
			WyNetworkHelper.sendToAllAround(new PacketSyncInfo(this.entity.getEntityId(), props), this.entity.dimension, this.entity.posX, this.entity.posY, this.entity.posZ, 256);
			
			ItemStack itemStack = this.entity.getHeldItem();
			
			if(itemStack != null)
			{
				if(itemStack.getTagCompound() == null)
					itemStack.setTagCompound(new NBTTagCompound());
				
				itemStack.getTagCompound().setInteger("metadata", 0);
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
}
