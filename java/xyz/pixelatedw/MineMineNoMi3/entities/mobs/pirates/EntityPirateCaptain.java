package xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedNPCData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.EntityAIGapCloser;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.EntityAIHakiCombat;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.swordsman.EntityAIOTasumaki;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.abilities.swordsman.EntityAIYakkodori;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityPirateCaptain extends PirateData
{ 

	private ItemStack swordStack;
	
	public EntityPirateCaptain(World world) 
	{
		super(world, new String[] {"piratec1", "piratec2", "piratec3", "piratec4", "piratec5"});
		this.tasks.addTask(0, new EntityAIHakiCombat(this));
		this.tasks.addTask(1, new EntityAIYakkodori(this));
		this.tasks.addTask(1, new EntityAIOTasumaki(this));
		this.tasks.addTask(1, new EntityAIGapCloser(this));
 	} 
	  
	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		
		ExtendedNPCData props = ExtendedNPCData.get(this);
			
		props.setDoriki(15 + this.worldObj.rand.nextInt(5));
		props.setBelly(30 + this.worldObj.rand.nextInt(30));

		if(!this.worldObj.isRemote)
		{
			Item[] randomSword = new Item[] {ListMisc.PirateCutlass, null};
			if(this.rand.nextInt(100) <= 60)
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
