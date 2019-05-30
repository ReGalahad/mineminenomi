package xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedNPCData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.EntityAIHakiCombat;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncNPCData;

public class EntityMarineCaptain extends MarineData
{ 

	private ItemStack swordStack;
	
	public EntityMarineCaptain(World world) 
	{
		super(world, new String[] {"marinec1", "marinec2", "marinec3", "marinec4", "marinec5"});
		this.tasks.addTask(0, new EntityAIHakiCombat(this));
 	}
	
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		
		ExtendedNPCData props = ExtendedNPCData.get(this);
		
		props.setDoriki(15 + this.worldObj.rand.nextInt(5));
		props.setBelly(20 + this.worldObj.rand.nextInt(20));

		if(!this.worldObj.isRemote)
		{
			if(this.rand.nextInt(100) <= 40)
			{
				props.setBusoHaki(true);
				
				Item[] randomSword = new Item[] {ListMisc.MarineSword, null};
				Item sword = randomSword[this.rand.nextInt(randomSword.length)];			
				if(sword != null)
				{
					swordStack = new ItemStack(sword);
					swordStack.setTagCompound(new NBTTagCompound());
					swordStack.stackTagCompound.setInteger("metadata", 1);
				}
			}
			else
			{
				Item[] randomSword = new Item[] {ListMisc.MarineSword, null};
				Item sword = randomSword[this.rand.nextInt(randomSword.length)];
				if(sword != null)
					swordStack = new ItemStack(sword);
			}
		}
	}

    protected void addRandomArmor()
    {
    	if(swordStack != null)
    		this.setCurrentItemOrArmor(0, swordStack);
    }
    
	public double[] itemOffset() 
	{
		return new double[] {0, 0, -0.1};
	}
}
