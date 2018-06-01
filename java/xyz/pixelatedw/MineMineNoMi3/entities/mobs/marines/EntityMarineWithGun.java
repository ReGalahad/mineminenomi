package xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.ai.EntityAISharpshooter;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;

public class EntityMarineWithGun extends MarineData
{
	private String[] textures = {"marinegun1", "marinegun2", "marinegun3"};
	
	public EntityMarineWithGun(World world) 
	{
		super(world);
		this.setTexture(textures[this.rand.nextInt(textures.length)]);
		this.tasks.removeTask(entityAIMeleeAttack);
		entityAIMeleeAttack = new EntityAIAttackOnCollide(this, 0.0D, false);
		this.tasks.addTask(0, entityAIMeleeAttack);
		this.tasks.addTask(0, new EntityAISharpshooter(this, 1.5f, 0));
 	} 
	  
	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}
	
	public int getCombatType() { return 1; }
	
	public int getDorikiPower() { return this.worldObj.rand.nextInt(3) + 10; }
	public int getBellyInPockets() { return this.worldObj.rand.nextInt(10) + 1; }
}
