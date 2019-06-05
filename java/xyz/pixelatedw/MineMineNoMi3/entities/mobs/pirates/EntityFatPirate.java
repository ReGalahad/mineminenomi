package xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityFatPirate extends PirateData
{
	public EntityFatPirate(World world) 
	{
		super(world, new String[] {"fatpirate1", "fatpirate2"});
		this.setSize(1.25F, 2.5F);
 	}
	
	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		
		ExtendedEntityData props = ExtendedEntityData.get(this);
		
		props.setDoriki(15 + this.worldObj.rand.nextInt(20));
		props.setBelly(10 + this.worldObj.rand.nextInt(20));
	}

    /*protected void addRandomArmor()
    {
    	Item[] randomSword = new Item[] {ListMisc.PirateCutlass, Items.iron_sword};
        this.setCurrentItemOrArmor(0, new ItemStack(randomSword[this.rand.nextInt(randomSword.length)]));
    }
    
	public double[] itemOffset() 
	{
		return new double[] {0, 0, -0.1};
	}*/
}
