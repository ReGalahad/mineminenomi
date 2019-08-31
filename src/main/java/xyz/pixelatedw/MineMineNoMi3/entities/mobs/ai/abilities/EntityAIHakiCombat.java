package xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class EntityAIHakiCombat extends EntityAIBase
{

    private EntityNewMob entity;
    private EntityNewMob target;

    public EntityAIHakiCombat(EntityNewMob entity)
    {
        this.entity = entity;
    }

	@Override
	public boolean shouldExecute()
	{
		ExtendedEntityData props = ExtendedEntityData.get(this.entity);

		if(!entity.hasBusoHaki())
			return false;
		
		if(!props.hasBusoHakiActive() && this.entity.getAttackTarget() != null)
		{
			props.triggerBusoHaki(true);
			this.entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(new AttributeModifier("Extra Haki Damage", 2, 2));
			
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
			props.triggerBusoHaki(false);
			this.entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeAllModifiers();

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
