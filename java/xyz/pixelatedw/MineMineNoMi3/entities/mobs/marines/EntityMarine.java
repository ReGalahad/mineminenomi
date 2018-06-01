package xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityMarine extends MarineData
{ 
	private String[] textures = {"marine1", "marine2", "marine3", "marine4"};
	
	public EntityMarine(World world) 
	{
		super(world);
		this.setTexture(textures[this.rand.nextInt(textures.length)]);
 	}
	
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}
	
    protected void addRandomArmor()
    {
        this.setCurrentItemOrArmor(0, new ItemStack(ListMisc.MarineSword));
    }
    
	public double[] itemOffset() 
	{
		return new double[] {0, 0, -0.1};
	}
	
	public int getDorikiPower() { return this.worldObj.rand.nextInt(3) + 10; }
	public int getBellyInPockets() { return this.worldObj.rand.nextInt(10) + 1; }
}
