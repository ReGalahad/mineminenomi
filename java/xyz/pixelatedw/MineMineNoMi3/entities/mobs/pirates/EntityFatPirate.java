package xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedNPCData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.EntityAIGapCloser;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.EntityAIHakiCombat;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.EntityAIKnockback;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.brawler.EntityAIHakaiHo;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.swordsman.EntityAIYakkodori;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityFatPirate extends PirateData
{
	private ItemStack swordStack;

	public EntityFatPirate(World world) 
	{
		super(world, new String[] {"fatpirate1", "fatpirate2"});
		this.setSize(0.70F, 2.5F);
		this.tasks.addTask(0, new EntityAIHakiCombat(this));
		this.tasks.addTask(1, new EntityAIGapCloser(this).setSpeed(1.1).setMaxCounter(5));
		this.tasks.addTask(1, new EntityAIKnockback(this));
		this.tasks.addTask(1, new EntityAIHakaiHo(this));
 	}
	
	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		
		ExtendedNPCData props = ExtendedNPCData.get(this);
		
		props.setDoriki(15 + this.worldObj.rand.nextInt(15));
		props.setBelly(10 + this.worldObj.rand.nextInt(20));

		if(!this.worldObj.isRemote)
		{
			Item[] randomSword = new Item[] {ListMisc.MarineSword, null};
			if(this.rand.nextInt(100) <= 20)
			{
				props.setBusoHaki(true);

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
				Item sword = randomSword[this.rand.nextInt(randomSword.length)];
				if(sword != null)
					swordStack = new ItemStack(sword);
			}
		}
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
